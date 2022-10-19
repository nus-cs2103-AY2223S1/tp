package seedu.address.logic.parser.profile;

import static seedu.address.logic.parser.CliSyntax.PREFIX_OPTION;

import java.util.Arrays;

import seedu.address.logic.commands.profile.FindProfileCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.profile.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindProfileCommandParser implements Parser<FindProfileCommand> {

    public static final String MESSAGE_MISSING_KEYWORDS = "Provide at least one keyword.\n";

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindProfileCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_OPTION);
        String trimmedArgs = argMultimap.getOptionArgs();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_MISSING_KEYWORDS + FindProfileCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindProfileCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
