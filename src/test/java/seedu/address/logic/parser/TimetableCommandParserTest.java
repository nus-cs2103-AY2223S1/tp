package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.TimetableCommand;
import seedu.address.logic.commands.TimetableIndexCommand;
import seedu.address.logic.commands.TimetableUserCommand;

public class TimetableCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, TimetableCommand.MESSAGE_USAGE);

    private TimetableCommandParser parser = new TimetableCommandParser();

    @Test
    public void parse_inValidPreamble_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no index/user and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_validUser_success() {
        TimetableCommand expectedCommand = new TimetableUserCommand();
        assertParseSuccess(parser, "user", expectedCommand);
    }

    @Test
    public void parse_validIndex_success() {
        Index index = INDEX_FIRST_PERSON;
        int indexNumber = INDEX_FIRST_PERSON.getOneBased();

        TimetableCommand expectedCommand = new TimetableIndexCommand(index);
        assertParseSuccess(parser, String.format("%d", indexNumber), expectedCommand);
    }
}
