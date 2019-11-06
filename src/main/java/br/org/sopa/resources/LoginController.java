package br.org.sopa.resources;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.org.sopa.domain.Usuario;

@RestController
public class LoginController {

	@PostMapping("/auth")
	@CrossOrigin
	public @ResponseBody ResponseEntity<?> login(Usuario usuario) {
		System.out.println(usuario.getUsername() + "\t" + usuario.getPassword());
		return ResponseEntity.ok().body(new ArrayList<>());
	}
}
