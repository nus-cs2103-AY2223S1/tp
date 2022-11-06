package seedu.foodrem.logic.parser.tagcommandparser;

import static java.util.Objects.requireNonNull;

import seedu.foodrem.commons.core.Messages;
import seedu.foodrem.commons.core.index.Index;
import seedu.foodrem.commons.util.StringUtil;
import seedu.foodrem.logic.commands.tagcommands.UntagCommand;
import seedu.foodrem.logic.parser.ArgumentMultimap;
import seedu.foodrem.logic.parser.ArgumentTokenizer;
import seedu.foodrem.logic.parser.CliSyntax;
import seedu.foodrem.logic.parser.Parser;
import seedu.foodrem.logic.parser.ParserUtil;
import seedu.foodrem.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UntagCommand object
 */
public class UntagCommandParser implements Parser<UntagCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the UntagCommand
     * and returns an UntagCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public UntagCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_NAME);
        Index index = StringUtil.validateAndGetIndexFromString(argMultimap.getPreamble().trim(),
                                                               UntagCommand.getUsage());
        if (!ParserUtil.arePrefixesPresent(argMultimap, CliSyntax.PREFIX_NAME)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, UntagCommand.getUsage()));
        }
        String name = ParserUtil.parseTagName(argMultimap.getValue(CliSyntax.PREFIX_NAME).get()).toString();

        return new UntagCommand(name, index);
    }
}
