package seedu.address.logic.parser;


import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Category;

import java.util.Locale;

public class CategoryParser {

    public static final Category defaultCategory = Category.NAME;

    public static Category parse(String keyword) throws ClassNotFoundException {
        if (keyword == null) {
            return defaultCategory;
        }
        keyword = keyword.toLowerCase();
        switch (keyword) {
        case "name":
        case "n":
            return Category.NAME;
        case "address":
        case "a":
            return Category.ADDRESS;
        case "email":
        case "e":
            return Category.EMAIL;
        case "phone":
        case "p":
            return Category.PHONE;
        default:
            throw new ClassNotFoundException();
        }
    }
}
