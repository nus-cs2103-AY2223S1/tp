package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SURVEY;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Survey;

/**
 * Parses inuput arguments and creates a new MarkCommand object
 */
public class MarkCommandParser implements Parser<MarkCommand> {

    private static final String REGEX = "(?<index>\\d+)";

    /**
     * Parses the given {@code String} of arguments in the context of the
     * MarkCommand and returns a MarkCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public MarkCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_SURVEY);

        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(args.trim());

        if (!matcher.find() || !argMultimap.getValue(PREFIX_SURVEY).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE));
        }

        Index index = ParserUtil.parseIndex(matcher.group("index").strip());
        Survey survey = ParserUtil.parseSurvey(argMultimap.getValue(PREFIX_SURVEY).get());

        return new MarkCommand(index, survey);
    }
}
