package jeryl.fyp.logic.parser;

import static jeryl.fyp.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static jeryl.fyp.logic.commands.CommandTestUtil.STUDENT_ID;
import static jeryl.fyp.logic.parser.CommandParserTestUtil.assertParseFailure;
import static jeryl.fyp.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static jeryl.fyp.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import jeryl.fyp.model.student.StudentId;
import org.junit.jupiter.api.Test;

import jeryl.fyp.logic.commands.DeleteCommand;

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
        assertParseSuccess(parser, "1", new DeleteCommand(STUDENT_ID));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }
}
