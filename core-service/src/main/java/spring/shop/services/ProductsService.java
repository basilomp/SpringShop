package spring.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.shop.dto.ProductDto;
import spring.shop.entities.Product;
import spring.shop.exceptions.ResourceNotFoundException;
import spring.shop.mappers.ProductMapper;
import spring.shop.repositories.ProductsRepository;
import spring.shop.repositories.specifications.ProductsSpecifications;
import spring.shop.validators.ProductValidator;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductsService {

    private final ProductValidator productValidator;
    private final ProductsRepository productsRepository;

//    TODO: Fix instances
    private final ProductMapper productMapper;

    public Page<ProductDto> findAll(Integer minPrice, Integer maxPrice, String partTitle, Integer page) {
        Specification<Product> spec = Specification.where(null);
        if (minPrice != null) {
            spec = spec.and(ProductsSpecifications.priceGreaterOrEqualsThan(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductsSpecifications.priceLessThanOrEqualsThan(maxPrice));
        }
        if (partTitle != null) {
            spec = spec.and(ProductsSpecifications.titleLike(partTitle));
        }
        return productsRepository.findAll(spec, PageRequest.of(page - 1, 50)).map(productMapper::toDto);
    }

    public List<Product> allProductsToList() {
        return productsRepository.findAll();
    }

    public ProductDto findById(Long id) {
        Product product = productsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found. Id: " + id));
        return ProductMapper.INSTANCE.toDto(product);
    }

    @Transactional
    public void saveProduct(ProductDto productDto) {
        productValidator.validate(productDto);
        Product product;
        if(!productsRepository.existsById(productDto.getId())) {
            product = ProductMapper.INSTANCE.toEntity(productDto);
            productsRepository.save(product);
        }
    }

    @Transactional
    public void updateProduct(ProductDto productDto) {
        productValidator.validate(productDto);
        Product product = productsRepository.findById(productDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Product not found. Id: " + productDto.getId()));
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        productsRepository.save(product);

    }

    @Transactional
    public void deleteById(Long id) {
        productsRepository.deleteById(id);
    }
}

