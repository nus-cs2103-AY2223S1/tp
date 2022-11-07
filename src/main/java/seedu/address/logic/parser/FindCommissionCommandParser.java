package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FIND_ALL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FIND_ANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_KEYWORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.logic.commands.FindCommissionCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.commission.CompositeCommissionPredicate;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new FindCommissionCommand object
 */
public class FindCommissionCommandParser implements Parser<FindCommissionCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommissionCommand
     * and returns a FindCommissionCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format.
     */
    public FindCommissionCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap mainArgMultimap =
                ArgumentTokenizer.tokenize(" " + args, PREFIX_KEYWORD, PREFIX_FIND_ALL, PREFIX_FIND_ANY);

        List<String> keywords = mainArgMultimap.getAllValues(PREFIX_KEYWORD);
        Optional<String> rawIntersectTags = mainArgMultimap.getValue(PREFIX_FIND_ALL);
        Optional<String> rawUnionTags = mainArgMultimap.getValue(PREFIX_FIND_ANY);

        List<Tag> intersectTags = new ArrayList<>();
        List<Tag> unionTags = new ArrayList<>();

        if (rawIntersectTags.isPresent()) {
            ArgumentMultimap argMultimap =
                    ArgumentTokenizer.tokenize(rawIntersectTags.map(rawCommand -> " " + rawCommand).orElse(""),
                            PREFIX_TAG);
            Set<Tag> givenIntersectTags = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
            intersectTags.addAll(givenIntersectTags);
        }

        if (rawUnionTags.isPresent()) {
            ArgumentMultimap argMultimap =
                    ArgumentTokenizer.tokenize(rawUnionTags.map(rawCommand -> " " + rawCommand).orElse(""), PREFIX_TAG);
            Set<Tag> givenUnionTags = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
            unionTags.addAll(givenUnionTags);
        }

        if (keywords.isEmpty() && intersectTags.isEmpty() && unionTags.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindCommissionCommand.MESSAGE_USAGE));
        }

        return new FindCommissionCommand(new CompositeCommissionPredicate(keywords, intersectTags, unionTags));
    }
}
