package soconnect.logic.parser.customise;

import static soconnect.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static soconnect.logic.commands.customise.CustomiseCommand.Attribute.ADDRESS;
import static soconnect.logic.commands.customise.CustomiseCommand.Attribute.EMAIL;
import static soconnect.logic.commands.customise.CustomiseCommand.Attribute.PHONE;
import static soconnect.logic.commands.customise.CustomiseCommand.Attribute.TAGS;
import static soconnect.logic.commands.customise.CustomiseCommand.MESSAGE_DUPLICATE_ATTRIBUTE;
import static soconnect.logic.parser.CommandParserTestUtil.assertParseFailure;
import static soconnect.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static soconnect.testutil.TypicalPersons.getTypicalSoConnect;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import soconnect.commons.core.GuiSettings;
import soconnect.logic.commands.customise.CustomiseCommand;
import soconnect.logic.commands.customise.CustomiseShowCommand;
import soconnect.model.ModelManager;
import soconnect.model.UserPrefs;

class CustomiseShowCommandParserTest {

    private CustomiseShowCommandParser parser = new CustomiseShowCommandParser();

    @Test
    public void parse_showOneAttribute_success() {
        ArrayList<CustomiseCommand.Attribute> attributes = new ArrayList<>();
        attributes.add(TAGS);
        ModelManager model = new ModelManager(getTypicalSoConnect(), new UserPrefs());
        model.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "ADDRESS,TAGS,PHONE,EMAIL"));

        assertParseSuccess(parser, " t/", new CustomiseShowCommand(attributes));
    }

    @Test
    public void parse_showFourAttributes_success() {
        ArrayList<CustomiseCommand.Attribute> attributes = new ArrayList<>();
        attributes.add(ADDRESS);
        attributes.add(TAGS);
        attributes.add(PHONE);
        attributes.add(EMAIL);
        ModelManager model = new ModelManager(getTypicalSoConnect(), new UserPrefs());
        model.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "ADDRESS,TAGS,PHONE,EMAIL"));

        assertParseSuccess(parser, " a/ t/ p/ e/", new CustomiseShowCommand(attributes));
    }

    @Test
    public void parse_showInvalidArgs_throwsParseException() {
        ModelManager model = new ModelManager(getTypicalSoConnect(), new UserPrefs());
        model.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "ADDRESS,TAGS,PHONE,EMAIL"));

        assertParseFailure(parser, " invalidText",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CustomiseShowCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_showRepeatArgs_throwsParseException() {
        ModelManager model = new ModelManager(getTypicalSoConnect(), new UserPrefs());
        model.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "ADDRESS,TAGS,PHONE,EMAIL"));

        assertParseFailure(parser, " a/ a/", MESSAGE_DUPLICATE_ATTRIBUTE);
    }

    @Test
    public void parse_showWithoutArgs_throwsParseException() {
        assertParseFailure(parser, "show",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CustomiseShowCommand.MESSAGE_USAGE));
    }
}
