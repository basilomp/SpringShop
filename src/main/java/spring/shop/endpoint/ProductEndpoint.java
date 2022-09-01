package spring.shop.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import spring.shop.services.ProductsService;
import spring.soap.products.GetAllProductsRequest;
import spring.soap.products.GetAllProductsResponse;
import spring.soap.products.Product;

import java.math.BigInteger;

@Endpoint
@RequiredArgsConstructor
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://www.mvg.com/spring/ws/products";
    private final ProductsService productsService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllProducts(@RequestPayload GetAllProductsRequest request) {
        GetAllProductsResponse response = new GetAllProductsResponse();
        productsService.allProductsToList().forEach(p -> {
            Product product = new Product();
            product.setTitle(p.getTitle());
            product.setPrice(BigInteger.valueOf(p.getPrice()));
            response.getProducts().add(product);
        });
        System.out.println(productsService.allProductsToList());
        return response;
    }

}
