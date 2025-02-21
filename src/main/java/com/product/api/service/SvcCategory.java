package com.product.api.service;

import java.util.List;

import com.product.api.entity.Category;

/**
 * Interfaz que define los metodos que ejecuta cada endpoint.
 * Procesa las solicitudes de los usuarios a la API.
 */

public interface SvcCategory {
	/**
	 * Muestra la lista de categorias registradas.
	 * @return lista de categorias registradas.
	 */
	public List<Category> getCategories();
}
