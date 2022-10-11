package seedu.foodrem.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_ITEM_NAME = new Prefix("n/");
    public static final Prefix PREFIX_ITEM_QUANTITY = new Prefix("qty/");
    public static final Prefix PREFIX_ITEM_UNIT = new Prefix("u/");
    public static final Prefix PREFIX_ITEM_BOUGHT_DATE = new Prefix("bgt/");
    public static final Prefix PREFIX_ITEM_EXPIRY_DATE = new Prefix("exp/");

    /* Definitions for Sort Command options */
    public static final Prefix PREFIX_SORT_BY_NAME = new Prefix("n/");
    public static final Prefix PREFIX_SORT_BY_QTY = new Prefix("qty/");
    public static final Prefix PREFIX_SORT_BY_BOUGHT_DATE = new Prefix("bgt/");
    public static final Prefix PREFIX_SORT_BY_EXPIRY_DATE = new Prefix("exp/");
    public static final Prefix PREFIX_SORT_BY_UNIT = new Prefix("unit/");
}
