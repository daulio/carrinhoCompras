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

import br.com.daulio.facilit.carrinho.dto.CupomDTO;
import br.com.daulio.facilit.carrinho.exception.ApiException;
import br.com.daulio.facilit.carrinho.service.CupomService;
import io.swagger.annotations.Api;

@RestController()
@RequestMapping("/cupom")
@Api(value = "API Cupons de desconto", tags = { "Cupom" })
public class CupomController {
	
	@Autowired private CupomService service;
	
	@PostMapping(value = "/", consumes = { MediaType.APPLICATION_JSON_VALUE}, produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> salvarNovo(@RequestBody @Valid CupomDTO dto) 
			throws Exception {
		
		CupomDTO cupomDTO = service.salvar(dto);
		return new ResponseEntity<>(cupomDTO, HttpStatus.CREATED);
	}
	
	@PatchMapping(value = "/", consumes = { MediaType.APPLICATION_JSON_VALUE}, produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> alterarCupom(@RequestBody @Valid CupomDTO dto) 
			throws Exception {
		CupomDTO cupomDTO = service.alterar(dto);
		return new ResponseEntity<>(cupomDTO, HttpStatus.OK);
	}
	
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<CupomDTO>> getListarTodos(){
		List<CupomDTO> cupons = service.listar();
		return new ResponseEntity<>(cupons, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{codigo}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CupomDTO> getCupomPorCodigo(@PathVariable String codigo) throws ApiException {
		
		CupomDTO find = service.buscarPorCodigo(codigo);
		return new ResponseEntity<>(find, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{codigo}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void excluirCupom(@PathVariable String codigo) throws ApiException  {
		service.excluir(codigo);
	}

}
