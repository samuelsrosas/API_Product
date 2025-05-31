package com.product.api.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.product.api.dto.in.DtoProductImageIn;
import com.product.api.entity.ProductImage;
import com.product.api.repository.RepoProductImage;
import com.product.common.dto.ApiResponse;
import com.product.exception.ApiException;
import com.product.exception.DBAccessException;

@Service
public class SvcProductImageImp implements SvcProductImage {
	
	@Autowired
	RepoProductImage repo;
	
	@Value("${app.upload.dir}")
	private String uploadDir;

	@Override
	public ResponseEntity<ApiResponse> createProductImage(DtoProductImageIn in) {
		try {
			// Eliminar el prefijo "data:image/png;base64," si existe
			if (in.getImage().startsWith("data:image")) {
			int commaIndex = in.getImage().indexOf(",");
				if (commaIndex != -1) {
					in.setImage(in.getImage().substring(commaIndex + 1));
				}
			}

			
			// Decodifica la cadena Base64 a bytes
			byte[] imageBytes = Base64.getDecoder().decode(in.getImage());

			// Genera un nombre único para la imagen (se asume extensión PNG)
			String fileName = UUID.randomUUID().toString() + ".png";

			// Construye la ruta completa donde se guardará la imagen
			Path imagePath = Paths.get(uploadDir, "img", "product", fileName);
		    
			// Asegurarse de que el directorio exista
			Files.createDirectories(imagePath.getParent());

			// Escribir el archivo en el sistema de archivos
			Files.write(imagePath, imageBytes);
			 
			// Crear la entidad ProductImage y guardar la URL en la base de datos
			ProductImage productImage = new ProductImage();
			productImage.setProduct_id(in.getProduct_id());
			productImage.setImage("img/product/" + fileName);
			productImage.setStatus(1); 

			// Guardar la ruta de la imagen
			repo.save(productImage);
			
			return new ResponseEntity<>(new ApiResponse("La imagen del product ha sido registrada"), HttpStatus.CREATED);
		}catch (DataAccessException e) {
		    throw new DBAccessException(e);
		}catch (IOException e) {
			throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar el archivo");
		}
	}
	
	private void validateProductImageId(Integer id) {
		try {
			if(repo.findById(id).isEmpty()) {
				throw new ApiException(HttpStatus.NOT_FOUND, "El id de la imagen no existe");
			}
		}catch (DataAccessException e) {
			throw new DBAccessException(e);
		}
	}

	@Override
	public ResponseEntity<ApiResponse> deleteProductImage(Integer id) {
		try {
			validateProductImageId(id);
			repo.deleteById(id);
			return new ResponseEntity<>(new ApiResponse("La imagen del producto ha sido eliminada"), HttpStatus.OK);
		}catch (DataAccessException e) {
			throw new DBAccessException(e);
		}
		
	}

}
