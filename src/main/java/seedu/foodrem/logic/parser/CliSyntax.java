package seedu.foodrem.logic.parser;

import java.util.regex.Pattern;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {
    /* Regex to match any of the prefixes */
    public static final Pattern PREFIX_REGEX = Pattern.compile("\\s+"
            + "(n|qty|u|bgt|exp|p|r)" + "/");

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_ITEM_QUANTITY = new Prefix("qty/");
    public static final Prefix PREFIX_ITEM_UNIT = new Prefix("u/");
    public static final Prefix PREFIX_ITEM_BOUGHT_DATE = new Prefix("bgt/");
    public static final Prefix PREFIX_ITEM_EXPIRY_DATE = new Prefix("exp/");
    public static final Prefix PREFIX_ITEM_PRICE = new Prefix("p/");
    public static final Prefix PREFIX_ITEM_REMARKS = new Prefix("r/");
}
