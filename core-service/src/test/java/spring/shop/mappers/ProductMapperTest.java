package spring.shop.mappers;

import org.junit.jupiter.api.Test;
import spring.shop.dto.ProductDto;
import spring.shop.entities.Product;

import static org.junit.jupiter.api.Assertions.*;


class ProductMapperTest {

    @Test
    void toEntity() {
        ProductDto dto = ProductDto.builder()
                .id(1L)
                .title("Milk")
                .price(100)
        .build();

        Product product = ProductMapper.INSTANCE.toEntity(dto);
        assertEquals(dto.getId(), product.getId());
        assertEquals(dto.getTitle(), product.getTitle());
        assertEquals(dto.getPrice(), product.getPrice());
    }

    @Test
    void toDto() {
        Product product = Product.builder()
                .id(2L)
                .title("Apple")
                .price(50)
                .build();

        ProductDto dto = ProductMapper.INSTANCE.toDto(product);
        assertEquals(product.getId(), dto.getId());
        assertEquals(product.getTitle(), dto.getTitle());
        assertEquals(product.getPrice(), dto.getPrice());
    }


}