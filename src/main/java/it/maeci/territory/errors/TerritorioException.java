package it.maeci.territory.errors;


public abstract class TerritorioException extends Exception {

    private static final long serialVersionUID = -6045118561830401336L;

    protected TerritorioException() {
        super();
    }

    protected TerritorioException(String message, Throwable cause) {
        super(message, cause);
    }

    protected TerritorioException(String message) {
        super(message);
    }

    protected TerritorioException(Throwable cause) {
        super(cause);
    }

    /**
     * Retrieves the specific error associated with this exception.
     *
     * @return the error of type TerritorioError that provides information
     *         about the nature of the exception.
     */
    public abstract TerritorioError getError();



}
