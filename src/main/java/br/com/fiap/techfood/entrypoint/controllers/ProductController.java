package br.com.fiap.techfood.entrypoint.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.techfood.core.domain.enums.CategoryEnum;
import br.com.fiap.techfood.core.usecase.ProductUseCase;
import br.com.fiap.techfood.entrypoint.dtos.ProductCreateDTO;
import br.com.fiap.techfood.entrypoint.dtos.ProductDTO;
import br.com.fiap.techfood.entrypoint.mappers.ProductMapper;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductUseCase productUseCase;

	@Autowired
	private ProductMapper productMapper;

	@PostMapping
	public ResponseEntity<ProductDTO> registerNewProduct(@RequestBody @Valid ProductCreateDTO productCreateDTO) {
		var product = productMapper.toProductDomain(productCreateDTO);
		var insertProduct = productUseCase.registerNewProduct(product);
		var responseBody = productMapper.domainToDto(insertProduct);
		return ResponseEntity.ok(responseBody);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductDTO> searchProductById(@PathVariable UUID id) {
		var product = productUseCase.searchProductById(id);
		var responseBody = productMapper.domainToDto(product);
		return ResponseEntity.ok(responseBody);
	}

	@GetMapping("/category")
	public ResponseEntity<List<ProductDTO>> searchProductByCategory(@RequestParam CategoryEnum name) {
		var productList = productUseCase.searchProductByCategory(name).stream().map(domain -> productMapper.domainToDto(domain)).toList();
		return ResponseEntity.ok().body(productList);
	}

	@GetMapping
	public ResponseEntity<List<ProductDTO>> findAll() {
		var productList = productUseCase.findAll().stream().map(domain -> productMapper.domainToDto(domain)).toList();
		return ResponseEntity.ok().body(productList);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
		productUseCase.deleteProduct(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	ResponseEntity<ProductDTO> updateProduct(@PathVariable UUID id, @RequestBody @Valid ProductCreateDTO productCreateDTO) {
		var product = productMapper.toProductDomain(productCreateDTO);
		var updatedProduct = productUseCase.updateProduct(id,product);
		var responseBody = productMapper.domainToDto(updatedProduct);
		return ResponseEntity.ok().body(responseBody);
	}
}
