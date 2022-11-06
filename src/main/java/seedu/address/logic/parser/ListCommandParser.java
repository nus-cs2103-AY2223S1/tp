package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LIST_ALL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LIST_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LIST_MARKED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LIST_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LIST_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LIST_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LIST_UNMARKED;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.commands.list.ListAllCommand;
import seedu.address.logic.commands.list.ListCommand;
import seedu.address.logic.commands.list.ListDeadlineCommand;
import seedu.address.logic.commands.list.ListMarkedCommand;
import seedu.address.logic.commands.list.ListModuleCommand;
import seedu.address.logic.commands.list.ListNameCommand;
import seedu.address.logic.commands.list.ListTagCommand;
import seedu.address.logic.commands.list.ListUnmarkedCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskIsDonePredicate;

/**
 * Parses input arguments and creates a matching new List command
 */
public class ListCommandParser implements Parser<ListCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a ListCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListCommand parse(String args) throws ParseException {

        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        PREFIX_LIST_ALL, PREFIX_LIST_UNMARKED, PREFIX_LIST_MARKED,
                        PREFIX_LIST_MODULE, PREFIX_LIST_TAG, PREFIX_LIST_DEADLINE, PREFIX_LIST_NAME);

        Prefix[] prefixes = new Prefix[]
            {PREFIX_LIST_ALL, PREFIX_LIST_UNMARKED, PREFIX_LIST_MARKED,
            PREFIX_LIST_MODULE, PREFIX_LIST_TAG, PREFIX_LIST_DEADLINE, PREFIX_LIST_NAME};
        List<Predicate<Task>> predicates = new ArrayList<>();

        for (int i = 0; i < prefixes.length; i++) {
            Prefix currPrefix = prefixes[i];
            if (argMultimap.getValue(currPrefix).isPresent()) {
                predicates.add(getPredicate(currPrefix, argMultimap.getValue(currPrefix).get()));
            }
        }

        if (predicates.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }

        return new ListCommand(predicates);
    }

    private Predicate<Task> getPredicate(Prefix prefix, String keyword) throws ParseException {
        switch (prefix.toString()) {
        case ListAllCommand.COMMAND_WORD:
            return PREDICATE_SHOW_ALL_PERSONS;
        case ListModuleCommand.COMMAND_WORD:
            return new ListModuleCommandParser().getPredicate(keyword);
        case ListUnmarkedCommand.COMMAND_WORD:
            return new TaskIsDonePredicate(List.of("false"));
        case ListMarkedCommand.COMMAND_WORD:
            return new TaskIsDonePredicate(List.of("true"));
        case ListDeadlineCommand.COMMAND_WORD:
            return new ListDeadlineCommandParser().getPredicate(keyword);
        case ListTagCommand.COMMAND_WORD:
            return new ListTagCommandParser().getPredicate(keyword);
        case ListNameCommand.COMMAND_WORD:
            return new ListNameCommandParser().getPredicate(keyword);
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
