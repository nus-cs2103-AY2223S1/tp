package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CustomiseCommand.Attribute.ADDRESS;
import static seedu.address.logic.commands.CustomiseCommand.Attribute.EMAIL;
import static seedu.address.logic.commands.CustomiseCommand.Attribute.PHONE;
import static seedu.address.logic.commands.CustomiseCommand.Attribute.TAGS;
import static seedu.address.logic.commands.CustomiseCommand.CustomiseSubCommand.HIDE;
import static seedu.address.logic.commands.CustomiseCommand.CustomiseSubCommand.ORDER;
import static seedu.address.logic.commands.CustomiseCommand.CustomiseSubCommand.SHOW;
import static seedu.address.logic.commands.CustomiseCommand.MESSAGE_HIDE_SUCCESS;
import static seedu.address.logic.commands.CustomiseCommand.MESSAGE_ORDER_SUCCESS;
import static seedu.address.logic.commands.CustomiseCommand.MESSAGE_SHOW_SUCCESS;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CustomiseCommand.Attribute;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

class CustomiseCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_order_success() {
        Attribute[] attributes = new Attribute[]{ADDRESS, TAGS, PHONE, EMAIL};
        model.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "NONE"));
        CustomiseCommand customiseCommand = new CustomiseCommand(ORDER, attributes);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "NONE"));

        assertCommandSuccess(customiseCommand, model, MESSAGE_ORDER_SUCCESS, expectedModel);
    }

    @Test
    public void execute_orderWithOneInput_throwsCommandException() {
        Attribute[] attributes = new Attribute[]{EMAIL};
        model.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "NONE"));
        CustomiseCommand customiseCommand = new CustomiseCommand(ORDER, attributes);

        assertCommandFailure(customiseCommand, model, MESSAGE_INVALID_COMMAND_FORMAT);
    }

    @Test
    public void execute_hideOneAttribute_success() {
        Attribute[] attributes = new Attribute[]{ADDRESS};
        model.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "NONE"));
        CustomiseCommand customiseCommand = new CustomiseCommand(HIDE, attributes);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "ADDRESS"));

        assertCommandSuccess(customiseCommand, model, MESSAGE_HIDE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_hideFourAttributes_success() {
        Attribute[] attributes = new Attribute[]{ADDRESS, TAGS, PHONE, EMAIL};
        model.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "NONE"));
        CustomiseCommand customiseCommand = new CustomiseCommand(HIDE, attributes);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "ADDRESS,EMAIL,PHONE,TAGS"));

        assertCommandSuccess(customiseCommand, model, MESSAGE_HIDE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_hideRepeatAttributes_success() {
        Attribute[] attributes = new Attribute[]{TAGS, PHONE, PHONE};
        model.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "NONE"));
        CustomiseCommand customiseCommand = new CustomiseCommand(HIDE, attributes);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "PHONE,TAGS"));

        assertCommandSuccess(customiseCommand, model, MESSAGE_HIDE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_showOneAttribute_success() {
        Attribute[] attributes = new Attribute[]{EMAIL};
        model.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "ADDRESS,EMAIL,PHONE,TAGS"));
        CustomiseCommand customiseCommand = new CustomiseCommand(SHOW, attributes);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "ADDRESS,PHONE,TAGS"));

        assertCommandSuccess(customiseCommand, model, MESSAGE_SHOW_SUCCESS, expectedModel);
    }

    @Test
    public void execute_showFourAttributes_success() {
        Attribute[] attributes = new Attribute[]{ADDRESS, TAGS, PHONE, EMAIL};
        model.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "ADDRESS,EMAIL,PHONE,TAGS"));
        CustomiseCommand customiseCommand = new CustomiseCommand(SHOW, attributes);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "NONE"));

        assertCommandSuccess(customiseCommand, model, MESSAGE_SHOW_SUCCESS, expectedModel);
    }

    @Test
    public void execute_showRepeatAttributes_success() {
        Attribute[] attributes = new Attribute[]{ADDRESS, ADDRESS, PHONE, PHONE};
        model.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "ADDRESS,EMAIL,PHONE,TAGS"));
        CustomiseCommand customiseCommand = new CustomiseCommand(SHOW, attributes);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setGuiSettings(new GuiSettings(1000, 500, 300, 100,
                "ADDRESS>TAGS>PHONE>EMAIL", "EMAIL,TAGS"));

        assertCommandSuccess(customiseCommand, model, MESSAGE_SHOW_SUCCESS, expectedModel);
    }
}
