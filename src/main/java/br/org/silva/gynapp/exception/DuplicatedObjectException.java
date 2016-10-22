package br.org.silva.gynapp.exception;

public class DuplicatedObjectException extends BusinessException{
	@Override
	public String getMessage() {
		return "Registro duplicado !";
	}
}
