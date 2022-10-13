package seedu.foodrem.logic.parser.tagcommandparser;

import static seedu.foodrem.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.foodrem.logic.commands.tagcommands.NewTagCommand;
import seedu.foodrem.logic.parser.ArgumentMultimap;
import seedu.foodrem.logic.parser.ArgumentTokenizer;
import seedu.foodrem.logic.parser.Parser;
import seedu.foodrem.logic.parser.ParserUtil;
import seedu.foodrem.logic.parser.exceptions.ParseException;
import seedu.foodrem.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddTagCommand object
 */
public class NewTagCommandParser implements Parser<NewTagCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddTagCommand
     * and returns an AddTagCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public NewTagCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, NewTagCommand.MESSAGE_USAGE));
        }

        String name = argMultimap.getValue(PREFIX_NAME).get().trim();

        Tag tag = new Tag(name);

        return new NewTagCommand(tag);
    }
}
