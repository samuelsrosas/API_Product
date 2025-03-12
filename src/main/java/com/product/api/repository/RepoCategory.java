package com.product.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import com.product.api.entity.Category;

import jakarta.transaction.Transactional;

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
	
	@Query(value = "SELECT * FROM category WHERE status = 1 ORDER BY category", nativeQuery = true)
	List<Category> getActiveCategories();
	
	@Query(value = "SELECT * FROM category WHERE category_id = :category_id", nativeQuery = true)
	Category getCategory(@Param("category_id") Integer category_id);
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO category (category, tag, status) VALUES (:categroy, :tag, 1)", nativeQuery = true)
	void createCategory(@Param("category") String category, @Param("tag") String tag);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE category SET category = :category, tag = :tag WHERE category_id = :category_id ", nativeQuery = true)
	void updateCategory(@Param("category_id") Integer category_id ,@Param("category") String category, @Param("tag") String tag);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE category SET status = :status WHERE category_id = :category_id ", nativeQuery = true)
	void updateCategoryStatus(@Param("category_id") Integer category_id, @Param("status") Integer status);
	
	
}
