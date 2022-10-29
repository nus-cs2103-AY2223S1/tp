package seedu.address.logic.parser.profile;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPTION;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.profile.DeleteProfileCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteProfileCommand object
 */
public class DeleteProfileCommandParser implements Parser<DeleteProfileCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteProfileCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_OPTION);
        String indexInput = argMultimap.getOptionArgs();
        if (indexInput.isEmpty()) {
            throw new ParseException(
                    String.format(DeleteProfileCommand.MESSAGE_MISSING_INDEX, DeleteProfileCommand.MESSAGE_USAGE));
        }
        try {
            Index index = ParserUtil.parseIndex(indexInput);
            return new DeleteProfileCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteProfileCommand.MESSAGE_USAGE), pe);
        }
    }

}
