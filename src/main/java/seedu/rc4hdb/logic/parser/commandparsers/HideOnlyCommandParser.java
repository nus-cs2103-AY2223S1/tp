package seedu.rc4hdb.logic.parser.commandparsers;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.rc4hdb.logic.commands.residentcommands.HideOnlyCommand;
import seedu.rc4hdb.logic.parser.exceptions.ParseException;

/**
 * Parses user input for a HideOnlyCommand.
 */
public class HideOnlyCommandParser extends ColumnManipulatorCommandParser {

    private static final Logger logger = Logger.getLogger("HideOnlyCommandParser");

    @Override
    public HideOnlyCommand parse(String args) throws ParseException {
        requireNonNull(args);
        logger.log(Level.INFO, "Going to start parsing.");

        requireNonEmpty(args);

        // Performs input validation before returning the corresponding fields to show and hide
        List<String> fieldsToHide = getBaseFieldList(args);
        List<String> fieldsToShow = getComplementFieldList(args);

        logger.log(Level.INFO, "Parsing completed, returning HideOnlyCommand.");
        return new HideOnlyCommand(fieldsToShow, fieldsToHide);
    }

    @Override
    public String getCommandWord() {
        return HideOnlyCommand.COMMAND_WORD;
    }

    @Override
    public String getCommandPresentTense() {
        return HideOnlyCommand.COMMAND_PRESENT_TENSE;
    }
}
