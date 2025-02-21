package com.product.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="category")
public class Category {
    /** Identificador de la categoría */
	@Id
    private Integer category_id;

    /** Nombre de la categoría */
    private String category;

    /** Etiqueta de la categoría */
    private String tag;

    /** Estado de la categoría */
    private Integer status;

    /**
     * Constructor de Category.
     * @param category_id el ID de la categoría
     * @param category el nombre de la categoría
     * @param tag la etiqueta de la categoría
     * @param status el estado de la categoría
     */
    public Category(Integer category_id, String category, String tag, Integer status) {
        this.category_id = category_id;
        this.category = category;
        this.tag = tag;
        this.status = status;
    }

    /**
     * Constructor de Category, asigna status=1.
     * @param category_id el ID de la categoría
     * @param category el nombre de la categoría
     * @param tag la etiqueta de la categoría
     */
    public Category(Integer category_id, String category, String tag) {
        this.category_id = category_id;
        this.category = category;
        this.tag = tag;
        this.status = 1;
    }
    
    /**
     * Constructor de Category
     */
    public Category() {
    	
    }

    /**
     * Obtiene el ID de la categoría.
     * @return el ID de la categoría
     */
    public Integer getCategory_id() {
        return category_id;
    }

    /**
     * Establece el ID de la categoría.
     * @param category_id el ID de la categoría a establecer
     */
    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    /**
     * Obtiene el nombre de la categoría.
     * @return el nombre de la categoría
     */
    public String getCategory() {
        return category;
    }

    /**
     * Establece el nombre de la categoría.
     * @param category el nombre de la categoría a establecer
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Obtiene la etiqueta.
     * @return la etiqueta
     */
    public String getTag() {
        return tag;
    }

    /**
     * Establece la etiqueta.
     * @param tag la etiqueta a establecer
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * Obtiene el estado.
     * @return el estado
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Establece el estado.
     * @param status el estado a establecer
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "{"+category_id+","+category+","+tag+","+status+"}";
    }

    /**
     * Verifica si la categoria está activa.
     * @return true si su status es 1, false en otro caso.
     */
    public boolean estaActiva(){
        return this.status == 1;
    }

    @Override
    public boolean equals(Object obj){
        // Verifica si la categoria comparte el atributo id, category o tag.
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Category category = (Category) obj;
        return this.category_id == category.getCategory_id() ||
               this.category.equals(category.getCategory()) ||
               this.tag.equals(category.getTag());
    }
}