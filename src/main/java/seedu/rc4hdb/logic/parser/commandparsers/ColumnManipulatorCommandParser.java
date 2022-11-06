package seedu.rc4hdb.logic.parser.commandparsers;

import static java.util.Objects.requireNonNull;
import static seedu.rc4hdb.model.resident.fields.ResidentField.LETTER_TO_FIELD_NAME_MAPPINGS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import seedu.rc4hdb.logic.commands.residentcommands.ColumnManipulatorCommand;
import seedu.rc4hdb.logic.parser.Parser;
import seedu.rc4hdb.logic.parser.exceptions.ParseException;

/**
 * Represents a parser for commands that can interact with Model to manipulate columns in the UI table.
 */
public abstract class ColumnManipulatorCommandParser implements Parser<ColumnManipulatorCommand> {

    public static final String INTENDED_USAGE_FORMAT = "Please enter the %s command followed by"
            + " at least one column to %s.\n"
            + "Each column can be specified by entering the first letter of the column header.\n"
            + "Example: %s n p e\n";

    public static final String INVALID_FIELDS_ENTERED = "Please check that you have entered"
            + " letters that correspond to valid column headers or field names.";

    public static final String WHITESPACE = " ";

    private static final String BASE_LIST = "baseList";

    private static final String COMPLEMENT_LIST = "complementList";

    public abstract String getCommandWord();

    public abstract String getCommandPresentTense();

    private static List<String> parseColumnsToShowOrHide(String args) {
        requireNonNull(args);
        String[] columnsToShowOrHide = args.trim().toLowerCase().split(WHITESPACE);
        return Arrays.asList(columnsToShowOrHide);
    }

    private static HashMap<String, List<String>> validateAndGenerateFieldLists(String args) throws ParseException {
        requireNonNull(args);
        List<String> lettersOfColumnsToShowOrHide = parseColumnsToShowOrHide(args);

        assert(lettersOfColumnsToShowOrHide != null);

        // Non-existent mappings will result in null values in the final list... null checks are performed below
        List<String> fullNamesOfColumnsToShowOrHide = lettersOfColumnsToShowOrHide.stream()
                .map(LETTER_TO_FIELD_NAME_MAPPINGS::get)
                .collect(Collectors.toList());

        List<String> baseList = new ArrayList<>();
        List<String> complementList = new ArrayList<>(ColumnManipulatorCommand.ALL_FIELDS);

        for (String column : fullNamesOfColumnsToShowOrHide) {
            if (column == null) {
                throw new ParseException(INVALID_FIELDS_ENTERED);
            }
            if (!ColumnManipulatorCommand.ALL_FIELDS.contains(column)) {
                throw new ParseException(INVALID_FIELDS_ENTERED);
            }
            baseList.add(column);
            complementList.remove(column);
        }

        HashMap<String, List<String>> hashMap = new HashMap<>();
        hashMap.put(BASE_LIST, baseList);
        hashMap.put(COMPLEMENT_LIST, complementList);
        return hashMap;
    }

    public static List<String> getBaseFieldList(String args) throws ParseException {
        requireNonNull(args);
        HashMap<String, List<String>> hashMapOfFieldLists = validateAndGenerateFieldLists(args);
        return hashMapOfFieldLists.get(BASE_LIST);
    }

    public static List<String> getComplementFieldList(String args) throws ParseException {
        requireNonNull(args);
        HashMap<String, List<String>> hashMapOfFieldLists = validateAndGenerateFieldLists(args);
        return hashMapOfFieldLists.get(COMPLEMENT_LIST);
    }

    /**
     * Enforces the input arguments after the command word should not be empty.
     * @param args The input arguments read from the user
     * @throws ParseException if the arguments are empty
     */
    public void requireNonEmpty(String args) throws ParseException {
        requireNonNull(args);
        if (args.isEmpty()) {
            throw new ParseException(String.format(INTENDED_USAGE_FORMAT,
                    getCommandWord(),
                    getCommandPresentTense(),
                    getCommandWord()));
        }
    }
}
