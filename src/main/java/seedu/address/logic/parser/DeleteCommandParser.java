package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RACE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RELIGION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SURVEY;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Race;
import seedu.address.model.person.Religion;
import seedu.address.model.person.Survey;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    private static final String REGEX = "(?<index>\\d+)";

    /**
     * Parses the given {@code String} of arguments in the context of the
     * DeleteCommand and returns a DeleteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_RACE, PREFIX_RELIGION, PREFIX_SURVEY);
        Optional<Index> index = Optional.empty();
        Optional<Race> race = Optional.empty();
        Optional<Religion> religion = Optional.empty();
        Optional<Survey> survey = Optional.empty();

        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(args.trim());
        if (!matcher.matches()) {
            if (Character.isDigit(args.trim().charAt(0))) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
            }
            if (argMultimap.getValue(PREFIX_RACE).isPresent()) {
                race = Optional.of(ParserUtil.parseRace(argMultimap.getValue(PREFIX_RACE).get()));
            }
            if (argMultimap.getValue(PREFIX_RELIGION).isPresent()) {
                religion = Optional.of(ParserUtil.parseReligion(argMultimap.getValue(PREFIX_RELIGION).get()));
            }
            if (argMultimap.getValue(PREFIX_SURVEY).isPresent()) {
                survey = Optional.of(ParserUtil.parseSurvey(argMultimap.getValue(PREFIX_SURVEY).get()));
            }
        } else {
            String indexString = matcher.group("index").strip();
            index = Optional.of(ParserUtil.parseIndex(indexString));
        }

        if (!isAnyAttributePresent(index, race, religion, survey)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }
        return new DeleteCommand(index, race, religion, survey);
    }

    /**
     * Returns true if at least one attribute is present.
     */
    private boolean isAnyAttributePresent(Optional<Index> index, Optional<Race> race, Optional<Religion> religion,
            Optional<Survey> survey) {
        if (index.isEmpty() && race.isEmpty() && religion.isEmpty() && survey.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
