package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.SocialCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PersonContainsSocial;

/**
 * Parses input arguments and creates a new SocialCommand object
 */
public class SocialCommandParser implements Parser<SocialCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SocialCommand
     * and returns a SocialCommand object for execution.
     *
     * @param args inputted argument
     * @return new SocialCommand
     * @throws ParseException if input is invalid
     */
    public SocialCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SocialCommand.MESSAGE_USAGE));
        }

        return new SocialCommand(new PersonContainsSocial(trimmedArgs));
    }
}
