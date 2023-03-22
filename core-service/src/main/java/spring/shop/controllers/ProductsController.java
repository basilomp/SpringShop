package spring.shop.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import spring.shop.dto.ProductDto;
import spring.shop.services.ProductsService;


@RestController
@RequestMapping("/api/v1/products")
@Tag(name = "Контроллер продуктов", description = "Контроллер для обработки запросов для операций с товарами")
@RequiredArgsConstructor
public class ProductsController {
    private final ProductsService productsService;

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
        return productsService.findAll(minPrice, maxPrice, titlePart, page);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Request for getting a product by its id")
    public ProductDto getProductById(@PathVariable Long id) {
        return productsService.findById(id);
    }

    @PostMapping
    @Operation(summary = "Request for adding/updating a new product")
    public void saveOrUpdateProduct(@RequestBody ProductDto productDto) {
        productsService.saveProduct(productDto);
    }

    @PutMapping
    @Operation(summary = "Request for updating an existing product")
    public void updateProduct(@RequestBody ProductDto productDto) {
        productsService.updateProduct(productDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Request for deleting an existing product")
    public void deleteById(@PathVariable Long id) {
        productsService.deleteById(id);
    }
}
