package com.product.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.api.dto.DtoCategoryIn;
import com.product.api.entity.Category;
import com.product.api.service.SvcCategory;
import com.product.common.dto.ApiResponse;
import com.product.exception.ApiException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/category")
@Tag(name = "Category", description = "Catálogo de categorias")
public class CtrlCategory{
	
	// Uso de la inyeccion de dependencias
	@Autowired
	SvcCategory svc;

	/**
	 * Mapeamos la solicitud HTTP GET.
	 * @return lo que devuelve el metodo getCategories del servicio
	 */
	@GetMapping
	@Operation(summary = "Consultar categorias", description = "Retorna todas las categorias registradas en el sistema")
	public ResponseEntity<List<Category>> getCategories(){
		return svc.getCategories();
	}
	
	@GetMapping("/active")
	@Operation(summary = "Consultar categorias activas", description = "Retorna todas las categorias activas registradas en el sistema")
	public ResponseEntity<List<Category>> getActiveCategories(){
		return svc.getActiveCategories();
	}
	
	@PostMapping
	@Operation(summary = "Crear categorias", description = "Crea una nueva categoria en el sistema")
	public ResponseEntity<ApiResponse> createCategory(@Valid @RequestBody DtoCategoryIn in, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getFieldError().getDefaultMessage());

		return svc.createCategory(in);
	}
	
	@PutMapping("/{id}")
	@Operation(summary = "Actualizar categorias", description = "Actualiza la informacion de una categoria")
	public ResponseEntity<ApiResponse> updateCategory(@PathVariable Integer id, @Valid @RequestBody DtoCategoryIn in,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getFieldError().getDefaultMessage());

		return svc.updateCategory(in, id);
	}
	
	@PatchMapping("/{id}/enable")
	@Operation(summary = "Activar categorias", description = "Cambia el status de una categoria a activo")
	public ResponseEntity<ApiResponse> enableCategory(@PathVariable Integer id) {
		return svc.enableCategory(id);
	}
	
	@PatchMapping("/{id}/disable")
	@Operation(summary = "Desactivar categorias", description = "Cambia el status de una categoria a inactivo")
	public ResponseEntity<ApiResponse> disableRegion(@PathVariable Integer id) {
		return svc.disableCategory(id);
	}

}
