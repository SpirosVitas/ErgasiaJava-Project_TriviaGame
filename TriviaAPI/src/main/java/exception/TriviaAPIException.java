package exception;

public class TriviaAPIException extends Exception {
	

	public TriviaAPIException(String message) {
		super(message);
	}
	public TriviaAPIException(String message, Throwable cause ) {
		super(message, cause);
	}

}
