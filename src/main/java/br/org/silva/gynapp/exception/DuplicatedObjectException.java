package br.org.silva.gynapp.exception;

public class DuplicatedObjectException extends Exception{
	@Override
	public String getMessage() {
		return "Registro duplicado !";
	}
}
