package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.client.EditClientCommand;
import seedu.address.model.Name;
import seedu.address.model.client.ClientEmail;
import seedu.address.model.client.ClientId;
import seedu.address.model.client.ClientMobile;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

/**
 * Represents tests for parsing Edit Project commands.
 */
public class EditClientCommandParserTest {

    private ClientCommandParser parser = new ClientCommandParser();
    @Test
    public void parse_compulsoryAndOptionalFieldsPresent_success() {

        Name name = new Name("Harry");
        ClientEmail email = new ClientEmail("harry@gmail.com");
        ClientMobile mobile = new ClientMobile("1234567");
        ClientId id = new ClientId(1);

        EditClientCommand expectedCommand = new EditClientCommand(id, name, email, mobile);

        assertParseSuccess(parser, EditClientCommand.COMMAND_FLAG, " n/Harry e/harry@gmail.com m/1234567 c/1",
                expectedCommand);
    }

    @Test
    public void parse_optionalFieldsMissing_success() {


    }
}
