package seedu.condonery.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.condonery.testutil.TypicalClients.getTypicalClientDirectory;
import static seedu.condonery.testutil.TypicalIndexes.INDEX_FIRST_PROPERTY;
import static seedu.condonery.testutil.TypicalIndexes.INDEX_SECOND_PROPERTY;
import static seedu.condonery.testutil.TypicalProperties.getTypicalPropertyDirectory;

import org.junit.jupiter.api.Test;

import seedu.condonery.commons.core.Messages;
import seedu.condonery.commons.core.index.Index;
import seedu.condonery.logic.commands.EditCommand.EditPropertyDescriptor;
import seedu.condonery.model.ClientDirectory;
import seedu.condonery.model.Model;
import seedu.condonery.model.ModelManager;
import seedu.condonery.model.PropertyDirectory;
import seedu.condonery.model.UserPrefs;
import seedu.condonery.model.property.Property;
import seedu.condonery.testutil.EditPropertyDescriptorBuilder;
import seedu.condonery.testutil.PropertyBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private final Model model = new ModelManager(getTypicalPropertyDirectory(),
            getTypicalClientDirectory(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Property editedProperty = new PropertyBuilder().build();
        EditPropertyDescriptor descriptor = new EditPropertyDescriptorBuilder(editedProperty).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PROPERTY, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PROPERTY_SUCCESS, editedProperty);

        Model expectedModel = new ModelManager(new PropertyDirectory(model.getPropertyDirectory()),
                new ClientDirectory(model.getClientDirectory()), new UserPrefs());
        expectedModel.setProperty(model.getFilteredPropertyList().get(0), editedProperty);

        CommandTestUtil.assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastProperty = Index.fromOneBased(model.getFilteredPropertyList().size());
        Property lastProperty = model.getFilteredPropertyList().get(indexLastProperty.getZeroBased());

        PropertyBuilder personInList = new PropertyBuilder(lastProperty);
        Property editedPerson = personInList.withName(CommandTestUtil.VALID_NAME_BOB)
            .withTags(CommandTestUtil.VALID_TAG_HUSBAND).build();

        EditPropertyDescriptor descriptor = new EditPropertyDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_BOB)
            .withTags(CommandTestUtil.VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(indexLastProperty, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PROPERTY_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new PropertyDirectory(model.getPropertyDirectory()),
                new ClientDirectory(model.getClientDirectory()), new UserPrefs());
        expectedModel.setProperty(lastProperty, editedPerson);

        CommandTestUtil.assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PROPERTY, new EditPropertyDescriptor());
        Property editedPerson = model.getFilteredPropertyList().get(INDEX_FIRST_PROPERTY.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PROPERTY_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new PropertyDirectory(model.getPropertyDirectory()),
                new ClientDirectory(model.getClientDirectory()), new UserPrefs());

        CommandTestUtil.assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        CommandTestUtil.showPropertyAtIndex(model, INDEX_FIRST_PROPERTY);

        Property personInFilteredList = model.getFilteredPropertyList().get(INDEX_FIRST_PROPERTY.getZeroBased());
        Property editedPerson = new PropertyBuilder(personInFilteredList)
                .withName(CommandTestUtil.VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PROPERTY,
            new EditPropertyDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PROPERTY_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new PropertyDirectory(model.getPropertyDirectory()),
                new ClientDirectory(model.getClientDirectory()), new UserPrefs());
        expectedModel.setProperty(model.getFilteredPropertyList().get(0), editedPerson);

        CommandTestUtil.assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Property firstPerson = model.getFilteredPropertyList().get(INDEX_FIRST_PROPERTY.getZeroBased());
        EditPropertyDescriptor descriptor = new EditPropertyDescriptorBuilder(firstPerson).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_PROPERTY, descriptor);

        CommandTestUtil.assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PROPERTY);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        CommandTestUtil.showPropertyAtIndex(model, INDEX_FIRST_PROPERTY);

        // edit person in filtered list into a duplicate in address book
        Property propertyInList = model.getPropertyDirectory().getPropertyList()
                .get(INDEX_SECOND_PROPERTY.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PROPERTY,
            new EditPropertyDescriptorBuilder(propertyInList).build());

        CommandTestUtil.assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PROPERTY);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPropertyList().size() + 1);
        EditPropertyDescriptor descriptor = new EditPropertyDescriptorBuilder()
                .withName(CommandTestUtil.VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        CommandTestUtil.assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PROPERTY_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        CommandTestUtil.showPropertyAtIndex(model, INDEX_FIRST_PROPERTY);
        Index outOfBoundIndex = INDEX_SECOND_PROPERTY;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getPropertyDirectory().getPropertyList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
            new EditPropertyDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_BOB).build());

        CommandTestUtil.assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PROPERTY_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_PROPERTY, CommandTestUtil.DESC_AMY);

        // same values -> returns true
        EditPropertyDescriptor copyDescriptor = new EditPropertyDescriptor(CommandTestUtil.DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_PROPERTY, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_PROPERTY, CommandTestUtil.DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_PROPERTY, CommandTestUtil.DESC_BOB)));
    }

}
