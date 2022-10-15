package soconnect.logic.parser;

import static soconnect.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static soconnect.logic.commands.CustomiseCommand.Attribute.ADDRESS;
import static soconnect.logic.commands.CustomiseCommand.Attribute.EMAIL;
import static soconnect.logic.commands.CustomiseCommand.Attribute.PHONE;
import static soconnect.logic.commands.CustomiseCommand.Attribute.TAGS;
import static soconnect.logic.commands.CustomiseCommand.CustomiseSubCommand.HIDE;
import static soconnect.logic.commands.CustomiseCommand.CustomiseSubCommand.ORDER;
import static soconnect.logic.commands.CustomiseCommand.CustomiseSubCommand.SHOW;
import static soconnect.logic.parser.CommandParserTestUtil.assertParseFailure;
import static soconnect.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static soconnect.testutil.TypicalPersons.getTypicalSoConnect;

import org.junit.jupiter.api.Test;

import soconnect.commons.core.GuiSettings;
import soconnect.logic.commands.CustomiseCommand;
import soconnect.logic.commands.CustomiseCommand.Attribute;
import soconnect.model.ModelManager;
import soconnect.model.TodoList;
import soconnect.model.UserPrefs;

class CustomiseCommandParserTest {

    private CustomiseCommandParser parser = new CustomiseCommandParser();

    @Test
    public void parse_orderValidArgs_success() {
        Attribute[] attributes = new Attribute[]{ADDRESS, TAGS, PHONE, EMAIL};
        ModelManager model = new ModelManager(getTypicalSoConnect(), new TodoList(), new UserPrefs());
        model.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "NONE"));

        assertParseSuccess(parser, "order a/ t/ p/ e/", new CustomiseCommand(ORDER, attributes));
    }

    @Test
    public void parse_orderIncompleteArgs_success() {
        Attribute[] attributes = new Attribute[]{ADDRESS, TAGS, PHONE, EMAIL};
        ModelManager model = new ModelManager(getTypicalSoConnect(), new TodoList(), new UserPrefs());
        model.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "NONE"));

        assertParseSuccess(parser, "order a/ t/ p/", new CustomiseCommand(ORDER, attributes));
    }

    @Test
    public void parse_hideOneAttribute_success() {
        Attribute[] attributes = new Attribute[]{ADDRESS};
        ModelManager model = new ModelManager(getTypicalSoConnect(), new TodoList(), new UserPrefs());
        model.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "NONE"));

        assertParseSuccess(parser, "hide a/", new CustomiseCommand(HIDE, attributes));
    }

    @Test
    public void parse_hideFourAttributes_success() {
        Attribute[] attributes = new Attribute[]{ADDRESS, TAGS, PHONE, EMAIL};
        ModelManager model = new ModelManager(getTypicalSoConnect(), new TodoList(), new UserPrefs());
        model.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "NONE"));

        assertParseSuccess(parser, "hide a/ t/ p/ e/", new CustomiseCommand(HIDE, attributes));
    }

    @Test
    public void parse_showOneAttribute_success() {
        Attribute[] attributes = new Attribute[]{TAGS};
        ModelManager model = new ModelManager(getTypicalSoConnect(), new TodoList(), new UserPrefs());
        model.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "ADDRESS,TAGS,PHONE,EMAIL"));

        assertParseSuccess(parser, "show t/", new CustomiseCommand(SHOW, attributes));
    }

    @Test
    public void parse_showFourAttributes_success() {
        Attribute[] attributes = new Attribute[]{ADDRESS, TAGS, PHONE, EMAIL};
        ModelManager model = new ModelManager(getTypicalSoConnect(), new TodoList(), new UserPrefs());
        model.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "ADDRESS,TAGS,PHONE,EMAIL"));

        assertParseSuccess(parser, "show a/ t/ p/ e/", new CustomiseCommand(SHOW, attributes));
    }

    @Test
    public void parse_hideInvalidArgs_throwsParseException() {
        ModelManager model = new ModelManager(getTypicalSoConnect(), new TodoList(), new UserPrefs());
        model.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "ADDRESS,TAGS,PHONE,EMAIL"));

        assertParseFailure(parser, "hide invalidText",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CustomiseCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_hideRepeatArgs_throwsParseException() {
        ModelManager model = new ModelManager(getTypicalSoConnect(), new TodoList(), new UserPrefs());
        model.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "ADDRESS,TAGS,PHONE,EMAIL"));

        assertParseFailure(parser, "hide p/ p/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CustomiseCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_showInvalidArgs_throwsParseException() {
        ModelManager model = new ModelManager(getTypicalSoConnect(), new TodoList(), new UserPrefs());
        model.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "ADDRESS,TAGS,PHONE,EMAIL"));

        assertParseFailure(parser, "show invalidText",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CustomiseCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_showRepeatArgs_throwsParseException() {
        ModelManager model = new ModelManager(getTypicalSoConnect(), new TodoList(), new UserPrefs());
        model.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "ADDRESS,TAGS,PHONE,EMAIL"));

        assertParseFailure(parser, "show a/ a/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CustomiseCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_orderWithoutArgs_throwsParseException() {
        assertParseFailure(parser, "order",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CustomiseCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_showWithoutArgs_throwsParseException() {
        assertParseFailure(parser, "show",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CustomiseCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_hideWithoutArgs_throwsParseException() {
        assertParseFailure(parser, "hide",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CustomiseCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CustomiseCommand.MESSAGE_USAGE));
    }
}
