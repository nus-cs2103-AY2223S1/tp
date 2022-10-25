package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeletePersonCommand;
import seedu.address.model.person.Name;

public class DeletePersonCommandParserTest {

    private DeletePersonCommandParser parser = new DeletePersonCommandParser();

    @Test
    public void parse_validArgs_returnsDeletePersonCommand() {
        assertParseSuccess(parser, NAME_DESC_AMY, new DeletePersonCommand(new Name(VALID_NAME_AMY)));
    }

    @Test
    public void execute_invalidName_throwsCommandException() {
        assertParseFailure(parser, INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser,
                "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePersonCommand.MESSAGE_USAGE));
    }
}
