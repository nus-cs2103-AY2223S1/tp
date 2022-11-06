package seedu.address.logic.parser.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.task.DeadlineTaskCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.TaskParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.Deadline;

/**
 * Parses input arguments and creates a new DeadlineTaskCommand object
 */
public class DeadlineTaskCommandParser implements Parser<DeadlineTaskCommand> {

    public static final String MESSAGE_DATE_PARSE_FAILURE = "Could not parse the date provided.";

    /**
     * Parses the given {@code String} of arguments in the context of the DeadlineTaskCommand
     * and returns an DeadlineTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeadlineTaskCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DEADLINE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());

        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeadlineTaskCommand.MESSAGE_USAGE),
                    pe
            );
        }


        String deadlineArg =
                argMultimap
                        .getValue(PREFIX_DEADLINE)
                        .orElseThrow(() -> new ParseException(
                                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeadlineTaskCommand.MESSAGE_USAGE))
                        );

        Deadline deadline = TaskParserUtil.parseDeadline(deadlineArg);
        return new DeadlineTaskCommand(index, deadline);
    }
}
