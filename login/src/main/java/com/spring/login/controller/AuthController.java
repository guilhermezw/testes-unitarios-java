package com.spring.login.controller;

import com.spring.login.annotation.RateLimited;
import com.spring.login.dto.CadastroRequestDTO;
import com.spring.login.dto.LoginRequestDTO;
import com.spring.login.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @RateLimited(message = "Muitas tentativas de login. Tente novamente em alguns minutos.")
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody @Valid LoginRequestDTO dto , HttpServletRequest request) {
        String token = authService.login(dto);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message","Login realizado com sucesso","token" ,token));
    }

    @PostMapping("/cadastro")
    public ResponseEntity<Map<String, Object>> cadastro(@RequestBody @Valid CadastroRequestDTO dto) {
        authService.cadastrarUsuario(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message","Usuário salvo" , "success" , true));
    }
}
