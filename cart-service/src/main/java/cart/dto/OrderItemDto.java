package cart.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.shop.entities.Product;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {

    @Schema(description = "Id of a product in an order")
    private Long productId;

    @Schema(description = "Title of a product in an order")
    private String title;

    @Schema(description = "Quantity of a product in an order")
    private int quantity;

    @Schema(description = "Price per a product in an order")
    private int pricePerProduct;

    @Schema(description = "Total price of a product in an order")
    private int price;

    public OrderItemDto(ProductDto product){
        this.productId = product.getId();
        this.title = product.getTitle();
        this.quantity = 1;
        this.pricePerProduct = product.getPrice();
        this.price = product.getPrice();
    }

    public void changeQuantity(int delta){
        this.quantity += delta;
        this.price = this.quantity * this.pricePerProduct;
    }

}
