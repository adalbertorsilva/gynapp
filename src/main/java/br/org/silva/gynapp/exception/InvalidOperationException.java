package br.org.silva.gynapp.exception;

public class InvalidOperationException extends RuntimeException {
	
	@Override
	public String getMessage() {
		return "Operação Inválida";
	}

}
