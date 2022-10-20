package seedu.condonery.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.condonery.testutil.TypicalClients.getTypicalClientDirectory;
import static seedu.condonery.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.condonery.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.condonery.testutil.TypicalProperties.getTypicalPropertyDirectory;

import org.junit.jupiter.api.Test;

import seedu.condonery.commons.core.Messages;
import seedu.condonery.commons.core.index.Index;
import seedu.condonery.logic.commands.property.EditPropertyCommand;
import seedu.condonery.logic.commands.property.EditPropertyCommand.EditPropertyDescriptor;
import seedu.condonery.model.ClientDirectory;
import seedu.condonery.model.Model;
import seedu.condonery.model.ModelManager;
import seedu.condonery.model.PropertyDirectory;
import seedu.condonery.model.UserPrefs;
import seedu.condonery.model.property.Property;
import seedu.condonery.testutil.EditPropertyDescriptorBuilder;
import seedu.condonery.testutil.PropertyBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditPropertyCommand.
 */
public class EditPropertyCommandTest {

    private final Model model = new ModelManager(getTypicalPropertyDirectory(),
            getTypicalClientDirectory(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Property editedProperty = new PropertyBuilder().build();
        EditPropertyDescriptor descriptor = new EditPropertyDescriptorBuilder(editedProperty).build();
        EditPropertyCommand editCommand = new EditPropertyCommand(INDEX_FIRST, descriptor);

        String expectedMessage = String.format(EditPropertyCommand.MESSAGE_EDIT_PROPERTY_SUCCESS, editedProperty);

        Model expectedModel = new ModelManager(
            new PropertyDirectory(model.getPropertyDirectory(), model.getUserPrefs().getUserImageDirectoryPath()),
            new ClientDirectory(model.getClientDirectory()), new UserPrefs()
        );
        expectedModel.setProperty(model.getFilteredPropertyList().get(0), editedProperty);

        CommandTestUtil.assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastProperty = Index.fromOneBased(model.getFilteredPropertyList().size());
        Property lastProperty = model.getFilteredPropertyList().get(indexLastProperty.getZeroBased());

        PropertyBuilder personInList = new PropertyBuilder(lastProperty);
        Property editedPerson = personInList.withName(CommandTestUtil.CLIENT_VALID_NAME_BOB)
            .withTags(CommandTestUtil.CLIENT_VALID_TAG_HUSBAND).build();

        EditPropertyDescriptor descriptor =
                new EditPropertyDescriptorBuilder()
                        .withName(CommandTestUtil.CLIENT_VALID_NAME_BOB)
                        .withTags(CommandTestUtil.CLIENT_VALID_TAG_HUSBAND)
                        .build();
        EditPropertyCommand editCommand = new EditPropertyCommand(indexLastProperty, descriptor);

        String expectedMessage = String.format(EditPropertyCommand.MESSAGE_EDIT_PROPERTY_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(
            new PropertyDirectory(model.getPropertyDirectory(), model.getUserPrefs().getUserImageDirectoryPath()),
            new ClientDirectory(model.getClientDirectory()), new UserPrefs()
        );
        expectedModel.setProperty(lastProperty, editedPerson);

        CommandTestUtil.assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditPropertyCommand editCommand = new EditPropertyCommand(INDEX_FIRST, new EditPropertyDescriptor());
        Property editedPerson = model.getFilteredPropertyList().get(INDEX_FIRST.getZeroBased());

        String expectedMessage = String.format(EditPropertyCommand.MESSAGE_EDIT_PROPERTY_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(
            new PropertyDirectory(model.getPropertyDirectory(), model.getUserPrefs().getUserImageDirectoryPath()),
            new ClientDirectory(model.getClientDirectory()), new UserPrefs()
        );

        CommandTestUtil.assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        CommandTestUtil.showPropertyAtIndex(model, INDEX_FIRST);

        Property personInFilteredList = model.getFilteredPropertyList().get(INDEX_FIRST.getZeroBased());
        Property editedPerson = new PropertyBuilder(personInFilteredList)
                .withName(CommandTestUtil.CLIENT_VALID_NAME_BOB).build();
        EditPropertyCommand editCommand = new EditPropertyCommand(INDEX_FIRST,
            new EditPropertyDescriptorBuilder().withName(CommandTestUtil.CLIENT_VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditPropertyCommand.MESSAGE_EDIT_PROPERTY_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(
            new PropertyDirectory(model.getPropertyDirectory(), model.getUserPrefs().getUserImageDirectoryPath()),
            new ClientDirectory(model.getClientDirectory()), new UserPrefs()
        );
        expectedModel.setProperty(model.getFilteredPropertyList().get(0), editedPerson);

        CommandTestUtil.assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Property firstPerson = model.getFilteredPropertyList().get(INDEX_FIRST.getZeroBased());
        EditPropertyDescriptor descriptor = new EditPropertyDescriptorBuilder(firstPerson).build();
        EditPropertyCommand editCommand = new EditPropertyCommand(INDEX_SECOND, descriptor);

        CommandTestUtil.assertCommandFailure(editCommand, model, EditPropertyCommand.MESSAGE_DUPLICATE_PROPERTY);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        CommandTestUtil.showPropertyAtIndex(model, INDEX_FIRST);

        // edit person in filtered list into a duplicate in address book
        Property propertyInList = model.getPropertyDirectory().getPropertyList()
                .get(INDEX_SECOND.getZeroBased());
        EditPropertyCommand editCommand = new EditPropertyCommand(INDEX_FIRST,
            new EditPropertyDescriptorBuilder(propertyInList).build());

        CommandTestUtil.assertCommandFailure(editCommand, model, EditPropertyCommand.MESSAGE_DUPLICATE_PROPERTY);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPropertyList().size() + 1);
        EditPropertyDescriptor descriptor = new EditPropertyDescriptorBuilder()
                .withName(CommandTestUtil.CLIENT_VALID_NAME_BOB).build();
        EditPropertyCommand editCommand = new EditPropertyCommand(outOfBoundIndex, descriptor);

        CommandTestUtil.assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PROPERTY_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        CommandTestUtil.showPropertyAtIndex(model, INDEX_FIRST);
        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getPropertyDirectory().getPropertyList().size());

        EditPropertyCommand editCommand = new EditPropertyCommand(outOfBoundIndex,
            new EditPropertyDescriptorBuilder().withName(CommandTestUtil.CLIENT_VALID_NAME_BOB).build());

        CommandTestUtil.assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PROPERTY_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditPropertyCommand standardCommand =
                new EditPropertyCommand(INDEX_FIRST, CommandTestUtil.PROPERTY_DESC_SCOTTS);

        // same values -> returns true
        EditPropertyDescriptor copyDescriptor = new EditPropertyDescriptor(CommandTestUtil.PROPERTY_DESC_SCOTTS);
        EditPropertyCommand commandWithSameValues =
                new EditPropertyCommand(INDEX_FIRST, copyDescriptor);
        assertTrue(standardCommand.getEditPropertyDescriptor()
                .equals(commandWithSameValues.getEditPropertyDescriptor()));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand
                .equals(new EditPropertyCommand(INDEX_SECOND, CommandTestUtil.PROPERTY_DESC_SCOTTS)));

        // different descriptor -> returns false
        assertFalse(standardCommand
                .equals(new EditPropertyCommand(INDEX_FIRST, CommandTestUtil.PROPERTY_DESC_WHISTLER)));
    }

}
