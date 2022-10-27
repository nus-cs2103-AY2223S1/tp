package seedu.condonery.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_PRICE = new Prefix("p/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_LOWER = new Prefix("l/");
    public static final Prefix PREFIX_UPPER = new Prefix("u/");
    public static final Prefix PREFIX_IMAGE_UPLOAD = new Prefix("-i");
}
