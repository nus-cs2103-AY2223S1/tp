package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_EMPTY_ARGUMENTS;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterClearCommand;
import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.commands.FilterCommandPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.TagMatchesQueryPredicate;
import seedu.address.model.tag.Tag;

public class FilterCommandParserTest {

    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArg_throwsParseException() {
        assertParseFailure(parser, "abc 123",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "    clear 123",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterClearCommand.MESSAGE_USAGE));

        assertParseFailure(parser, PREFIX_NAME.toString(),
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        String.format(MESSAGE_EMPTY_ARGUMENTS, PREFIX_NAME)));

        assertParseFailure(parser, PREFIX_TAG.toString(),
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        String.format(MESSAGE_EMPTY_ARGUMENTS, PREFIX_TAG)));
    }

    @Test
    public void parse_validArgs_returnsFilterCommand() {
        // no leading and trailing whitespaces
        Set<NameContainsKeywordsPredicate> namePredicates = Stream.of("Alice", "Bob")
                .map(name -> new NameContainsKeywordsPredicate(name))
                .collect(Collectors.toSet());
        Set<Tag> tagSetQuery = Set.of(new Tag("friend"), new Tag("neighbor"));
        Set<TagMatchesQueryPredicate> tagPredicates = tagSetQuery.stream()
                .map(tag -> new TagMatchesQueryPredicate(tag)).collect(Collectors.toSet());
        FilterCommand expectedFindCommand =
                new FilterCommand(new FilterCommandPredicate(namePredicates, tagPredicates));
        String input1 = PREFIX_NAME + "Alice,Bob" + " " + PREFIX_TAG + "friend,neighbor";
        assertParseSuccess(parser, input1, expectedFindCommand);

        // multiple whitespaces between keywords
        String input2 = PREFIX_NAME + " Alice , \t Bob  \t" + " " + PREFIX_TAG + " friend , \t neighbor  \t";
        assertParseSuccess(parser, input2, expectedFindCommand);
    }

    @Test
    public void parse_validArgs_returnsFilterClearCommand() {
        // no leading and trailing whitespaces
        Set<NameContainsKeywordsPredicate> namePredicates = Stream.of("Alice", "Bob")
                .map(name -> new NameContainsKeywordsPredicate(name))
                .collect(Collectors.toSet());
        Set<Tag> tagSetQuery = Set.of(new Tag("friend"), new Tag("neighbor"));
        Set<TagMatchesQueryPredicate> tagPredicates = tagSetQuery.stream()
                .map(tag -> new TagMatchesQueryPredicate(tag)).collect(Collectors.toSet());

        FilterCommand expectedFindCommand =
                new FilterClearCommand(new FilterCommandPredicate(namePredicates, tagPredicates));

        String input1 = FilterClearCommand.COMMAND_SPECIFIER + " " + PREFIX_NAME + "Alice,Bob" + " "
                + PREFIX_TAG
                + "friend,neighbor";
        assertParseSuccess(parser, input1, expectedFindCommand);

        // multiple whitespaces between keywords
        String input2 = FilterClearCommand.COMMAND_SPECIFIER + " " + PREFIX_NAME + " Alice , \t Bob  \t" + " "
                + PREFIX_TAG + " friend , \t neighbor  \t";
        assertParseSuccess(parser, input2, expectedFindCommand);
    }

    @Test
    public void parse_clearAll_returnsFilterClearCommand() {
        // no leading and trailing whitespaces
        FilterCommand expectedFindCommand =
                new FilterClearCommand();

        String input1 = FilterClearCommand.COMMAND_SPECIFIER;
        assertParseSuccess(parser, input1, expectedFindCommand);

        // multiple whitespaces between keywords
        String input2 = FilterClearCommand.COMMAND_SPECIFIER + "   \t ";
        assertParseSuccess(parser, input2, expectedFindCommand);
    }
}
