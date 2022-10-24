package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.PersonBuilder.DEFAULT_PHYS_EMAIL;
import static seedu.address.testutil.PersonBuilder.DEFAULT_PHYS_NAME;
import static seedu.address.testutil.PersonBuilder.DEFAULT_PHYS_PHONE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.UpdatePhysicianCommand;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Uid;


class UpdatePhysicianCommandParserTest {

    @Test
    void parse_noArgumentsSupplied_displaysHelp() {
        assertParseFailure(new SetPhysicianCommandParser(), UpdatePhysicianCommand.COMMAND_WORD,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdatePhysicianCommand.MESSAGE_USAGE));
    }

    @Test
    void parse_noUidSupplied_displaysHelp() {
        String input = UpdatePhysicianCommand.COMMAND_WORD + " n/J e/jd@example.com p/81234567";
        assertParseFailure(new SetPhysicianCommandParser(), input,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdatePhysicianCommand.MESSAGE_USAGE));
    }

    @Test
    void parse_assignJohnDoeToPatient_success() {
        Command setPhysCommand = new UpdatePhysicianCommand(new Uid(3L), new Name(DEFAULT_PHYS_NAME),
                new Phone(DEFAULT_PHYS_PHONE), new Email(DEFAULT_PHYS_EMAIL));
        String input = UpdatePhysicianCommand.COMMAND_WORD + " id/3 n/" + DEFAULT_PHYS_NAME + " e/" + DEFAULT_PHYS_EMAIL
                + " p/" + DEFAULT_PHYS_PHONE;
        assertParseSuccess(new SetPhysicianCommandParser(), input, setPhysCommand);
    }
}
