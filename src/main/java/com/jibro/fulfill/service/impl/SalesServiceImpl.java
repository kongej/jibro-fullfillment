package com.jibro.fulfill.service.impl;

import org.springframework.stereotype.Service;

import com.jibro.fulfill.repository.OrderRepository;
import com.jibro.fulfill.service.SalesService;

@Service
public class SalesServiceImpl implements SalesService{
	private OrderRepository orderRepository;
	
	private SalesServiceImpl(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	
//	@Autowired
//	private CompanyRepository companyRepository;
	
	//list
//	public List<ProductListResponseDto> productList(String productName, Integer page){
//		
//		final int pageSize = 10;
//		
//		List<Product> products;
//		
//		if(page == null) {
//			page = 0;
//		}else {
//			page -= 1;
//		}
//		
//		if(productName == null) {
//			Pageable pageable = PageRequest.of(page, pageSize, Direction.DESC, "productId");
//			products = this.orderRepository.findAll(pageable).toList();
//		}else {
//			Pageable pageable = PageRequest.of(page, pageSize);
//			Sort sort = Sort.by(Order.desc("created_At"));
//			pageable.getSort().and(sort);
//			products = this.productRepository.findByProductNameContains(productName, pageable);
//		}
//		
//		
//		//String productId, String productName, Integer cost, Integer safetyStock, 
//		// Integer stockCount, Integer defectiveCount, 
//		// String productImage, String maker
//		return products.stream().map(product -> new ProductListResponseDto(
//				product.getProductId(), product.getProductName(), product.getCost(),
//				product.getSafetyStock(), product.getStockCount(), product.getDefectiveCount(),
//			    product.getProductImage(), product.getMaker(), product.getMaker().getCompanyId())).collect(Collectors.toList());
//	}
//	
//	// insert
//	public String insert(ProductInsertDto productInsertDto) {
//		
////		if(!companyRepository.existsById(productInsertDto.getMaker())) {
////			throw new IllegalArgumentException("관련 거래처는 존재하지 않습니다.");
////		}
//		
//		Product product = Product.builder()
//				.productId(productInsertDto.getProductId())
//				.productName(productInsertDto.getProductName())
//				.cost(productInsertDto.getCost())
//				.safetyStock(productInsertDto.getSafetyStock())
//				.stockCount(productInsertDto.getStockCount())
//				.defectiveCount(productInsertDto.getDefectiveCount())
//				.productImage(productInsertDto.getProductImage())
//				.maker(productInsertDto.getMaker())
//				.build();
//		
//		this.productRepository.save(product);
//		return product.getProductId();
//	}
//	
//	//read
//	public ProductReadResponseDto read(String productId) throws NoSuchElementException{
//		Product product = this.productRepository.findById(productId).orElseThrow();
//		ProductReadResponseDto productReadResponseDto = new ProductReadResponseDto();
//		
//		productReadResponseDto.fromProduct(product);		
//		return productReadResponseDto;
//	}
//	
//	//mod_list
//	public ProductModResponseDto edit(String productId) throws NoSuchElementException{
//		Product product = this.productRepository.findById(productId).orElseThrow();
//		
//		return ProductModResponseDto.ProductFactory(product);
//	}
//	
//	//mod
//	public void update(ProductModDto productModDto) throws NoSuchElementException{
//		Product product = this.productRepository.findById(productModDto.getProductId()).orElseThrow();
//		product = productModDto.fill(product);
//		this.productRepository.save(product);
//	}
//	
//	// delete
//	public void delete(String productId) throws NoSuchElementException{
//		Product product = this.productRepository.findById(productId).orElseThrow();
//		this.productRepository.delete(product);
//	}
}
