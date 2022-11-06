package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_CHOCOLATE;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_SOCKS;
import static seedu.address.logic.commands.CommandTestUtil.EVENT_TITLE_DESC_CHOCOLATE;
import static seedu.address.logic.commands.CommandTestUtil.EVENT_TITLE_DESC_SOCKS;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EVENT_TITLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PURPOSE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_START_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.PURPOSE_DESC_CHOCOLATE;
import static seedu.address.logic.commands.CommandTestUtil.PURPOSE_DESC_SOCKS;
import static seedu.address.logic.commands.CommandTestUtil.START_TIME_DESC_CHOCOLATE;
import static seedu.address.logic.commands.CommandTestUtil.START_TIME_DESC_SOCKS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_CHOCOLATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_TITLE_CHOCOLATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PURPOSE_CHOCOLATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_CHOCOLATE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalEvents.CHOCOLATE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddEventCommand;
import seedu.address.model.date.Date;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventTitle;
import seedu.address.model.event.Purpose;
import seedu.address.model.event.StartTime;
import seedu.address.testutil.EventBuilder;

public class AddEventCommandParserTest {
    private AddEventCommandParser parser = new AddEventCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Event expectedEvent = new EventBuilder(CHOCOLATE).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + EVENT_TITLE_DESC_CHOCOLATE + DATE_DESC_CHOCOLATE
                        + START_TIME_DESC_CHOCOLATE + PURPOSE_DESC_CHOCOLATE,
                new AddEventCommand(expectedEvent));

        // multiple eventTitle - last eventTitle accepted
        assertParseSuccess(parser, EVENT_TITLE_DESC_SOCKS + EVENT_TITLE_DESC_CHOCOLATE + DATE_DESC_CHOCOLATE
                        + START_TIME_DESC_CHOCOLATE + PURPOSE_DESC_CHOCOLATE,
                new AddEventCommand(expectedEvent));

        // multiple date - last date accepted
        assertParseSuccess(parser, EVENT_TITLE_DESC_CHOCOLATE + DATE_DESC_SOCKS + DATE_DESC_CHOCOLATE
                        + START_TIME_DESC_CHOCOLATE + PURPOSE_DESC_CHOCOLATE,
                new AddEventCommand(expectedEvent));

        // multiple startTime - last startTime accepted
        assertParseSuccess(parser, EVENT_TITLE_DESC_CHOCOLATE + DATE_DESC_CHOCOLATE + START_TIME_DESC_SOCKS
                        + START_TIME_DESC_CHOCOLATE + PURPOSE_DESC_CHOCOLATE,
                new AddEventCommand(expectedEvent));

        // multiple purpose - last purpose accepted
        assertParseSuccess(parser, EVENT_TITLE_DESC_CHOCOLATE + DATE_DESC_CHOCOLATE
                        + START_TIME_DESC_CHOCOLATE + PURPOSE_DESC_SOCKS + PURPOSE_DESC_CHOCOLATE,
                new AddEventCommand(expectedEvent));

    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE);

        // missing eventTitle prefix
        assertParseFailure(parser, VALID_EVENT_TITLE_CHOCOLATE + DATE_DESC_CHOCOLATE + START_TIME_DESC_CHOCOLATE
                + PURPOSE_DESC_CHOCOLATE, expectedMessage);

        // missing date prefix
        assertParseFailure(parser, EVENT_TITLE_DESC_CHOCOLATE + VALID_DATE_CHOCOLATE + START_TIME_DESC_CHOCOLATE
                + PURPOSE_DESC_CHOCOLATE, expectedMessage);


        // missing startTime prefix
        assertParseFailure(parser, EVENT_TITLE_DESC_CHOCOLATE + DATE_DESC_CHOCOLATE + VALID_START_TIME_CHOCOLATE
                + PURPOSE_DESC_CHOCOLATE, expectedMessage);

        // missing purpose prefix
        assertParseFailure(parser, EVENT_TITLE_DESC_CHOCOLATE + DATE_DESC_CHOCOLATE + START_TIME_DESC_CHOCOLATE
                + VALID_PURPOSE_CHOCOLATE, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid eventTitle
        assertParseFailure(parser, INVALID_EVENT_TITLE_DESC + DATE_DESC_CHOCOLATE + START_TIME_DESC_CHOCOLATE
                + PURPOSE_DESC_CHOCOLATE, EventTitle.MESSAGE_CONSTRAINTS);

        // invalid date
        assertParseFailure(parser, EVENT_TITLE_DESC_CHOCOLATE + INVALID_DATE_DESC + START_TIME_DESC_CHOCOLATE
                + PURPOSE_DESC_CHOCOLATE, Date.MESSAGE_CONSTRAINTS);

        // invalid startTime
        assertParseFailure(parser, EVENT_TITLE_DESC_CHOCOLATE + DATE_DESC_CHOCOLATE + INVALID_START_TIME_DESC
                + PURPOSE_DESC_CHOCOLATE, StartTime.MESSAGE_FORMAT_CONSTRAINTS);

        // invalid Purpose
        assertParseFailure(parser, EVENT_TITLE_DESC_CHOCOLATE + DATE_DESC_CHOCOLATE + START_TIME_DESC_CHOCOLATE
                + INVALID_PURPOSE_DESC, Purpose.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_EVENT_TITLE_DESC + DATE_DESC_CHOCOLATE + START_TIME_DESC_CHOCOLATE
                        + INVALID_PURPOSE_DESC, EventTitle.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + EVENT_TITLE_DESC_CHOCOLATE + DATE_DESC_CHOCOLATE
                        + START_TIME_DESC_CHOCOLATE + PURPOSE_DESC_CHOCOLATE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE));
    }

}
