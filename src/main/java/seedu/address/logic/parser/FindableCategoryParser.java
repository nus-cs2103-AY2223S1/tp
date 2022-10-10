package seedu.address.logic.parser;

import seedu.address.logic.parser.exceptions.InvalidCategoryException;
import seedu.address.model.person.FindableCategory;

public class FindableCategoryParser {
    public final static FindableCategory parse(String string) throws InvalidCategoryException {
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
