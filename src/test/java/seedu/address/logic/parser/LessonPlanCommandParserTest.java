package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_PLAN_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON_PLAN;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.LessonPlanCommand;
import seedu.address.model.person.LessonPlan;

public class LessonPlanCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, LessonPlanCommand.MESSAGE_USAGE);

    private LessonPlanCommandParser parser = new LessonPlanCommandParser();

    @Test
    public void parse_validArgs_returnsLessonPlanCommand() {
        String userInput = "1 " + PREFIX_LESSON_PLAN + VALID_LESSON_PLAN_AMY;
        LessonPlanCommand expectedCommand = new LessonPlanCommand(INDEX_FIRST_PERSON,
                new LessonPlan(VALID_LESSON_PLAN_AMY));

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_noPrefixField_failure() {
        assertParseFailure(parser, "1 " + VALID_LESSON_PLAN_AMY, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_emptyField_failure() {
        assertParseFailure(parser, "1" + PREFIX_LESSON_PLAN, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "1" + PREFIX_LESSON_PLAN + " ", MESSAGE_INVALID_FORMAT);
    }
}
