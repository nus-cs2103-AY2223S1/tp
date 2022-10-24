package taskbook.logic.parser.tasks;

import java.util.regex.Pattern;
import java.util.stream.Stream;

import taskbook.commons.core.Messages;
import taskbook.logic.commands.tasks.TaskSortAddedChronologicalCommand;
import taskbook.logic.commands.tasks.TaskSortCommand;
import taskbook.logic.commands.tasks.TaskSortDateChronologicalCommand;
import taskbook.logic.commands.tasks.TaskSortDateReverseChronologicalCommand;
import taskbook.logic.commands.tasks.TaskSortDescriptionAlphabeticalCommand;
import taskbook.logic.commands.tasks.TaskSortDescriptionReverseAlphabeticalCommand;
import taskbook.logic.parser.ArgumentMultimap;
import taskbook.logic.parser.ArgumentTokenizer;
import taskbook.logic.parser.CliSyntax;
import taskbook.logic.parser.Parser;
import taskbook.logic.parser.Prefix;
import taskbook.logic.parser.exceptions.ParseException;
import taskbook.logic.parser.tasks.enums.SortTypes;

/**
 * Parses input arguments and creates a new TaskSortCommand.
 */
public class TaskSortCommandParser implements Parser<TaskSortCommand> {
    // Note: the space at the start of the arguments is necessary due to ArgumentTokenizer behavior.
    private static final Pattern SORT =
            Pattern.compile(String.format("\\s+%s.*", CliSyntax.PREFIX_SORT_TYPE.getPrefix()));

    /**
     * Parses the given {@code String} of arguments in the context of the TaskSortCommand
     * and returns an TaskSortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public TaskSortCommand parse(String args) throws ParseException {
        if (args.contains(CliSyntax.PREFIX_ASSIGN_FROM.getPrefix())
                && args.contains(CliSyntax.PREFIX_ASSIGN_TO.getPrefix())) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, TaskSortCommand.MESSAGE_USAGE));
        }

        if (SORT.matcher(args).matches()) {
            return parseWithPrefix(args, CliSyntax.PREFIX_SORT_TYPE);
        }

        throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, TaskSortCommand.MESSAGE_USAGE));
    }

    private TaskSortCommand parseWithPrefix(String args, Prefix firstPrefix) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, firstPrefix);

        if (!arePrefixesPresent(argMultimap, firstPrefix)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(
                    Messages.MESSAGE_INVALID_COMMAND_FORMAT, TaskSortCommand.MESSAGE_USAGE));
        }
        SortTypes sortTypes = SortTypes.parse(argMultimap.getValue(CliSyntax.PREFIX_SORT_TYPE).orElse("INVALID"));
        switch (sortTypes) {
        case DESC_ALPHABETICAL:
            return new TaskSortDescriptionAlphabeticalCommand();
        case DESC_REVERSE_ALPHABETICAL:
            return new TaskSortDescriptionReverseAlphabeticalCommand();
        case CHRONOLOGICAL_ADDED:
            return new TaskSortAddedChronologicalCommand();
        case CHRONOLOGICAL_DATE:
            return new TaskSortDateChronologicalCommand();
        case REVERSE_CHRONOLOGICAL_DATE:
            return new TaskSortDateReverseChronologicalCommand();
        default:
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    TaskSortCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
