package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.DisplayGroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.group.FullGroupNamePredicate;

/**
 * Parses input arguments and creates a new DisplayGroupCommand object
 */
public class DisplayGroupCommandParser implements Parser<DisplayGroupCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DisplayGroupCommand
     * and returns a DisplayGroupCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DisplayGroupCommand parse(String args) throws ParseException {

        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args);
        if (argumentMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DisplayGroupCommand.MESSAGE_USAGE));
        }

        String name = argumentMultimap.getPreamble();
        return new DisplayGroupCommand(new FullGroupNamePredicate(name));



    }

}
