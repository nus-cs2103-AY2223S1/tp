package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_KEYWORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.commission.CompositeCustomerPredicate;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {
    private static final String INTERSECT_FLAG = "-all";
    private static final String UNION_FLAG = "-any";

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        int intersectTagsGroupStart = trimmedArgs.indexOf(INTERSECT_FLAG);
        int intersectTagsContentStart = intersectTagsGroupStart + 4;
        int unionTagsGroupStart = trimmedArgs.indexOf(UNION_FLAG);
        int unionTagsContentStart = unionTagsGroupStart + 4;

        String rawKeywords = "";
        String rawIntersectTags = "";
        String rawUnionTags = "";

        // Improve readability only
        String possibleRawUnionTags = unionTagsContentStart >= trimmedArgs.length() ? ""
                : trimmedArgs.substring(unionTagsContentStart).trim();

        if (intersectTagsGroupStart != -1) {
            rawKeywords = trimmedArgs.substring(0, intersectTagsGroupStart).trim();
            if (unionTagsGroupStart != -1) {
                rawIntersectTags = trimmedArgs.substring(intersectTagsGroupStart + 4, unionTagsGroupStart);
                rawUnionTags = possibleRawUnionTags;
            } else {
                rawIntersectTags = intersectTagsContentStart >= trimmedArgs.length() ? ""
                        : trimmedArgs.substring(intersectTagsContentStart).trim();
            }
        } else {
            if (unionTagsGroupStart != -1) {
                rawKeywords = trimmedArgs.substring(0, unionTagsGroupStart).trim();
                rawUnionTags = possibleRawUnionTags;
            } else {
                rawKeywords = trimmedArgs;
            }
        }

        List<String> keywords = new ArrayList<>();
        List<Tag> intersectTags = new ArrayList<>();
        List<Tag> unionTags = new ArrayList<>();
        if (!rawKeywords.isEmpty()) {
            // Whitespace required for tokenize()
            rawKeywords = " " + rawKeywords;
            ArgumentMultimap argMultimap =
                    ArgumentTokenizer.tokenize(rawKeywords, PREFIX_KEYWORD);
            List<String> givenKeywords = argMultimap.getAllValues(PREFIX_KEYWORD);
            for (String givenKeyword : givenKeywords) {
                if (givenKeyword.isBlank()) {
                    throw new ParseException(Messages.MESSAGE_KEYWORD_EMPTY);
                }
                keywords.add(givenKeyword);
            }
        }

        if (!rawIntersectTags.isEmpty()) {
            rawIntersectTags = " " + rawIntersectTags;
            ArgumentMultimap argMultimap =
                    ArgumentTokenizer.tokenize(rawIntersectTags, PREFIX_TAG);
            Set<Tag> givenIntersectTags = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
            intersectTags.addAll(givenIntersectTags);
        }

        if (!rawUnionTags.isEmpty()) {
            rawUnionTags = " " + rawUnionTags;
            ArgumentMultimap argMultimap =
                    ArgumentTokenizer.tokenize(rawUnionTags, PREFIX_TAG);
            Set<Tag> givenUnionTags = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
            unionTags.addAll(givenUnionTags);
        }

        if (keywords.isEmpty() && intersectTags.isEmpty() && unionTags.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindCommand.MESSAGE_USAGE));
        }

        return new FindCommand(new CompositeCustomerPredicate(keywords, intersectTags, unionTags));
    }

}
