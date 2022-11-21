package validate.isbn.validateISBN.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import validate.isbn.validateISBN.model.ValidationResult;
import validate.isbn.validateISBN.service.ValidateISBNService;

@RestController
@RequestMapping("/validate")
public class ValidateISBNController {
    private final ValidateISBNService validateISBNService;

    public ValidateISBNController(ValidateISBNService validateISBNService) {
        this.validateISBNService = validateISBNService;
    }

    /**
     * This method calls the service class to get the result of the 10 digit ISBN validation
     * @param rawISBN
     * @return
     */
    @GetMapping("/tendigit")
    public ResponseEntity<ValidationResult> validateTenDigitISBN(@RequestParam String rawISBN) {
        ValidationResult result = validateISBNService.validateTenDigitISBN(rawISBN);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * This method calls the service class to get the result of the 13 digit ISBN validation
     * @param rawISBN
     * @return
     */
    @GetMapping("/thirteendigit")
    public ResponseEntity<ValidationResult> validateThirteenDigitISBN(@RequestParam String rawISBN) {
        ValidationResult result = validateISBNService.validateThirteenDigitISBN(rawISBN);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
