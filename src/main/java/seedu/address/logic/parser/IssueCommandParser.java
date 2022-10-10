package seedu.address.logic.parser;

import seedu.address.logic.commands.client.AddClientCommand;
import seedu.address.logic.commands.client.DeleteClientCommand;
import seedu.address.logic.commands.client.EditClientCommand;
import seedu.address.logic.commands.client.TagClientCommand;
import seedu.address.logic.commands.issue.*;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.commons.core.Messages.FLAG_UNKNOWN_COMMAND;

public class IssueCommandParser implements Parser<IssueCommand> {
    /**
     * Method to parse any commands that have to do with issues (start with 'issue')
     *
     * @param flag      flag used in command
     * @param arguments arguments used in command
     * @return a IssueCommand
     * @throws ParseException
     */
    @Override
    public IssueCommand parse(String flag, String arguments) throws ParseException {
        switch (flag.strip()) {
            case AddIssueCommand.COMMAND_FLAG:
                return parseAddIssueCommand(arguments);
            case EditIssueCommand.COMMAND_FLAG:
                return parseEditIssueCommand(arguments);
            case DeleteIssueCommand.COMMAND_FLAG:
                return parseDeleteIssueCommand(arguments);
            case ListIssueCommand.COMMAND_FLAG:
                return parseListIssueCommand(arguments);
            default:
                throw new ParseException(FLAG_UNKNOWN_COMMAND);
        }
    }

    //TODO: implement
    private AddIssueCommand parseAddIssueCommand(String arguments) {
        return null;
    }

    //TODO: implement
    private EditIssueCommand parseEditIssueCommand(String arguments) {
        return null;
    }

    //TODO: implement
    private DeleteIssueCommand parseDeleteIssueCommand(String arguments) {
        return null;
    }

    //TODO: implement
    private ListIssueCommand parseListIssueCommand(String arguments) {
        return null;
    }



}
