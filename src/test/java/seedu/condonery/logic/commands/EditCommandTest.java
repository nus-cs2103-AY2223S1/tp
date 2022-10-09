package seedu.condonery.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.condonery.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.condonery.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.condonery.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.condonery.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.condonery.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.condonery.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.condonery.logic.commands.CommandTestUtil.showPropertyAtIndex;
import static seedu.condonery.testutil.TypicalIndexes.INDEX_FIRST_PROPERTY;
import static seedu.condonery.testutil.TypicalIndexes.INDEX_SECOND_PROPERTY;
import static seedu.condonery.testutil.TypicalPersons.getTypicalPropertyDirectory;

import org.junit.jupiter.api.Test;

import seedu.condonery.commons.core.Messages;
import seedu.condonery.commons.core.index.Index;
import seedu.condonery.logic.commands.EditCommand.EditPropertyDescriptor;
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

    private final Model model = new ModelManager(getTypicalPropertyDirectory(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Property editedProperty = new PropertyBuilder().build();
        EditPropertyDescriptor descriptor = new EditPropertyDescriptorBuilder(editedProperty).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PROPERTY, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PROPERTY_SUCCESS, editedProperty);

        Model expectedModel = new ModelManager(new PropertyDirectory(model.getPropertyDirectory()), new UserPrefs());
        expectedModel.setProperty(model.getFilteredPropertyList().get(0), editedProperty);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastProperty = Index.fromOneBased(model.getFilteredPropertyList().size());
        Property lastProperty = model.getFilteredPropertyList().get(indexLastProperty.getZeroBased());

        PropertyBuilder personInList = new PropertyBuilder(lastProperty);
        Property editedPerson = personInList.withName(VALID_NAME_BOB)
            .withTags(VALID_TAG_HUSBAND).build();

        EditPropertyDescriptor descriptor = new EditPropertyDescriptorBuilder().withName(VALID_NAME_BOB)
            .withTags(VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(indexLastProperty, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PROPERTY_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new PropertyDirectory(model.getPropertyDirectory()), new UserPrefs());
        expectedModel.setProperty(lastProperty, editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PROPERTY, new EditPropertyDescriptor());
        Property editedPerson = model.getFilteredPropertyList().get(INDEX_FIRST_PROPERTY.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PROPERTY_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new PropertyDirectory(model.getPropertyDirectory()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPropertyAtIndex(model, INDEX_FIRST_PROPERTY);

        Property personInFilteredList = model.getFilteredPropertyList().get(INDEX_FIRST_PROPERTY.getZeroBased());
        Property editedPerson = new PropertyBuilder(personInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PROPERTY,
            new EditPropertyDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PROPERTY_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new PropertyDirectory(model.getPropertyDirectory()), new UserPrefs());
        expectedModel.setProperty(model.getFilteredPropertyList().get(0), editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Property firstPerson = model.getFilteredPropertyList().get(INDEX_FIRST_PROPERTY.getZeroBased());
        EditPropertyDescriptor descriptor = new EditPropertyDescriptorBuilder(firstPerson).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_PROPERTY, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PROPERTY);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showPropertyAtIndex(model, INDEX_FIRST_PROPERTY);

        // edit person in filtered list into a duplicate in address book
        Property propertyInList = model.getPropertyDirectory().getPropertyList()
                .get(INDEX_SECOND_PROPERTY.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PROPERTY,
            new EditPropertyDescriptorBuilder(propertyInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PROPERTY);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPropertyList().size() + 1);
        EditPropertyDescriptor descriptor = new EditPropertyDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PROPERTY_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPropertyAtIndex(model, INDEX_FIRST_PROPERTY);
        Index outOfBoundIndex = INDEX_SECOND_PROPERTY;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getPropertyDirectory().getPropertyList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
            new EditPropertyDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PROPERTY_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_PROPERTY, DESC_AMY);

        // same values -> returns true
        EditPropertyDescriptor copyDescriptor = new EditPropertyDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_PROPERTY, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_PROPERTY, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_PROPERTY, DESC_BOB)));
    }

}
