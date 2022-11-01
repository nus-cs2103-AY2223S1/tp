package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_REASON_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TIME_PERIOD_DESC;
import static seedu.address.logic.commands.CommandTestUtil.REASON_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.REASON_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_EAR;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_NOSE;
import static seedu.address.logic.commands.CommandTestUtil.TIME_PERIOD_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TIME_PERIOD_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REASON_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REASON_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPOINTMENT;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditAppointmentCommand;
import seedu.address.logic.commands.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.address.model.person.Appointment;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditAppointmentDescriptorBuilder;

public class EditAppointmentCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditAppointmentCommand.MESSAGE_USAGE);
    private final EditAppointmentCommandParser parser = new EditAppointmentCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_REASON_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditAppointmentCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + REASON_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + REASON_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid reason
        assertParseFailure(parser, "1" + INVALID_REASON_DESC, Appointment.REASON_MESSAGE_CONSTRAINTS);
        // invalid date
        assertParseFailure(parser, "1" + INVALID_DATE_DESC, Appointment.DATE_MESSAGE_CONSTRAINTS);
        // invalid timePeriod
        assertParseFailure(parser, "1" + INVALID_TIME_PERIOD_DESC, Appointment.TIME_PERIOD_MESSAGE_CONSTRAINTS);
        // invalid tag
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_REASON_DESC + INVALID_DATE_DESC,
                Appointment.REASON_MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index appointmentIndex = INDEX_FIRST_APPOINTMENT;
        String userInput = appointmentIndex.getOneBased()
                + REASON_DESC_AMY + DATE_DESC_AMY + TIME_PERIOD_DESC_AMY + TAG_DESC_EAR + TAG_DESC_NOSE;

        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder().withReason(VALID_REASON_AMY)
                .withDateTime(LocalDateTime.parse(VALID_DATE_AMY, Appointment.DATE_FORMATTER))
                .withTimePeriod(List.of(1, 0, 0))
                .withTags("Ear", "Nose")
                .build();
        EditAppointmentCommand expectedCommand = new EditAppointmentCommand(appointmentIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        Index appointmentIndex = INDEX_FIRST_APPOINTMENT;
        String userInput = appointmentIndex.getOneBased() + REASON_DESC_AMY;

        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder()
                .withReason(VALID_REASON_AMY).build();
        EditAppointmentCommand expectedCommand = new EditAppointmentCommand(appointmentIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index appointmentIndex = INDEX_FIRST_APPOINTMENT;
        String userInput = appointmentIndex.getOneBased()
                + REASON_DESC_AMY + DATE_DESC_AMY + TIME_PERIOD_DESC_AMY
                + REASON_DESC_BOB + DATE_DESC_BOB + TIME_PERIOD_DESC_BOB;

        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder().withReason(VALID_REASON_BOB)
                .withDateTime(LocalDateTime.parse(VALID_DATE_BOB, Appointment.DATE_FORMATTER))
                .withTimePeriod(List.of(0, 0, 0)).build();
        EditAppointmentCommand expectedCommand = new EditAppointmentCommand(appointmentIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        Index appointmentIndex = INDEX_FIRST_APPOINTMENT;
        String userInput = appointmentIndex.getOneBased()
                + INVALID_REASON_DESC + REASON_DESC_BOB;

        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder().withReason(VALID_REASON_BOB)
                .build();
        EditAppointmentCommand expectedCommand = new EditAppointmentCommand(appointmentIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
