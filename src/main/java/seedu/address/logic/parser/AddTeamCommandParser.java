package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.teams.AddTeamCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.group.Group;

/**
 * Parses input arguments and creates a new TeamCommand object
 */
public class AddTeamCommandParser implements Parser<AddTeamCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * AddGroupCommand.
     *
     * @param args refer to the subsequent arguments after the initial command word.
     * @return an AddGroupCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddTeamCommand parse(String args) throws ParseException {
        try {
            requireNonNull(args);
            String trimmedArgs = args.trim();
            Group newGroup = ParserUtil.parseGroup(trimmedArgs);
            return new AddTeamCommand(newGroup);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(
                            MESSAGE_INVALID_COMMAND_FORMAT, AddTeamCommand.MESSAGE_USAGE),
                    pe);
        }
    }
}
