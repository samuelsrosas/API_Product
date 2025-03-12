package com.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CtrlProduct {

	@GetMapping
	public List<Category> getCategories(){
		// Lista con las categorias
		List<Category> categories = new ArrayList<>();
		
		categories.add(new Category(1, "Lentes", "Lts"));
		categories.add(new Category(2, "Relojes", "Rljs"));
		return categories;
	}

}
