package bookface.logic.parser.edit;

import static bookface.logic.parser.CliSyntax.PREFIX_EMAIL;
import static bookface.logic.parser.CliSyntax.PREFIX_NAME;
import static bookface.logic.parser.CliSyntax.PREFIX_PHONE;
import static bookface.logic.parser.CliSyntax.PREFIX_TAG;

import bookface.commons.core.Messages;
import bookface.commons.core.index.Index;
import bookface.logic.commands.edit.EditUserCommand;
import bookface.logic.parser.ArgumentMultimap;
import bookface.logic.parser.ArgumentTokenizer;
import bookface.logic.parser.Parseable;
import bookface.logic.parser.ParserUtil;
import bookface.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates the relevant new EditCommand object for the relevant entity to be added
 */
public class EditUserArgumentsParser implements Parseable<EditUserCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public EditUserCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, EditUserCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    EditUserCommand.MESSAGE_USAGE), pe);
        }

        EditUserCommand.EditPersonDescriptor editPersonDescriptor = new EditUserCommand.EditPersonDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }

        ParserUtil.parseTagsWrappedInOptional(argMultimap.getAllValues(PREFIX_TAG))
                .ifPresent(editPersonDescriptor::setTags);

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(bookface.logic.commands.edit.EditUserCommand.MESSAGE_NOT_EDITED);
        }

        return new EditUserCommand(index, editPersonDescriptor);
    }
}
