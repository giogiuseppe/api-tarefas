package application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import application.domain.usuario.Usuario;
import application.domain.usuario.UsuarioDTO;
import application.infra.security.DadosTokenJWT;
import application.infra.security.TokenService;

@RestController
@RequestMapping("/auth")
public class UsuarioController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<DadosTokenJWT> auth(@RequestBody UsuarioDTO usuarioDTO) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(usuarioDTO.nome(), usuarioDTO.senha());
        var authentication = manager.authenticate(authenticationToken);
    
        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
    
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
}
