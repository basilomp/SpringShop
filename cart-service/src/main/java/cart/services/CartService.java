package cart.services;

import cart.dto.Cart;
import cart.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import spring.shop.entities.Product;
import spring.shop.exceptions.ResourceNotFoundException;
import spring.shop.services.ProductsService;
import spring.shop.validators.ProductValidator;


import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductsService productsService;
    private final ProductValidator productValidator;
    private final CacheManager cacheManager;
    private final RestTemplate restTemplate;

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
            ProductDto productDto = restTemplate.getForObject("http://localhost:8189/web-market-core/api/v1/products" +
                    "/" + id, ProductDto.class);
            cart.addProduct(productDto);

        }
        return cart;
    }

    @CachePut(value = "Cart", key = "#cartName")
    public void decreaseProductByIdInCart(Long id, String cartName) {
        productValidator.validate(productsService.findById(id));
        Cart cart = getCurrentCart(cartName);
        cart.decreaseProduct(id);
        cacheManager.getCache("Cart").put(cartName, cart);
    }

    @CachePut(value = "Cart", key = "#cartName")
    public void removeProductFromCart(Long id, String cartName) {
        productValidator.validate(productsService.findById(id));
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