package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;


import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.LessonCommand;
import seedu.address.logic.commands.LessonIndexCommand;
import seedu.address.logic.commands.LessonUserCommand;
import seedu.address.testutil.LessonBuilder;

public class LessonCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, LessonCommand.MESSAGE_USAGE);

    private static final String VALID_LESSON_INPUT = "l/tut m/CS2103T d/4 start/14:00 end/15:00";

    private LessonCommandParser parser = new LessonCommandParser();


    @Test
    public void parse_inValidPreamble_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // index no field specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // no index/user and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidIndexWithValidLesson_failure() {
        // negative index
        assertParseFailure(parser, "-5 " + VALID_LESSON_INPUT, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0 " + VALID_LESSON_INPUT, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string " + VALID_LESSON_INPUT, MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 n/Bobby McBobface " + VALID_LESSON_INPUT, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidUserWithValidLesson_failure() {
        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "user some random string " + VALID_LESSON_INPUT, MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "user n/Bobby McBobface " + VALID_LESSON_INPUT, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_validUserWithMissingLessonParts_failure() {
        // no field specified
        assertParseFailure(parser, "user", MESSAGE_INVALID_FORMAT);

        // missing type
        assertParseFailure(parser, "user m/CS2103T d/4 start/14:00 end/15:00", MESSAGE_INVALID_FORMAT);

        // missing mod
        assertParseFailure(parser, "user l/tut d/4 start/14:00 end/15:00", MESSAGE_INVALID_FORMAT);

        // missing day
        assertParseFailure(parser, "user l/tut m/CS2103T start/14:00 end/15:00", MESSAGE_INVALID_FORMAT);

        // missing start
        assertParseFailure(parser, "user l/tut m/CS2103T d/4 end/15:00", MESSAGE_INVALID_FORMAT);

        // missing end
        assertParseFailure(parser, "user l/tut m/CS2103T d/4 start/14:00", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_validIndexWithMissingLessonParts_failure() {
        int index = INDEX_FIRST_PERSON.getOneBased();
        // no field specified
        assertParseFailure(parser, String.format("%d", index), MESSAGE_INVALID_FORMAT);

        // missing type
        assertParseFailure(parser, String.format("%d m/CS2103T d/4 start/14:00 end/15:00", index),
                MESSAGE_INVALID_FORMAT);

        // missing mod
        assertParseFailure(parser, String.format("%d l/tut d/4 start/14:00 end/15:00", index),
                MESSAGE_INVALID_FORMAT);

        // missing day
        assertParseFailure(parser, String.format("%d l/tut m/CS2103T start/14:00 end/15:00", index),
                MESSAGE_INVALID_FORMAT);

        // missing start
        assertParseFailure(parser, String.format("%d l/tut m/CS2103T d/4 end/15:00", index),
                MESSAGE_INVALID_FORMAT);

        // missing end
        assertParseFailure(parser, String.format("%d l/tut m/CS2103T d/4 start/14:00", index),
                MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_validUserWithValidLesson_success() {
        LessonCommand expectedCommand = new LessonUserCommand(new LessonBuilder().build());
        assertParseSuccess(parser, "user " + VALID_LESSON_INPUT, expectedCommand);
    }

    @Test
    public void parse_validIndexWithValidLesson_success() {
        Index index = INDEX_FIRST_PERSON;
        int indexNumber = INDEX_FIRST_PERSON.getOneBased();
        LessonCommand expectedCommand = new LessonIndexCommand(new LessonBuilder().build(), index);
        assertParseSuccess(parser, String.format("%d %s", indexNumber, VALID_LESSON_INPUT), expectedCommand);
    }
}
