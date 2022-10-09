package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.FindTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.TagContainsKeywordPredicate;

public class FindTagCommandParser {
    public FindTagCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTagCommand.MESSAGE_USAGE));
        }

        String[] keyword = trimmedArgs.split("\\s+");

        return new FindTagCommand(new TagContainsKeywordPredicate(keyword[0]));
    }
}
