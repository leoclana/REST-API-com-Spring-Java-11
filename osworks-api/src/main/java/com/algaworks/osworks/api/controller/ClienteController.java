package com.algaworks.osworks.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.algaworks.osworks.domain.model.Cliente;
import com.algaworks.osworks.domain.repository.ClientesRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	// ** Forma 1 : Manager **
	//@PersistenceContext
	//private EntityManager manager;
	
	// ** Forma 2 : Interface "JpaRepository" **
	@Autowired
	private ClientesRepository clienteRepository;
	
	//============================================================
	
	@GetMapping
	public List<Cliente> listar() {
		List<Cliente> retorno = new ArrayList<Cliente>();
		
		// ** Forma 1 : Manager **
		//retorno =  manager.createQuery("from Cliente", Cliente.class).getResultList();
		
		// ** Forma 2 : Interface "JpaRepository" **
		retorno.addAll(clienteRepository.findAll());
		
		return retorno;
		
	}
	@GetMapping("/findNome/{nome}")
	public List<Cliente> findNome(@PathVariable String nome) {
		return clienteRepository.findByNome(nome);
	}
	@GetMapping("/findNomeContaining/{partNome}")
	public List<Cliente> findNomeContaining(@PathVariable String partNome) {
		return clienteRepository.findByNomeContaining(partNome);
	}
	
	/*
	 * @GetMapping("/{clientId}") 
	 * public Cliente buscar(@PathVariable Long clientId) { 
	 * Optional<Cliente> cli = clienteRepository.findById(clientId);
	 * return cli.orElse(null); 
	 * }
	 */
	//@GetMapping("/{clientId}")
	//public Optional<Cliente> buscar(@PathVariable Long clientId) {
	//	return clienteRepository.findById(clientId);
	//}
	@GetMapping("/{clientId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clientId) {
		Optional<Cliente> cliente = clienteRepository.findById(clientId);
		//return cliente.isPresent() ? ResponseEntity.ok(cliente.get()) : ResponseEntity.notFound().build();
		return cliente.isPresent() ? ResponseEntity.ok(cliente.get()) : ResponseEntity.noContent().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@RequestBody Cliente cliente ) {
		return clienteRepository.save(cliente);
	}
	
	@PutMapping("/{clientId}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long clientId, @RequestBody Cliente cliente) {
		
		if(!clienteRepository.existsById(clientId)) {
			return ResponseEntity.notFound().build();
		}
		
		cliente.setId(clientId);
		clienteRepository.save(cliente);
		
		return ResponseEntity.ok(cliente);
	}
	
	//Exclusão .. Video-2 ( https://cafe.algaworks.com/sri-aula2-whd/ ) -> 01:02:17

}
