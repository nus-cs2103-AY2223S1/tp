package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.HideAppointmentsCommand;
import seedu.address.model.person.predicates.HideAppointmentPredicate;
import seedu.address.model.person.predicates.HideAppointmentPredicate.HideBy;

public class HideAppointmentsCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, HideAppointmentsCommand.MESSAGE_USAGE);
    private HideAppointmentsCommandParser parser = new HideAppointmentsCommandParser();

    @Test
    public void parse_validReasonArgs_returnHideCommand() {
        List<String> l = new ArrayList<>();
        l.add("ear");
        assertParseSuccess(parser, " r/ear", new HideAppointmentsCommand(
                new HideAppointmentPredicate(HideBy.KEYWORD, l)));
    }

    @Test
    public void parse_validIsMarkedArgs_returnHideCommand() {
        List<String> l = new ArrayList<>();
        l.add("marked");
        assertParseSuccess(parser, " s/marked", new HideAppointmentsCommand(
                new HideAppointmentPredicate(HideBy.IS_MARKED, l)));
    }

    @Test
    public void parse_invalidArgs_fails() {
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);
    }
}
