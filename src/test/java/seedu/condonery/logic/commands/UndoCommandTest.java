package seedu.condonery.logic.commands;

import static seedu.condonery.testutil.TypicalClients.getTypicalClientDirectory;
import static seedu.condonery.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.condonery.testutil.TypicalProperties.getTypicalPropertyDirectory;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import seedu.condonery.logic.commands.property.EditPropertyCommand;
import seedu.condonery.logic.commands.property.EditPropertyCommand.EditPropertyDescriptor;
import seedu.condonery.model.Model;
import seedu.condonery.model.ModelManager;
import seedu.condonery.model.UserPrefs;
import seedu.condonery.model.client.ClientDirectory;
import seedu.condonery.model.property.Property;
import seedu.condonery.model.property.PropertyDirectory;
import seedu.condonery.testutil.EditPropertyDescriptorBuilder;
import seedu.condonery.testutil.PropertyBuilder;
import seedu.condonery.testutil.TypicalProperties;

/**
 * Contains integration tests (interaction with the Model) and unit tests for UndoCommandTest.
 */
public class UndoCommandTest {

    private final Model model = new ModelManager(getTypicalPropertyDirectory(),
            getTypicalClientDirectory(), new UserPrefs());

    @Test
    public void execute_undoEditedProperty_success() {
        Property originalProperty = TypicalProperties.PINNACLE;
        Property editedProperty = new PropertyBuilder(originalProperty).withName("Bob").build();
        EditPropertyDescriptor descriptor = new EditPropertyDescriptorBuilder(editedProperty).build();

        EditPropertyCommand editCommand = new EditPropertyCommand(INDEX_FIRST, descriptor);
        UndoCommand undoCommand = new UndoCommand();

        Path imageDirectoryPath = model.getUserPrefs().getUserImageDirectoryPath();

        Model expectedEditModel = new ModelManager(
            new PropertyDirectory(model.getPropertyDirectory(), imageDirectoryPath),
            new ClientDirectory(model.getClientDirectory(), imageDirectoryPath),
            new UserPrefs()
        );
        Model expectedUndoModel = new ModelManager(
            new PropertyDirectory(model.getPropertyDirectory(), imageDirectoryPath),
            new ClientDirectory(model.getClientDirectory(), imageDirectoryPath),
            new UserPrefs()
        );

        String expectedEditMessage = String.format(EditPropertyCommand.MESSAGE_EDIT_PROPERTY_SUCCESS, editedProperty);
        String expectedUndoMessage = UndoCommand.MESSAGE_SUCCESS;

        expectedEditModel.setProperty(model.getFilteredPropertyList().get(0), editedProperty);
        CommandTestUtil.assertCommandSuccess(editCommand, model, expectedEditMessage, expectedEditModel);
        model.addCommand(editCommand);
        CommandTestUtil.assertCommandSuccess(undoCommand, model, expectedUndoMessage, expectedUndoModel);
    }

}
