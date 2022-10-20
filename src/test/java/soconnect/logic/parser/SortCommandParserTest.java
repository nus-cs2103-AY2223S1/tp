package soconnect.logic.parser;

import static soconnect.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static soconnect.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static soconnect.logic.parser.CliSyntax.PREFIX_EMAIL;
import static soconnect.logic.parser.CliSyntax.PREFIX_NAME;
import static soconnect.logic.parser.CliSyntax.PREFIX_PHONE;
import static soconnect.logic.parser.CliSyntax.PREFIX_TAG;
import static soconnect.logic.parser.CommandParserTestUtil.assertParseFailure;
import static soconnect.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.List;

import org.junit.jupiter.api.Test;

import soconnect.logic.commands.SortCommand;
import soconnect.logic.commands.SortCommand.SortArgument;
import soconnect.model.tag.Tag;

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
            new SortCommand(List.of(new SortArgument(PREFIX_NAME, false, null))));
        assertParseSuccess(parser, " n/!",
            new SortCommand(List.of(new SortArgument(PREFIX_NAME, true, null))));
        assertParseSuccess(parser, " p/",
            new SortCommand(List.of(new SortArgument(PREFIX_PHONE, false, null))));
        assertParseSuccess(parser, " p/!",
            new SortCommand(List.of(new SortArgument(PREFIX_PHONE, true, null))));
        assertParseSuccess(parser, " e/",
            new SortCommand(List.of(new SortArgument(PREFIX_EMAIL, false, null))));
        assertParseSuccess(parser, " e/!",
            new SortCommand(List.of(new SortArgument(PREFIX_EMAIL, true, null))));
        assertParseSuccess(parser, " a/",
            new SortCommand(List.of(new SortArgument(PREFIX_ADDRESS, false, null))));
        assertParseSuccess(parser, " a/!",
            new SortCommand(List.of(new SortArgument(PREFIX_ADDRESS, true, null))));
        assertParseSuccess(parser, " t/friend",
            new SortCommand(List.of(new SortArgument(PREFIX_TAG, false, new Tag("friend")))));
        assertParseSuccess(parser, " t/!friend",
            new SortCommand(List.of(new SortArgument(PREFIX_TAG, true, new Tag("friend")))));
    }

    @Test
    void parse_multipleArgument_success() {
        // test that 1-level sorts can be chained together in the correct order to form multi-level sort

        assertParseSuccess(parser, " n/ a/!", new SortCommand(List.of(
            new SortArgument(PREFIX_NAME, false, null),
            new SortArgument(PREFIX_ADDRESS, true, null))));
    }
}
