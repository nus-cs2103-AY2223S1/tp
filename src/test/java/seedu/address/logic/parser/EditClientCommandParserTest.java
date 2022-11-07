package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_MISSING_ARGUMENTS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.client.EditClientCommand;
import seedu.address.model.Name;
import seedu.address.model.client.ClientEmail;
import seedu.address.model.client.ClientMobile;

/**
 * Represents tests for parsing Edit Client commands.
 */
public class EditClientCommandParserTest {
    private ClientCommandParser parser = new ClientCommandParser();

    @Test
    public void parse_argumentsAbsent_failure() {

        //extraneous words
        assertParseFailure(parser, EditClientCommand.COMMAND_FLAG, "abcd",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditClientCommand.MESSAGE_USAGE));

        //missing all arguments
        assertParseFailure(parser, EditClientCommand.COMMAND_FLAG, " ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditClientCommand.MESSAGE_USAGE));


        //missing compulsory id
        assertParseFailure(parser, EditClientCommand.COMMAND_FLAG, " n/Harry e/harry@gmail.com m/1234567",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditClientCommand.MESSAGE_USAGE));

        //has only id and no optionals
        assertParseFailure(parser, EditClientCommand.COMMAND_FLAG, " c/1",
                String.format(MESSAGE_MISSING_ARGUMENTS, EditClientCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArguments_failure() {

        //invalid name
        assertParseFailure(parser, EditClientCommand.COMMAND_FLAG, " n/###Harry c/1",
                String.format(Name.MESSAGE_CONSTRAINTS));
        assertParseFailure(parser, EditClientCommand.COMMAND_FLAG, " n/ c/1",
                String.format(Name.MESSAGE_CONSTRAINTS));

        //invalid client id
        assertParseFailure(parser, EditClientCommand.COMMAND_FLAG, " n/Harry c/-1",
                String.format(ParserUtil.MESSAGE_INVALID_INDEX));

        //invalid email
        assertParseFailure(parser, EditClientCommand.COMMAND_FLAG, " e/invalidemail c/1",
                String.format(ClientEmail.MESSAGE_CONSTRAINTS));
        assertParseFailure(parser, EditClientCommand.COMMAND_FLAG, " e/ c/1",
                String.format(ClientEmail.MESSAGE_CONSTRAINTS));

        //invalid phone
        assertParseFailure(parser, EditClientCommand.COMMAND_FLAG, " m/12 c/1",
                ClientMobile.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, EditClientCommand.COMMAND_FLAG, " m/ c/1",
                ClientMobile.MESSAGE_CONSTRAINTS);
    }
}
