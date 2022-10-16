package seedu.phu.logic.parser;

import seedu.phu.logic.parser.exceptions.InvalidCategoryException;
import seedu.phu.model.internship.ComparableCategory;


/**
 * Parses input arguments
 */
public class ComparableCategoryParser {

    public static final String EXCEPTION_MESSAGE = "Category can only be one of the following:\n"
            + "company_name (or n), position (or p), "
            + "application_process(or pr), date (or d)";

    /**
     * Parses the given {@code String} of arguments in the context of categories available
     * and returns a Category
     * @throws InvalidCategoryException if no such category is found
     */

    public static ComparableCategory parse(String keyword) throws InvalidCategoryException {
        switch (keyword.toLowerCase()) {
        case "company_name":
        case "n":
            return ComparableCategory.NAME;
        case "application_process":
        case "pr":
            return ComparableCategory.APPLICATION_PROCESS;
        case "position":
        case "p":
            return ComparableCategory.POSITION;
        case "date":
        case "d":
            return ComparableCategory.DATE;
        default:
            throw new InvalidCategoryException(EXCEPTION_MESSAGE);
        }
    }
}
