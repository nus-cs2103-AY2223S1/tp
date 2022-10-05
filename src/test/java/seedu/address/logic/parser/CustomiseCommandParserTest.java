package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CustomiseCommand.Attribute.ADDRESS;
import static seedu.address.logic.commands.CustomiseCommand.Attribute.EMAIL;
import static seedu.address.logic.commands.CustomiseCommand.Attribute.PHONE;
import static seedu.address.logic.commands.CustomiseCommand.Attribute.TAGS;
import static seedu.address.logic.commands.CustomiseCommand.CustomiseSubCommand.HIDE;
import static seedu.address.logic.commands.CustomiseCommand.CustomiseSubCommand.ORDER;
import static seedu.address.logic.commands.CustomiseCommand.CustomiseSubCommand.SHOW;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CustomiseCommand;
import seedu.address.logic.commands.CustomiseCommand.Attribute;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

class CustomiseCommandParserTest {

    private CustomiseCommandParser parser = new CustomiseCommandParser();

    @Test
    public void parse_order_validArgs_success() {
        Attribute[] attributes = new Attribute[]{ADDRESS, TAGS, PHONE, EMAIL};
        ModelManager model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "NONE"));

        assertParseSuccess(parser, "order a/ t/ p/ e/", new CustomiseCommand(ORDER, attributes));
    }

    @Test
    public void parse_order_incompleteArgs_success() {
        Attribute[] attributes = new Attribute[]{ADDRESS, TAGS, PHONE, EMAIL};
        ModelManager model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "NONE"));

        assertParseSuccess(parser, "order a/ t/ p/", new CustomiseCommand(ORDER, attributes));
    }

    @Test
    public void parse_hideOneAttribute_success() {
        Attribute[] attributes = new Attribute[]{ADDRESS};
        ModelManager model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "NONE"));

        assertParseSuccess(parser, "hide a/", new CustomiseCommand(HIDE, attributes));
    }

    @Test
    public void parse_hideFourAttributes_success() {
        Attribute[] attributes = new Attribute[]{ADDRESS, TAGS, PHONE, EMAIL};
        ModelManager model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "NONE"));

        assertParseSuccess(parser, "hide a/ t/ p/ e/", new CustomiseCommand(HIDE, attributes));
    }

    @Test
    public void parse_showOneAttribute_success() {
        Attribute[] attributes = new Attribute[]{TAGS};
        ModelManager model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "ADDRESS,TAGS,PHONE,EMAIL"));

        assertParseSuccess(parser, "show t/", new CustomiseCommand(SHOW, attributes));
    }

    @Test
    public void parse_showFourAttributes_success() {
        Attribute[] attributes = new Attribute[]{ADDRESS, TAGS, PHONE, EMAIL};
        ModelManager model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "ADDRESS,TAGS,PHONE,EMAIL"));

        assertParseSuccess(parser, "show a/ t/ p/ e/", new CustomiseCommand(SHOW, attributes));
    }

    @Test
    public void parse_hide_invalidArgs_throwsParseException() {
        ModelManager model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "ADDRESS,TAGS,PHONE,EMAIL"));

        assertParseFailure(parser, "hide invalidText",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CustomiseCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_hide_repeatArgs_throwsParseException() {
        ModelManager model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "ADDRESS,TAGS,PHONE,EMAIL"));

        assertParseFailure(parser, "hide p/ p/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CustomiseCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_show_invalidArgs_throwsParseException() {
        ModelManager model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "ADDRESS,TAGS,PHONE,EMAIL"));

        assertParseFailure(parser, "show invalidText",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CustomiseCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_show_repeatArgs_throwsParseException() {
        ModelManager model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "ADDRESS,TAGS,PHONE,EMAIL"));

        assertParseFailure(parser, "show a/ a/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CustomiseCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_order_withoutArgs_throwsParseException() {
        assertParseFailure(parser, "order",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CustomiseCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_show_withoutArgs_throwsParseException() {
        assertParseFailure(parser, "show",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CustomiseCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_hide_withoutArgs_throwsParseException() {
        assertParseFailure(parser, "hide",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CustomiseCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CustomiseCommand.MESSAGE_USAGE));
    }
}
