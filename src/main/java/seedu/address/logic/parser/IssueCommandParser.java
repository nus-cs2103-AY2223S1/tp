package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.FLAG_UNKNOWN_COMMAND;

import seedu.address.logic.commands.issue.AddIssueCommand;
import seedu.address.logic.commands.issue.DeleteIssueCommand;
import seedu.address.logic.commands.issue.EditIssueCommand;
import seedu.address.logic.commands.issue.IssueCommand;
import seedu.address.logic.commands.issue.ListIssueCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parser to parse any commands related to issues
 */
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
