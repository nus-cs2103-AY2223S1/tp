package seedu.uninurse.logic.parser;

import static seedu.uninurse.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.uninurse.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.uninurse.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.uninurse.testutil.Assert.assertThrows;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_FIRST_ATTRIBUTE;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.uninurse.logic.commands.DeleteMedicationCommand;

/**
 * Contains unit tests for {@code DeleteMedicationCommandParser}.
 */
public class DeleteMedicationCommandParserTest {

    private final DeleteMedicationCommandParser parser = new DeleteMedicationCommandParser();

    @Test
    public void parse_nullArgs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

    @Test
    public void parse_validArgs_success() {
        assertParseSuccess(parser, "1 1", new DeleteMedicationCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE));
        assertParseSuccess(parser, " 1 1 ", new DeleteMedicationCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE));
    }

    @Test
    public void parse_invalidPatientIndex_failure() {
        assertParseFailure(parser, "a 1", MESSAGE_INVALID_INDEX);
        assertParseFailure(parser, "0 1", MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_invalidMedicationIndex_failure() {
        assertParseFailure(parser, "1 a", MESSAGE_INVALID_INDEX);
        assertParseFailure(parser, "1 0", MESSAGE_INVALID_INDEX);
    }
}
