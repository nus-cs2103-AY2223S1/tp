package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.RolesCommand;
import seedu.address.model.person.position.Professor;


public class EditRolesCommandParserTest {

    private RolesCommandParser parser = new RolesCommandParser();

    @Test
    public void parse_validArgs_returnsRolesCommand() {
        assertParseSuccess(parser, "1 roles/Coordinator", new RolesCommand(INDEX_FIRST_PERSON, "Coordinator"));
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        assertParseFailure(parser, "1 roles/ccoordinator", String.format(Professor.MESSAGE_CONSTRAINTS));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RolesCommand.MESSAGE_USAGE));
    }

}
