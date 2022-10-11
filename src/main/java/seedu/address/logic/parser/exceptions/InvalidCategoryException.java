package seedu.address.logic.parser.exceptions;

public class InvalidCategoryException extends ParseException {
    private static String DEFAULT_MESSAGE = "Category can only be one of the following:\n"
                                            + "company_name (or n), position (or p), "
                                            + "application_process(or pr), tags (or t), date (or d)";


    public InvalidCategoryException() {
        super(DEFAULT_MESSAGE);
    }
}
