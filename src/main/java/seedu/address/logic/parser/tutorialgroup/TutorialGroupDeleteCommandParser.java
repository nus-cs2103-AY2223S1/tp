package seedu.address.logic.parser.tutorialgroup;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.tutorialgroup.TutorialGroupDeleteCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new TutorialGroupDeleteCommand object
 */
public class TutorialGroupDeleteCommandParser implements Parser<TutorialGroupDeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TutorialGroupDeleteCommand
     * and returns a TutorialGroupDeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TutorialGroupDeleteCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new TutorialGroupDeleteCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, TutorialGroupDeleteCommand.MESSAGE_USAGE), pe);
        }
    }

}
