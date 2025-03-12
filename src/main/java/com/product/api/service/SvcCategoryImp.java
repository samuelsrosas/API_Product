package com.product.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.api.entity.Category;
import com.product.api.repository.RepoCategory;

/**
 * Clase que implementa la interfaz de servicio SvcCategory
 */
@Service
public class SvcCategoryImp implements SvcCategory{
	// Inyeccion de dependencias.
	@Autowired
	RepoCategory repo;
	
	/**
	 * Retornamos lo que devuelve el metodo getCategories del repositorio.
	 */
	@Override
	public List<Category> getCategories(){
		return repo.getCategories();
	}
}
