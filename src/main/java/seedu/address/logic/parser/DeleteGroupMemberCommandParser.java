package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.NoSuchElementException;

import seedu.address.logic.commands.DeleteGroupMemberCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteGroupMemberCommand object
 */
public class DeleteGroupMemberCommandParser implements Parser<DeleteGroupMemberCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteGroupMemberCommand
     * and returns a DeleteGroupMemberCommand object for execution.
     * @param args user input
     * @return command to add member to specified group
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteGroupMemberCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_GROUP, PREFIX_NAME);

        String personGroup;
        String name;

        try {
            personGroup = argMultimap.getValue(PREFIX_GROUP).get();
            name = argMultimap.getValue(PREFIX_NAME).get();
        } catch (NoSuchElementException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteGroupMemberCommand.MESSAGE_USAGE), e);
        }
        return new DeleteGroupMemberCommand(personGroup, name);
    }
}
