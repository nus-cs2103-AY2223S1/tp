package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindNameCommand;
import seedu.address.logic.commands.FindSubjectCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.SubjectContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindSubjectCommandParser implements Parser<FindSubjectCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @return FindSubjectCommand
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindSubjectCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindNameCommand.MESSAGE_USAGE));
        }

        String[] subjectKeywords = trimmedArgs.split("\\s+");

        return new FindSubjectCommand(new SubjectContainsKeywordsPredicate(Arrays.asList(subjectKeywords)));
    }

}
