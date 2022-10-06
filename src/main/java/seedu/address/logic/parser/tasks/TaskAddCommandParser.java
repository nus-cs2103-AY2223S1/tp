package seedu.address.logic.parser.tasks;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGN_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGN_TO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import java.util.stream.Stream;

import seedu.address.logic.commands.tasks.TaskAddCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.task.Description;
import seedu.address.model.task.enums.Assignment;

/**
 * Parses input arguments and creates a new TaskAddCommand object
 */
public class TaskAddCommandParser implements Parser<TaskAddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TaskAddCommand
     * and returns an TaskAddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public TaskAddCommand parse(String args) throws ParseException {
        // Note: the space at the start of the arguments is necessary due to ArgumentTokenizer behavior.
        if (args.startsWith(" " + PREFIX_ASSIGN_TO.getPrefix())) {
            return parseWithPrefix(args, PREFIX_ASSIGN_TO);
        }

        // Note: the space at the start of the arguments is necessary due to ArgumentTokenizer behavior.
        if (args.startsWith(" " + PREFIX_ASSIGN_FROM.getPrefix())) {
            return parseWithPrefix(args, PREFIX_ASSIGN_FROM);
        }

        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskAddCommand.MESSAGE_USAGE));
    }

    private TaskAddCommand parseWithPrefix(String args, Prefix firstPrefix) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, firstPrefix, PREFIX_DESCRIPTION);

        if (!arePrefixesPresent(argMultimap, firstPrefix, PREFIX_DESCRIPTION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskAddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(firstPrefix).get());
        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        Assignment assignment =
                firstPrefix.equals(PREFIX_ASSIGN_FROM)
                ? Assignment.FROM
                : Assignment.TO;

        return new TaskAddCommand(name, description, assignment);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
