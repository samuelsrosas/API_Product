package com.product.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;

public class DtoCategoryIn {
	
	/** Nombre de la categoría */
	@JsonProperty("category")
	@NotNull(message="La categoria es obligatoria")
	private String category;
	
	/** Etiqueta de la categoría */
	@JsonProperty("tag")
	@NotNull(message="El tag es obligatorio")
	private String tag;

	public String getCategory() {
		return category;
	}

	public String getTag() {
		return tag;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
	
}
