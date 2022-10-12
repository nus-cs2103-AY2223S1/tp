package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

/**
 * Contains the prefixes of the fields declared by the user
 */
public class FieldPrefixes {

    // A list of Prefix instances
    private List<Prefix> prefixes;

    // Contains the pairings of the prefix and field name
    private HashMap<Prefix, String> map;

    /**
     * Constructs a new FieldPrefixes instance
     */
    public FieldPrefixes() {
        prefixes = new ArrayList<>();
        map = new HashMap<>();
    }

    /**
     * Checks if the given prefix has previously been declared by the user.
     *
     * @param prefix A given Prefix instance.
     * @return true if prefix is in the list of known prefixes, false otherwise.
     */
    public boolean contains(Prefix prefix) {
        return prefixes.contains(prefix);
    }

    /**
     * Adds a prefix and a corresponding field name to the model.
     *
     * @param prefix The Prefix instance representing the prefix of the field.
     * @param name The name of the field.
     * @param model {@code Model} which the prefix and field name should be added on.
     * @throws ParseException if the prefix or the field has been declared previously.
     */
    public void addPrefix(Prefix prefix, String name, Model model) throws ParseException {
        if (matchesDefaultPrefixes(prefix) || prefixes.contains(prefix)) {
            throw new ParseException("Prefix has been stored previously. Enter a different prefix");
        }
        if (map.values().contains(name)) {
            throw new ParseException("Field has been stored previously. Enter a different field");
        }
        prefixes.add(prefix);
        model.addField(name);
        map.put(prefix, name);
    }

    /**
     * Removes a prefix from the known list of prefixes.
     *
     * @param prefix The Prefix instance to be removed
     * @throws ParseException if the Prefix can not be found.
     */
    public void removePrefix(Prefix prefix) throws ParseException {
        if (!prefixes.contains(prefix)) {
            throw new ParseException("Field not found");
        }
        prefixes.remove(prefix);
        map.remove(prefix);
    }

    /**
     * Checks if a given prefix is the same as the five default prefixes
     * contained in CLI Syntax.
     *
     * @param prefix A given Prefix instance.
     * @return true if the prefix matches the default prefixes, false otherwise.
     */
    public boolean matchesDefaultPrefixes(Prefix prefix) {
        return prefix.equals(PREFIX_ADDRESS)
                || prefix.equals(PREFIX_NAME)
                || prefix.equals(PREFIX_PHONE)
                || prefix.equals(PREFIX_TAG)
                || prefix.equals(PREFIX_EMAIL);
    }
}
