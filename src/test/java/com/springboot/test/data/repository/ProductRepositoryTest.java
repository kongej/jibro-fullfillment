package com.springboot.test.data.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jibro.fulfill.JibroFulfillApplication;
import com.jibro.fulfill.entity.Company;
import com.jibro.fulfill.entity.Product;
import com.jibro.fulfill.repository.CompanyRepository;
import com.jibro.fulfill.repository.ProductRepository;

@SpringBootTest(classes = JibroFulfillApplication.class)
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Test
    public void testCreateProduct() {
        // Create and save a Company
        Company company = new Company();
        company.setCompanyId("C001");
        company.setCompanyName("Example Company");
        company.setCompanyEmail("example@company.com");
        company.setCompanyContact("123-456-7890");
        company.setCompanyCategory("M");
        companyRepository.save(company);

        // Create and save a Product
        Product product = new Product();
        product.setProductId("P001");
        product.setProductName("Example Product");
        product.setCost(100);
        product.setProductImage("example.jpg");
        product.setMaker(company);

        productRepository.save(product);

        // Verify the Product was saved correctly
        Product savedProduct = productRepository.findById("P001").orElse(null);
        assertThat(savedProduct).isNotNull();
        assertThat(savedProduct.getProductName()).isEqualTo("Example Product");
        assertThat(savedProduct.getMaker().getCompanyId()).isEqualTo("C001");
        
        System.out.println(product);
    
    }
}