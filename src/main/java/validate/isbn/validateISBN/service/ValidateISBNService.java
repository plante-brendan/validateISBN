package validate.isbn.validateISBN.service;

import org.springframework.stereotype.Service;
import validate.isbn.validateISBN.model.ValidationResult;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ValidateISBNService {
    /**
     * This method does the formatting validation for 10 digit ISBN using regex.
     * It then takes trimmed isbn and performs the check digit calculation
     * @param rawISBN
     * @return
     */
    public ValidationResult validateTenDigitISBN(String rawISBN) {
        //The regex here first checks for acceptable prefixes: ISBN, ISBN:, ISBN-10, or ISBN-10:
        //Next, it is looking for 0-9 plus X ten times
        //Next, it specifies there can be exactly three spaces or dashes total excluding the space after the prefix
        //Next, it specifies that with dashes or spaces there will be 13 digits excluding the prefix
        //Finally, it specifies the section requirements:
        //0-9 1 to 5 times for the first section
        //0-9 1 to 7 times for the second section
        //0-9 1 to 6 times for the third section
        //0-9 plus X 1 time for the last section
        String regex = "^(?:ISBN(?:-10)?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}$)[0-9]{1,5}[- ]?[0-9]{1,7}[- ]?[0-9]{1,6}[- ]?[0-9X]$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(rawISBN);
        if(matcher.matches() == false) {
            return new ValidationResult(false, "This ISBN violates one of the formatting rules of the 10 digit ISBN");
        }

        //This call transforms the valid ISBN into a more usable 10 character array
        String isbn = transformISBN(rawISBN);
        char[] isbnArray = isbn.toCharArray();
        int counter = 10;
        int totalValue = 0;

        //This block performs the calculation to get the total value of the ISBN.
        //X is the only possible non-numeric character here, so it is treated as 10.
        for(char isbnDigit : isbnArray) {
            Integer value = null;
            if (isbnDigit != 'X') {
                value = Character.getNumericValue(isbnDigit);
            }
            else {
                value = 10;
            }

            totalValue += counter * value;
            counter --;
        }

        //This block checks if the total ISBN value mod 11 is 0. If so it is valid.
        if(totalValue % 11 == 0) {

            return new ValidationResult(true, "Valid ISBN");
        }
        else {
            return new ValidationResult(false, "The check digit provided is incorrect");
        }
    }

    /**
     * This method does the formatting validation for 13 digit ISBN using regex.
     * It then takes trimmed isbn and performs the check digit calculation
     * @param rawISBN
     * @return
     */
    public ValidationResult validateThirteenDigitISBN(String rawISBN) {
        //The regex here first checks for acceptable prefixes: ISBN, ISBN:, ISBN-13, or ISBN-13:
        //Next, it is looking for 0-9 thirteen times
        //Next, it specifies there can be exactly four spaces or dashes total excluding the space after the prefix
        //Next, it specifies that with dashes or spaces there will be 17 digits excluding the prefix
        //Finally, it specifies the section requirements:
        //The first section must be 978 or 979
        //0-9 1 to 5 times for the second section
        //0-9 1 to 7 times for the third section
        //0-9 1 to 6 times for the fourth section
        //0-9 1 time for the last section
        String regex = "^(?:ISBN(?:-13)?:? )?(?=[0-9]{13}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)97[89][- ]?[0-9]{1,5}[- ]?[0-9]{1,7}[- ]?[0-9]{1,6}[- ]?[0-9]$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(rawISBN);
        if(matcher.matches() == false) {
            return new ValidationResult(false, "This ISBN violates one of the formatting rules of the 13 digit ISBN");
        }

        //This call transforms the valid ISBN into a more usable 13 character array
        String isbn = transformISBN(rawISBN);
        char[] isbnArray = isbn.toCharArray();
        int totalValue = 0;

        //Going to less than length - 1 to leave out last digit
        for(int i = 0; i < isbnArray.length - 1; i++) {
            int value = Character.getNumericValue(isbnArray[i]);
            if(i % 2 == 0) {
                totalValue += value * 1;
            }
            else {
                totalValue += value * 3;
            }
        }

        //Comparing the expected check digit to the actual to get result
        int checkDigitExpected = totalValue % 10;
        int checkDigitActual = 10 - Character.getNumericValue(isbnArray[isbnArray.length - 1]);

        if(checkDigitExpected == checkDigitActual) {
            return new ValidationResult(true, "Valid ISBN");
        }
        else {
            return new ValidationResult(false, "The check digit provided is incorrect");
        }
    }

    /**
     * This method takes in an unformatted ISBN and trims the prefix, colon, spaces, and dashes
     * This makes performing the check digit calculation far easier
     * @param rawISBN
     * @return
     */
    private String transformISBN(String rawISBN) {
        if(rawISBN.startsWith("ISBN-10") || rawISBN.startsWith("ISBN-13")) {
            rawISBN = rawISBN.substring(7);
        }
        else if(rawISBN.startsWith("ISBN")) {
            rawISBN = rawISBN.substring(4);
        }
        if(rawISBN.contains(":")) {
            rawISBN = rawISBN.replaceAll(":", "");
        }
        if(rawISBN.contains(" ")) {
            rawISBN = rawISBN.replaceAll(" ", "");
        }
        if(rawISBN.contains("-")) {
            rawISBN = rawISBN.replaceAll("-", "");
        }

        return rawISBN;
    }
}
