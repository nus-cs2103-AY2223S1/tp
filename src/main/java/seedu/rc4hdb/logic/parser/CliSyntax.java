package seedu.rc4hdb.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ROOM = new Prefix("r/");
    public static final Prefix PREFIX_GENDER = new Prefix("g/");
    public static final Prefix PREFIX_HOUSE = new Prefix("h/");
    public static final Prefix PREFIX_MATRIC_NUMBER = new Prefix("m/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_FILTER_ANY = new Prefix("/any");
    public static final Prefix PREFIX_FILTER_ALL = new Prefix("/all");

}
