package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.HideAppointmentsCommand;
import seedu.address.model.person.HideAppointmentPredicate;

public class HideAppointmentsCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, HideAppointmentsCommand.MESSAGE_USAGE);
    private HideAppointmentsCommandParser parser = new HideAppointmentsCommandParser();

    @Test
    public void parse_validReasonArgs_returnHideCommand() {
        assertParseSuccess(parser, " r/ear", new HideAppointmentsCommand(
                new HideAppointmentPredicate(HideAppointmentPredicate.HideBy.KEYWORD, "ear")));
    }

    @Test
    public void parse_validIsMarkedArgs_returnHideCommand() {
        assertParseSuccess(parser, " s/marked", new HideAppointmentsCommand(
                new HideAppointmentPredicate(HideAppointmentPredicate.HideBy.IS_MARKED, "marked")));
    }

    @Test
    public void parse_invalidArgs_fails() {
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);
    }
}
