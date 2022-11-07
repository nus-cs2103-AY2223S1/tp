package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_TIME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_TIME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.MeetingTime;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

class AddMeetingCommandTest {

    private static final String MEETINGTIME_STUB_1 = "10-10-2022-22:30";
    private static final String MEETINGTIME_STUB_2 = "01-09-2023-03:04";
    private static final String SECOND_PERSON_MEETINGTIME = "11-04-2022-11:00";
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());


    @Test
    public void execute_addSingleMeetingUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson)
                .withMeetingTimes(MEETINGTIME_STUB_1).build();

        Set<MeetingTime> editedTimes = new HashSet<>();
        editedTimes.addAll(editedPerson.getMeetingTimes());

        AddMeetingCommand addMeetingCommand = new AddMeetingCommand(INDEX_FIRST_PERSON, editedTimes);

        String expectedMessage = String.format(AddMeetingCommand.MESSAGE_ADD_MEETING_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(addMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addMultipleMeetingUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson)
                .withMeetingTimes(MEETINGTIME_STUB_1, MEETINGTIME_STUB_2).build();

        Set<MeetingTime> editedTimes = new HashSet<>();
        editedTimes.addAll(editedPerson.getMeetingTimes());

        AddMeetingCommand addMeetingCommand = new AddMeetingCommand(INDEX_FIRST_PERSON, editedTimes);

        String expectedMessage = String.format(AddMeetingCommand.MESSAGE_ADD_MEETING_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(addMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addSingleMeetingFilteredList_success() {
        showPersonAtIndex(model, INDEX_SECOND_PERSON);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson)
                .withMeetingTimes(SECOND_PERSON_MEETINGTIME, MEETINGTIME_STUB_1).build();

        Set<MeetingTime> editedTimes = new HashSet<>();
        editedTimes.addAll(editedPerson.getMeetingTimes());

        AddMeetingCommand addMeetingCommand = new AddMeetingCommand(INDEX_FIRST_PERSON, editedTimes);

        String expectedMessage = String.format(AddMeetingCommand.MESSAGE_ADD_MEETING_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(addMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addMultipleMeetingFilteredList_success() {
        showPersonAtIndex(model, INDEX_SECOND_PERSON);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson)
                .withMeetingTimes(SECOND_PERSON_MEETINGTIME, MEETINGTIME_STUB_1, MEETINGTIME_STUB_2).build();

        Set<MeetingTime> editedTimes = new HashSet<>();
        editedTimes.addAll(editedPerson.getMeetingTimes());

        AddMeetingCommand addMeetingCommand = new AddMeetingCommand(INDEX_FIRST_PERSON, editedTimes);

        String expectedMessage = String.format(AddMeetingCommand.MESSAGE_ADD_MEETING_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(addMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        AddMeetingCommand addMeetingCommand = new AddMeetingCommand(outOfBoundIndex, new HashSet<MeetingTime>());

        assertCommandFailure(addMeetingCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void undo_commandExecuted_undoSuccessful() throws Exception {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson)
                .withMeetingTimes(MEETINGTIME_STUB_1, MEETINGTIME_STUB_2).build();

        Set<MeetingTime> editedTimes = new HashSet<>();
        editedTimes.addAll(editedPerson.getMeetingTimes());

        AddMeetingCommand addMeetingCommand = new AddMeetingCommand(INDEX_FIRST_PERSON, editedTimes);

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        addMeetingCommand.execute(expectedModel);
        CommandResult result = addMeetingCommand.undo(expectedModel);

        assertEquals(String.format(addMeetingCommand.MESSAGE_UNDO, firstPerson), result.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }

    @Test
    void testEquals() {
        Set<MeetingTime> meetingTimesAmy = new HashSet<>();
        meetingTimesAmy.add(new MeetingTime(VALID_MEETING_TIME_AMY));

        Set<MeetingTime> meetingTimesBob = new HashSet<>();
        meetingTimesBob.add(new MeetingTime(VALID_MEETING_TIME_BOB));

        final AddMeetingCommand standardCommand = new AddMeetingCommand(INDEX_FIRST_PERSON, meetingTimesAmy);

        // same values -> returns true
        AddMeetingCommand commandWithSameValues = new AddMeetingCommand(INDEX_FIRST_PERSON, meetingTimesAmy);
        assertTrue(standardCommand.equals(commandWithSameValues));
        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));
        // null -> returns false
        assertFalse(standardCommand.equals(null));
        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));
        // different index -> returns false
        assertFalse(standardCommand.equals(new AddMeetingCommand(INDEX_SECOND_PERSON, meetingTimesAmy)));
        // different meetingTimes -> returns false
        assertFalse(standardCommand.equals(new AddMeetingCommand(INDEX_FIRST_PERSON, meetingTimesBob)));
    }
}
