package seedu.classify.logic.parser;

import java.util.Arrays;

import seedu.classify.commons.core.Messages;
import seedu.classify.logic.commands.FindCommand;
import seedu.classify.logic.parser.exceptions.ParseException;
import seedu.classify.model.student.IdPredicate;
import seedu.classify.model.student.NameContainsKeywordsPredicate;

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
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultiMap = ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_STUDENT_NAME,
                CliSyntax.PREFIX_ID);

        if (argMultiMap.getValue(CliSyntax.PREFIX_STUDENT_NAME).isPresent()) {
            String[] nameKeywords = argMultiMap.getValue(CliSyntax.PREFIX_STUDENT_NAME).get().split("\\s+");
            return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        } else if (argMultiMap.getValue(CliSyntax.PREFIX_ID).isPresent()) {
            return new FindCommand(
                    new IdPredicate(ParserUtil.parseId(argMultiMap.getValue(CliSyntax.PREFIX_ID).get())));
        } else {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

    }

}
