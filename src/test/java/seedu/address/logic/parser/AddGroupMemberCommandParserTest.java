package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.person.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddGroupMemberCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class AddGroupMemberCommandParserTest {

    private AddGroupMemberCommandParser parser = new AddGroupMemberCommandParser();
    @Test
    public void parse_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

    @Test
    public void parse_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("Alpha Andre"));
        assertThrows(ParseException.class, () -> parser.parse("Alpha n/Bob"));
        assertThrows(ParseException.class, () -> parser.parse("g/Alpha Charles"));
        assertThrows(ParseException.class, () -> parser.parse(" "));
    }

    @Test
    public void parse_validInput_commandParseSuccess() {
        assertParseSuccess(parser, AddGroupMemberCommand.COMMAND_WORD + " g/group n/name",
                new AddGroupMemberCommand("group", "name"));
    }
}
