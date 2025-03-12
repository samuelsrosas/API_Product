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
	public ResponseEntity<List<Category>> getActiveCategories();
	public ResponseEntity<ApiResponse> createCategory(DtoCategoryIn in);
	public ResponseEntity<ApiResponse> updateCategory(DtoCategoryIn in, Integer id);
	public ResponseEntity<ApiResponse> enableCategory(Integer id);
	public ResponseEntity<ApiResponse> disableCategory(Integer id);
}
