package com.company.rewards.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ApplicationException extends RuntimeException{

	private static final long serialVersionUID = 9137810309798535355L;
	
	private String erroCode;
	private String errorMsg;
}
