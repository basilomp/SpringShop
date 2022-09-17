package cart;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import spring.shop.services.ProductsService;

@SpringBootTest
public class AppTest {

    @MockBean
    ProductsService productsService;

    @Test
    void contextLoads() {}
}
