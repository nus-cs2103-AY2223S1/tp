package seedu.address.logic.parser.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AFTER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BEFORE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FLAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT;

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
import seedu.address.model.task.Deadline;

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
                ArgumentTokenizer.tokenize(
                        args,
                        PREFIX_CONTACT,
                        PREFIX_FLAG,
                        PREFIX_PROJECT,
                        PREFIX_BEFORE,
                        PREFIX_AFTER
                );
        String keywordFilter = argMultimap.getPreamble();
        List<String> contactFilters = argMultimap.getAllValues(PREFIX_CONTACT);
        List<String> projectNames = argMultimap.getAllValues(PREFIX_PROJECT);
        List<String> flags = argMultimap.getAllValues(PREFIX_FLAG);
        Optional<String> beforeArgs = argMultimap.getValue(PREFIX_BEFORE);
        Optional<String> afterArgs = argMultimap.getValue(PREFIX_AFTER);

        if (keywordFilter.isEmpty()
                && contactFilters.isEmpty()
                && projectNames.isEmpty()
                && flags.isEmpty()
                && beforeArgs.isEmpty()
                && afterArgs.isEmpty()
                && !args.trim().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ListTasksCommand.MESSAGE_USAGE));
        }

        Set<Index> teammatesIndexes;
        Optional<Deadline> before = Optional.empty();;
        Optional<Deadline> after = Optional.empty();;


        if (beforeArgs.isPresent()) {
            before = Optional.ofNullable(TaskParserUtil.parseDeadline(beforeArgs.get()));
        }

        if (afterArgs.isPresent()) {
            after = Optional.ofNullable(TaskParserUtil.parseDeadline(afterArgs.get()));
        }

        try {
            teammatesIndexes = TaskParserUtil.parseIndexes(contactFilters);

        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ListTasksCommand.MESSAGE_USAGE), pe);
        }
        return new ListTasksCommand(keywordFilter, projectNames, flags, before, after, teammatesIndexes);
    }

}
