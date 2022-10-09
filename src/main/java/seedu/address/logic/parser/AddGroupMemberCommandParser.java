package seedu.address.logic.parser;

import seedu.address.logic.commands.AddGroupMemberCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import java.util.NoSuchElementException;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

public class AddGroupMemberCommandParser implements Parser<AddGroupMemberCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code RemarkCommand}
     * and returns a {@code RemarkCommand} object for execution.
     * @throws seedu.address.logic.parser.exceptions.ParseException if the user input does not conform the expected format
     */
    public AddGroupMemberCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_GROUP, PREFIX_NAME);

            String personGroup;
            String name;

            try {
                personGroup = argMultimap.getPreamble();
                name = argMultimap.getValue(PREFIX_GROUP).get();

            } catch (NoSuchElementException e) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddGroupMemberCommand.MESSAGE_USAGE), e);
            }
            return new AddGroupMemberCommand(personGroup, name);
        }
}
