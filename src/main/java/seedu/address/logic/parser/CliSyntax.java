package seedu.address.logic.parser;

import java.util.ArrayList;
import java.util.Arrays;

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
    public static final Prefix PREFIX_SKILLTAG = new Prefix(("st/"));
    public static final Prefix PREFIX_DEGREETAG = new Prefix(("dt/"));
    public static final Prefix PREFIX_JOBTYPETAG = new Prefix(("jtt/"));
    private static ArrayList<Prefix> prefixes = new ArrayList<>(Arrays.asList(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
            PREFIX_ADDRESS, PREFIX_SKILLTAG, PREFIX_DEGREETAG, PREFIX_JOBTYPETAG));
    public static void addPrefix(Prefix pref) {
        prefixes.add(pref);
    }
}
