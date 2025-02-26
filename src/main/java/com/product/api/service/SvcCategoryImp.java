package com.product.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.product.api.entity.Category;
import com.product.api.repository.RepoCategory;
import com.product.exception.*;

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
	public ResponseEntity<List<Category>> getCategories(){
		try {
			return new ResponseEntity<>(repo.getCategories(), HttpStatus.OK);
		} catch (DataAccesException e) {
			System.out.print(e.getLocalizedMessage);
			throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Error");
		}
	}
}
