package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.PersonBuilder.DEFAULT_CONTACT_EMAIL;
import static seedu.address.testutil.PersonBuilder.DEFAULT_CONTACT_NAME;
import static seedu.address.testutil.PersonBuilder.DEFAULT_CONTACT_PHONE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.UpdateContactCommand;
import seedu.address.model.category.Category;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Uid;

class UpdateContactCommandParserTest {

    @Test
    void parse_noArgumentsSupplied_displaysHelp() {
        assertParseFailure(new UpdateContactCommandParser(), UpdateContactCommand.COMMAND_WORD,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateContactCommand.MESSAGE_USAGE));
    }

    @Test
    void parse_noUidSupplied_displaysHelp() {
        String input = UpdateContactCommand.COMMAND_WORD + " n/J e/jd@example.com p/81234567";
        assertParseFailure(new UpdateContactCommandParser(), input,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateContactCommand.MESSAGE_USAGE));
    }

    @Test
    void parse_assignNurseToPatient_fail() {
        Command setPhysCommand = new UpdateContactCommand(new Uid(3L), new Name(DEFAULT_CONTACT_NAME),
                new Phone(DEFAULT_CONTACT_PHONE), new Email(DEFAULT_CONTACT_EMAIL),
                new Category(Category.NURSE_SYMBOL));
        String input = UpdateContactCommand.COMMAND_WORD + " id/3 n/" + DEFAULT_CONTACT_NAME
                + " e/" + DEFAULT_CONTACT_EMAIL + " p/" + DEFAULT_CONTACT_PHONE + " c/" + Category.NURSE_SYMBOL;
        assertParseSuccess(new UpdateContactCommandParser(), input, setPhysCommand);
    }

    @Test
    void parse_assignJohnDoeToPatient_success() {
        Command setPhysCommand = new UpdateContactCommand(new Uid(3L), new Name(DEFAULT_CONTACT_NAME),
                new Phone(DEFAULT_CONTACT_PHONE), new Email(DEFAULT_CONTACT_EMAIL),
                new Category(Category.PHYSICIAN_SYMBOL));
        String input = UpdateContactCommand.COMMAND_WORD + " id/3 n/" + DEFAULT_CONTACT_NAME
                + " e/" + DEFAULT_CONTACT_EMAIL + " p/" + DEFAULT_CONTACT_PHONE + " c/" + Category.PHYSICIAN_SYMBOL;
        assertParseSuccess(new UpdateContactCommandParser(), input, setPhysCommand);
    }

    @Test
    void parse_assignJohnDoeNokToPatient_success() {
        Command setPhysCommand = new UpdateContactCommand(new Uid(3L), new Name(DEFAULT_CONTACT_NAME),
                new Phone(DEFAULT_CONTACT_PHONE), new Email(DEFAULT_CONTACT_EMAIL),
                new Category(Category.NEXTOFKIN_SYMBOL));
        String input = UpdateContactCommand.COMMAND_WORD + " id/3 n/" + DEFAULT_CONTACT_NAME
                + " e/" + DEFAULT_CONTACT_EMAIL + " p/" + DEFAULT_CONTACT_PHONE + " c/" + Category.NEXTOFKIN_SYMBOL;
        assertParseSuccess(new UpdateContactCommandParser(), input, setPhysCommand);
    }
}
