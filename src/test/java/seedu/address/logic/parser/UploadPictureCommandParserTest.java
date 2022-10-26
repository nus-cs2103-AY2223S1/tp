package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UploadPictureCommand;

public class UploadPictureCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            UploadPictureCommand.MESSAGE_USAGE);
    private UploadPictureCommandParser parser = new UploadPictureCommandParser();

    @Test
    public void parseInvalidIndexInput_failure() {
        assertParseFailure(parser, "hello", MESSAGE_INVALID_FORMAT);

        assertParseFailure(parser, "-1", MESSAGE_INVALID_FORMAT);

    }

    @Test
    public void parseValidIndexInput_success() {
        UploadPictureCommand firstValidIndexExpectedCommand = new UploadPictureCommand(INDEX_FIRST_STUDENT);
        assertParseSuccess(parser, "1", firstValidIndexExpectedCommand);

        UploadPictureCommand secondValidIndexExpectedCommand = new UploadPictureCommand(INDEX_SECOND_STUDENT);
        assertParseSuccess(parser, "2", secondValidIndexExpectedCommand);

        UploadPictureCommand thirdValidIndexExpectedCommand = new UploadPictureCommand(INDEX_THIRD_STUDENT);
        assertParseSuccess(parser, "3", thirdValidIndexExpectedCommand);
    }


}
