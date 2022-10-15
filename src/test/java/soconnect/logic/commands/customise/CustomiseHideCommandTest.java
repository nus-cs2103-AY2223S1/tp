package soconnect.logic.commands.customise;

import static soconnect.logic.commands.CommandTestUtil.assertCommandSuccess;
import static soconnect.logic.commands.customise.CustomiseCommand.Attribute.ADDRESS;
import static soconnect.logic.commands.customise.CustomiseCommand.Attribute.EMAIL;
import static soconnect.logic.commands.customise.CustomiseCommand.Attribute.PHONE;
import static soconnect.logic.commands.customise.CustomiseCommand.Attribute.TAGS;
import static soconnect.logic.commands.customise.CustomiseHideCommand.MESSAGE_SUCCESS;
import static soconnect.testutil.TypicalPersons.getTypicalSoConnect;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import soconnect.commons.core.GuiSettings;
import soconnect.model.Model;
import soconnect.model.ModelManager;
import soconnect.model.UserPrefs;

class CustomiseHideCommandTest {

    private Model model = new ModelManager(getTypicalSoConnect(), new UserPrefs());

    @Test
    public void execute_hideOneAttribute_success() {
        ArrayList<CustomiseCommand.Attribute> attributes = new ArrayList<>();
        attributes.add(ADDRESS);
        model.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "NONE"));
        CustomiseHideCommand customiseHideCommand = new CustomiseHideCommand(attributes);

        ModelManager expectedModel = new ModelManager(model.getSoConnect(), new UserPrefs());
        expectedModel.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "ADDRESS"));

        assertCommandSuccess(customiseHideCommand, model, MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_hideFourAttributes_success() {
        ArrayList<CustomiseCommand.Attribute> attributes = new ArrayList<>();
        attributes.add(ADDRESS);
        attributes.add(TAGS);
        attributes.add(PHONE);
        attributes.add(EMAIL);
        model.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "NONE"));
        CustomiseHideCommand customiseHideCommand = new CustomiseHideCommand(attributes);

        ModelManager expectedModel = new ModelManager(model.getSoConnect(), new UserPrefs());
        expectedModel.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "ADDRESS,EMAIL,PHONE,TAGS"));

        assertCommandSuccess(customiseHideCommand, model, MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_hideRepeatAttributes_success() {
        ArrayList<CustomiseCommand.Attribute> attributes = new ArrayList<>();
        attributes.add(PHONE);
        attributes.add(PHONE);
        attributes.add(TAGS);
        model.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "NONE"));
        CustomiseHideCommand customiseHideCommand = new CustomiseHideCommand(attributes);

        ModelManager expectedModel = new ModelManager(model.getSoConnect(), new UserPrefs());
        expectedModel.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "PHONE,TAGS"));

        assertCommandSuccess(customiseHideCommand, model, MESSAGE_SUCCESS, expectedModel);
    }
}