package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;

import seedu.address.logic.commands.HidePatientsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicates.TagContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new HidePatientCommand.
 */
public class HidePatientsCommandParser implements Parser<HidePatientsCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the HidePatientCommand.
     * and returns a FilterPatientCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public HidePatientsCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TAG, PREFIX_NAME);
        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            return new HidePatientsCommand(
                    new TagContainsKeywordsPredicate(argMultimap.getValue(PREFIX_TAG).orElse("")));
        } else if (argMultimap.getValue(PREFIX_NAME).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HidePatientsCommand.MESSAGE_USAGE));
        }
        String trimmedArgs = argMultimap.getValue(PREFIX_NAME).orElse("").trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, HidePatientsCommand.MESSAGE_USAGE));
        }
        String[] keywords = trimmedArgs.split("\\s+");
        return new HidePatientsCommand(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
    }

}
