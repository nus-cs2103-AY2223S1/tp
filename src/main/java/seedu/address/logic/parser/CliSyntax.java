package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_MONEY_OWED = new Prefix("owed/");
    public static final Prefix PREFIX_MONEY_PAID = new Prefix("paid/");
    public static final Prefix PREFIX_RATES_PER_CLASS = new Prefix("rate/");
    public static final Prefix PREFIX_ADDITIONAL_NOTES = new Prefix("nt/");
    public static final Prefix PREFIX_CLASS_DATE_TIME = new Prefix("dt/");

}
