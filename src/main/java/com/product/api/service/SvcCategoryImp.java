package com.product.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.product.api.dto.DtoCategoryIn;
import com.product.api.entity.Category;
import com.product.api.repository.RepoCategory;
import com.product.common.dto.ApiResponse;
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
		} catch (DataAccessException e) {
			System.out.print(e.getLocalizedMessage());
			throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Error");
		}
	}

	@Override
	public ResponseEntity<List<Category>> getActiveCategories() {
		try {
			return new ResponseEntity<>(repo.getActiveCategories(), HttpStatus.OK);
		} catch (DataAccessException e) {
			throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Error");
		}
		//return null;
	}

	@Override
	public ResponseEntity<ApiResponse> createCategory(DtoCategoryIn in) {
		try {
			repo.createCategory(in.getCategory(), in.getTag());
			return new ResponseEntity<>(new ApiResponse("La categoria ha sido registrada"), HttpStatus.CREATED);
		} catch(DataAccessException e){
			if (e.getLocalizedMessage().contains("ux_category"))
				throw new ApiException(HttpStatus.CONFLICT, "El nombre de la categoria ya esta registrado");
			if (e.getLocalizedMessage().contains("ux_tag"))
				throw new ApiException(HttpStatus.CONFLICT, "El tag de la categoria ya esta registrado");
			throw new DBAccessException(e);
		}
	}

	@Override
	public ResponseEntity<ApiResponse> updateCategory(DtoCategoryIn in, Integer id) {
		try {
			validateCategoryId(id);
			repo.updateCategory(id, in.getCategory(), in.getTag());
			return new ResponseEntity<>(new ApiResponse("La categoria ha sido actualizada"), HttpStatus.OK);
		} catch(DataAccessException e){
			if (e.getLocalizedMessage().contains("ux_category"))
				throw new ApiException(HttpStatus.CONFLICT, "El nombre de la categoria ya esta registrado");
			if (e.getLocalizedMessage().contains("ux_tag"))
				throw new ApiException(HttpStatus.CONFLICT, "El tag de la categoria ya esta registrado");
			throw new DBAccessException(e);
		}
	}

	@Override
	public ResponseEntity<ApiResponse> enableCategory(Integer id) {
		try {
			validateCategoryId(id);
			repo.updateCategoryStatus(id, 1);
			return new ResponseEntity<>(new ApiResponse("La categoria ha sido activada"), HttpStatus.OK);
		} catch(DataAccessException e){
			throw new DBAccessException(e);
		}
	}

	@Override
	public ResponseEntity<ApiResponse> disableCategory(Integer id) {
		try {
			validateCategoryId(id);
			repo.updateCategoryStatus(id, 0);
			return new ResponseEntity<>(new ApiResponse("La categoria ha sido desactivada"), HttpStatus.OK);
		} catch(DataAccessException e){
			throw new DBAccessException(e);
		}
	}
	
	private void validateCategoryId(Integer id) {
		try {
			if (repo.getCategory(id) == null)
				throw new ApiException(HttpStatus.NOT_FOUND, "El id de la categoria no existe");
		} catch (DataAccessException e) {
			throw new DBAccessException(e);
		}
	}
}
