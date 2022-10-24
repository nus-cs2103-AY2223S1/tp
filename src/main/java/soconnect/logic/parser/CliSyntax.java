package soconnect.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands.
 */
public class CliSyntax {

    /* Person prefix indicator*/
    public static final String INDICATOR_NAME = "n/";
    public static final String INDICATOR_PHONE = "p/";
    public static final String INDICATOR_EMAIL = "e/";
    public static final String INDICATOR_ADDRESS = "a/";

    /* Tag indicator - Should not have same indicator in both Person and Todo prefix indicator*/
    public static final String INDICATOR_TAG = "t/";

    /* Todo prefix indicator*/
    public static final String INDICATOR_DESCRIPTION = "d/";
    public static final String INDICATOR_DATE = "date/";
    public static final String INDICATOR_PRIORITY = "p/";

    /* Person prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix(INDICATOR_NAME);
    public static final Prefix PREFIX_PHONE = new Prefix(INDICATOR_PHONE);
    public static final Prefix PREFIX_EMAIL = new Prefix(INDICATOR_EMAIL);
    public static final Prefix PREFIX_ADDRESS = new Prefix(INDICATOR_ADDRESS);

    /* Tag prefix definitions */
    public static final Prefix PREFIX_TAG = new Prefix(INDICATOR_TAG);

    /* Todo prefix definitions */
    public static final Prefix PREFIX_DESCRIPTION = new Prefix(INDICATOR_DESCRIPTION);
    public static final Prefix PREFIX_DATE = new Prefix(INDICATOR_DATE);
    public static final Prefix PREFIX_PRIORITY = new Prefix(INDICATOR_PRIORITY);
}
