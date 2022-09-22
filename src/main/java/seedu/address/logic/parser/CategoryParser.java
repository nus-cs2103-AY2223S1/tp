package seedu.address.logic.parser;


import seedu.address.model.person.Category;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;

/**
 * Parses input arguments
 */
public class CategoryParser {


    private static final String CATEGORY_PATTERN = PREFIX_CATEGORY.getPrefix() + "*";
    /**
     * Parses the given {@code String} of arguments in the context of categories available
     * and returns a Category
     * @throws ClassNotFoundException if no such category is found
     */
    public static Category parse(String keyword) throws ClassNotFoundException {
        keyword = keyword.toLowerCase().trim();
        Pattern p = Pattern.compile(PREFIX_CATEGORY.getPrefix() + "(.+)");
        Matcher m = p.matcher(keyword);
        if (m.matches()) {
            keyword = keyword.replace(PREFIX_CATEGORY.getPrefix(), "");
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
            case "tag":
            case "t":
                return Category.TAG;
            default:
                throw new ClassNotFoundException();
            }
        }
        throw new ClassNotFoundException();
    }
}
