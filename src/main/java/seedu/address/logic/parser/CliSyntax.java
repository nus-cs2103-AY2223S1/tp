package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    /* Contact Prefixes*/
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    /* Module Prefixes*/
    public static final Prefix PREFIX_MODULE = new Prefix("m/");
    public static final Prefix PREFIX_LECTURE = new Prefix("l/");
    public static final Prefix PREFIX_TUTORIAL = new Prefix("t/");
    public static final Prefix PREFIX_ZOOM = new Prefix("z/");
    public static final Prefix PREFIX_ASSIGNMENT = new Prefix("a/");

    /* Command Types*/
    public static final String COMMAND_IDENTIFIER_PERSON = "p";
    public static final String COMMAND_IDENTIFIER_MODULE = "m";

}
