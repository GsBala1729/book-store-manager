package com.finzly.bookmanager.controller;


import com.finzly.bookmanager.models.UserDetails;
import com.finzly.bookmanager.models.UserRetrieveRequest;
import com.finzly.bookmanager.service.IUserService;
import com.finzly.bookmanager.service.UserDetailsLoader;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "user/")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})
public class UserController {
    private final IUserService userService;
    private final UserDetailsLoader userDetailsLoader;

    @GetMapping
    public ResponseEntity getUsers(@RequestParam(value = "user_id", required = false) final Long userId,
                                   @RequestParam(value = "user_name", required = false) final String userName) {

        return ResponseEntity.ok().body(userService.queryUsers(UserRetrieveRequest.builder().
                id(userId).userName(userName).build()));
    }

    @PostMapping("login")
    public ResponseEntity login(@NonNull @RequestBody final UserDetails userDetails) {
        if(!userDetailsLoader.isValidUser(userDetails)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("In  valid Credentials or User");
        }
        return ResponseEntity.ok().body(userService.queryUsers(UserRetrieveRequest.builder()
                .userName(userDetails.getUserName()).build()).stream().findFirst().orElse(null));
    }

    @PostMapping
    public ResponseEntity createUser(@NonNull @RequestBody final UserDetails userDetails) {
        userService.createUser(userDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body("User Details created in the DB");
    }

    @PutMapping
    public ResponseEntity updateUser(@NonNull @RequestBody final UserDetails userDetails) {
        userService.updateUser(userDetails);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("User Details updated in DB");
    }
}
