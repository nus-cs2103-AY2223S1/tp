package seedu.rc4hdb.logic.parser.commandparsers;

import static java.util.Objects.requireNonNull;
import static seedu.rc4hdb.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.rc4hdb.model.resident.fields.ResidentField.LETTER_TO_FIELD_NAME_MAPPINGS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import seedu.rc4hdb.logic.commands.residentcommands.ColumnManipulatorCommand;
import seedu.rc4hdb.logic.parser.exceptions.ParseException;

/**
 * Represents a parser for commands that can interact with Model to manipulate columns in the UI table.
 */
public abstract class ColumnManipulatorCommandParser implements CommandParser<ColumnManipulatorCommand> {

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

        // Non-existent mappings will result in null values in the final list...
        // Checks for these null values are performed in the populateBaseAndComplementLists method below
        List<String> fullNamesOfColumnsToShowOrHide = getFullNamesOfColumnsToShowOrHide(lettersOfColumnsToShowOrHide);

        List<String> baseList = new ArrayList<>();
        List<String> complementList = new ArrayList<>(ColumnManipulatorCommand.ALL_FIELDS);
        populateBaseAndComplementListsWithColumns(fullNamesOfColumnsToShowOrHide, baseList, complementList);

        HashMap<String, List<String>> hashMap = new HashMap<>();
        populateHashMapWithBaseAndComplementLists(hashMap, baseList, complementList);

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

    private static List<String> getFullNamesOfColumnsToShowOrHide(List<String> lettersOfColumnsToShowOrHide) {
        requireNonNull(lettersOfColumnsToShowOrHide);
        return lettersOfColumnsToShowOrHide.stream()
                .map(LETTER_TO_FIELD_NAME_MAPPINGS::get)
                .collect(Collectors.toList());
    }

    private static void populateBaseAndComplementListsWithColumns(List<String> fullNamesOfColumnsToShowOrHide,
                                                       List<String> baseList,
                                                       List<String> complementList) throws ParseException {
        requireAllNonNull(fullNamesOfColumnsToShowOrHide, baseList, complementList);
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
    }

    private static void populateHashMapWithBaseAndComplementLists(HashMap<String, List<String>> hashMap,
                                                                  List<String> baseList,
                                                                  List<String> complementList) {
        requireAllNonNull(hashMap, baseList, complementList);
        hashMap.put(BASE_LIST, baseList);
        hashMap.put(COMPLEMENT_LIST, complementList);
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
