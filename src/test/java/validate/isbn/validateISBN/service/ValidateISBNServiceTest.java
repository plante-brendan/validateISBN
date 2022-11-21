package validate.isbn.validateISBN.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import validate.isbn.validateISBN.model.ValidationResult;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ValidateISBNServiceTest {
    private static final String CHECK_DIGIT = "The check digit provided is incorrect";
    private static final String FORMATTING_10 = "This ISBN violates one of the formatting rules of the 10 digit ISBN";
    private static final String FORMATTING_13 = "This ISBN violates one of the formatting rules of the 13 digit ISBN";
    @InjectMocks
    ValidateISBNService validateISBNService;

    @Test
    public void validateTenDigitISBNTest() {
        ValidationResult result = validateISBNService.validateTenDigitISBN("007462542X");
        assertTrue(result.getValid());

        result = validateISBNService.validateTenDigitISBN("0 0746 2542 X");
        assertTrue(result.getValid());

        result = validateISBNService.validateTenDigitISBN("ISBN 007462542X");
        assertTrue(result.getValid());

        result = validateISBNService.validateTenDigitISBN("ISBN: 007462542X");
        assertTrue(result.getValid());

        result = validateISBNService.validateTenDigitISBN("ISBN-10 007462542X");
        assertTrue(result.getValid());

        result = validateISBNService.validateTenDigitISBN("ISBN-10: 007462542X");
        assertTrue(result.getValid());

        result = validateISBNService.validateTenDigitISBN("0-0746-2542-X");
        assertTrue(result.getValid());

        result = validateISBNService.validateTenDigitISBN("007462-54-2-X");
        assertFalse(result.getValid());
        assertEquals(result.getMessage(), FORMATTING_10);

        result = validateISBNService.validateTenDigitISBN("0-0-7462542-X");
        assertFalse(result.getValid());
        assertEquals(result.getMessage(), FORMATTING_10);

        result = validateISBNService.validateTenDigitISBN("00-746-254-2X");
        assertFalse(result.getValid());
        assertEquals(result.getMessage(), FORMATTING_10);

        result = validateISBNService.validateTenDigitISBN("00-746-254-2X");
        assertFalse(result.getValid());
        assertEquals(result.getMessage(), FORMATTING_10);

        result = validateISBNService.validateTenDigitISBN("007462542");
        assertFalse(result.getValid());
        assertEquals(result.getMessage(), FORMATTING_10);

        result = validateISBNService.validateTenDigitISBN("007 462542 X");
        assertFalse(result.getValid());
        assertEquals(result.getMessage(), FORMATTING_10);

        result = validateISBNService.validateTenDigitISBN("0074625429");
        assertFalse(result.getValid());
        assertEquals(result.getMessage(), CHECK_DIGIT);
    }

    @Test
    public void validateThirteenDigitISBNTest() {
        ValidationResult result = validateISBNService.validateThirteenDigitISBN("9781861972712");
        assertTrue(result.getValid());

        result = validateISBNService.validateThirteenDigitISBN("978 1 861 97271 2");
        assertTrue(result.getValid());

        result = validateISBNService.validateThirteenDigitISBN("ISBN 978 1 861 97271 2");
        assertTrue(result.getValid());

        result = validateISBNService.validateThirteenDigitISBN("ISBN: 978-1-861-97271-2");
        assertTrue(result.getValid());

        result = validateISBNService.validateThirteenDigitISBN("ISBN-13 978-1-861-97271-2");
        assertTrue(result.getValid());

        result = validateISBNService.validateThirteenDigitISBN("ISBN-13: 978 1 861 97271 2");
        assertTrue(result.getValid());

        result = validateISBNService.validateThirteenDigitISBN("97818619727123");
        assertFalse(result.getValid());
        assertEquals(result.getMessage(), FORMATTING_13);

        result = validateISBNService.validateThirteenDigitISBN("978186197271");
        assertFalse(result.getValid());
        assertEquals(result.getMessage(), FORMATTING_13);

        result = validateISBNService.validateThirteenDigitISBN("9781861972719");
        assertFalse(result.getValid());
        assertEquals(result.getMessage(), CHECK_DIGIT);

        result = validateISBNService.validateThirteenDigitISBN("976 1 861 97271 2");
        assertFalse(result.getValid());
        assertEquals(result.getMessage(), FORMATTING_13);

        result = validateISBNService.validateThirteenDigitISBN("978 186197 27 1 2");
        assertFalse(result.getValid());
        assertEquals(result.getMessage(), FORMATTING_13);

        result = validateISBNService.validateThirteenDigitISBN("97 8 18619727 1 2");
        assertFalse(result.getValid());
        assertEquals(result.getMessage(), FORMATTING_13);

        result = validateISBNService.validateThirteenDigitISBN("978 1 8 6197271 2");
        assertFalse(result.getValid());
        assertEquals(result.getMessage(), FORMATTING_13);

        result = validateISBNService.validateThirteenDigitISBN("978 1 861 9727 12");
        assertFalse(result.getValid());
        assertEquals(result.getMessage(), FORMATTING_13);

        result = validateISBNService.validateThirteenDigitISBN("978 1861 97271 2");
        assertFalse(result.getValid());
        assertEquals(result.getMessage(), FORMATTING_13);
    }
}
