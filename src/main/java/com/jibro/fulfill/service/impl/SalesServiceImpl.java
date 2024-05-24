package com.jibro.fulfill.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.jibro.fulfill.dto.sales.SalesSummaryRequestDto;
import com.jibro.fulfill.dto.sales.SalesSummaryResponseDto;
import com.jibro.fulfill.repository.CompanyRepository;
import com.jibro.fulfill.repository.OrderRepository;
import com.jibro.fulfill.service.SalesService;

@Service
@Transactional
public class SalesServiceImpl implements SalesService{
	
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CompanyRepository companyRepository;

	
    @Autowired
    private JavaMailSender javaMailSender;
	
	@Override
	public Page<SalesSummaryResponseDto> getOrderSummaries(SalesSummaryRequestDto dto, int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "orderDate"));
		return orderRepository.findOrderSummaries(dto.getFrom(), dto.getTo(), pageable);
	}

	@Override
	public void sendEmailClick(String email, LocalDateTime fromDate, LocalDateTime toDate) {
		List<SalesSummaryResponseDto> sales = orderRepository.findOrderSummariesEmailContent(fromDate, toDate);
	    Object[] result = orderRepository.findMinAndMaxOrderDates();
	    
	    if(fromDate == null) {
	    	fromDate = (LocalDateTime) ((Object[]) result[0])[0];
	    }
	    if(toDate == null) {
	    	toDate = (LocalDateTime) ((Object[]) result[0])[1];
	    }
	    
		String[] emails = email.split(",");
		String from = fromDate.toString().substring(0,10);
		String to = toDate.toString().substring(0,10);
		String subject =  from + " ~ " +  to + "매출 정보";
		String text = makeEmailContent(sales, subject);
		for(String toEmail: emails) {
			toEmail = toEmail.trim();
			sendEmail(toEmail, subject, text);
		}
	}
	
//    @Scheduled(cron = "0 * * * * *") 
	@Scheduled(cron = "0 0 8 1 * *") // 매달 1일 8시에 실행
    public void sendMonthlyEmail() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime from = now.withDayOfMonth(1).minusMonths(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime to = now.withDayOfMonth(1).minusDays(1).withHour(23).withMinute(59).withSecond(59).withNano(999);
        
        List<SalesSummaryResponseDto> sales = orderRepository.findOrderSummariesEmailContent(from, to);
        
        String lastMonth = String.format("%02d월", LocalDate.now().minusMonths(1).getMonthValue());
        String subject = lastMonth + " 매출 정보";

		String text = makeEmailContent(sales, subject);
    	// 이메일 전송 로직을 여기에 추가
    	List<String> emailList = companyRepository.findCompanyEmailsByCategory();
    	for(String toEmail: emailList) {
			sendEmail(toEmail, subject, text);
		}
    			
    }
    
    private String makeEmailContent(List<SalesSummaryResponseDto> salesList, String subject) {
    	
    	String emailTemplate = "<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"><style>table th, table td {text-align: center; vertical-align: middle;}</style></head><body><p>안녕하세요. Jibro Fulfillment 입니다</p><p>";
    	emailTemplate += subject;
    	emailTemplate += " 입니다.</p><table style=\"width:400px\"><thead><tr><th>제품코드</th><th>날짜</th><th>매출수량</th></tr></thead><tbody>";
        
        for (SalesSummaryResponseDto sale : salesList) {
            emailTemplate += "<tr><td>" + sale.getProduct().getProductId() + "</td><td>" + sale.getOrderDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "</td><td>" + sale.getTotalCount() + "</td></tr>";
        }
        
        emailTemplate += "</tbody></table><p>감사합니다.</p></body></html>";
        
        return emailTemplate;
    	
	}

	private void sendEmail(String to, String subject, String text) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(message);
    }
}
	

