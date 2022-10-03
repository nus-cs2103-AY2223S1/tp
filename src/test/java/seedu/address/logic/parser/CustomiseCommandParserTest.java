package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CustomiseCommand.Attribute.ADDRESS;
import static seedu.address.logic.commands.CustomiseCommand.Attribute.EMAIL;
import static seedu.address.logic.commands.CustomiseCommand.Attribute.PHONE;
import static seedu.address.logic.commands.CustomiseCommand.Attribute.TAGS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CustomiseCommand;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.logic.commands.CustomiseCommand.Attribute;

class CustomiseCommandParserTest {

    private CustomiseCommandParser parser = new CustomiseCommandParser();

    @Test
    public void parse_validArgs_returnsCustomiseCommand() {
        Attribute[] attributes = new Attribute[]{ADDRESS, TAGS, PHONE, EMAIL};
        ModelManager model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.setGuiSettings(new GuiSettings(1000, 500, 300, 100, "ADDRESS>TAGS>PHONE>EMAIL"));

        assertParseSuccess(parser, "order a/ t/ p/ e/", new CustomiseCommand("order", attributes));
    }

    @Test
    public void parse_IncompleteArgs_returnsSameCommand() {
        Attribute[] attributes = new Attribute[]{ADDRESS, TAGS, PHONE, EMAIL};
        ModelManager model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.setGuiSettings(new GuiSettings(1000, 500, 300, 100, "ADDRESS>TAGS>PHONE>EMAIL"));

        assertParseSuccess(parser, "order a/ t/ p/", new CustomiseCommand("order", attributes));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CustomiseCommand.MESSAGE_USAGE));
    }
}