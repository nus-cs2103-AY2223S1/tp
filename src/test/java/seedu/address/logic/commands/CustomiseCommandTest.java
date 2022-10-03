package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CustomiseCommand.Attribute.ADDRESS;
import static seedu.address.logic.commands.CustomiseCommand.Attribute.EMAIL;
import static seedu.address.logic.commands.CustomiseCommand.Attribute.PHONE;
import static seedu.address.logic.commands.CustomiseCommand.Attribute.TAGS;
import static seedu.address.logic.commands.CustomiseCommand.MESSAGE_ORDER_SUCCESS;
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
        model.setGuiSettings(new GuiSettings(1000, 500, 300, 100, "ADDRESS>TAGS>PHONE>EMAIL"));
        CustomiseCommand customiseCommand = new CustomiseCommand("order", attributes);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setGuiSettings(new GuiSettings(1000, 500, 300, 100, "ADDRESS>TAGS>PHONE>EMAIL"));

        assertCommandSuccess(customiseCommand, model, MESSAGE_ORDER_SUCCESS, expectedModel);
    }
}
