package spring.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class ShopApplication {

//    С помощью Spring AOP посчитайте по каждому сервису суммарное время, затрачиваемое на выполнение методов этих
//    сервисов. И по endpoint'у /statistic выдайте полученную статистику клиенту.
//    Подключить редис(Memurai) по примеру
//  **Вывести в консоль пользователя, который залогинился
//    Пример:
//
//    ProductService: 1200 ms
//    OrderService: 95 ms
//    UserService: 2000 ms

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }

}
