package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.MEETING_1;
import static seedu.address.logic.commands.CommandTestUtil.MEETING_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_MEETING_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_MEETING_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_MEETING_1;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showMeetingAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalMeetings.getTypicalMeetingList;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditMeetingCommand.EditMeetingDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.AddressBook;
import seedu.address.model.MeetingList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.Meeting;
import seedu.address.testutil.EditMeetingDescriptorBuilder;
import seedu.address.testutil.MeetingBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditMeetingCommand.
 */
public class EditMeetingCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalMeetingList(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Meeting editedMeeting = new MeetingBuilder().build();
        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder(editedMeeting).build();
        EditMeetingCommand editMeetingCommand = new EditMeetingCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditMeetingCommand.MESSAGE_EDIT_MEETING_SUCCESS, editedMeeting);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new MeetingList(model.getMeetingList()), new UserPrefs());
        expectedModel.setMeeting(model.getFilteredMeetingList().get(0), editedMeeting);

        assertCommandSuccess(editMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastMeeting = Index.fromOneBased(model.getFilteredMeetingList().size());
        Meeting lastMeeting = model.getFilteredMeetingList().get(indexLastMeeting.getZeroBased());

        MeetingBuilder meetingInList = new MeetingBuilder(lastMeeting);
        Meeting editedMeeting = meetingInList.withDescription(VALID_NAME_MEETING_1)
                .withLocation(VALID_LOCATION_MEETING_1).withDateAndTime(VALID_DATE_MEETING_1).build();

        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder().withDescription(VALID_NAME_MEETING_1)
                .withLocation(VALID_LOCATION_MEETING_1).withDate(VALID_DATE_MEETING_1).build();
        EditMeetingCommand editMeetingCommand = new EditMeetingCommand(indexLastMeeting, descriptor);

        String expectedMessage = String.format(EditMeetingCommand.MESSAGE_EDIT_MEETING_SUCCESS, editedMeeting);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new MeetingList(model.getMeetingList()), new UserPrefs());
        expectedModel.setMeeting(lastMeeting, editedMeeting);

        assertCommandSuccess(editMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditMeetingCommand editMeetingCommand = new EditMeetingCommand(INDEX_FIRST_PERSON, new EditMeetingDescriptor());
        Meeting editedMeeting = model.getFilteredMeetingList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditMeetingCommand.MESSAGE_EDIT_MEETING_SUCCESS, editedMeeting);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new MeetingList(model.getMeetingList()), new UserPrefs());

        assertCommandSuccess(editMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() throws CommandException, ParseException {
        showMeetingAtIndex(model, INDEX_FIRST_PERSON);

        Meeting meetingInFilteredList = model.getFilteredMeetingList().get(INDEX_FIRST_PERSON.getZeroBased());
        Meeting editedMeeting = new MeetingBuilder(meetingInFilteredList).withDescription(VALID_NAME_MEETING_1).build();
        EditMeetingCommand editMeetingCommand = new EditMeetingCommand(INDEX_FIRST_PERSON,
                new EditMeetingDescriptorBuilder().withDescription(VALID_NAME_MEETING_1).build());

        String expectedMessage = String.format(EditMeetingCommand.MESSAGE_EDIT_MEETING_SUCCESS, editedMeeting);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new MeetingList(model.getMeetingList()), new UserPrefs());
        expectedModel.setMeeting(model.getFilteredMeetingList().get(0), editedMeeting);

        System.out.println(editMeetingCommand.execute(model).getFeedbackToUser());
        System.out.println(expectedMessage);

        assertCommandSuccess(editMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateMeetingUnfilteredList_failure() {
        Meeting firstMeeting = model.getFilteredMeetingList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder(firstMeeting).build();
        EditMeetingCommand editMeetingCommand = new EditMeetingCommand(INDEX_SECOND_PERSON, descriptor);

        assertCommandFailure(editMeetingCommand, model, EditMeetingCommand.MESSAGE_DUPLICATE_MEETING);
    }

    @Test
    public void execute_duplicateMeetingFilteredList_failure() {
        showMeetingAtIndex(model, INDEX_FIRST_PERSON);

        // edit person in filtered list into a duplicate in address book
        Meeting meetingInList = model.getMeetingList().getMeetingList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditMeetingCommand editMeetingCommand = new EditMeetingCommand(INDEX_FIRST_PERSON,
                new EditMeetingDescriptorBuilder(meetingInList).build());

        assertCommandFailure(editMeetingCommand, model, EditMeetingCommand.MESSAGE_DUPLICATE_MEETING);
    }

    @Test
    public void execute_invalidMeetingIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredMeetingList().size() + 1);
        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder()
                .withDescription(VALID_NAME_MEETING_1).build();
        EditMeetingCommand editMeetingCommand = new EditMeetingCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editMeetingCommand, model, Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showMeetingAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getMeetingList().getMeetingList().size());

        EditMeetingCommand editMeetingCommand = new EditMeetingCommand(outOfBoundIndex,
                new EditMeetingDescriptorBuilder().withDescription(VALID_NAME_MEETING_1).build());

        assertCommandFailure(editMeetingCommand, model, Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditMeetingCommand standardCommand = new EditMeetingCommand(INDEX_FIRST_PERSON, MEETING_1);

        // same values -> returns true
        EditMeetingDescriptor copyDescriptor = new EditMeetingDescriptor(MEETING_1);
        EditMeetingCommand commandWithSameValues = new EditMeetingCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditMeetingCommand(INDEX_SECOND_PERSON, MEETING_1)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditMeetingCommand(INDEX_FIRST_PERSON, MEETING_2)));
    }

}
