package seedu.rc4hdb.logic.parser.commandparsers;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.rc4hdb.logic.commands.residentcommands.ShowOnlyCommand;
import seedu.rc4hdb.logic.parser.exceptions.ParseException;

/**
 * Parses user input for a ShowOnlyCommand.
 */
public class ShowOnlyCommandParser extends ColumnManipulatorCommandParser {

    private static final Logger logger = Logger.getLogger("ShowOnlyCommandParser");

    @Override
    public ShowOnlyCommand parse(String args) throws ParseException {
        requireNonNull(args);
        logger.log(Level.INFO, "Going to start parsing.");

        requireNonEmpty(args);

        // Performs input validation before returning the corresponding fields to show and hide
        List<String> fieldsToShow = getBaseFieldList(args);
        List<String> fieldsToHide = getComplementFieldList(args);

        logger.log(Level.INFO, "Parsing completed, returning ShowOnlyCommand.");
        return new ShowOnlyCommand(fieldsToShow, fieldsToHide);
    }

    @Override
    public String getCommandWord() {
        return ShowOnlyCommand.COMMAND_WORD;
    }

    @Override
    public String getCommandPresentTense() {
        return ShowOnlyCommand.COMMAND_PRESENT_TENSE;
    }
}
