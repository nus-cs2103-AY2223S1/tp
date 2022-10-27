package seedu.studmap.logic.parser;

import static seedu.studmap.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_ASSIGNMENT;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;

import seedu.studmap.logic.commands.FilterCommand;
import seedu.studmap.logic.parser.exceptions.ParseException;
import seedu.studmap.model.student.AssignmentContainsKeywordsPredicate;
import seedu.studmap.model.student.ModuleContainsKeywordsPredicate;
import seedu.studmap.model.student.TagContainsKeywordsPredicate;


/**
 * Parses input arguments and create a new FilterCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterCommand
     * and returns a FilterCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public FilterCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MODULE, PREFIX_ASSIGNMENT, PREFIX_TAG);
        if (trimmedArgs.length() == 2 || trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords;

        if (argMultimap.getValue(PREFIX_ASSIGNMENT).isPresent()) {
            nameKeywords = argMultimap.getValue(PREFIX_ASSIGNMENT).get().split("\\s+");
            return new FilterCommand(new AssignmentContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        }
        if (argMultimap.getValue(PREFIX_MODULE).isPresent()) {
            nameKeywords = argMultimap.getValue(PREFIX_MODULE).get().split("\\s+");
            return new FilterCommand(new ModuleContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        }
        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            nameKeywords = argMultimap.getValue(PREFIX_TAG).get().split("\\s+");
            return new FilterCommand(new TagContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }
    }
}
