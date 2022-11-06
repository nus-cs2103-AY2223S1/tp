package seedu.uninurse.logic.parser;

import static seedu.uninurse.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;

import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.commons.exceptions.IllegalValueException;
import seedu.uninurse.logic.commands.AddTaskCommand;
import seedu.uninurse.logic.parser.exceptions.ParseException;
import seedu.uninurse.model.task.Task;

/**
 * Parses input arguments and creates a new {@code AddTaskCommand}.
 */
public class AddTaskCommandParser implements Parser<AddTaskCommand> {
    /**
     * Parses the given String of arguments in the context of the AddTaskCommand
     * and returns an AddTaskCommand object for execution.
     *
     * @param args The given user input to be parsed.
     * @return AddTaskCommand.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public AddTaskCommand parse(String args) throws ParseException {
        requireAllNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TASK_DESCRIPTION);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddTaskCommand.MESSAGE_USAGE), ive);
        }

        Task newTask = ParserUtil.parseTask(argMultimap.getValue(PREFIX_TASK_DESCRIPTION).get());

        return new AddTaskCommand(index, newTask);
    }
}
