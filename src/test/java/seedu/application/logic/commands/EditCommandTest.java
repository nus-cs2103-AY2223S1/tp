package seedu.application.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.application.logic.commands.CommandTestUtil.DESC_FACEBOOK;
import static seedu.application.logic.commands.CommandTestUtil.DESC_GOOGLE;
import static seedu.application.logic.commands.CommandTestUtil.INVALID_FUTURE_DATE;
import static seedu.application.logic.commands.CommandTestUtil.VALID_COMPANY_FACEBOOK;
import static seedu.application.logic.commands.CommandTestUtil.VALID_CONTACT_FACEBOOK;
import static seedu.application.logic.commands.CommandTestUtil.VALID_TAG_PREFERRED;
import static seedu.application.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.application.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.application.logic.commands.CommandTestUtil.showApplicationAtIndex;
import static seedu.application.testutil.TypicalApplications.getTypicalApplicationBook;
import static seedu.application.testutil.TypicalApplications.getTypicalApplicationBookWithArchive;
import static seedu.application.testutil.TypicalApplications.getTypicalApplicationBookWithUpcomingInterview;
import static seedu.application.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;
import static seedu.application.testutil.TypicalIndexes.INDEX_SECOND_APPLICATION;
import static seedu.application.testutil.TypicalIndexes.INDEX_THIRD_APPLICATION;

import org.junit.jupiter.api.Test;

import seedu.application.commons.core.Messages;
import seedu.application.commons.core.index.Index;
import seedu.application.logic.commands.EditCommand.EditApplicationDescriptor;
import seedu.application.model.ApplicationBook;
import seedu.application.model.Model;
import seedu.application.model.ModelManager;
import seedu.application.model.UserPrefs;
import seedu.application.model.application.Application;
import seedu.application.model.application.exceptions.InvalidFutureApplicationException;
import seedu.application.testutil.ApplicationBuilder;
import seedu.application.testutil.EditApplicationDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalApplicationBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Application editedApplication = new ApplicationBuilder().build();
        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder(editedApplication).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_APPLICATION, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_APPLICATION_SUCCESS, editedApplication);

        Model expectedModel = new ModelManager(new ApplicationBook(model.getApplicationBook()), new UserPrefs());
        expectedModel.setApplication(model.getFilteredApplicationList().get(0), editedApplication);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastApplication = Index.fromOneBased(model.getFilteredApplicationList().size());
        Application lastApplication = model.getFilteredApplicationList().get(indexLastApplication.getZeroBased());

        ApplicationBuilder applicationInList = new ApplicationBuilder(lastApplication);
        Application editedApplication = applicationInList.withCompany(VALID_COMPANY_FACEBOOK)
                .withContact(VALID_CONTACT_FACEBOOK).withTags(VALID_TAG_PREFERRED).build();

        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder()
                .withCompany(VALID_COMPANY_FACEBOOK).withContact(VALID_CONTACT_FACEBOOK).withTags(VALID_TAG_PREFERRED)
                .build();
        EditCommand editCommand = new EditCommand(indexLastApplication, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_APPLICATION_SUCCESS, editedApplication);

        Model expectedModel = new ModelManager(new ApplicationBook(model.getApplicationBook()), new UserPrefs());
        expectedModel.setApplication(lastApplication, editedApplication);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_APPLICATION, new EditApplicationDescriptor());
        Application editedApplication = model.getFilteredApplicationList().get(INDEX_FIRST_APPLICATION.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_APPLICATION_SUCCESS, editedApplication);

        Model expectedModel = new ModelManager(new ApplicationBook(model.getApplicationBook()), new UserPrefs());
        expectedModel.commitApplicationBook();

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_applicationWithInterview_success() {
        Model modelWithInterview = new ModelManager(getTypicalApplicationBookWithUpcomingInterview(), new UserPrefs());
        Application application = modelWithInterview.getFilteredApplicationList()
                .get(INDEX_THIRD_APPLICATION.getZeroBased());
        assert application.hasInterview();

        Application editedApplication = new ApplicationBuilder()
                .withInterview(application.getInterview().get()).build();
        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder(editedApplication).build();
        EditCommand editCommand = new EditCommand(INDEX_THIRD_APPLICATION, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_APPLICATION_SUCCESS, editedApplication);

        Model expectedModel = new ModelManager(
                new ApplicationBook(modelWithInterview.getApplicationBook()), new UserPrefs());
        expectedModel.setApplication(application, editedApplication);

        assertCommandSuccess(editCommand, modelWithInterview, expectedMessage, expectedModel);
    }

    @Test
    public void execute_archivedApplication_success() {
        Model modelWithArchive = new ModelManager(getTypicalApplicationBookWithArchive(), new UserPrefs());
        new ListArchiveCommand().execute(modelWithArchive);
        Application application = modelWithArchive
                .getFilteredApplicationList().get(INDEX_FIRST_APPLICATION.getZeroBased());
        assert application.isArchived();

        Application editedApplication = new ApplicationBuilder().withArchiveStatus(true).build();
        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder(editedApplication).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_APPLICATION, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_APPLICATION_SUCCESS, editedApplication);

        Model expectedModel = new ModelManager(
                new ApplicationBook(modelWithArchive.getApplicationBook()), new UserPrefs());
        new ListArchiveCommand().execute(expectedModel);
        expectedModel.setApplication(application, editedApplication);

        assertCommandSuccess(editCommand, modelWithArchive, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateApplicationUnfilteredList_failure() {
        Application firstApplication = model.getFilteredApplicationList().get(INDEX_FIRST_APPLICATION.getZeroBased());
        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder(firstApplication).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_APPLICATION, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_APPLICATION);
    }

    @Test
    public void execute_duplicateApplicationFilteredList_failure() {
        showApplicationAtIndex(model, INDEX_FIRST_APPLICATION);

        // edit application in filtered list into a duplicate in application book
        Application applicationInList = model.getApplicationBook().getApplicationList()
                .get(INDEX_SECOND_APPLICATION.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_APPLICATION,
                new EditApplicationDescriptorBuilder(applicationInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_APPLICATION);
    }

    @Test
    public void execute_futureDateEdited_failure() {
        showApplicationAtIndex(model, INDEX_FIRST_APPLICATION);
        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder()
                .withDate(INVALID_FUTURE_DATE).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_APPLICATION, descriptor);

        assertCommandFailure(editCommand, model, new InvalidFutureApplicationException().getMessage());
    }

    @Test
    public void execute_invalidApplicationIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredApplicationList().size() + 1);
        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder()
                .withCompany(VALID_COMPANY_FACEBOOK).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of application book
     */
    @Test
    public void execute_invalidApplicationIndexFilteredList_failure() {
        showApplicationAtIndex(model, INDEX_FIRST_APPLICATION);
        Index outOfBoundIndex = INDEX_SECOND_APPLICATION;
        // ensures that outOfBoundIndex is still in bounds of application book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getApplicationBook().getApplicationList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditApplicationDescriptorBuilder().withCompany(VALID_COMPANY_FACEBOOK).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_APPLICATION, DESC_GOOGLE);

        // same values -> returns true
        EditApplicationDescriptor copyDescriptor = new EditApplicationDescriptor(DESC_GOOGLE);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_APPLICATION, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_APPLICATION, DESC_GOOGLE)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_APPLICATION, DESC_FACEBOOK)));
    }

}
