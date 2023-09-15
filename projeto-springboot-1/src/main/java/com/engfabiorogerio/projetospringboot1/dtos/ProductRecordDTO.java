package com.engfabiorogerio.projetospringboot1.dtos;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRecordDTO(@NotBlank String name,@NotNull BigDecimal value) {
	
	

}
