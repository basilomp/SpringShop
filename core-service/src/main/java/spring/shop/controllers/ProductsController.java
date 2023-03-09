package spring.shop.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import spring.shop.converters.ProductConverter;
import spring.shop.dto.ProductDto;
import spring.shop.entities.Product;
import spring.shop.exceptions.ResourceNotFoundException;
import spring.shop.services.ProductsService;
import spring.shop.validators.ProductValidator;


@RestController
@RequestMapping("/api/v1/products")
@Tag(name = "Контроллер продуктов", description = "Контроллер для обработки запросов для операций с товарами")
@RequiredArgsConstructor
public class ProductsController {
    private final ProductsService productsService;
    private final ProductConverter productConverter;
    private final ProductValidator productValidator;

    @GetMapping
    @Operation(summary = "Request for getting paged list of products")
    public Page<ProductDto> getAllProducts(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "min_price", required = false) Integer minPrice,
            @RequestParam(name = "max_price", required = false) Integer maxPrice,
            @RequestParam(name = "title_part", required = false) String titlePart
    ) {
        if (page < 1) {
            page = 1;
        }
        return productsService.findAll(minPrice, maxPrice, titlePart, page).map(
                p -> productConverter.entityToDto(p)
        );
    }

    @GetMapping("/{id}")
    @Operation(summary = "Request for getting a product by its id")
    public ProductDto getProductById(@PathVariable Long id) {
        Product product = productsService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found, id: " + id));
        return productConverter.entityToDto(product);
    }

    @PostMapping
    @Operation(summary = "Request for adding a new product")
    public ProductDto saveNewProduct(@RequestBody ProductDto productDto) {
        productValidator.validate(productDto);
        Product product = productConverter.dtoToEntity(productDto);
        product = productsService.save(product);
        return productConverter.entityToDto(product);
    }

    @PutMapping
    @Operation(summary = "Request for updating an existing product")
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        productValidator.validate(productDto);
        Product product = productsService.update(productDto);
        return productConverter.entityToDto(product);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Request for deleting an existing product")
    public void deleteById(@PathVariable Long id) {
        productsService.deleteById(id);
    }
}
