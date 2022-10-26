package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("-n");
    public static final Prefix PREFIX_OWNER_NAME = new Prefix("-owner");
    public static final Prefix PREFIX_PHONE = new Prefix("-ph");
    public static final Prefix PREFIX_EMAIL = new Prefix("-e");
    public static final Prefix PREFIX_ADDRESS = new Prefix("-a");
    public static final Prefix PREFIX_PRIORITY = new Prefix("-priority");
    public static final Prefix PREFIX_PRICE_RANGE = new Prefix("-r");
    public static final Prefix PREFIX_CHARACTERISTICS = new Prefix("-c");
    public static final Prefix PREFIX_PRICE = new Prefix("-price"); // temporary, should refactor all prefixes
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("-d");
    public static final Prefix PREFIX_MATCH_ALL = new Prefix("-all");
    public static final Prefix PREFIX_STRICT = new Prefix("-strict");
}
