package nus.climods.logic.parser;

import static java.util.Objects.requireNonNull;
import static nus.climods.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;

import nus.climods.logic.commands.FindCommand;
import nus.climods.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    private static final String DEFAULT_ERROR_MESSAGE =
        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE);

    private static List<Pattern> generateSearchRegexes(List<String> searchTokens) throws PatternSyntaxException {
        return searchTokens.stream().map(token -> Pattern.compile(token, Pattern.CASE_INSENSITIVE))
            .collect(Collectors.toList());
    }

    @Override
    public FindCommand parse(String searchPhrase) throws ParseException {
        String trimmedSearchPhrase = searchPhrase.trim();
        if (trimmedSearchPhrase.isEmpty()) {
            throw new ParseException(DEFAULT_ERROR_MESSAGE);
        }

        List<Pattern> searchRegexes;
        List<String> searchTokens = Arrays.asList(trimmedSearchPhrase.split("\\s+"));
        try {
            searchRegexes = generateSearchRegexes(searchTokens);
        } catch (PatternSyntaxException patternSyntaxException) {
            throw new ParseException(DEFAULT_ERROR_MESSAGE);
        }

        requireNonNull(searchRegexes);
        return new FindCommand(searchRegexes);
    }
}
