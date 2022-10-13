package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("-n");
    public static final Prefix PREFIX_PHONE = new Prefix("-ph");
    public static final Prefix PREFIX_EMAIL = new Prefix("-e");
    public static final Prefix PREFIX_ADDRESS = new Prefix("-a");
    public static final Prefix PREFIX_TAG = new Prefix("-t");
    public static final Prefix PREFIX_PRICE_RANGE = new Prefix("-r");
    public static final Prefix PREFIX_CHARACTERISTICS = new Prefix("-c");
    public static final Prefix PREFIX_PROPERTIES = new Prefix("-pr");
    public static final Prefix PREFIX_PRICE = new Prefix("-price"); // temporary, should refactor all prefixes
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("-d");
    public static final Prefix PREFIX_PROPERTY = new Prefix("-prop");
    public static final Prefix PREFIX_BUYER = new Prefix("-buyer");
    public static final Prefix PREFIX_SELLER = new Prefix("-seller");
}
