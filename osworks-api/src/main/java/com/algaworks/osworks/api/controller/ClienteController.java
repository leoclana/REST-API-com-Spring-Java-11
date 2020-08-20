package com.algaworks.osworks.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.osworks.domain.model.Cliente;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@GetMapping
	public List<Cliente> listar() {
		var cliente1 = new Cliente();
		cliente1.setId(1L);
		cliente1.setNome("Leo");
		cliente1.setEmail("leoclana@gmail");
		cliente1.setTelefone("21 99999-9991");
		
		var cliente2 = new Cliente();
		cliente2.setId(2L);
		cliente2.setNome("Mari Lana");
		cliente2.setEmail("marilana@gmail");
		cliente2.setTelefone("21 99999-9992");
		
		
		return Arrays.asList(cliente1, cliente2);
	}

}
