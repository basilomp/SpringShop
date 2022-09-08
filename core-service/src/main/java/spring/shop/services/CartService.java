package spring.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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

    @Value("Cart")
    private String CACHE_CART;
    private Cart cart;

    @Cacheable(value = "Cart", key = "#cartName")
    public Cart getCurrentCart(String cartName){
        cart = cacheManager.getCache(CACHE_CART).get(cartName, Cart.class);
        if(!Optional.ofNullable(cart).isPresent()){
            cart = new Cart(cartName, cacheManager);
            cacheManager.getCache(CACHE_CART).put(cartName, cart);
        }
        return cart;
    }

    @CachePut(value = "Cart", key = "#cartName")
    public Cart addProductByIdToCart(Long id, String cartName){
        Cart cart = getCurrentCart(cartName);
        if(!cart.addProductCount(id)) {
            Product product = productsService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Не удалось найти продукт"));
            cart.addProduct(product);
        }
        return cart;
    }

    @CachePut(value = "Cart", key = "#cartName")
    public void decreaseProductByIdInCart(Long id, String cartName) {
        Product product = productsService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Не удалось " +
                "найти продукт"));
        Cart cart = getCurrentCart(cartName);
        cart.decreaseProduct(product.getId());
        cacheManager.getCache("Cart").put(cartName, cart);
    }

    @CachePut(value = "Cart", key = "#cartName")
    public void removeProductFromCart(Long id, String cartName) {
        Product product = productsService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Не удалось " +
                "найти продукт"));
        Cart cart = getCurrentCart(cartName);
        cart.removeProduct(id);
        cacheManager.getCache("Cart").put(cartName, cart);
    }

    @CachePut(value = "Cart", key = "#cartName")
    public Cart clear(String cartName){
        Cart cart = getCurrentCart(cartName);
        cart.clear();
        return cart;
    }
}