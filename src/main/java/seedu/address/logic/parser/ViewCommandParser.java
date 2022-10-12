package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_NAME;

import java.util.Arrays;

import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.Id;
import seedu.address.model.student.IdPredicate;
import seedu.address.model.student.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new ViewCommand object
 */
public class ViewCommandParser implements Parser<ViewCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewCommand
     * and returns a ViewCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultiMap = ArgumentTokenizer.tokenize(args, PREFIX_STUDENT_NAME, PREFIX_ID);

        if (argMultiMap.getValue(PREFIX_STUDENT_NAME).isPresent()) {
            String[] nameKeywords = argMultiMap.getValue(PREFIX_STUDENT_NAME).get().split("\\s+");
            return new ViewCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        } else if (argMultiMap.getValue(PREFIX_ID).isPresent()) {
            return new ViewCommand(new IdPredicate(new Id(argMultiMap.getValue(PREFIX_ID).get())));
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
        }

    }

}
