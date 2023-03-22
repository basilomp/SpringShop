package spring.shop.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import spring.shop.dto.ProductDto;
import spring.shop.entities.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product toEntity(ProductDto productDto);

    ProductDto toDto(Product product);
}
