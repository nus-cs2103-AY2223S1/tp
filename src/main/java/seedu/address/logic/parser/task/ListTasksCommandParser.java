package seedu.address.logic.parser.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FLAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.task.ListTasksCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.TaskParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Parses input arguments and creates a new ListTasksCommand object
 */
public class ListTasksCommandParser implements Parser<ListTasksCommand> {

    private static final String ALL_FLAG = "a";
    private static final String COMPLETED_FLAG = "c";

    /**
     * Parses the given {@code String} of arguments in the context of the ListTasksCommand
     * and returns an ListTasksCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListTasksCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_CONTACT, PREFIX_FLAG);

        Optional<String> keywordFilter = argMultimap.getValue(PREFIX_TITLE);
        List<String> contactFilters = argMultimap.getAllValues(PREFIX_CONTACT);
        List<String> flags = argMultimap.getAllValues(PREFIX_FLAG);

        if (keywordFilter.isEmpty() && contactFilters.isEmpty() && flags.isEmpty() && !args.trim().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ListTasksCommand.MESSAGE_USAGE));
        }

        Set<Index> personsIndexes;
        Predicate<Task> basePredicate = Model.PREDICATE_INCOMPLETE_TASKS;

        try {
            personsIndexes = TaskParserUtil.parseIndexes(contactFilters);
            if (flags.contains(ALL_FLAG)) {
                basePredicate = Model.PREDICATE_SHOW_ALL_TASKS;
            }
            if (flags.contains(COMPLETED_FLAG)) {
                basePredicate = Model.PREDICATE_COMPLETED_TASKS;
            }
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ListTasksCommand.MESSAGE_USAGE), pe);
        }
        return new ListTasksCommand(basePredicate, keywordFilter, personsIndexes);
    }

}
