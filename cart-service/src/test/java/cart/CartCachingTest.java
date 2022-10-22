package cart;

import cart.dto.Cart;
import cart.dto.ProductDto;
import cart.services.CartService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;
import spring.shop.services.ProductsService;

import java.util.Objects;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CartService.class)
@ActiveProfiles("test")
public class CartCachingTest {
    @MockBean
    private ProductsService productsService;

    @MockBean
    private CacheManager cacheManager;

    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private CartService cartService;

    @BeforeEach
    public void initCart() {
//        cartService = new CartService(productsService, cacheManager, restTemplate);
//        Mockito.when(cacheManager.getCache("Cart")
//                .get(Mockito.anyString(), Cart.class)).thenReturn(new Cart());
//        cartService.clear("test_cart");
//        cartService = new CartService(productsService, cacheManager, restTemplate);


//        cartService.getCurrentCart("test_cart");
//        cacheManager.getCache("Cart");
//        Mockito.when(cacheManager.getCache("Cart"))
//            .get(Mockito.anyString(), Cart.class)).thenReturn(new Cart());
        cartService.clear("test_cart");

    }

    @Test
    public void checkCache() {
        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setTitle("Crisps");
        productDto.setPrice(100);
        cartService = new CartService(productsService, cacheManager, restTemplate);
        cartService.addProductByIdToCart(1l, "test_cart");

        Assertions.assertEquals(1, cartService.getCurrentCart("test_cart").getItems().size());

        }
//
//
//    @Test
//    public void addToCartTest() {
//        ProductDto product = new ProductDto();
//        product.setId(5L);
//        product.setTitle("X");
//        product.setPrice(100);
//
//        Mockito.doReturn(new Cart()).when(cartService).getCurrentCart("test_cart");
//        cartService.addProductByIdToCart(5l, "test_cart");
//        cartService.addProductByIdToCart(5l, "test_cart");
//        cartService.addProductByIdToCart(5l, "test_cart");
//
//        Mockito.verify(restTemplate, Mockito.times(1)).getForObject(Mockito.any(), Mockito.any());
//        Assertions.assertEquals(1, cartService.getCurrentCart("test_cart").getItems().size());
//    }
}
