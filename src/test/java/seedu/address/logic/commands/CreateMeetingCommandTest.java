package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import org.junit.jupiter.api.Test;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.UniqueMeetingList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.testutil.MeetingBuilder;
import seedu.address.testutil.PersonBuilder;

public class CreateMeetingCommandTest {

    @Test
    public void execute_meetingCreatedByModel_addSuccessful() throws Exception {
        Meeting validMeeting = new MeetingBuilder().build();
        String meetingInfo = "Amy ;;; Do CS2103 Project ;;; 16-10-2022 1530 ;;; University Town";
        CreateMeetingCommand createMeetingCommand = new CreateMeetingCommand(meetingInfo);
        CreateMeetingCommandTest.ModelStubAcceptingMeetingCreated modelStub
            = new CreateMeetingCommandTest.ModelStubAcceptingMeetingCreated();
        CommandResult commandResult = createMeetingCommand.execute(modelStub);
        assertEquals(String.format(CreateMeetingCommand.MESSAGE_CREATE_MEETING_SUCCESS, validMeeting),
            commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validMeeting), modelStub.meetingsAdded);
    }

/*    @Test
    public void execute_duplicateMeeting_throwsDuplicateMeetingException() {
        String meetingInfo = "asdasd as asd ";
        CreateMeetingCommand createMeetingCommand = new CreateMeetingCommand(meetingInfo);
        CreateMeetingCommandTest.ModelStub modelStub = new CreateMeetingCommandTest.ModelStubWithMeeting();

        assertThrows(DuplicateMeetingException.class, aszdszdsadasd AddCommand.MESSAGE_DUPLICATE_PERSON,
            () -> createMeetingCommand.execute(modelStub));
    }

    @Test
    public void execute_personToMeetNotFound_throwsPersonNotFoundException() {
        Person validPerson = new PersonBuilder().build();
        AddCommand addCommand = new AddCommand(validPerson);
        CreateMeetingCommandTest.ModelStub modelStub = new CreateMeetingCommandTest.ModelStubWithPerson(validPerson);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
    }

    @Test
    public void execute_wrongNumberOfArguments_throwsIndexOutOfBoundsException() {
        Person validPerson = new PersonBuilder().build();
        AddCommand addCommand = new AddCommand(validPerson);
        CreateMeetingCommandTest.ModelStub modelStub = new CreateMeetingCommandTest.ModelStubWithPerson(validPerson);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
    }*/

/*    @Test
    public void execute_dateAndTimeInWrongFormat_throwsParseException() {
        Person validPerson = new PersonBuilder().build();
        AddCommand addCommand = new AddCommand(validPerson);
        CreateMeetingCommandTest.ModelStub modelStub = new CreateMeetingCommandTest.ModelStubWithMeeting(validPerson);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
    }*/

    /*@Test
    public void equals() {
        Person alice = new PersonBuilder().withName("Alice").build();
        Person bob = new PersonBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));*//*
    }*/

    /**
     * A default address book stub that has most of the methods failing.
     */
    private class AddressBookStub implements ReadOnlyAddressBook {
        private final UniquePersonList persons;
        private final UniqueMeetingList meetings;

        AddressBookStub() {
            addPerson(new PersonBuilder().withTags("Classmate", "Dalao").build());
        }

        /*
         * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
         * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
         *
         * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
         *   among constructors.
         */
        {
            persons = new UniquePersonList();
            meetings = new UniqueMeetingList();
        }

        //// list overwrite operations

        /**
         * Replaces the contents of the person list with {@code persons}.
         * {@code persons} must not contain duplicate persons.
         */
        public void setPersons(List<Person> persons) {
            this.persons.setPersons(persons);
        }

        //// person-level operations

        /**
         * Adds a person to the address book.
         * The person must not already exist in the address book.
         */
        public void addPerson(Person p) {
            persons.add(p);
        }

        /**
         * Returns true if a person with the same identity as {@code person} exists in the address book.
         */
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return persons.contains(person);
        }

        /**
         * Replaces the given person {@code target} in the list with {@code editedPerson}.
         * {@code target} must exist in the address book.
         * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
         */
        public void setPerson(Person target, Person editedPerson) {
            requireNonNull(editedPerson);

            persons.setPerson(target, editedPerson);
        }

        //// meeting-level operations

        /**
         * Returns true if a Meeting with the same person to meet
         * and date and time as {@code meeting} exists in the address book.
         */
        public boolean hasMeeting(Meeting meeting) {
            requireNonNull(meeting);
            return meetings.contains(meeting);
        }

        //// util methods

        @Override
        public String toString() {
            return persons.asUnmodifiableObservableList().size() + " persons";
            // TODO: refine later
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons.asUnmodifiableObservableList();
        }

        @Override
        public ObservableList<Meeting> getMeetingList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean equals(Object other) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public int hashCode() {
            return persons.hashCode();
        }
    }

    /**
     * A default model stub that has most of the methods failing.
     */
    private class ModelStub implements Model {
        AddressBookStub addressBookStub = new AddressBookStub();
        private final FilteredList<Person> filteredPersons
            =  new FilteredList<>(addressBookStub.getPersonList());

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            return filteredPersons;
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            requireNonNull(predicate);
            filteredPersons.setPredicate(predicate);
        }

        @Override
        public Meeting createNewMeeting(ArrayList<Person> peopleToMeet, String meetingTitle,
                                        String meetingDateAndTime, String meetingLocation) throws ParseException {
            return new Meeting(peopleToMeet, meetingTitle, meetingDateAndTime, meetingLocation);
        }

        @Override
        public boolean hasMeeting(Meeting meeting) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addMeeting(Meeting newMeeting) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteMeeting(Meeting newMeeting) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Updates the filter of the filtered meeting list to filter by implementation.
         *
         * @param predicate
         * @throws NullPointerException if {@param meetingList} is null.
         */
        @Override
        public void updateFilteredMeetingList(Predicate<Meeting> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Returns an unmodifiable view of the filtered meetings list
         */
        @Override
        public ObservableList<Meeting> getFilteredMeetingList() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single Meeting.
     * To test for duplicateMeetingException
     */
    private class ModelStubWithMeeting extends CreateMeetingCommandTest.ModelStub {
        private final Meeting meeting;

        ModelStubWithMeeting(Meeting meeting) {
            requireNonNull(meeting);
            this.meeting = meeting;
        }

        @Override
        public boolean hasMeeting(Meeting meeting) {
            requireNonNull(meeting);
            return this.meeting.isSameMeeting(meeting);
        }
    }

    /**
     * A Model stub that always accepts the person being added.
     */
    private class ModelStubAcceptingMeetingCreated extends CreateMeetingCommandTest.ModelStub {
        final ArrayList<Meeting> meetingsAdded = new ArrayList<>();

        @Override
        public boolean hasMeeting(Meeting meeting) {
            requireNonNull(meeting);
            return meetingsAdded.stream().anyMatch(meeting::isSameMeeting);
        }

        @Override
        public void addMeeting(Meeting meeting) {
            requireNonNull(meeting);
            meetingsAdded.add(meeting);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
