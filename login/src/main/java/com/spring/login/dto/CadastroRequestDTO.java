package com.spring.login.dto;

import com.spring.login.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CadastroRequestDTO {

    @NotBlank(message = "O nome é obrigatório.")
    private String nome;

    @Email(message = "E-mail inválido.")
    @NotBlank(message = "O e-mail é obrigatório.")
    private String email;

    @NotBlank(message = "A senha é obrigatória.")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&.#_-])[A-Za-z\\d@$!%*?&.#_-]{8,}$",
            message = "A senha deve conter no mínimo 8 caracteres, uma letra maiúscula, uma minúscula, um número e um símbolo especial."
    )
    private String senha;

    private Role role;

    public CadastroRequestDTO(String nome, String email, String senha, Role role) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.role = role;
    }

    public @NotBlank(message = "O nome é obrigatório.") String getNome() {
        return nome;
    }

    public void setNome(@NotBlank(message = "O nome é obrigatório.") String nome) {
        this.nome = nome;
    }

    public @Email(message = "E-mail inválido.") @NotBlank(message = "O e-mail é obrigatório.") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "E-mail inválido.") @NotBlank(message = "O e-mail é obrigatório.") String email) {
        this.email = email;
    }

    public @NotBlank(message = "A senha é obrigatória.") @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&.#_-])[A-Za-z\\d@$!%*?&.#_-]{8,}$",
            message = "A senha deve conter no mínimo 8 caracteres, uma letra maiúscula, uma minúscula, um número e um símbolo especial."
    ) String getSenha() {
        return senha;
    }

    public void setSenha(@NotBlank(message = "A senha é obrigatória.") @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&.#_-])[A-Za-z\\d@$!%*?&.#_-]{8,}$",
            message = "A senha deve conter no mínimo 8 caracteres, uma letra maiúscula, uma minúscula, um número e um símbolo especial."
    ) String senha) {
        this.senha = senha;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}