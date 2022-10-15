package soconnect.logic.commands.customise;

import static soconnect.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static soconnect.logic.commands.CommandTestUtil.assertCommandFailure;
import static soconnect.logic.commands.CommandTestUtil.assertCommandSuccess;
import static soconnect.logic.commands.customise.CustomiseCommand.Attribute;
import static soconnect.logic.commands.customise.CustomiseCommand.Attribute.ADDRESS;
import static soconnect.logic.commands.customise.CustomiseCommand.Attribute.EMAIL;
import static soconnect.logic.commands.customise.CustomiseCommand.Attribute.PHONE;
import static soconnect.logic.commands.customise.CustomiseCommand.Attribute.TAGS;
import static soconnect.logic.commands.customise.CustomiseOrderCommand.MESSAGE_SUCCESS;
import static soconnect.testutil.TypicalPersons.getTypicalSoConnect;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import soconnect.commons.core.GuiSettings;
import soconnect.model.Model;
import soconnect.model.ModelManager;
import soconnect.model.UserPrefs;

class CustomiseOrderCommandTest {

    private Model model = new ModelManager(getTypicalSoConnect(), new UserPrefs());

    @Test
    public void execute_order_success() {
        ArrayList<Attribute> attributes = new ArrayList<>();
        attributes.add(ADDRESS);
        attributes.add(TAGS);
        attributes.add(PHONE);
        attributes.add(EMAIL);
        model.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "NONE"));
        CustomiseOrderCommand customiseOrderCommand = new CustomiseOrderCommand(attributes);

        ModelManager expectedModel = new ModelManager(model.getSoConnect(), new UserPrefs());
        expectedModel.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "NONE"));

        assertCommandSuccess(customiseOrderCommand, model, MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_orderWithOneInput_throwsCommandException() {
        ArrayList<Attribute> attributes = new ArrayList<>();
        attributes.add(EMAIL);
        model.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "NONE"));
        CustomiseOrderCommand customiseOrderCommand = new CustomiseOrderCommand(attributes);

        assertCommandFailure(customiseOrderCommand, model, MESSAGE_INVALID_COMMAND_FORMAT);
    }

}