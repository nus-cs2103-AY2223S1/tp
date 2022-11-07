package paymelah.logic.parser;

import static java.util.Objects.requireNonNull;
import static paymelah.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static paymelah.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static paymelah.logic.parser.CliSyntax.PREFIX_NAME;
import static paymelah.logic.parser.CliSyntax.PREFIX_PHONE;
import static paymelah.logic.parser.CliSyntax.PREFIX_TAG;
import static paymelah.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static paymelah.logic.parser.ParserUtil.argumentMultimapToPersonDescriptor;

import paymelah.commons.core.index.Index;
import paymelah.logic.commands.EditCommand;
import paymelah.logic.parser.ParserUtil.PersonDescriptor;
import paymelah.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_TELEGRAM,
                        PREFIX_ADDRESS, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        PersonDescriptor personDescriptor = argumentMultimapToPersonDescriptor(argMultimap);

        if (!personDescriptor.isAnyFieldSet()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, personDescriptor);
    }
}
