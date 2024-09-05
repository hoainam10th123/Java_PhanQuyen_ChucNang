package com.hoainam10th.phanquyendatabase.controller;

import com.hoainam10th.phanquyendatabase.dtos.LoginDto;
import com.hoainam10th.phanquyendatabase.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/auth")
@RestController
public class AuthController {
    private final UserService userService;

    @PostMapping("login")
    public ResponseEntity<Object> login(@RequestBody LoginDto data){
        return ResponseEntity.ok(userService.login(data));
    }
}
