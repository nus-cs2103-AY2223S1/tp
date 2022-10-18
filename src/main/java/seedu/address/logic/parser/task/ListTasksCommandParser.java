package seedu.address.logic.parser.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.task.ListTasksCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.TaskParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ListTasksCommand object
 */
public class ListTasksCommandParser implements Parser<ListTasksCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListTasksCommand
     * and returns an ListTasksCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListTasksCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_CONTACT);

        Optional<String> keywordFilter = argMultimap.getValue(PREFIX_NAME);
        List<String> contactFilters = argMultimap.getAllValues(PREFIX_CONTACT);

        if (keywordFilter.isEmpty() && contactFilters.isEmpty() && !args.trim().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ListTasksCommand.MESSAGE_USAGE));
        }

        Set<Index> personsIndexes;
        try {
            personsIndexes = TaskParserUtil.parseIndexes(contactFilters);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ListTasksCommand.MESSAGE_USAGE), pe);
        }
        return new ListTasksCommand(keywordFilter, personsIndexes);
    }

}
