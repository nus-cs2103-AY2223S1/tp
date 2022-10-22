package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.AddQCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.question.Description;
import seedu.address.model.question.ImportantTag;
import seedu.address.model.question.Question;

/**
 * Parses input arguments and creates a new AddQCommand object
 */
public class AddQCommandParser implements Parser<AddQCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddQCommand
     * and returns an AddQCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddQCommand parse(String args) throws ParseException {
        try {
            Description description = ParserUtil.parseDescription(args);
            ImportantTag importantTag = new ImportantTag(false);
            Question question = new Question(description, importantTag);
            return new AddQCommand(question);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddQCommand.MESSAGE_USAGE), pe);
        }
    }

}
