package seedu.address.logic.parser;


import seedu.address.model.person.Category;

/**
 * Parses input arguments
 */
public class CategoryParser {

    /**
     * Parses the given {@code String} of arguments in the context of categories available
     * and returns a Category
     * @throws ClassNotFoundException if no such category is found
     */
    public static Category parse(String keyword) throws ClassNotFoundException {
        keyword = keyword.toLowerCase().trim();
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
