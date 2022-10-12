package seedu.address.logic.parser.ta;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;

import seedu.address.logic.commands.ta.AddTeachingAssistantCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ta.TeachingAssistant;
import seedu.address.model.ta.TeachingAssistantId;
import seedu.address.model.ta.TeachingAssistantName;

/**
 * Parses input arguments and creates a new AddTeachingAssistant object
 */
public class AddTeachingAssistantCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the AddTeachingAssistantCommand
     * and returns an AddTeachingAssistant object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddTeachingAssistantCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ID);

        ParserUtil.assertPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ID);
        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddTeachingAssistantCommand.MESSAGE_USAGE));
        }

        TeachingAssistantName name = ParserUtil.parseTeachingAssistantName(argMultimap.getValue(PREFIX_NAME).get());
        TeachingAssistantId id = ParserUtil.parseTeachingAssistantId(argMultimap.getValue(PREFIX_ID).get());

        TeachingAssistant ta = new TeachingAssistant(name, id);

        return new AddTeachingAssistantCommand(ta);
    }
}
