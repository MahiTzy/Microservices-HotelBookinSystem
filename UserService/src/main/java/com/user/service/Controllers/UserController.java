package com.user.service.Controllers;

import com.user.service.Models.User;
import com.user.service.Services.UserService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
//    @PreAuthorize("hasAuthority('SCOPE_internal')")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User user1 = userService.saveUser(user);
        return ResponseEntity.ok(user1);
    }

//    @PreAuthorize("hasAuthority('SCOPE_internal')")
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    //    private int count = 0;
    //    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelServiceFallback")
    //    @Retry(name = "ratingHotelRetry", fallbackMethod = "ratingHotelServiceFallback")
    @GetMapping("/{userId}")
//    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @RateLimiter(name = "ratingHotelRateLimiter", fallbackMethod = "ratingHotelServiceFallback")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
//        System.out.println("Fallback method called "+count++);
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    public ResponseEntity<User> ratingHotelServiceFallback(String userId, Exception e) {
        e.printStackTrace();

        System.out.println("Fallback method called" + e.getMessage());
        User user = User.builder().userID("1234").name("Dummy").email("dummy@gmail.com").about("Dummy data").build();
        return ResponseEntity.ok(user);
    }
}
