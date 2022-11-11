package eatwhere.foodguide.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("-n");
    public static final Prefix PREFIX_PRICE = new Prefix("-p");
    public static final Prefix PREFIX_CUISINE = new Prefix("-c");
    public static final Prefix PREFIX_LOCATION = new Prefix("-l");
    public static final Prefix PREFIX_TAG = new Prefix("-t");
    public static final Prefix PREFIX_HELP = new Prefix("-h");
    public static final Prefix PREFIX_RANDOM = new Prefix("-r");

}
