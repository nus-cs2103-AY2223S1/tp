package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MESSAGE_COUNT;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AddResponseCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.Response;

/**
 * Parses input arguments and creates a new {@code AddResponseCommand} object
 */
public class AddResponseCommandParser implements Parser<AddResponseCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the
     * {@code AddResponseCommand} and returns a {@code AddResponseCommand}
     * object for execution.
     * @throws ParseException if user input does not conform to the expected format
     */
    @Override
    public AddResponseCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MESSAGE_COUNT);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddResponseCommand.MESSAGE_USAGE));
        }

        if (!argMultimap.getValue(PREFIX_MESSAGE_COUNT).isPresent()) {
            throw new ParseException(AddResponseCommand.MESSAGE_MISSING_PREFIX);
        }

        Response response = ParserUtil.parseResponse(argMultimap.getValue(PREFIX_MESSAGE_COUNT).orElse(""));

        return new AddResponseCommand(index, response);
    }
}
