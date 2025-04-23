package com.product.api.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.product.api.dto.DtoCategoryIn;
import com.product.api.entity.Category;
import com.product.common.dto.ApiResponse;

/**
 * Interfaz que define los metodos que ejecuta cada endpoint.
 * Procesa las solicitudes de los usuarios a la API.
 */

public interface SvcCategory {
	/**
	 * Muestra la lista de categorias registradas.
	 * @return lista de categorias registradas.
	 */
	public ResponseEntity<List<Category>> getCategories();
	
	/**
	 * Muestra la lista de categorias activas registradas.
	 * @return lista de categorias activas.
	 */
	public ResponseEntity<List<Category>> getActiveCategories();
	
	/**
	 * Registra una nueva categoria.
	 * @param in la categoria a registrar.
	 */
	public ResponseEntity<ApiResponse> createCategory(DtoCategoryIn in);
	
	/**
	 * Modifica una categoria registrada.
	 * @param in los datos a modificar.
	 * @param id el id de la categoria a modificar.
	 */
	public ResponseEntity<ApiResponse> updateCategory(DtoCategoryIn in, Integer id);
	
	/**
	 * Modifica el status a 1 de una categoria
	 * @param id el id de la categoria a activar.
	 */
	public ResponseEntity<ApiResponse> enableCategory(Integer id);
	
	/**
	 * Modifica el status a 0 de una categoria
	 * @param id el id de la categoria a desactivar.
	 */
	public ResponseEntity<ApiResponse> disableCategory(Integer id);
}
