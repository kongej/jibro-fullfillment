package com.springboot.test.data.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jibro.fulfill.JibroFulfillApplication;
import com.jibro.fulfill.entity.Company;
import com.jibro.fulfill.entity.Ongoing;
import com.jibro.fulfill.entity.Order;
import com.jibro.fulfill.entity.Product;
import com.jibro.fulfill.repository.CompanyRepository;
import com.jibro.fulfill.repository.OngoingRepository;
import com.jibro.fulfill.repository.OrderRepository;
import com.jibro.fulfill.repository.ProductRepository;

@SpringBootTest(classes = JibroFulfillApplication.class)
public class OngoingRepositoryTest {

    @Autowired
    private OngoingRepository ongoingRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testSaveOngoing() {
        // 테스트에 필요한 데이터 생성
        Company delever = new Company();
        delever.setCompanyId("D1");
        delever.setCompanyName("택배사");
        delever.setCompanyEmail("delivery@example.com");
        delever.setCompanyContact("010-1234-5678");
        delever.setCompanyCategory("D");
        companyRepository.save(delever);

        Company seller = new Company();
        seller.setCompanyId("S1");
        seller.setCompanyName("판매자");
        seller.setCompanyEmail("seller@example.com");
        seller.setCompanyContact("010-9876-5432");
        seller.setCompanyCategory("S");
        companyRepository.save(seller);

        Product product = new Product();
        product.setProductId("P1");
        product.setProductName("제품1");
        product.setCost(10000);
        product.setMaker(seller);
        productRepository.save(product);

        Order order = new Order();
        order.setOrderId("O2");
        order.setProduct(product);
        order.setOrdererName("주문자");
        order.setPhoneNum("010-1111-2222");
        order.setAddress("주소");
        order.setCount(1);
        order.setOrderStatus(1);
        order.setSeller(seller);
       
        order = orderRepository.save(order);
        
        Ongoing ongoing = new Ongoing();
        ongoing.setInvc("221141");
        ongoing.setOrder(order);
        ongoing.setDelever(delever);

        // Ongoing 저장
        ongoing = ongoingRepository.save(ongoing); // Save and get the saved instance

        // 저장된 Ongoing을 조회
        System.out.println("savedOrder : " + orderRepository.findById(
             ongoing.getOrder().getOrderId()).get());

        System.out.println("savedOngoing : " + ongoingRepository.findById(
             ongoing.getOngoingId()).get());
    }
}
