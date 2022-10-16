package seedu.clinkedin.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;

import seedu.clinkedin.logic.parser.exceptions.DuplicatePrefixException;
import seedu.clinkedin.logic.parser.exceptions.PrefixNotFoundException;

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
    public static final Prefix PREFIX_STATUS = new Prefix("s/");
    public static final Prefix PREFIX_NOTE = new Prefix("note/");
    public static final Prefix PREFIX_SKILLTAG = new Prefix(("st/"));
    public static final Prefix PREFIX_DEGREETAG = new Prefix(("dt/"));
    public static final Prefix PREFIX_JOBTYPETAG = new Prefix(("jtt/"));
    private static ArrayList<Prefix> prefixTags = new ArrayList<>(Arrays.asList(PREFIX_SKILLTAG, PREFIX_DEGREETAG,
            PREFIX_JOBTYPETAG));
    private static ArrayList<Prefix> prefixes = new ArrayList<>(Arrays.asList(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
            PREFIX_ADDRESS, PREFIX_SKILLTAG, PREFIX_DEGREETAG, PREFIX_JOBTYPETAG, PREFIX_STATUS, PREFIX_NOTE));
    private static ArrayList<Prefix> uniquePrefixes = new ArrayList<>(Arrays.asList(PREFIX_NAME, PREFIX_PHONE,
            PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_STATUS));

    /**
     * Adds a tag prefix to the list of prefixes.
     * @param pref Prefix to be added.
     * @throws DuplicatePrefixException If the prefix is already present in the list of prefixes.
     */
    public static void addTagPrefix(Prefix pref) throws DuplicatePrefixException {
        if (prefixTags.contains(pref) || prefixes.contains(pref)) {
            throw new DuplicatePrefixException();
        }
        prefixTags.add(pref);
        prefixes.add(pref);
    }

    /**
     * Removes a tag prefix from the list of prefixes.
     * @param pref Prefix to be removed.
     * @throws PrefixNotFoundException If the prefix doesn't exist in the list of prefixes.
     */
    public static void removeTagPrefix(Prefix pref) throws PrefixNotFoundException {
        if (!prefixTags.contains(pref) || !prefixes.contains(pref)) {
            throw new PrefixNotFoundException();
        }
        prefixTags.remove(pref);
        prefixes.remove(pref);
    }
    public static Prefix[] getPrefixes() {
        requireNonNull(prefixes);
        Prefix[] pref = new Prefix[prefixes.size()];
        pref = prefixes.toArray(pref);
        return pref;
    }
    public static ArrayList<Prefix> getPrefixTags() {
        requireNonNull(prefixTags);
        return prefixTags;
    }
    public static ArrayList<Prefix> getUniquePrefixes() {
        requireNonNull(uniquePrefixes);
        return uniquePrefixes;
    }
}
