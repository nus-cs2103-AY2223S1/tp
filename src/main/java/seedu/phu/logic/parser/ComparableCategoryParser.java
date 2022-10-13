package seedu.phu.logic.parser;

import static seedu.phu.logic.parser.CliSyntax.PREFIX_CATEGORY;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.phu.model.internship.ComparableCategory;


/**
 * Parses input arguments
 */
public class ComparableCategoryParser {


    private static final String CATEGORY_PATTERN = PREFIX_CATEGORY.getPrefix() + "*";
    /**
     * Parses the given {@code String} of arguments in the context of categories available
     * and returns a Category
     * @throws ClassNotFoundException if no such category is found
     */
    public static ComparableCategory parse(String keyword) throws ClassNotFoundException {
        keyword = keyword.toLowerCase().trim();
        Pattern p = Pattern.compile(PREFIX_CATEGORY.getPrefix() + "(.+)");
        Matcher m = p.matcher(keyword);
        if (m.matches()) {
            keyword = keyword.replace(PREFIX_CATEGORY.getPrefix(), "");
            switch (keyword) {
            case "name":
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
                throw new ClassNotFoundException();
            }
        }
        throw new ClassNotFoundException();
    }
}
