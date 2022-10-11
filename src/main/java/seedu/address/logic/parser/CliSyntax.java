package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {
    /* Prefix definitions*/
    public static final Prefix PREFIX_NAME = new Prefix("n/");

    /* Prefix definitions for Classes*/
    public static final Prefix PREFIX_SUBJECT = new Prefix("sub/");
    public static final Prefix PREFIX_DAY = new Prefix("d/");
    public static final Prefix PREFIX_TIME = new Prefix("t/");


    /* Prefix definitions for Persons*/
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("tag/");
    //public static final Prefix PREFIX_CLASS = new Prefix("c/"); TODO: after model decides how to store a persons class

    /* Prefix definitions for Students*/
    public static final Prefix PREFIX_SCHOOL = new Prefix("sch/");
    public static final Prefix PREFIX_NEXTOFKIN = new Prefix("nok/");

    /* Prefix definitions shared by Students and Classes*/
    public static final Prefix PREFIX_LEVEL = new Prefix("l/");

    /* Prefix definitions for Tutors*/
    public static final Prefix PREFIX_QUALIFICATION = new Prefix("q/");
    public static final Prefix PREFIX_INSTITUTION = new Prefix("i/");

}
