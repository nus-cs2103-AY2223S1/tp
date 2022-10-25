package seedu.rc4hdb.logic.parser.commandparsers;

import java.util.List;

import static java.util.Objects.requireNonNull;
import seedu.rc4hdb.logic.commands.modelcommands.ShowOnlyCommand;
import seedu.rc4hdb.logic.parser.exceptions.ParseException;

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
    public String getCommandVerbs() {
        return ShowOnlyCommand.COMMAND_VERBS;
    }
}
