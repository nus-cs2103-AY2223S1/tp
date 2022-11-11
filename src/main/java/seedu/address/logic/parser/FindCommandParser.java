package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.teammate.ContainsTagPredicate;
import seedu.address.model.teammate.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TAG);

        String namesJoined = argMultimap.getPreamble();
        List<String> nameKeywords = Arrays.asList(namesJoined.split("\\s+"));
        if (namesJoined.equals("")) {
            nameKeywords = new ArrayList<>();
        }

        List<String> tagKeywords = argMultimap.getAllValues(PREFIX_TAG);
        tagKeywords = tagKeywords.stream().map(x -> x.trim().toLowerCase()).collect(Collectors.toList());

        return new FindCommand(new NameContainsKeywordsPredicate(nameKeywords),
                new ContainsTagPredicate(tagKeywords));
    }

}
