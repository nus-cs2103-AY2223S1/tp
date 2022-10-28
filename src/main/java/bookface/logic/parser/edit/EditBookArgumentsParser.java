package bookface.logic.parser.edit;

import static bookface.logic.parser.CliSyntax.PREFIX_AUTHOR;
import static bookface.logic.parser.CliSyntax.PREFIX_TITLE;

import bookface.commons.core.Messages;
import bookface.commons.core.index.Index;
import bookface.logic.commands.edit.EditBookCommand;
import bookface.logic.parser.ArgumentMultimap;
import bookface.logic.parser.ArgumentTokenizer;
import bookface.logic.parser.Parseable;
import bookface.logic.parser.ParserUtil;
import bookface.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates the relevant new EditCommand object for the relevant entity to be added
 */
public class EditBookArgumentsParser implements Parseable<EditBookCommand> {
    /**
     * Parses the given arguments in the context of the EditCommand
     * and returns a EditCommand object for execution.
     */
    @Override
    public EditBookCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, EditBookCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_AUTHOR, PREFIX_TITLE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    EditBookCommand.MESSAGE_USAGE), pe);
        }

        EditBookCommand.EditBookDescriptor editBookDescriptor = new EditBookCommand.EditBookDescriptor();
        if (argMultimap.getValue(PREFIX_TITLE).isPresent()) {
            editBookDescriptor.setTitle(ParserUtil.parseTitle(argMultimap.getValue(PREFIX_TITLE).get()));
        }
        if (argMultimap.getValue(PREFIX_AUTHOR).isPresent()) {
            editBookDescriptor.setAuthor(ParserUtil.parseAuthor(argMultimap.getValue(PREFIX_AUTHOR).get()));
        }

        if (!editBookDescriptor.isAnyFieldEdited()) {
            throw new ParseException(bookface.logic.commands.edit.EditBookCommand.MESSAGE_NOT_EDITED);
        }

        return new EditBookCommand(index, editBookDescriptor);
    }
}
