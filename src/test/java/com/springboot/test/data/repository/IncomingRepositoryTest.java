package com.springboot.test.data.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jibro.fulfill.JibroFulfillApplication;
import com.jibro.fulfill.entity.Company;
import com.jibro.fulfill.entity.Incoming;
import com.jibro.fulfill.entity.Product;
import com.jibro.fulfill.repository.CompanyRepository;
import com.jibro.fulfill.repository.IncomingRepository;
import com.jibro.fulfill.repository.ProductRepository;

@SpringBootTest(classes = JibroFulfillApplication.class)
public class IncomingRepositoryTest {

    @Autowired
    private IncomingRepository incomingRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CompanyRepository companyRepository;
    
    @Test
    public void testSaveIncoming() {
    	
		 Company company = new Company();
	     company.setCompanyId("C001");
	     company.setCompanyName("Example Company");
	     company.setCompanyEmail("example@company.com");
	     company.setCompanyContact("123-456-7890");
	     company.setCompanyCategory("M");
	     companyRepository.save(company);
    	
        // Product 생성 및 저장
        Product product = new Product();
        product.setProductId("P001");
        product.setProductName("Test Product");
        product.setCost(1000);
        product.setSafetyStock(10);
        product.setStockCount(50);
        product.setDefectiveCount(2);
        product.setProductImage("test-image.jpg");
        product.setMaker(company);
        productRepository.save(product);

        // Incoming 생성 및 저장
        Incoming incoming = new Incoming();
        incoming.setIncomingCount(20);
        incoming.setTotal(20000);
        incoming.setProduct(product);
        incomingRepository.save(incoming);

        // 저장된 Incoming 조회
        Incoming savedIncoming = incomingRepository.findById(incoming.getIncomingId()).orElse(null);

        // 값 확인
        assertThat(savedIncoming).isNotNull();
        assertThat(savedIncoming.getIncomingCount()).isEqualTo(20);
        assertThat(savedIncoming.getTotal()).isEqualTo(20000);
        assertThat(savedIncoming.getProduct()).isNotNull();
        assertThat(savedIncoming.getProduct().getProductId()).isEqualTo("P001");
        assertThat(savedIncoming.getProduct().getProductName()).isEqualTo("Test Product");

        System.out.println(incoming);
    }
}
