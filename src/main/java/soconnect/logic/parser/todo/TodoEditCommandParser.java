package soconnect.logic.parser.todo;

import static java.util.Objects.requireNonNull;
import static soconnect.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static soconnect.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static soconnect.logic.parser.CliSyntax.PREFIX_TAG;

import soconnect.commons.core.Messages;
import soconnect.commons.core.index.Index;
import soconnect.logic.commands.todo.TodoEditCommand;
import soconnect.logic.commands.todo.TodoEditCommand.EditTodoDescriptor;
import soconnect.logic.parser.ArgumentMultimap;
import soconnect.logic.parser.ArgumentTokenizer;
import soconnect.logic.parser.Parser;
import soconnect.logic.parser.ParserUtil;
import soconnect.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code TodoEditCommand} object.
 */
public class TodoEditCommandParser implements Parser<TodoEditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code TodoEditCommand}
     * and returns an {@code TodoEditCommand} object for execution.
     *
     * @throws ParseException If the user input does not conform with the expected format.
     */
    public TodoEditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION, PREFIX_PRIORITY,
            PREFIX_TAG);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                TodoEditCommand.MESSAGE_USAGE), pe);
        }

        EditTodoDescriptor editTodoDescriptor = createEditedTodo(argMultimap);
        return new TodoEditCommand(index, editTodoDescriptor);
    }

    private EditTodoDescriptor createEditedTodo(ArgumentMultimap argMultimap) throws ParseException {
        EditTodoDescriptor editTodoDescriptor = new EditTodoDescriptor();
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editTodoDescriptor.setDescription(ParserUtil.parseDescription(
                argMultimap.getValue(PREFIX_DESCRIPTION).get()));
        }

        if (argMultimap.getValue(PREFIX_PRIORITY).isPresent()) {
            editTodoDescriptor.setPriority(ParserUtil.parsePriority(
                argMultimap.getValue(PREFIX_PRIORITY).get()));
        }

        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            editTodoDescriptor.setTags(ParserUtil.parseTags(
                argMultimap.getAllValues(PREFIX_TAG)));
        }

        if (!editTodoDescriptor.isAnyFieldEdited()) {
            throw new ParseException(TodoEditCommand.MESSAGE_NOT_EDITED);
        }
        return editTodoDescriptor;
    }
}
