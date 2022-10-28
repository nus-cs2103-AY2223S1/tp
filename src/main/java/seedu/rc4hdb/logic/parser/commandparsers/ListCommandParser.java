package seedu.rc4hdb.logic.parser.commandparsers;

import static java.util.Objects.requireNonNull;
import static seedu.rc4hdb.logic.commands.residentcommands.ListCommand.COMMAND_WORD;
import static seedu.rc4hdb.logic.commands.residentcommands.ListCommand.EXCLUDE_SPECIFIER;
import static seedu.rc4hdb.logic.commands.residentcommands.ListCommand.INCLUDE_SPECIFIER;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.rc4hdb.logic.commands.residentcommands.ListCommand;
import seedu.rc4hdb.logic.parser.exceptions.ParseException;

/**
 * Parses user input for a ListCommand.
 */
public class ListCommandParser extends ColumnManipulatorCommandParser {

    public static final List<String> LIST_OF_SPECIFIERS = List.of(INCLUDE_SPECIFIER, EXCLUDE_SPECIFIER);

    public static final String SPECIFIER_USAGE = "Please enter the " + COMMAND_WORD
            + " command without any specifiers, or use either " + COMMAND_WORD + " " + INCLUDE_SPECIFIER
            + " or " + COMMAND_WORD + " " + EXCLUDE_SPECIFIER + " followed by at least one column"
            + " to include or exclude.";

    private static final Logger logger = Logger.getLogger("ListCommandParser");

    @Override
    public ListCommand parse(String args) throws ParseException {
        requireNonNull(args);
        logger.log(Level.INFO, "Going to start parsing.");

        if (args.isEmpty()) {
            logger.log(Level.WARNING, "Empty arguments when parsing. Returning command for list.");
            return new ListCommand();
        }

        String listSpecifier = getSpecifierIfPresent(args, LIST_OF_SPECIFIERS, SPECIFIER_USAGE);
        logger.log(Level.INFO, "Valid specifier found. Checking for fields entered after the specifier.");

        String stringOfFieldsToProcess = getArgumentsAfterSpecifierIfPresent(args, LIST_OF_SPECIFIERS, SPECIFIER_USAGE);
        logger.log(Level.INFO, "Non-empty fields found after the specifier. Checking for validity of fields.");

        // Performs input validation before returning the corresponding fields to show and hide
        List<String> fieldsToShow = getBaseFieldList(stringOfFieldsToProcess);
        List<String> fieldsToHide = getComplementFieldList(stringOfFieldsToProcess);
        logger.log(Level.INFO, "Valid fields entered.");

        if (listSpecifier.equals(INCLUDE_SPECIFIER)) {
            logger.log(Level.INFO, "Parsing completed, returning command for list /i.");
            return new ListCommand(fieldsToShow, fieldsToHide);
        } else {
            logger.log(Level.INFO, "Parsing completed, returning command for list /e.");
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
