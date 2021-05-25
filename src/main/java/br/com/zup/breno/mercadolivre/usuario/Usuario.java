package br.com.zup.breno.mercadolivre.usuario;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull @Email
    private String login;
    @NotNull @Size(min = 6)
    private String senha;
    @NotNull @Future
    private LocalDateTime instanteDeCriacao;

    public Usuario(@NotNull String login, @NotNull String senha) {
        this.login = login;
        this.senha = new BCryptPasswordEncoder().encode(senha);
        this.instanteDeCriacao = LocalDateTime.now().plusMinutes(5);
    }

    public static boolean isPasswordClean(String password) {
        return password.equals(password.trim());
    }
}
