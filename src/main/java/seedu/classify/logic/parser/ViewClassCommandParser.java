package seedu.classify.logic.parser;

import seedu.classify.commons.core.Messages;
import seedu.classify.logic.commands.ViewClassCommand;
import seedu.classify.logic.parser.exceptions.ParseException;
import seedu.classify.model.student.Class;
import seedu.classify.model.student.ClassPredicate;

/**
 * Parses input arguments and creates a new ViewClassCommand object
 */
public class ViewClassCommandParser implements Parser<ViewClassCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewClassCommand
     * and returns a ViewClassCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewClassCommand parse(String args) throws ParseException {
        try {
            Class className = ParserUtil.parseClass(args);
            return new ViewClassCommand(new ClassPredicate(className));
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, ViewClassCommand.MESSAGE_USAGE));
        }
    }

}
