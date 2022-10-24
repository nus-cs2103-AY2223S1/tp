package seedu.rc4hdb.logic.parser.commandparsers;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.rc4hdb.logic.commands.modelcommands.ListCommand;
import seedu.rc4hdb.logic.parser.Parser;
import seedu.rc4hdb.logic.parser.exceptions.ParseException;
import seedu.rc4hdb.model.resident.fields.ResidentFields;

/**
 * Parses input arguments and creates a new ListCommand object
 */
public class ListCommandParser implements Parser<ListCommand> {
    public static final char INCLUDE_SPECIFIER = 'i';
    public static final char EXCLUDE_SPECIFIER = 'e';

    public static final String INTENDED_USAGE = "Please include a specifier [/i] or [/e]"
            + " followed by at least one field to include or exclude.";

    public static final String MUST_HAVE_ONE_COLUMN_VISIBLE = "You must have at least one column visible at all times.";

    @Override
    public ListCommand parse(String args) throws ParseException {
        requireNonNull(args);
        if (args.isEmpty()) {
            return new ListCommand();
        }
        if (hasInvalidSpecifier(args)) {
            throw new ParseException(INTENDED_USAGE);
        }
        if (hasEmptyFieldBody(args)) {
            throw new ParseException(INTENDED_USAGE);
        }

        // Create one list for each specifier
        List<String> fieldsToInclude = new ArrayList<>();
        List<String> fieldsToExclude = new ArrayList<>(ResidentFields.LOWERCASE_FIELDS);

        populateFieldLists(args, fieldsToInclude, fieldsToExclude);
        if (fieldsToExclude.isEmpty()) {
            throw new ParseException(MUST_HAVE_ONE_COLUMN_VISIBLE);
        }

        if (getListSpecifier(args) == INCLUDE_SPECIFIER) {
            return new ListCommand(fieldsToInclude, fieldsToExclude);
        } else {
            return new ListCommand(fieldsToExclude, fieldsToInclude);
        }
    }

    private boolean hasInvalidSpecifier(String args) {
        if (hasInvalidSpecifierLength(args)) {
            return true;
        }
        char specifier = getListSpecifier(args);
        return specifier != INCLUDE_SPECIFIER && specifier != EXCLUDE_SPECIFIER;
    }

    private boolean hasInvalidSpecifierLength(String args) {
        int nextIndex = args.indexOf("/") + 2;
        if (nextIndex < args.length()) {
            return args.charAt(nextIndex) != ' ';
        }
        return false;
    }

    private char getListSpecifier(String args) {
        return args.charAt(args.indexOf("/") + 1);
    }

    private String[] getSpecifiedFields(String args) {
        String fieldsString = args.toLowerCase().substring(args.indexOf("/") + 2).trim();
        if (fieldsString.isEmpty()) {
            return new String[] {};
        }
        return fieldsString.split(" ");
    }

    private boolean hasEmptyFieldBody(String args) {
        return getSpecifiedFields(args).length == 0;
    }

    private void populateFieldLists(String args,
                                    List<String> fieldsToInclude,
                                    List<String> fieldsToExclude) throws ParseException {
        String[] specifiedFields = getSpecifiedFields(args);
        for (String field : specifiedFields) {
            if (ResidentFields.LOWERCASE_FIELDS.contains(field)) {
                fieldsToInclude.add(field);
                fieldsToExclude.remove(field);
            } else {
                throw new ParseException(INTENDED_USAGE);
            }
        }
    }
}
