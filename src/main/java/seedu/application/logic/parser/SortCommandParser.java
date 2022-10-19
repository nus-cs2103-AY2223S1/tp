package seedu.application.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.application.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.application.logic.parser.CliSyntax.PREFIX_ORDER;
import static seedu.application.logic.parser.CliSyntax.PREFIX_REVERSE;

import java.util.Optional;

import seedu.application.logic.commands.SortByCompanyCommand;
import seedu.application.logic.commands.SortByDateCommand;
import seedu.application.logic.commands.SortByPositionCommand;
import seedu.application.logic.commands.SortCommand;
import seedu.application.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ORDER,
                PREFIX_REVERSE);

        boolean shouldReverse = argMultimap.hasPrefix(PREFIX_REVERSE);
        Optional<String> orderKeyword = argMultimap.getValue(PREFIX_ORDER);
        if (orderKeyword.isEmpty()) {
            // Sort by date by default if no order specified
            return new SortByDateCommand(shouldReverse);
        }

        switch (orderKeyword.get().toLowerCase()) {
        case SortByCompanyCommand.ORDER_KEYWORD:
            return new SortByCompanyCommand(shouldReverse);

        case SortByPositionCommand.ORDER_KEYWORD:
            return new SortByPositionCommand(shouldReverse);

        case SortByDateCommand.ORDER_KEYWORD:
            return new SortByDateCommand(shouldReverse);

        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SortCommand.MESSAGE_USAGE));
        }
    }

}
