package br.com.fiap.store.exception;

public class DBException extends Exception {
	public DBException() { super(); }
	
	public DBException(String message, Throwable cause, boolean enableSupression, boolean WritableStackTrace) {
		super(message, cause, enableSupression, WritableStackTrace);
	}
	
	public DBException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public DBException(String message) {
		super(message);
	}
	
	public DBException(Throwable cause) {
		super(cause);
	}
}