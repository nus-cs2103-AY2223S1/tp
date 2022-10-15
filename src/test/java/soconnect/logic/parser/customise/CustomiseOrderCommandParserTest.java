package soconnect.logic.parser.customise;

import static org.junit.jupiter.api.Assertions.*;
import static soconnect.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static soconnect.logic.commands.customise.CustomiseCommand.Attribute.ADDRESS;
import static soconnect.logic.commands.customise.CustomiseCommand.Attribute.EMAIL;
import static soconnect.logic.commands.customise.CustomiseCommand.Attribute.PHONE;
import static soconnect.logic.commands.customise.CustomiseCommand.Attribute.TAGS;
import static soconnect.logic.parser.CommandParserTestUtil.assertParseFailure;
import static soconnect.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static soconnect.testutil.TypicalPersons.getTypicalSoConnect;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import soconnect.commons.core.GuiSettings;
import soconnect.logic.commands.customise.CustomiseCommand;
import soconnect.logic.commands.customise.CustomiseOrderCommand;
import soconnect.model.ModelManager;
import soconnect.model.UserPrefs;

class CustomiseOrderCommandParserTest {

    private CustomiseOrderCommandParser parser = new CustomiseOrderCommandParser();

    @Test
    public void parse_orderValidArgs_success() {
        ArrayList<CustomiseCommand.Attribute> attributes = new ArrayList<>();
        attributes.add(ADDRESS);
        attributes.add(TAGS);
        attributes.add(PHONE);
        attributes.add(EMAIL);
        ModelManager model = new ModelManager(getTypicalSoConnect(), new UserPrefs());
        model.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "NONE"));

        assertParseSuccess(parser, " a/ t/ p/ e/", new CustomiseOrderCommand(attributes));
    }

    @Test
    public void parse_orderIncompleteArgs_success() {
        ArrayList<CustomiseCommand.Attribute> attributes = new ArrayList<>();
        attributes.add(ADDRESS);
        attributes.add(TAGS);
        attributes.add(PHONE);
        attributes.add(EMAIL);
        ModelManager model = new ModelManager(getTypicalSoConnect(), new UserPrefs());
        model.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "NONE"));

        assertParseSuccess(parser, " a/ t/ p/", new CustomiseOrderCommand(attributes));
    }

    @Test
    public void parse_orderWithoutArgs_throwsParseException() {
        assertParseFailure(parser, "order",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CustomiseOrderCommand.MESSAGE_USAGE));
    }
}