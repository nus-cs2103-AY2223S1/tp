package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {
    /* Prefix definitions*/
    public static final Prefix PREFIX_NAME = new Prefix("n/");

    /* Prefix definitions for Classes*/
    public static final Prefix PREFIX_SUBJECT_OR_SCHOOL = new Prefix("s/");
    public static final Prefix PREFIX_DAY = new Prefix("d/");
    public static final Prefix PREFIX_TIME = new Prefix("t/");


    /* Prefix definitions for Persons*/
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("#/");

    /* Prefix definitions for Students*/

    /* Prefix definitions shared by Students and Classes*/
    public static final Prefix PREFIX_LEVEL = new Prefix("l/");

    /* Prefix definitions for Tutors*/
    public static final Prefix PREFIX_QUALIFICATION = new Prefix("q/");
    public static final Prefix PREFIX_INSTITUTION = new Prefix("i/");

    /* Prefix definitions for Next Of Kins*/
    public static final Prefix PREFIX_RELATIONSHIP = new Prefix("r/");

}
