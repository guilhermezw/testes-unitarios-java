package com.spring.login.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class LoginRequestDTO {

    @Email(message = "O e-mail fornecido não é um endereço válido.")
    @NotBlank(message = "O e-mail é obrigatório.")
    private String email;

    @NotBlank(message = "A senha é obrigatória.")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&.#_-])[A-Za-z\\d@$!%*?&.#_-]{8,}$",
            message = "A senha deve conter no mínimo 8 caracteres, uma letra maiúscula, uma minúscula, um número e um símbolo especial."
    )
    private String senha;

    public LoginRequestDTO() {
    }

    public LoginRequestDTO(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}