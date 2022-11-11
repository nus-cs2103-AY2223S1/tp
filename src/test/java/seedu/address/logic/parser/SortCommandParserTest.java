package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;
import seedu.address.model.internship.SortCriteria;

public class SortCommandParserTest {
    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_validSortCriteria_returnsSortCommand() {
        SortCommand expectedSortCommand = new SortCommand(SortCriteria.Applied);

        // lower case
        assertParseSuccess(parser, "applied", expectedSortCommand);

        // alternating caps
        assertParseSuccess(parser, "aPpLiEd", expectedSortCommand);
    }

    @Test
    public void parse_emptySortCriteria_throwsParseException() {
        assertParseFailure(parser, " ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidSortCriteria_throwsParseException() {
        assertParseFailure(parser, "invalidsort", SortCriteria.MESSAGE_CONSTRAINTS);
    }
}
