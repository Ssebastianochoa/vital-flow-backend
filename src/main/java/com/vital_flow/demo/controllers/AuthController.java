package com.vital_flow.demo.controllers;

import com.vital_flow.demo.dtos.AuthResponse;
import com.vital_flow.demo.dtos.LoginRequest;
import com.vital_flow.demo.services.AuthService;
import com.vital_flow.demo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authorizations")
public class AuthController {

    @Autowired
    public AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // Lógica de autenticación
        // Verificar credenciales, generar token, etc.
        // Ejemplo simple: verificar que las credenciales sean válidas
        if (authService.areValidCredentials(loginRequest.getEmail(), loginRequest.getPassword())) {
            String token = JwtUtil.generateToken(loginRequest.getEmail());
            return ResponseEntity.ok(new AuthResponse(token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
