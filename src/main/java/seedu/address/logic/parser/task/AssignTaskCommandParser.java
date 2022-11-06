package seedu.address.logic.parser.task;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADD_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DELETE_CONTACT;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.task.AssignTaskCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.TaskParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AssignTaskCommand object
 */
public class AssignTaskCommandParser implements Parser<AssignTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AssignTaskCommand
     * and returns an AssignTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AssignTaskCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ADD_CONTACT, PREFIX_DELETE_CONTACT);

        Index taskIndex;
        Set<Index> teammatesAddIndexes;
        Set<String> teammatesAddNames;
        Set<Index> teammatesDeleteIndexes;
        Set<String> teammatesDeleteNames;


        if (argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignTaskCommand.MESSAGE_USAGE));
        }

        String indexString = argMultimap.getPreamble();

        try {
            taskIndex = TaskParserUtil.parseIndex(indexString);

            List<String> contactsAddString = argMultimap.getAllValues(PREFIX_ADD_CONTACT);
            teammatesAddIndexes = TaskParserUtil.parseIndexesMixed(contactsAddString);
            teammatesAddNames = TaskParserUtil.parseTextsMixed(contactsAddString);

            List<String> contactsDeleteString = argMultimap.getAllValues(PREFIX_DELETE_CONTACT);
            teammatesDeleteIndexes = TaskParserUtil.parseIndexesMixed(contactsDeleteString);
            teammatesDeleteNames = TaskParserUtil.parseTextsMixed(contactsDeleteString);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AssignTaskCommand.MESSAGE_USAGE), pe);
        }

        return new AssignTaskCommand(taskIndex, teammatesAddIndexes, teammatesAddNames,
                teammatesDeleteIndexes, teammatesDeleteNames);
    }
}
