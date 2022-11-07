package seedu.modquik.logic.parser.consultation;

import static seedu.modquik.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.modquik.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.modquik.logic.commands.consultation.AddConsultationCommand;
import seedu.modquik.logic.parser.ModQuikParser;
import seedu.modquik.model.consultation.Consultation;
import seedu.modquik.model.datetime.DatetimeCommonUtils;
import seedu.modquik.testutil.ConsultationBuilder;

/**
 * Test cases for adding consultations
 */
public class AddConsultationCommandParserTest {

    private ModQuikParser parser = new ModQuikParser();

    @Test
    public void parse_validArgs_returnsAddConsultationCommand() {
        Consultation consultation = new ConsultationBuilder()
                .withName("Ray").withModule("CS1101").withVenue("COM1")
                .withDescription("recursion")
                .withTimeslot("2023-01-01 08:00", "2023-01-01 09:00")
                .build();
        String userInput = "add consultation n/Ray m/CS1101 v/COM1 d/recursion D/2023-01-01 T/08:00-09:00";
        assertParseSuccess(parser, userInput, new AddConsultationCommand(consultation));
    }


    @Test
    public void parse_invalidDateFormat_throwsParseException() {
        String userInput;
        userInput = "add consultation n/Ray m/CS1101 v/COM1 d/recursion D/hello T/08:00-09:00";
        assertParseFailure(parser, userInput, DatetimeCommonUtils.DATE_MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidDate_throwsParseException() {
        String userInput;
        userInput = "add consultation n/Ray m/CS1101 v/COM1 d/recursion D/2022-13-13 T/08:00-09:00";
        assertParseFailure(parser, userInput, DatetimeCommonUtils.DATE_MESSAGE_CONSTRAINTS_UNPARSABLE);
    }

    @Test
    public void parse_invalidTimeStartEnd_throwsParseException() {
        String userInput;
        userInput = "add consultation n/Ray m/CS1101 v/COM1 d/recursion D/2023-01-01 T/08:00-07:00";
        assertParseFailure(parser, userInput, DatetimeCommonUtils.TIMERANGE_MESSAGE_CONSTRAINTS_START_END);
    }
}
