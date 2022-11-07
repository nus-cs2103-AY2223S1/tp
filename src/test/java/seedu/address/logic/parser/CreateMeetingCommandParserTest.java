package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CreateMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Copied over from DeleteCommandParserTest:
 *
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class CreateMeetingCommandParserTest {

    private CreateMeetingCommandParser parser = new CreateMeetingCommandParser();

    @Test
    public void parse_validArgs_returnsCreateMeetingCommand() throws ParseException {
        String[] nameArgs = {"Alex", "Yu"};
        assertParseSuccess(parser, "  Alex }} Yu ;;; Study session ;;; 26-10-2022 1830 ;;; Central Library  ",
            new CreateMeetingCommand(nameArgs, "Study session",
                "Wednesday, 26 October 2022 06:30 pm", "Central Library"));
        assertEquals(parser.parse("  Alex }} Yu ;;; Study session ;;; 26-10-2022 1830 ;;; Central Library  "),
            new CreateMeetingCommand(nameArgs, "Study session", "Wednesday, 26 October 2022 06:30 pm",
                "Central Library"));
    }

    @Test
    public void newMeeting_wrongDateAndTimeFormat_throwsParseException() throws Exception {
        String meetingInfo = "Amy ;;; Do CS2103 Project ;;; tomorrow ;;; University Town";
        assertThrows(ParseException.class, "Meeting date: tomorrow is not in dd-MM-yyyy format", () ->
            parser.parse(meetingInfo));
    }

    @Test
    public void parse_wrongNumberOfArgs_throwsParseException() {
        assertParseFailure(parser, "Amy ;;; Do CS2103 Project ;;; University Town",
            String.format(CreateMeetingCommand.INCORRECT_NUMBER_OF_ARGUMENTS));
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        assertParseFailure(parser, "",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateMeetingCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyDescription_throwsParseException() {
        assertParseFailure(parser, " Amy ;;; ;;; 26-10-2022 1830 ;;; University Town",
            String.format(CreateMeetingCommand.INCORRECT_NUMBER_OF_ARGUMENTS));
    }

    @Test
    public void parse_emptyDateTime_throwsParseException() {
        assertParseFailure(parser, "Amy ;;; Do CS2103 Project ;;; ;;; University Town",
            String.format("Meeting date cannot be empty"));
    }

    @Test
    public void parse_emptyLocation_throwsParseException() {
        assertParseFailure(parser, "Amy ;;; Do CS2103 Project ;;; 26-10-2022 1830 ;;;",
            String.format(CreateMeetingCommand.INCORRECT_NUMBER_OF_ARGUMENTS));
    }

}
