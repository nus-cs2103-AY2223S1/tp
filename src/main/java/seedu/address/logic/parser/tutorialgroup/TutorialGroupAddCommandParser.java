package seedu.address.logic.parser.tutorialgroup;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GROUP;

import java.util.stream.Stream;

import seedu.address.logic.commands.tutorialgroup.TutorialGroupAddCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.TutorialGroup;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class TutorialGroupAddCommandParser implements Parser<TutorialGroupAddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TaskAddCommand
     * and returns an TaskAddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TutorialGroupAddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TUTORIAL_GROUP);

        if (!arePrefixesPresent(argMultimap, PREFIX_TUTORIAL_GROUP)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    TutorialGroupAddCommand.MESSAGE_USAGE));
        }

        TutorialGroup tutorialGroup = ParserUtil.parseTutorialGroup(argMultimap.getValue(PREFIX_TUTORIAL_GROUP).get());

        return new TutorialGroupAddCommand(tutorialGroup);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
