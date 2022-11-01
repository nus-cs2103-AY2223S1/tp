package seedu.uninurse.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.uninurse.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;

import java.util.List;
import java.util.NoSuchElementException;

import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.logic.commands.EditTaskCommand;
import seedu.uninurse.logic.commands.EditTaskCommand.EditTaskDescriptor;
import seedu.uninurse.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditTaskCommand object
 */
public class EditTaskCommandParser implements Parser<EditTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditTaskCommand
     * and returns an EditTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditTaskCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TASK_DESCRIPTION);

        try {
            List<Index> indices = ParserUtil.parseTwoIndex(argMultimap.getPreamble());
            EditTaskDescriptor editTaskDescriptor =
                    ParserUtil.parseEditTaskDescriptor(argMultimap.getValue(PREFIX_TASK_DESCRIPTION).orElseThrow());

            return new EditTaskCommand(indices.get(0), indices.get(1), editTaskDescriptor);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditTaskCommand.MESSAGE_USAGE), pe);
        } catch (NoSuchElementException nse) {
            throw new ParseException(EditTaskCommand.MESSAGE_NOT_EDITED);
        }
    }
}
