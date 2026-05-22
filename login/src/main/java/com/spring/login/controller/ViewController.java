package com.spring.login.controller;

import com.spring.login.dto.PerfilResponseDTO;
import com.spring.login.service.ViewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/view")
public class ViewController {

    private final ViewService viewService;

    public ViewController(ViewService viewService) {
        this.viewService = viewService;
    }

    @GetMapping("/mensagem")
    public ResponseEntity<String> exibirMensagem() {
        return ResponseEntity.status(HttpStatus.OK).body(viewService.exibirMensagem());
    }

    @GetMapping("/perfil")
    public ResponseEntity<PerfilResponseDTO> perfil() {
        return ResponseEntity.status(HttpStatus.OK).body(viewService.exibirMinhasInformações());
    }
}
