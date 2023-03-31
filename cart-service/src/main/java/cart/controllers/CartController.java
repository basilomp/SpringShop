package cart.controllers;

import cart.dto.Cart;
import cart.services.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
@Tag(name = "CartController", description = "Controller for processing cart requests")
public class CartController {
    private final CartService service;

    @PostMapping
    @Operation(summary = "Method for getting the current cart")
    public Cart getCurrentCart(@RequestBody String cartName){
        return service.getCurrentCart(cartName);
    }

    @PostMapping("/add/{id}")
    @Operation(summary = "Method for adding a product to a cart")
    public void addProductToCart(@PathVariable Long id, @RequestBody String cartName){
        service.addProductByIdToCart(id, cartName);
    }

    @PostMapping("/clear")
    @Operation(summary = "Method for removing all products from a cart")
    public void clearCart(@RequestBody String cartName) {
        service.getCurrentCart(cartName).clear();
    }

    @PostMapping("/decrease/{id}")
    @Operation(summary = "Decrease the quantity of a product in a cart by 1")
    public void decreaseProductInCart(@PathVariable Long id, @RequestBody String cartName) {
        service.decreaseProductByIdInCart(id, cartName);
    }

    @PostMapping("/remove/{id}")
    @Operation(summary = "Method for removing a product from a cart")
    public void removeProductFromCart(@PathVariable Long id, @RequestBody String cartName) {
        service.removeProductFromCart(id, cartName);
    }

}
