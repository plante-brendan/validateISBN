package validate.isbn.validateISBN.model;
import java.io.Serializable;

public class ValidationResult implements Serializable {
    public Boolean valid;
    public String message;

    public ValidationResult(boolean valid, String message) {
        this.valid = valid;
        this.message = message;
    }

    public Boolean getValid() {
        return valid;
    }

    public String getMessage() {
        return message;
    }
}
