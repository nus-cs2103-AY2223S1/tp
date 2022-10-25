package seedu.rc4hdb.logic.parser.commandparsers;

import static java.util.Objects.requireNonNull;
import static seedu.rc4hdb.logic.commands.modelcommands.ListCommand.COMMAND_WORD;
import static seedu.rc4hdb.logic.commands.modelcommands.ListCommand.EXCLUDE_SPECIFIER;
import static seedu.rc4hdb.logic.commands.modelcommands.ListCommand.INCLUDE_SPECIFIER;

import java.util.List;

import seedu.rc4hdb.logic.commands.modelcommands.ListCommand;
import seedu.rc4hdb.logic.parser.exceptions.ParseException;

/**
 * Parses user input for a ListCommand.
 */
public class ListCommandParser extends ColumnManipulatorCommandParser {

    public static final List<String> LIST_OF_SPECIFIERS = List.of(INCLUDE_SPECIFIER, EXCLUDE_SPECIFIER);

    public static final String INTENDED_SPECIFIER_USAGE_MESSAGE = "Please enter the " + COMMAND_WORD
            + " command without any specifiers, or use either " + COMMAND_WORD + " " + INCLUDE_SPECIFIER
            + " or " + COMMAND_WORD + " " + EXCLUDE_SPECIFIER + " followed by at least one column"
            + " to include or exclude.";

    @Override
    public ListCommand parse(String args) throws ParseException {
        requireNonNull(args);
        if (args.isEmpty()) {
            return new ListCommand();
        }

        String listSpecifier = getSpecifierIfPresent(args, LIST_OF_SPECIFIERS, INTENDED_SPECIFIER_USAGE_MESSAGE);
        String stringOfFieldsToProcess = getArgumentsAfterSpecifierIfPresent(args, LIST_OF_SPECIFIERS,
                INTENDED_SPECIFIER_USAGE_MESSAGE);

        List<String> fieldsToShow = getBaseFieldList(stringOfFieldsToProcess);
        List<String> fieldsToHide = getComplementFieldList(stringOfFieldsToProcess);

        if (listSpecifier.equals(INCLUDE_SPECIFIER)) {
            return new ListCommand(fieldsToShow, fieldsToHide);
        } else {
            return new ListCommand(fieldsToHide, fieldsToShow);
        }
    }

    @Override
    public String getCommandWord() {
        return ListCommand.COMMAND_WORD;
    }

    @Override
    public String getCommandPresentTense() {
        return ListCommand.COMMAND_PRESENT_TENSE;
    }
}
