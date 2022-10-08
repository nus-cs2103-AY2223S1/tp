package jarvis.logic.parser;

import static jarvis.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static jarvis.logic.parser.CliSyntax.PREFIX_NAME;
import static java.util.Objects.requireNonNull;

import jarvis.commons.core.index.Index;
import jarvis.logic.commands.EditStudentCommand;
import jarvis.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditStudentCommandParser implements Parser<EditStudentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditStudentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditStudentCommand.MESSAGE_USAGE),
                    pe);
        }

        EditStudentCommand.EditStudentDescriptor editStudentDescriptor = new EditStudentCommand.EditStudentDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editStudentDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }

        if (!editStudentDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditStudentCommand.MESSAGE_NOT_EDITED);
        }

        return new EditStudentCommand(index, editStudentDescriptor);
    }
}
