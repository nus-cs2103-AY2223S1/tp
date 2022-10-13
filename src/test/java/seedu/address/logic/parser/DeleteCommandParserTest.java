package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TYPE;
import static seedu.address.logic.commands.CommandTestUtil.TYPE_EXPENDITURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_EXPENDITURE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteCommand;
import seedu.address.model.entry.EntryType;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        EntryType expenditureType = new EntryType(VALID_TYPE_EXPENDITURE);
        String validUserInput = "1" + TYPE_EXPENDITURE;
        assertParseSuccess(parser, validUserInput, new DeleteCommand(INDEX_FIRST_PERSON, expenditureType));
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        String invalidUserInput = "a" + TYPE_EXPENDITURE;
        assertParseFailure(
                parser,
                invalidUserInput,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidEntryType_throwsParseException() {
        String invalidUserInput = "1 " + INVALID_TYPE;
        assertParseFailure(
                parser,
                invalidUserInput,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }
}
