package seedu.rc4hdb.logic.parser.commandparsers;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import seedu.rc4hdb.logic.commands.modelcommands.ColumnManipulatorCommand;
import seedu.rc4hdb.logic.parser.Parser;
import seedu.rc4hdb.logic.parser.exceptions.ParseException;

public abstract class ColumnManipulatorCommandParser implements Parser<ColumnManipulatorCommand> {

    public static final String INTENDED_USAGE_FORMAT = "Please enter the %s command followed by"
            + " at least one column to %s\n"
            + "Example: %s n p e\n";

    public static final String INVALID_FIELDS_ENTERED = "Please check that you have entered"
            + " valid column headers or field names.";

    public static final String WHITESPACE = " ";

    private static final String BASE_LIST = "baseList";

    private static final String COMPLEMENT_LIST = "complementList";

    public static String getArgumentsAfterSpecifierIfPresent(String args, List<String> specifierList,
                                                             String errorMessage) throws ParseException {
        String result = args;
        for (String specifier : specifierList) {
            if (args.contains(specifier)) {
                int indexAfterEndOfSpecifier = args.indexOf(specifier) + specifier.length();
                result = args.substring(indexAfterEndOfSpecifier);
            }
        }
        if (result.trim().isEmpty()) {
            throw new ParseException(errorMessage);
        }
        return result;
    }

    public static String getSpecifierIfPresent(String args, List<String> specifierList,
                                               String errorMessage) throws ParseException {
        for (String specifier : specifierList) {
            if (args.contains(specifier)) {
                return specifier;
            }
        }
        throw new ParseException(errorMessage);
    }

    private static List<String> parseColumnsToShowOrHide(String args) {
        requireNonNull(args);

        String[] columnsToShowOrHide = args.trim().toLowerCase().split(WHITESPACE);
        return Arrays.asList(columnsToShowOrHide);
    }

    private static HashMap<String, List<String>> validateAndGenerateFieldLists(String args) throws ParseException {
        requireNonNull(args);

        List<String> listOfColumnsToShowOrHide = parseColumnsToShowOrHide(args);
        List<String> baseList = new ArrayList<>();
        List<String> complementList = new ArrayList<>(ColumnManipulatorCommand.ALL_FIELDS);

        for (String column : listOfColumnsToShowOrHide) {
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
        HashMap<String, List<String>> hashMapOfFieldLists = validateAndGenerateFieldLists(args);
        return hashMapOfFieldLists.get(BASE_LIST);
    }

    public static List<String> getComplementFieldList(String args) throws ParseException {
        HashMap<String, List<String>> hashMapOfFieldLists = validateAndGenerateFieldLists(args);
        return hashMapOfFieldLists.get(COMPLEMENT_LIST);
    }

    public void requireNonEmpty(String args) throws ParseException {
        if (args.isEmpty()) {
            throw new ParseException(String.format(INTENDED_USAGE_FORMAT,
                    getCommandWord(),
                    getCommandPresentTense(),
                    getCommandPresentTense()));
        }
    }

    public abstract String getCommandWord();

    public abstract String getCommandPresentTense();
}
