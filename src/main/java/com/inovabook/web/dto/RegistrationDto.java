package com.inovabook.web.dto;


import com.inovabook.web.validation.annotation.UniqueEmail;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import com.inovabook.web.validation.annotation.PasswordMatches;
import com.inovabook.web.validation.annotation.UniqueEmail;
import com.inovabook.web.validation.annotation.UniqueUsername;
import com.inovabook.web.validation.annotation.StrongPassword;
import lombok.Data;

@PasswordMatches
@Data
public class RegistrationDto {
    private Long id;
    @NotBlank(message="Campo obrigatório")
    @Pattern(regexp = "[a-zA-Z0-9._-]+$", message="Nome de usuário pode conter letras e/ou números e traços")
    @UniqueUsername
    private String username;
    @NotBlank(message="Campo obrigatório")
    @Size(min=8,max=64, message="Senha deve conter de 8 a 64 caracteres")
    @StrongPassword
    private String password;
    @NotBlank
    private String confirmPassword;
    @NotBlank(message="Campo obrigatório")
    @Email
    @Size(min = 4, max=254, message="Email muito longo")
    @UniqueEmail
    private String email;
    @AssertTrue
    private boolean acceptedTerms;
}
