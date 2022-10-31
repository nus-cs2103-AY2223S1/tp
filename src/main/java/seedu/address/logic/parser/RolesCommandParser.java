package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLES;

import java.util.NoSuchElementException;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.RolesCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.position.Professor;

/**
 * Parses input arguments and creates a new RolesCommand object
 */
public class RolesCommandParser implements Parser<RolesCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code RolesCommand}
     * and returns a {@code RolesCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RolesCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ROLES);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    RolesCommand.MESSAGE_USAGE), ive);
        } catch (NoSuchElementException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    RolesCommand.MESSAGE_USAGE), e);
        }


        String roles = argMultimap.getValue(PREFIX_ROLES).orElse("");
        String[] rolesArray = roles.split(", ");
        for (String role: rolesArray) {

            if (!Professor.isValidRole(role)) {
                throw new ParseException(Professor.MESSAGE_CONSTRAINTS);
            }
        }

        return new RolesCommand(index, roles);
    }
}
