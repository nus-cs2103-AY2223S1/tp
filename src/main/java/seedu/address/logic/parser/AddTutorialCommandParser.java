package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddTutorialCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tutorial.Content;
import seedu.address.model.tutorial.Group;
import seedu.address.model.tutorial.Time;
import seedu.address.model.tutorial.Tutorial;

/**
 * Parses input arguments and creates a new AddTutorialCommand object
 */
public class AddTutorialCommandParser implements Parser<AddTutorialCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddTutorialCommand
     * and returns an AddTutorialCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddTutorialCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_GROUP, PREFIX_CONTENT, PREFIX_TIME);

        if (!arePrefixesPresent(argMultimap, PREFIX_GROUP, PREFIX_CONTENT, PREFIX_TIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTutorialCommand.MESSAGE_USAGE));
        }

        Group group = ParserUtil.parseGroup(argMultimap.getValue(PREFIX_GROUP).get());
        Content content = ParserUtil.parseContent(argMultimap.getValue(PREFIX_CONTENT).get());
        Time time = ParserUtil.parseTime(argMultimap.getValue(PREFIX_TIME).get());

        Tutorial newTutorial = new Tutorial(group, content, time, false);
        return new AddTutorialCommand(newTutorial);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
