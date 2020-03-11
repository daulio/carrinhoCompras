package br.com.daulio.facilit.carrinho.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.daulio.facilit.carrinho.dto.ProdutoDTO;
import br.com.daulio.facilit.carrinho.exception.ApiException;
import br.com.daulio.facilit.carrinho.service.ProdutoService;
import io.swagger.annotations.Api;

@RestController()
@RequestMapping("/produto")
@Api(value = "API Produtos", tags = { "Produto" })
public class ProdutoController {
	
	@Autowired private ProdutoService service;
	
	@PostMapping(value = "/", consumes = { MediaType.APPLICATION_JSON_VALUE}, produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> salvarNovo(@RequestBody @Valid ProdutoDTO dto) 
			throws Exception {
		
		ProdutoDTO produtoDTO = service.salvar(dto);
		return new ResponseEntity<>(produtoDTO, HttpStatus.CREATED);
	}
	
	@PatchMapping(value = "/", consumes = { MediaType.APPLICATION_JSON_VALUE}, produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> alterarCupom(@RequestBody @Valid ProdutoDTO dto) 
			throws Exception {
		ProdutoDTO produtoDTO = service.alterar(dto);
		return new ResponseEntity<>(produtoDTO, HttpStatus.OK);
	}
	
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<ProdutoDTO>> getListarTodos(){
		List<ProdutoDTO> produtos = service.listar();
		return new ResponseEntity<>(produtos, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ProdutoDTO> getProdutoPorId(@PathVariable Long id) throws ApiException {
		
		ProdutoDTO find = service.buscarPorId(id);
		return new ResponseEntity<>(find, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void excluirProduto(@PathVariable Long id) throws ApiException  {
		service.excluir(id);
	}

}
