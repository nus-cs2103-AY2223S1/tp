package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ListStudentCommand;
import seedu.address.logic.commands.ListTuitionClassCommand;
import seedu.address.logic.commands.ListTutorCommand;

public class ListCommandParserTest {

    private ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_missingPreamble_failure() {

        assertParseFailure(parser, "", AddCommand.Entity.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidPreamble_failure() {

        assertParseFailure(parser, "gangster", AddCommand.Entity.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser, "student and some string", AddCommand.Entity.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser, "student n/name", AddCommand.Entity.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_validPreamble_success() {

        assertParseSuccess(parser, "student", new ListStudentCommand());

        assertParseSuccess(parser, "tutor", new ListTutorCommand());

        assertParseSuccess(parser, "class", new ListTuitionClassCommand());
    }
}