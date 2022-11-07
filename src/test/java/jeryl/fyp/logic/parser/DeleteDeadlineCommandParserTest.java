package jeryl.fyp.logic.parser;

import static jeryl.fyp.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static jeryl.fyp.commons.core.Messages.MESSAGE_INVALID_DEADLINE_RANK;
import static jeryl.fyp.logic.commands.CommandTestUtil.DEADLINE_RANK_DESC;
import static jeryl.fyp.logic.commands.CommandTestUtil.INVALID_DEADLINE_RANK_DESC;
import static jeryl.fyp.logic.commands.CommandTestUtil.INVALID_STUDENT_ID_DESC;
import static jeryl.fyp.logic.commands.CommandTestUtil.STUDENT_ID_DESC_AMY;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_DEADLINE_RANK;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY;
import static jeryl.fyp.logic.parser.CommandParserTestUtil.assertParseFailure;
import static jeryl.fyp.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import jeryl.fyp.logic.commands.DeleteDeadlineCommand;
import jeryl.fyp.model.student.StudentId;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside the DeleteCommand code.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteDeadlineCommandParserTest {

    private DeleteDeadlineCommandParser parser = new DeleteDeadlineCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, STUDENT_ID_DESC_AMY + DEADLINE_RANK_DESC, new DeleteDeadlineCommand(
                new StudentId(VALID_STUDENT_ID_AMY), VALID_DEADLINE_RANK));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // Invalid inputs
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteDeadlineCommand.MESSAGE_USAGE));
        // Invalid StudentId
        assertParseFailure(parser, INVALID_STUDENT_ID_DESC + DEADLINE_RANK_DESC, StudentId.MESSAGE_CONSTRAINTS);
        // Invalid Deadline Rank
        assertParseFailure(parser, STUDENT_ID_DESC_AMY + INVALID_DEADLINE_RANK_DESC,
                MESSAGE_INVALID_DEADLINE_RANK);

        // StudentId missing prefix
        assertParseFailure(parser, VALID_STUDENT_ID_AMY + DEADLINE_RANK_DESC,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteDeadlineCommand.MESSAGE_USAGE));
        // Deadline Rank missing prefix
        assertParseFailure(parser, STUDENT_ID_DESC_AMY + VALID_DEADLINE_RANK,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteDeadlineCommand.MESSAGE_USAGE));
    }
}
