package seedu.phu.logic.parser;

import seedu.phu.logic.parser.exceptions.InvalidCategoryException;
import seedu.phu.model.internship.FindableCategory;

/**
 * A class to parse user input to FindableCategory instance.
 */
public class FindableCategoryParser {

    public static final String EXCEPTION_MESSAGE = "Category can only be one of the following:\n"
            + "company_name (or n), position (or p), "
            + "application_process(or pr), tags (or t), date (or d)";

    /**
     * Parses the string to a FindableCategory instance.
     *
     * @param string user input.
     * @return a FindableCategory instance.
     * @throws InvalidCategoryException if user input is invalid.
     */

    public static FindableCategory parse(String string) throws InvalidCategoryException {
        switch (string.toLowerCase()) {
        case "company_name":
        case "n":
            return FindableCategory.COMPANY_NAME;
        case "application_process":
        case "pr":
            return FindableCategory.APPLICATION_PROCESS;
        case "position":
        case "p":
            return FindableCategory.POSITION;
        case "date":
        case "d":
            return FindableCategory.DATE;
        case "tags":
        case "t":
            return FindableCategory.TAGS;
        default:
            throw new InvalidCategoryException(EXCEPTION_MESSAGE);
        }
    }
}
