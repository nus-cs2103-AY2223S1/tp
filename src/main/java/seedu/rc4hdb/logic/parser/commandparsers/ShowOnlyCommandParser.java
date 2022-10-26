package seedu.rc4hdb.logic.parser.commandparsers;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.rc4hdb.logic.commands.residentcommands.ShowOnlyCommand;
import seedu.rc4hdb.logic.parser.exceptions.ParseException;

/**
 * Parses user input for a ShowOnlyCommand.
 */
public class ShowOnlyCommandParser extends ColumnManipulatorCommandParser {

    @Override
    public ShowOnlyCommand parse(String args) throws ParseException {
        requireNonNull(args);
        requireNonEmpty(args);

        List<String> fieldsToShow = getBaseFieldList(args);
        List<String> fieldsToHide = getComplementFieldList(args);

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
