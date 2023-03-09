package spring.shop.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import spring.shop.dto.ProfileDto;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/profile")
@Tag(name = "Контроллер личного кабинета")
public class ProfileController {

    @GetMapping
    @Operation(summary = "Request for getting user information")
    public ProfileDto getCurrentUserInfo(@RequestHeader String username) {
        return new ProfileDto(username);
    }
}
