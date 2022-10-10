package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_NAME_PARSER = new Prefix("/n");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_NEXT_OF_KIN = new Prefix("nok/");
    public static final Prefix PREFIX_PATIENT_TYPE = new Prefix("pt/");
    public static final Prefix PREFIX_INPATIENT_PARSER = new Prefix("/inp");
    public static final Prefix PREFIX_OUTPATIENT_PARSER = new Prefix("/outp");
    public static final Prefix PREFIX_HOSPITAL_WING = new Prefix("hw/");
    public static final Prefix PREFIX_HOSPITAL_WING_PARSER = new Prefix("/hw");
    public static final Prefix PREFIX_FLOOR_NUMBER = new Prefix("fn/");
    public static final Prefix PREFIX_FLOOR_NUMBER_PARSER = new Prefix("/fn");
    public static final Prefix PREFIX_WARD_NUMBER = new Prefix("wn/");
    public static final Prefix PREFIX_MEDICATION = new Prefix("m/");


}
