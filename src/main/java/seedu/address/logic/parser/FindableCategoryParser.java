package seedu.address.logic.parser;

import seedu.address.logic.parser.exceptions.InvalidCategoryException;
import seedu.address.model.person.FindableCategory;

/**
 * A class to parse user input to FindableCategory instance.
 */
public class FindableCategoryParser {
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
            throw new InvalidCategoryException();
        }
    }
}
