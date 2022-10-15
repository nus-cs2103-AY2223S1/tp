package longtimenosee.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Person prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");

    public static final Prefix PREFIX_TAG = new Prefix("t/");

    public static final Prefix PREFIX_BIRTHDAY = new Prefix("b/");

    public static final Prefix PREFIX_INCOME = new Prefix("i/");

    public static final Prefix PREFIX_RISK_APPETITE = new Prefix("ra/");

    /* Policy prefix definitions */

    public static final Prefix PREFIX_TITLE = new Prefix("ti/");
    public static final Prefix PREFIX_COMPANY = new Prefix("cmp/");
    public static final Prefix PREFIX_COMMISSION = new Prefix("cms/");
    public static final Prefix PREFIX_COVERAGES = new Prefix("cov/");

    /* Policy prefix definitions */
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("desc/");
    public static final Prefix PREFIX_PERSON_NAME = new Prefix("pName/");
    public static final Prefix PREFIX_DATE = new Prefix("date/");
    public static final Prefix PREFIX_START_TIME = new Prefix("start/");
    public static final Prefix PREFIX_END_TIME = new Prefix("end/");


}
