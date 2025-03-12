package com.product.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.api.entity.Category;
import com.product.api.service.SvcCategory;

@RestController
@RequestMapping("/category")
public class CtrlCategory{
	
	// Uso de la inyeccion de dependencias
	@Autowired
	SvcCategory svc;

	/**
	 * Mapeamos la solicitud HTTP GET.
	 * @return lo que devuelve el metodo getCategories del servicio
	 */
	@GetMapping
	public ResponseEntity<List<Category>> getCategories(){
		return svc.getCategories();
	}

}
