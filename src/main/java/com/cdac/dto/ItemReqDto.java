package com.cdac.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ItemReqDto {

	 @NotBlank
	    @Size(min = 2, max = 255)
	    private String itemName;

	    @NotNull
	    @Min(1)
	    private Integer quantity;
}
