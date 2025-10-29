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

@PasswordMatches(message = "{password.mismatch}")
@Data
public class RegistrationDto {
    private Long id;
    @NotBlank(message="{form.field.required}")
    @Pattern(regexp = "[a-zA-Z0-9._-]+$", message="Nome de usuário pode conter letras e/ou números e traços")
    @UniqueUsername
    private String username;
    @NotBlank(message="{form.field.required}")
    @Size(min=8,max=64, message="{password.short}")
    @StrongPassword
    private String password;
    @NotBlank(message="{form.field.required}")
    private String confirmPassword;
    @NotBlank(message="{form.field.required}")
    @Email
    @Size(min = 4, max=254, message="Email muito longo")
    @UniqueEmail
    private String email;
    @AssertTrue(message="Você deve aceitar os termos para realizar o cadastro")
    private boolean acceptedTerms;
}
