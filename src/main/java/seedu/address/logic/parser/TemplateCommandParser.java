package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.TemplateCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new TemplateCommand object
 */
public class TemplateCommandParser implements Parser<TemplateCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TemplateCommand
     * and returns a TemplateCommand object.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public TemplateCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String person = ParserUtil.parseTemplate(args);
        return new TemplateCommand(person);
    }

}
