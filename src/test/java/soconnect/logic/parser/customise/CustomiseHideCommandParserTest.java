package soconnect.logic.parser.customise;

import static org.junit.jupiter.api.Assertions.*;
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
import soconnect.logic.commands.customise.CustomiseHideCommand;
import soconnect.model.ModelManager;
import soconnect.model.UserPrefs;

class CustomiseHideCommandParserTest {

    private CustomiseHideCommandParser parser = new CustomiseHideCommandParser();

    @Test
    public void parse_hideOneAttribute_success() {
        ArrayList<CustomiseCommand.Attribute> attributes = new ArrayList<>();
        attributes.add(ADDRESS);
        ModelManager model = new ModelManager(getTypicalSoConnect(), new UserPrefs());
        model.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "NONE"));

        assertParseSuccess(parser, " a/", new CustomiseHideCommand(attributes));
    }

    @Test
    public void parse_hideFourAttributes_success() {
        ArrayList<CustomiseCommand.Attribute> attributes = new ArrayList<>();
        attributes.add(ADDRESS);
        attributes.add(TAGS);
        attributes.add(PHONE);
        attributes.add(EMAIL);
        ModelManager model = new ModelManager(getTypicalSoConnect(), new UserPrefs());
        model.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "NONE"));

        assertParseSuccess(parser, " a/ t/ p/ e/", new CustomiseHideCommand(attributes));
    }

    @Test
    public void parse_hideInvalidArgs_throwsParseException() {
        ModelManager model = new ModelManager(getTypicalSoConnect(), new UserPrefs());
        model.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "ADDRESS,TAGS,PHONE,EMAIL"));

        assertParseFailure(parser, " invalidText",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CustomiseHideCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_hideRepeatArgs_throwsParseException() {
        ModelManager model = new ModelManager(getTypicalSoConnect(), new UserPrefs());
        model.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "ADDRESS,TAGS,PHONE,EMAIL"));

        assertParseFailure(parser, " p/ p/", MESSAGE_DUPLICATE_ATTRIBUTE);
    }

    @Test
    public void parse_hideWithoutArgs_throwsParseException() {
        assertParseFailure(parser, "hide",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CustomiseHideCommand.MESSAGE_USAGE));
    }
}
