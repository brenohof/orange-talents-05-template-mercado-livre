package br.com.zup.breno.mercadolivre.security;

import br.com.zup.breno.mercadolivre.security.LoginRequest;
import br.com.zup.breno.mercadolivre.security.Token;
import br.com.zup.breno.mercadolivre.security.TokenResponse;
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
    private Token token;

    @PostMapping
    public ResponseEntity<?> autenticar(@Valid @RequestBody LoginRequest request) {
        UsernamePasswordAuthenticationToken dadosLogin = request.toModel();

        try {
            Authentication authentication = authManager.authenticate(dadosLogin);
            authentication.getPrincipal();
            String novoToken = token.gerarToken(authentication);
            return ResponseEntity.ok(new TokenResponse(novoToken, "Bearer"));
        } catch (AuthenticationException exception) {
            return ResponseEntity.badRequest().build();
        }

    }
}
