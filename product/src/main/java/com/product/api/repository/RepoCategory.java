package com.product.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import com.product.api.entity.Category;

/*
 * Indicamos que a clase es un repositorio
 * Para encapsular el comportamiento de almacenamiento
 * recuperacion y busqueda que emula una coleccion de objetos
 * 
 * Category - entidad de mapeo de datos
 * Integer 	- tipo de dato de la llave primaria
 */
@Repository
public interface RepoCategory  extends JpaRepository<Category, Integer>{
	/*
	 * Declaramos la firma del metodo getCategories que consulte las categorias registradas
	 * 
	 * value 	   - especifica la sentencia del query
	 * nativeQuery - configura si la consulta es nativa
	 */
	@Query(value = "SELECT * FROM category ORDER BY category", nativeQuery = true)
	List<Category> getCategories();
}
