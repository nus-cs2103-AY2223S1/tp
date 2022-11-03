package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_Q2;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalQuestions.Q2;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddQCommand;
import seedu.address.model.question.Question;
import seedu.address.testutil.QuestionBuilder;

public class AddQCommandParserTest {
    private AddQCommandParser parser = new AddQCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Question expectedQuestion = new QuestionBuilder(Q2).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + DESCRIPTION_Q2, new AddQCommand(expectedQuestion));

    }


    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddQCommand.MESSAGE_USAGE);

        // missing description prefix
        assertParseFailure(parser, "",
                expectedMessage);
    }

}
