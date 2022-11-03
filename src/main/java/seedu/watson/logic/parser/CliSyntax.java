package seedu.watson.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");

    // New fields
    public static final Prefix PREFIX_STUDENTCLASS = new Prefix("c/");
    public static final Prefix PREFIX_REMARK = new Prefix("rem/");
    public static final Prefix PREFIX_SUBJECT = new Prefix("s/");


    // Fields for marking attendance
    public static final Prefix PREFIX_DATE = new Prefix("d/");
    public static final Prefix PREFIX_INDEX_NUMBERS = new Prefix("ind/");

    // For prediction
    public static final Prefix PREFIX_FUTURE_ASSESSMENT_DIFFICULTY = new Prefix("diff/");

}
