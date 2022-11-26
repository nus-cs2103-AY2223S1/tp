package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.COMPLETION_STATUS_DESC_NO;
import static seedu.address.logic.commands.CommandTestUtil.COMPLETION_STATUS_DESC_YES;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COMPLETION_STATUS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LINK_STATUS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE;
import static seedu.address.logic.commands.CommandTestUtil.LINK_STATUS_DESC_NO;
import static seedu.address.logic.commands.CommandTestUtil.LINK_STATUS_DESC_YES;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_DESC_CS2030;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_DESC_CS2040;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalModules.CS2030;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterTasksCommand;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.task.FilterPredicate;

public class FilterTasksCommandParserTest {
    private FilterTasksCommandParser parser = new FilterTasksCommandParser();

    @Test
    public void parse_modulePresent_success() {
        FilterPredicate expectedPredicate = new FilterPredicate(Optional.of(CS2030), Optional.empty(),
                Optional.empty());

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + MODULE_DESC_CS2030,
                new FilterTasksCommand(expectedPredicate));

        // multiple modules - last module accepted
        assertParseSuccess(parser, MODULE_DESC_CS2040 + MODULE_DESC_CS2030,
                new FilterTasksCommand(expectedPredicate));
    }

    @Test
    public void parse_commpletionStatusPresent_success() {
        FilterPredicate expectedPredicate = new FilterPredicate(Optional.empty(), Optional.of(true),
                Optional.empty());

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + COMPLETION_STATUS_DESC_YES,
                new FilterTasksCommand(expectedPredicate));

        // multiple completion status - last completion status accepted
        assertParseSuccess(parser, COMPLETION_STATUS_DESC_NO + COMPLETION_STATUS_DESC_YES,
                new FilterTasksCommand(expectedPredicate));
    }

    @Test
    public void parse_linkStatusPresent_success() {
        FilterPredicate expectedPredicate = new FilterPredicate(Optional.empty(), Optional.empty(),
                Optional.of(true));

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + LINK_STATUS_DESC_YES,
                new FilterTasksCommand(expectedPredicate));

        // multiple link status - last link status accepted
        assertParseSuccess(parser, LINK_STATUS_DESC_NO + LINK_STATUS_DESC_YES,
                new FilterTasksCommand(expectedPredicate));
    }

    @Test
    public void parse_allFieldsPresent_success() {
        FilterPredicate expectedPredicate = new FilterPredicate(Optional.of(CS2030), Optional.of(true),
                Optional.of(true));

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + MODULE_DESC_CS2030 + COMPLETION_STATUS_DESC_YES
                + LINK_STATUS_DESC_YES, new FilterTasksCommand(expectedPredicate));

        // multiple modules - last module accepted
        assertParseSuccess(parser, MODULE_DESC_CS2040 + MODULE_DESC_CS2030 + COMPLETION_STATUS_DESC_YES
                + LINK_STATUS_DESC_YES, new FilterTasksCommand(expectedPredicate));

        // multiple completion status - last completion status accepted
        assertParseSuccess(parser, MODULE_DESC_CS2030 + COMPLETION_STATUS_DESC_NO + COMPLETION_STATUS_DESC_YES
                + LINK_STATUS_DESC_YES, new FilterTasksCommand(expectedPredicate));

        // multiple link status - last link status accepted
        assertParseSuccess(parser, MODULE_DESC_CS2030 + COMPLETION_STATUS_DESC_YES + LINK_STATUS_DESC_NO
                + LINK_STATUS_DESC_YES, new FilterTasksCommand(expectedPredicate));
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid module
        assertParseFailure(parser, INVALID_MODULE + COMPLETION_STATUS_DESC_YES + LINK_STATUS_DESC_YES,
                ModuleCode.MODULE_CODE_CONSTRAINTS);

        // invalid completion status
        assertParseFailure(parser, MODULE_DESC_CS2030 + INVALID_COMPLETION_STATUS_DESC + LINK_STATUS_DESC_YES,
                FilterTasksCommandParser.RESPONSE_CONSTRAINTS);

        // invalid link status
        assertParseFailure(parser, MODULE_DESC_CS2030 + COMPLETION_STATUS_DESC_YES + INVALID_LINK_STATUS_DESC,
                FilterTasksCommandParser.RESPONSE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_MODULE + INVALID_COMPLETION_STATUS_DESC + LINK_STATUS_DESC_YES,
                ModuleCode.MODULE_CODE_CONSTRAINTS);
    }
}
