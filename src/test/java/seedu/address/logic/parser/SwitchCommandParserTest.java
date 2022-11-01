package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_MISSING_PREFIXES_ALL;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_FIELD;
import static seedu.address.logic.commands.CommandTestUtil.TYPE_CONSULTATION;
import static seedu.address.logic.commands.CommandTestUtil.TYPE_STUDENT;
import static seedu.address.logic.commands.CommandTestUtil.TYPE_TUTORIAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FIELD;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.ModelType.STUDENT;
import static seedu.address.model.ModelType.TUTORIAL;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SwitchCommand;

public class SwitchCommandParserTest {

    private SwitchCommandParser parser = new SwitchCommandParser();

    @Test
    public void parse_validField_success() {

        // valid field - accepted
        assertParseSuccess(parser, TYPE_STUDENT, new SwitchCommand(STUDENT));

        // multiple fields - last field accepted
        assertParseSuccess(parser, TYPE_CONSULTATION + TYPE_STUDENT + TYPE_TUTORIAL,
                new SwitchCommand(TUTORIAL));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        // missing fields prefix
        assertParseFailure(parser, "student",
                String.format(MESSAGE_MISSING_PREFIXES_ALL, Arrays.toString(new Prefix[]{PREFIX_FIELD}),
                SwitchCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid field
        assertParseFailure(parser, INVALID_FIELD, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SwitchCommand.MESSAGE_USAGE));

        //multiple fields - last field invalid
        assertParseFailure(parser, TYPE_CONSULTATION + TYPE_TUTORIAL + INVALID_FIELD,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SwitchCommand.MESSAGE_USAGE));

        //empty field
        assertParseFailure(parser, " f/  ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SwitchCommand.MESSAGE_USAGE));

        //case-sensitive
        assertParseFailure(parser, " f/Student", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SwitchCommand.MESSAGE_USAGE));

    }
}
