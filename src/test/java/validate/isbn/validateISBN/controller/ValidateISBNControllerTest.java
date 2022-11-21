package validate.isbn.validateISBN.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import validate.isbn.validateISBN.model.ValidationResult;
import validate.isbn.validateISBN.service.ValidateISBNService;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ValidateISBNControllerTest {

    @InjectMocks
    private ValidateISBNController validateISBNController;

    @Mock
    private ValidateISBNService validateISBNService;

    private ValidationResult validationResult;

    @BeforeEach
    public void setup() {
        validationResult = new ValidationResult(true, "Valid ISBN");
    }

    @Test
    public void validateTenDigitISBNTest() {
        when(validateISBNService.validateTenDigitISBN("placeholder")).thenReturn(validationResult);
        ResponseEntity<ValidationResult> result = validateISBNController.validateTenDigitISBN("placeholder");
    }

    @Test
    public void validateThirteenDigitISBNTest() {
        when(validateISBNService.validateThirteenDigitISBN("placeholder")).thenReturn(validationResult);
        ResponseEntity<ValidationResult> result = validateISBNController.validateThirteenDigitISBN("placeholder");
    }


}
