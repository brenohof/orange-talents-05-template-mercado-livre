package br.com.zup.breno.mercadolivre.autenticacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private GerenciadorDeToken gerenciadorDeToken;

    @PostMapping
    public ResponseEntity<?> autenticar(@Valid @RequestBody LoginRequest request) {
        UsernamePasswordAuthenticationToken dadosLogin = request.toModel();

        try {
            Authentication authentication = authManager.authenticate(dadosLogin);
            String novoToken = gerenciadorDeToken.gerarToken(authentication);
            return ResponseEntity.ok(new TokenResponse(novoToken, "Bearer"));
        } catch (AuthenticationException exception) {
            return ResponseEntity.badRequest().build();
        }

    }
}
