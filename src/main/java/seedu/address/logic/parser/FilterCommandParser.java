package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.ApplicationStatus;
import seedu.address.model.person.InternshipHasApplicationStatusPredicate;

public class FilterCommandParser implements Parser<FilterCommand> {

    public FilterCommand parse(String args) throws ParseException {
        String trimmedLowerCaseArgs = args.trim().toLowerCase();
        if (trimmedLowerCaseArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        return new FilterCommand(
                new InternshipHasApplicationStatusPredicate(ApplicationStatus.parse(trimmedLowerCaseArgs)));
    }
}
