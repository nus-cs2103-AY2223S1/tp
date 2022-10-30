package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.AppendCommand.MESSAGE_NOT_APPENDED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SURVEY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AppendCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Survey;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AppendCommand object
 */
public class AppendCommandParser implements Parser<AppendCommand> {

    @Override
    public AppendCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_SURVEY, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AppendCommand.MESSAGE_USAGE), pe);
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_SURVEY) && !arePrefixesPresent(argMultimap, PREFIX_TAG)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_NOT_APPENDED));
        }


        Set<Tag> newTags = parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG));
        Set<Survey> newSurveys = parseSurveysForEdit(argMultimap.getAllValues(PREFIX_SURVEY));

        return new AppendCommand(index, newSurveys, newTags);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    public static Set<Tag> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Collections.emptySet();
        }
        return ParserUtil.parseTags(tags);
    }

    /**
     * Parses {@code Collection<String> surveys} into a {@code Set<Survey>} if {@code surveys} is non-empty.
     * If {@code surveys} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Survey>} containing zero surveys.
     */
    public static Set<Survey> parseSurveysForEdit(Collection<String> surveys) throws ParseException {
        assert surveys != null;

        if (surveys.isEmpty()) {
            return Collections.emptySet();
        }
        return ParserUtil.parseSurveys(surveys);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    public static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
