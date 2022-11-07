package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HOMEWORK_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOMEWORK;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.HomeworkCommand;
import seedu.address.model.person.Homework;

public class HomeworkCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, HomeworkCommand.MESSAGE_USAGE);

    private HomeworkCommandParser parser = new HomeworkCommandParser();

    @Test
    public void parse_validArgs_returnsHomeworkCommand() {
        String userInput = "1 " + PREFIX_HOMEWORK + VALID_HOMEWORK_AMY;
        HomeworkCommand expectedCommand = new HomeworkCommand(INDEX_FIRST_PERSON, new Homework(VALID_HOMEWORK_AMY));

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_noPrefixField_failure() {
        assertParseFailure(parser, "1 " + VALID_HOMEWORK_AMY, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_emptyField_failure() {
        assertParseFailure(parser, "1 " + PREFIX_HOMEWORK, Homework.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1 " + PREFIX_HOMEWORK + " ", Homework.MESSAGE_CONSTRAINTS);
    }
}
