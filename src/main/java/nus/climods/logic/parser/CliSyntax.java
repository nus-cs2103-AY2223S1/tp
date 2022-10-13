package nus.climods.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Address book prefixes*/
    /* Prefix definitions */
    public static final Prefix PREFIX_CODE = new Prefix("c/");
    public static final Prefix PREFIX_TUTORIAL = new Prefix("t/");
    //TODO: add more details to the module
    public static final Prefix PREFIX_LECTURE = new Prefix("l/");
    public static final Prefix PREFIX_TAG = new Prefix("u/");

    // details for person, can delete later
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");

    /* CLI mods prefixes */

}
