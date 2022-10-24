package seedu.address.logic.parser.event;

import static seedu.address.logic.parser.CliSyntax.PREFIX_OPTION;

import java.util.Arrays;

import seedu.address.logic.commands.event.FindEventCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.TitleContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindEventCommand object
 */
public class FindEventCommandParser implements Parser<FindEventCommand> {

    public static final String MESSAGE_MISSING_KEYWORDS = "Provide at least one keyword.\n";

    /**
     * Parses the given {@code String} of arguments in the context of the FindEventCommand
     * and returns a FindEventCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindEventCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_OPTION);
        String trimmedArgs = argMultimap.getOptionArgs();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_MISSING_KEYWORDS + FindEventCommand.MESSAGE_USAGE));
        }

        String[] titleKeywords = trimmedArgs.split("\\s+");

        return new FindEventCommand(new TitleContainsKeywordsPredicate(Arrays.asList(titleKeywords)));
    }

}
