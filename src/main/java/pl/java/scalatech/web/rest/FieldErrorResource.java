package pl.java.scalatech.web.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FieldErrorResource {

	private String resource;
	
	private String field;
	
	private String code;
	
	private String message;
}