package spring.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import spring.shop.dto.Cart;
import spring.shop.entities.Product;
import spring.shop.exceptions.ResourceNotFoundException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductsService productsService;
    private final CacheManager cacheManager;
    private Cart cart;

    public Cart getCurrentCart(String cartName){
        cart = cacheManager.getCache("Cart").get(cartName, Cart.class);
        if(!Optional.ofNullable(cart).isPresent()){
            cart = new Cart(cartName, cacheManager);
            cacheManager.getCache("Cart").put(cartName, cart);
        }
        return cart;
    }

    public void addProductByIdToCart(Long id, String cartName){
        if(!getCurrentCart(cartName).addProductCount(id)){
            Product product = productsService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Не удалось найти продукт"));
            Cart cart = getCurrentCart(cartName);
            cart.addProduct(product);
            cacheManager.getCache("Cart").put(cartName, cart);
        }
    }

    public void clear(String cartName){
        Cart cart = getCurrentCart(cartName);
        cart.clear();
        cacheManager.getCache("Cart").put(cartName, cart);
    }
}