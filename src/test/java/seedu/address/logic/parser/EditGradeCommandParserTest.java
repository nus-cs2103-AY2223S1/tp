package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.GradeCommand;
import seedu.address.model.person.Assignment;

public class EditGradeCommandParserTest {

    private GradeCommandParser parser = new GradeCommandParser();

    @Test
    public void parse_validArgs_returnsGradeCommand() {
        assertParseSuccess(parser, "1 assignment/1 grade/10/10", new GradeCommand(INDEX_FIRST_PERSON,
                Index.fromOneBased(1), "10/10"));
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        assertParseFailure(parser, "1 assignment/1 ", String.format(Assignment.GRADE_CONSTRAINTS));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(GradeCommand.EMPTY_FIELD));
    }

    @Test
    public void parse_noArgs_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT, GradeCommand.MESSAGE_USAGE));
    }

}
