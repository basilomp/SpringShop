package cart.controllers;

import cart.dto.Cart;
import cart.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
public class CartController {
    private final CartService service;

    @PostMapping
    public Cart getCurrentCart(@RequestBody String cartName){
        return service.getCurrentCart(cartName);
    }

    @PostMapping("/add/{id}")
    public void addProductToCart(@PathVariable Long id, @RequestBody String cartName){
        service.addProductByIdToCart(id, cartName);
    }

    @PostMapping("/clear")
    public void clearCart(@RequestBody String cartName) {
        service.getCurrentCart(cartName).clear();
    }

    //Метод для уменьшения количества
    @PostMapping("/decrease/{id}")
    public void decreaseProductInCart(@PathVariable Long id, @RequestBody String cartName) {
        service.decreaseProductByIdInCart(id, cartName);
    }

    //Метод для полного удаления позиции из корзины
    @PostMapping("/remove/{id}")
    public void removeProductFromCart(@PathVariable Long id, @RequestBody String cartName) {
        service.removeProductFromCart(id, cartName);
    }

}
