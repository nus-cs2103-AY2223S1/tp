package longtimenosee.logic.parser;


import static longtimenosee.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static longtimenosee.logic.commands.CommandTestEventUtil.DATE_DESC_AMY;
import static longtimenosee.logic.commands.CommandTestEventUtil.DATE_DESC_BOB;
import static longtimenosee.logic.commands.CommandTestEventUtil.DESCRIPTION_DESC_AMY;
import static longtimenosee.logic.commands.CommandTestEventUtil.DESCRIPTION_DESC_BOB;
import static longtimenosee.logic.commands.CommandTestEventUtil.END_DESC_AMY;
import static longtimenosee.logic.commands.CommandTestEventUtil.END_DESC_BOB;
import static longtimenosee.logic.commands.CommandTestEventUtil.INVALID_DATE_DESC;
import static longtimenosee.logic.commands.CommandTestEventUtil.INVALID_DESCRIPTION_DESC;
import static longtimenosee.logic.commands.CommandTestEventUtil.INVALID_FUTURE_DATE_DESC;
import static longtimenosee.logic.commands.CommandTestEventUtil.INVALID_NAME_DESC;
import static longtimenosee.logic.commands.CommandTestEventUtil.INVALID_START_DESC;
import static longtimenosee.logic.commands.CommandTestEventUtil.NAME_DESC_AMY;
import static longtimenosee.logic.commands.CommandTestEventUtil.NAME_DESC_BOB;
import static longtimenosee.logic.commands.CommandTestEventUtil.START_DESC_AMY;
import static longtimenosee.logic.commands.CommandTestEventUtil.START_DESC_BOB;
import static longtimenosee.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static longtimenosee.logic.parser.CommandParserTestUtil.assertParseFailure;
import static longtimenosee.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static longtimenosee.testutil.TypicalEvents.WITH_AMY;

import org.junit.jupiter.api.Test;

import longtimenosee.logic.commands.AddEventCommand;
import longtimenosee.model.event.Date;
import longtimenosee.model.event.Description;
import longtimenosee.model.event.Duration;
import longtimenosee.model.event.Event;
import longtimenosee.model.person.Name;
import longtimenosee.testutil.EventBuilder;



public class AddEventCommandParserTest {
    private AddEventCommandParser parser = new AddEventCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Event expectedEvent = new EventBuilder(WITH_AMY).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_AMY
                + DESCRIPTION_DESC_AMY + DATE_DESC_AMY + START_DESC_AMY
                + END_DESC_AMY, new AddEventCommand(expectedEvent));
        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_BOB + NAME_DESC_AMY
                + DESCRIPTION_DESC_AMY + DATE_DESC_AMY + START_DESC_AMY
                + END_DESC_AMY, new AddEventCommand(expectedEvent));
        // multiple desc, last one accepted
        assertParseSuccess(parser, NAME_DESC_AMY
                + DESCRIPTION_DESC_BOB
                + DESCRIPTION_DESC_AMY
                + DATE_DESC_AMY + START_DESC_AMY
                + END_DESC_AMY, new AddEventCommand(expectedEvent));
        // multiple dates, last one accepted
        assertParseSuccess(parser, NAME_DESC_AMY
                + DESCRIPTION_DESC_AMY
                + DATE_DESC_BOB
                + DATE_DESC_AMY + START_DESC_AMY
                + END_DESC_AMY, new AddEventCommand(expectedEvent));
        // multiple start, last one accepted
        assertParseSuccess(parser, NAME_DESC_AMY
                + DESCRIPTION_DESC_AMY
                + DATE_DESC_AMY + START_DESC_BOB
                + START_DESC_AMY
                + END_DESC_AMY, new AddEventCommand(expectedEvent));
        // multiple end, last one accepted
        assertParseSuccess(parser, NAME_DESC_AMY
                + DESCRIPTION_DESC_AMY
                + DATE_DESC_AMY
                + START_DESC_AMY
                + END_DESC_BOB
                + END_DESC_AMY, new AddEventCommand(expectedEvent));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE);
        //missing name prefix
        assertParseFailure(parser, DESCRIPTION_DESC_AMY + DATE_DESC_AMY + START_DESC_AMY
                + END_DESC_AMY, expectedMessage);
        //missing desc prefix
        assertParseFailure(parser, NAME_DESC_AMY + DATE_DESC_AMY + START_DESC_AMY
                + END_DESC_AMY, expectedMessage);

    }

    @Test
    public void parse_invalidFieldValue_failure() {
        //invalid name
        assertParseFailure(parser, INVALID_NAME_DESC
                + DESCRIPTION_DESC_AMY + DATE_DESC_AMY + START_DESC_AMY
                + END_DESC_AMY, Name.MESSAGE_CONSTRAINTS);
        //invalid desc
        assertParseFailure(parser, NAME_DESC_AMY
                + INVALID_DESCRIPTION_DESC + DATE_DESC_AMY + START_DESC_AMY
                + END_DESC_AMY, Description.MESSAGE_CONSTRAINTS);
        //invalid start or end
        assertParseFailure(parser, NAME_DESC_AMY
                + DESCRIPTION_DESC_AMY + DATE_DESC_AMY + INVALID_START_DESC
                + END_DESC_AMY, Duration.FORMAT_CONSTRAINTS);
        //start comes before end (Bob starts at 19:00, Amy starts at 7:00)
        assertParseFailure(parser, NAME_DESC_AMY
                + DESCRIPTION_DESC_AMY + DATE_DESC_AMY + START_DESC_BOB
                + END_DESC_AMY, Duration.MESSAGE_CONSTRAINTS);
        //invalid date format (not YYYY_MM_DD)
        assertParseFailure(parser, NAME_DESC_AMY
                + DESCRIPTION_DESC_AMY + INVALID_DATE_DESC + START_DESC_AMY
                + END_DESC_AMY, Date.MESSAGE_FORMAT_CONSTRAINTS);
        //invalid date format (too far into future / past)
        assertParseFailure(parser, NAME_DESC_AMY
                + DESCRIPTION_DESC_AMY + INVALID_FUTURE_DATE_DESC + START_DESC_AMY
                + END_DESC_AMY, Date.YEAR_RANGE_CONSTRAINTS);
    }
}
