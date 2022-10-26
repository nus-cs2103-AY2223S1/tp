package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;

import seedu.address.logic.commands.UnhidePatientsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicates.TagContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new UnhidePatientCommand.
 */
public class UnhidePatientsCommandParser implements Parser<UnhidePatientsCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the HidePatientCommand.
     * and returns a FilterPatientCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnhidePatientsCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TAG, PREFIX_NAME);
        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            return new UnhidePatientsCommand(
                    new TagContainsKeywordsPredicate(argMultimap.getAllValues(PREFIX_TAG)));
        }
        if (argMultimap.getValue(PREFIX_NAME).isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnhidePatientsCommand.MESSAGE_USAGE));
        }
        List<String> nameKeywords = argMultimap.getAllValues(PREFIX_NAME);

        return new UnhidePatientsCommand(new NameContainsKeywordsPredicate(nameKeywords));
    }
}
