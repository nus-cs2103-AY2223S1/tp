package soconnect.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands.
 */
public class CliSyntax {

    public static final String INDICATOR_NAME = "n/";
    public static final String INDICATOR_PHONE = "p/";
    public static final String INDICATOR_EMAIL = "e/";
    public static final String INDICATOR_ADDRESS = "a/";
    public static final String INDICATOR_TAG = "t/";
    public static final String INDICATOR_DESCRIPTION = "d/";
    public static final String INDICATOR_PRIORITY = "priority/";

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix(INDICATOR_NAME);
    public static final Prefix PREFIX_PHONE = new Prefix(INDICATOR_PHONE);
    public static final Prefix PREFIX_EMAIL = new Prefix(INDICATOR_EMAIL);
    public static final Prefix PREFIX_ADDRESS = new Prefix(INDICATOR_ADDRESS);
    public static final Prefix PREFIX_TAG = new Prefix(INDICATOR_TAG);
    public static final Prefix PREFIX_DESCRIPTION = new Prefix(INDICATOR_DESCRIPTION);
    public static final Prefix PREFIX_PRIORITY = new Prefix(INDICATOR_PRIORITY);

}
