package seedu.rc4hdb.logic.parser.commandparsers;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.rc4hdb.logic.commands.residentcommands.HideOnlyCommand;
import seedu.rc4hdb.logic.parser.exceptions.ParseException;

/**
 * Parses user input for a HideOnlyCommand.
 */
public class HideOnlyCommandParser extends ColumnManipulatorCommandParser {
    @Override
    public HideOnlyCommand parse(String args) throws ParseException {
        requireNonNull(args);
        requireNonEmpty(args);

        List<String> fieldsToHide = getBaseFieldList(args);
        List<String> fieldsToShow = getComplementFieldList(args);

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
