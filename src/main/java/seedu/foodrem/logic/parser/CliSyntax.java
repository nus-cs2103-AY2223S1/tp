package seedu.foodrem.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_ITEM_QUANTITY = new Prefix("qty/");
    public static final Prefix PREFIX_ITEM_UNIT = new Prefix("u/");
    public static final Prefix PREFIX_ITEM_BOUGHT_DATE = new Prefix("bgt/");
    public static final Prefix PREFIX_ITEM_EXPIRY_DATE = new Prefix("exp/");
    public static final Prefix PREFIX_TAG_NAME = new Prefix("n/");
    public static final Prefix PREFIX_ID = new Prefix("id/");
}
