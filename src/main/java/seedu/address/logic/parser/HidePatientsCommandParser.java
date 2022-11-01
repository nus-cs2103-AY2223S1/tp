package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_EMPTY_NAME;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TAGS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;

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
            List<String> tags = argMultimap.getAllValues(PREFIX_TAG);
            boolean areValidTags = areValidTags(tags);
            if (!areValidTags) {
                throw new ParseException(MESSAGE_INVALID_TAGS);
            }
            return new HidePatientsCommand(
                    new TagContainsKeywordsPredicate(argMultimap.getAllValues(PREFIX_TAG)));
        } else if (argMultimap.getValue(PREFIX_NAME).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HidePatientsCommand.MESSAGE_USAGE));
        }
        List<String> keywords = argMultimap.getAllValues(PREFIX_NAME);
        if (keywords.stream().anyMatch(x -> x.equals(""))) {
            throw new ParseException(MESSAGE_EMPTY_NAME);
        }
        return new HidePatientsCommand(new NameContainsKeywordsPredicate(keywords));
    }

    /**
     * Checks if given tags match ear, nose or throat.
     * @param tags The values gotten from the user after the t/ prefix.
     * @return Whether tags are all valid.
     */
    public boolean areValidTags(List<String> tags) {
        for (String s: tags) {
            if (!s.equalsIgnoreCase("ear") && !s.equalsIgnoreCase("nose")
                && !s.equalsIgnoreCase("throat")) {
                return false;
            }
        }
        return true;
    }

}
