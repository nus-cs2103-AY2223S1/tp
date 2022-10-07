package seedu.address.logic.parser;

import java.util.Arrays;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;

public class ListCommandParser implements Parser {
    public ListCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            return new ListCommand(null, null, null, null);
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new ListCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
