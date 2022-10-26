package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SURVEY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AppendCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Survey;
import seedu.address.model.tag.Tag;

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

        Set<Tag> newTags = parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG));
        Set<Survey> newSurveys = parseSurveysForEdit(argMultimap.getAllValues(PREFIX_SURVEY));

        return new AppendCommand(index, newSurveys, newTags);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Set<Tag> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Collections.emptySet();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return ParserUtil.parseTags(tagSet);
    }

    /**
     * Parses {@code Collection<String> surveys} into a {@code Set<Survey>} if {@code surveys} is non-empty.
     * If {@code surveys} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Survey>} containing zero surveys.
     */
    private Set<Survey> parseSurveysForEdit(Collection<String> surveys) throws ParseException {
        assert surveys != null;

        if (surveys.isEmpty()) {
            return Collections.emptySet();
        }
        // THROW ERROR IF "s/" is found
        Collection<String> surveySet = surveys.size() == 1 && surveys.contains("") ? Collections.emptySet() : surveys;
        return ParserUtil.parseSurveys(surveySet);
    }
}
