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
    public static final Prefix PREFIX_RISKTAG = new Prefix("r/");
    public static final Prefix PREFIX_PLANTAG = new Prefix("ip/");
    public static final Prefix PREFIX_CLIENTTAG = new Prefix("c/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_APPOINTMENT_DATE = new Prefix("d/");
    public static final Prefix PREFIX_APPOINTMENT_LOCATION = new Prefix("l/");
    public static final Prefix PREFIX_INCOME = new Prefix("i/");
    public static final Prefix PREFIX_MONTHLY = new Prefix("m/");
}
