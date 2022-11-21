package validate.isbn.validateISBN.model;

import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ValidationResult implements Serializable {
    public Boolean valid;
    public String message;
}
