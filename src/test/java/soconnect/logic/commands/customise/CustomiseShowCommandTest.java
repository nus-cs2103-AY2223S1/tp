package soconnect.logic.commands.customise;

import static soconnect.logic.commands.CommandTestUtil.assertCommandSuccess;
import static soconnect.logic.commands.customise.CustomiseCommand.Attribute.ADDRESS;
import static soconnect.logic.commands.customise.CustomiseCommand.Attribute.EMAIL;
import static soconnect.logic.commands.customise.CustomiseCommand.Attribute.PHONE;
import static soconnect.logic.commands.customise.CustomiseCommand.Attribute.TAGS;
import static soconnect.logic.commands.customise.CustomiseShowCommand.MESSAGE_SUCCESS;
import static soconnect.testutil.TypicalPersons.getTypicalSoConnect;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import soconnect.commons.core.GuiSettings;
import soconnect.model.Model;
import soconnect.model.ModelManager;
import soconnect.model.UserPrefs;

class CustomiseShowCommandTest {

    private Model model = new ModelManager(getTypicalSoConnect(), new UserPrefs());

    @Test
    public void execute_showOneAttribute_success() {
        ArrayList<CustomiseCommand.Attribute> attributes = new ArrayList<>();
        attributes.add(EMAIL);
        model.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "ADDRESS,EMAIL,PHONE,TAGS"));
        CustomiseShowCommand customiseShowCommand = new CustomiseShowCommand(attributes);

        ModelManager expectedModel = new ModelManager(model.getSoConnect(), new UserPrefs());
        expectedModel.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "ADDRESS,PHONE,TAGS"));

        assertCommandSuccess(customiseShowCommand, model, MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_showFourAttributes_success() {
        ArrayList<CustomiseCommand.Attribute> attributes = new ArrayList<>();
        attributes.add(ADDRESS);
        attributes.add(TAGS);
        attributes.add(PHONE);
        attributes.add(EMAIL);
        model.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "ADDRESS,EMAIL,PHONE,TAGS"));
        CustomiseShowCommand customiseShowCommand = new CustomiseShowCommand(attributes);

        ModelManager expectedModel = new ModelManager(model.getSoConnect(), new UserPrefs());
        expectedModel.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "NONE"));

        assertCommandSuccess(customiseShowCommand, model, MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_showRepeatAttributes_success() {
        ArrayList<CustomiseCommand.Attribute> attributes = new ArrayList<>();
        attributes.add(ADDRESS);
        attributes.add(ADDRESS);
        attributes.add(PHONE);
        attributes.add(PHONE);
        model.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "ADDRESS,EMAIL,PHONE,TAGS"));
        CustomiseShowCommand customiseShowCommand = new CustomiseShowCommand(attributes);

        ModelManager expectedModel = new ModelManager(model.getSoConnect(), new UserPrefs());
        expectedModel.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "EMAIL,TAGS"));

        assertCommandSuccess(customiseShowCommand, model, MESSAGE_SUCCESS, expectedModel);
    }
}
