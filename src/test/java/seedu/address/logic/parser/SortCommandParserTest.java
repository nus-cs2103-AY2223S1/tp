package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.SortCommand.SortArgument;
import seedu.address.model.tag.Tag;

class SortCommandParserTest {
    private final String failureMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE);
    private final SortCommandParser parser = new SortCommandParser();

    @Test
    void parse_noValidArguments_failure() {
        assertParseFailure(parser, "", failureMessage);
        assertParseFailure(parser, "   ", failureMessage);
        assertParseFailure(parser, "abcd", failureMessage);
        assertParseFailure(parser, "an/namep/phone", failureMessage);
    }

    @Test
    void parse_oneArgument_success() {
        // test that the individual 1-level sorts function correctly

        assertParseSuccess(parser, " n/",
            new SortCommand(List.of(new SortArgument(new Prefix("n/"), false, null))));
        assertParseSuccess(parser, " n/!",
            new SortCommand(List.of(new SortArgument(new Prefix("n/"), true, null))));
        assertParseSuccess(parser, " p/",
            new SortCommand(List.of(new SortArgument(new Prefix("p/"), false, null))));
        assertParseSuccess(parser, " p/!",
            new SortCommand(List.of(new SortArgument(new Prefix("p/"), true, null))));
        assertParseSuccess(parser, " e/",
            new SortCommand(List.of(new SortArgument(new Prefix("e/"), false, null))));
        assertParseSuccess(parser, " e/!",
            new SortCommand(List.of(new SortArgument(new Prefix("e/"), true, null))));
        assertParseSuccess(parser, " a/",
            new SortCommand(List.of(new SortArgument(new Prefix("a/"), false, null))));
        assertParseSuccess(parser, " a/!",
            new SortCommand(List.of(new SortArgument(new Prefix("a/"), true, null))));
        assertParseSuccess(parser, " t/friend",
            new SortCommand(List.of(new SortArgument(new Prefix("t/"), false, new Tag("friend")))));
        assertParseSuccess(parser, " t/!friend",
            new SortCommand(List.of(new SortArgument(new Prefix("t/"), true, new Tag("friend")))));
    }

    @Test
    void parse_multipleArgument_success() {
        // test that 1-level sorts can be chained together in the correct order to form multi-level sort

        assertParseSuccess(parser, " n/ a/!", new SortCommand(List.of(
            new SortArgument(new Prefix("n/"), false, null),
            new SortArgument(new Prefix("a/"), true, null))));
    }
}
