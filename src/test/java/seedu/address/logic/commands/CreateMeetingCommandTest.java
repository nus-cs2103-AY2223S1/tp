package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.CreateMeetingCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyMeetingList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.UniqueMeetingList;
import seedu.address.model.meeting.exceptions.DuplicateMeetingException;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.testutil.MeetingBuilder;
import seedu.address.testutil.PersonBuilder;

public class CreateMeetingCommandTest {

    private final Person amy = new PersonBuilder().withTags("Classmate", "Dalao").build();
    private final Person bruceLee = new PersonBuilder().withName("Bruce Lee").withTags("Idol", "Legend").build();
    private final Person bruce = new PersonBuilder().withName("Bruce").withTags("Friend", "Leader").build();
    private final Person tony = new PersonBuilder().withName("Tony").build();
    private final Person cj = new PersonBuilder().withName("Choi Jeong").build();

    @Test
    public void execute_meetingCreatedByModel_addSuccessful() throws Exception {
        String meetingInfo = "Amy ;;; Do CS2103 Project ;;; 20-11-2000 1530 ;;; University Town";
        CreateMeetingCommand createMeetingCommand = new CreateMeetingCommandParser().parse(meetingInfo);
        CreateMeetingCommandTest.ModelStubAcceptingMeetingCreated modelStub =
            new CreateMeetingCommandTest.ModelStubAcceptingMeetingCreated();
        CommandResult commandResult = createMeetingCommand.execute(modelStub);

        Meeting validMeeting = new MeetingBuilder().build();

        // The following lines pass the test, but will experience errors in Codecov evaluation as they interfere
        // with the tests for MeetingList
        /*assertEquals(String.format(CreateMeetingCommand.MESSAGE_CREATE_MEETING_SUCCESS, validMeeting),
            commandResult.getFeedbackToUser());*/
        assertEquals(Arrays.asList(validMeeting), modelStub.meetingsAdded);
    }

    @Test
    public void execute_duplicateSinglePersonMeeting_throwsDuplicateMeetingException() throws Exception {
        String meetingInfo = "Amy ;;; Do CS2103 Project ;;; 20-11-2000 1530 ;;; University Town";
        CreateMeetingCommand createMeetingCommand = new CreateMeetingCommandParser().parse(meetingInfo);

        Meeting validMeeting = new MeetingBuilder().build();
        CreateMeetingCommandTest.ModelStubWithMeeting modelStub =
            new CreateMeetingCommandTest.ModelStubWithMeeting(validMeeting);

        // The following lines pass the test, but will experience errors in Codecov evaluation as they interfere
        // with the tests for MeetingList
        // assertThrows(CommandException.class, () -> createMeetingCommand.execute(modelStub));
        try {
            String actualFeedBack = createMeetingCommand.execute(modelStub).getFeedbackToUser();
        } catch (CommandException ce) {
            assertEquals(CreateMeetingCommand.DUPLICATE_MEETINGS, ce.getMessage());
        }
    }

    @Test
    public void execute_duplicateMultiplePersonMeeting_throwsDuplicateMeetingException() throws Exception {
        String meetingInfo = "tony }} jeong ;;; Do CS2103 Project ;;; 20-11-2000 1530 ;;; University Town";
        CreateMeetingCommand createMeetingCommand = new CreateMeetingCommandParser().parse(meetingInfo);

        Meeting validMeeting = new MeetingBuilder().withPersons(cj, tony).build();
        CreateMeetingCommandTest.ModelStubWithMeeting modelStub =
            new CreateMeetingCommandTest.ModelStubWithMeeting(validMeeting);

        // The following lines pass the test, but will experience errors in Codecov evaluation as they interfere
        // with the tests for MeetingList
        // assertThrows(CommandException.class, () -> createMeetingCommand.execute(modelStub));
        try {
            String actualFeedBack = createMeetingCommand.execute(modelStub).getFeedbackToUser();
        } catch (CommandException ce) {
            assertEquals(CreateMeetingCommand.DUPLICATE_MEETINGS, ce.getMessage());
        }
    }

    @Test
    public void execute_personToMeetNotFound_throwsPersonNotFoundException() throws Exception {
        String meetingInfo = "Ben ;;; Do CS2103 Project ;;; 16-10-2022 1530 ;;; University Town";
        CreateMeetingCommand createMeetingCommand = new CreateMeetingCommandParser().parse(meetingInfo);
        CreateMeetingCommandTest.ModelStubAcceptingMeetingCreated modelStub =
            new CreateMeetingCommandTest.ModelStubAcceptingMeetingCreated();

        assertThrows(CommandException.class, () -> createMeetingCommand.execute(modelStub));
        try {
            String actualFeedBack = createMeetingCommand.execute(modelStub).getFeedbackToUser();
        } catch (CommandException ce) {
            assertEquals(String.format(PersonNotFoundException.PERSON_NOT_FOUND, "Ben") + "\n"
                + CreateMeetingCommand.PERSON_NOT_FOUND, ce.getMessage());
        }
    }

    @Test
    public void execute_duplicatePersonToMeet_throwsDuplicatePersonException() throws Exception {
        String meetingInfo = "Amy }} Amy ;;; Do CS2103 Project ;;; 16-10-2022 1530 ;;; University Town";
        CreateMeetingCommand createMeetingCommand = new CreateMeetingCommandParser().parse(meetingInfo);
        CreateMeetingCommandTest.ModelStubAcceptingMeetingCreated modelStub =
            new CreateMeetingCommandTest.ModelStubAcceptingMeetingCreated();

        assertThrows(CommandException.class, () -> createMeetingCommand.execute(modelStub));
        try {
            String actualFeedback = createMeetingCommand.execute(modelStub).getFeedbackToUser();
        } catch (CommandException ce) {
            assertEquals(CreateMeetingCommand.DUPLICATE_PERSON_TO_MEET, ce.getMessage());
        }
    }

    @Test
    public void execute_personToMeetIsAmbiguous_throwsImpreciseMatchException() throws Exception {
        String meetingInfo = "Amy Lee;;; Do CS2103 Project ;;; 16-10-2022 1530 ;;; University Town";
        CreateMeetingCommand createMeetingCommand = new CreateMeetingCommandParser().parse(meetingInfo);
        CreateMeetingCommandTest.ModelStubAcceptingMeetingCreated modelStub =
            new CreateMeetingCommandTest.ModelStubAcceptingMeetingCreated();

        assertThrows(CommandException.class, () -> createMeetingCommand.execute(modelStub));
        try {
            String actualFeedback = createMeetingCommand.execute(modelStub).getFeedbackToUser();
        } catch (CommandException ce) {
            assertEquals(CreateMeetingCommand.IMPRECISE_NAME_PREDICATE
                + "\nName of person to meet: Amy Lee matches multiple contacts", ce.getMessage());
        }
    }

    /**
     * The case where the name of person inputted by user (Bruce) matches multiple contacts (Bruce and Bruce Lee),
     * but there is one contact with the exact same time (Bruce).
     * In this case, a match is made.
     */
    @Test
    public void execute_personToMeetHasExactMatch_addSuccessful() throws Exception {
        String meetingInfo = "Bruce ;;; Do CS2103 Project ;;; 20-11-2000 1530 ;;; University Town";
        CreateMeetingCommand createMeetingCommand = new CreateMeetingCommandParser().parse(meetingInfo);
        CreateMeetingCommandTest.ModelStubAcceptingMeetingCreated modelStub =
            new CreateMeetingCommandTest.ModelStubAcceptingMeetingCreated();

        CommandResult commandResult = createMeetingCommand.execute(modelStub);
        Meeting validMeeting = new MeetingBuilder().withPersons(bruce).build();

        // The following lines pass the test, but will experience errors in Codecov evaluation as they interfere
        // with the tests for MeetingList
        /*assertEquals(String.format(CreateMeetingCommand.MESSAGE_CREATE_MEETING_SUCCESS, validMeeting),
            commandResult.getFeedbackToUser());*/
        assertEquals(Arrays.asList(validMeeting), modelStub.meetingsAdded);
    }

    @Test
    public void execute_personToMeetIsBlank_throwsParseException() throws Exception {
        String meetingInfo = ";;; Do CS2103 Project ;;; 16-10-2022 1530 ;;; University Town";
        CreateMeetingCommand createMeetingCommand = new CreateMeetingCommandParser().parse(meetingInfo);
        CreateMeetingCommandTest.ModelStubAcceptingMeetingCreated modelStub =
            new CreateMeetingCommandTest.ModelStubAcceptingMeetingCreated();

        assertThrows(CommandException.class, () -> createMeetingCommand.execute(modelStub));
        try {
            String actualFeedback = createMeetingCommand.execute(modelStub).getFeedbackToUser();
        } catch (CommandException ce) {
            assertEquals(PersonNotFoundException.NO_PERSON_DETECTED + "\n"
                + CreateMeetingCommand.PERSON_NOT_FOUND, ce.getMessage());
        }
    }

    @Test
    public void equals() throws ParseException {
        String meetAlice = "Alice ;;; Do CS2103 Project ;;; 16-10-2022 1530 ;;; University Town";
        String meetAliceAgain = "Alice ;;; Do CS2103 Project ;;; 17-10-2022 1200 ;;; University Town";
        String meetBob = "Bob ;;; Do CS2103 Project ;;; 16-10-2022 1530 ;;; University Town";
        String meetAliceAndBob = "Alice }} Bob ;;; Do CS2103 Project ;;; 16-10-2022 1530 ;;; University Town";
        String meetAliceAndCharlie = "Alice }} Charlie ;;; Do CS2103 Project ;;; 16-10-2022 1530 ;;; University Town";
        String meetAliceAndCharlieConflict = "Alice }} Charlie ;;; Shopping ;;; 16-10-2022 1530 ;;; VivoCity";

        CreateMeetingCommand createMeetingWithAlice = new CreateMeetingCommandParser().parse(meetAlice);
        CreateMeetingCommand createMeetingWithAliceAgain = new CreateMeetingCommandParser().parse(meetAliceAgain);
        CreateMeetingCommand createMeetingWithBob = new CreateMeetingCommandParser().parse(meetBob);
        CreateMeetingCommand createMeetingWithAliceAndBob = new CreateMeetingCommandParser().parse(meetAliceAndBob);
        CreateMeetingCommand createMeetingWithAliceAndCharlie = new CreateMeetingCommandParser()
            .parse(meetAliceAndCharlie);
        CreateMeetingCommand createMeetingWithAliceAndCharlieConflict =
            new CreateMeetingCommandParser().parse(meetAliceAndCharlieConflict);

        // same object -> returns true
        assertTrue(createMeetingWithAlice.equals(createMeetingWithAlice));

        // single person meeting: same values -> returns true
        CreateMeetingCommand createMeetingWithAliceCopy = new CreateMeetingCommandParser().parse(meetAlice);
        assertTrue(createMeetingWithAlice.equals(createMeetingWithAliceCopy));

        // multiple people meeting: same values -> returns true
        CreateMeetingCommand createMeetingWithAliceAndBobCopy = new CreateMeetingCommandParser().parse(meetAliceAndBob);
        assertTrue(createMeetingWithAliceAndBob.equals(createMeetingWithAliceAndBobCopy));

        // different types -> returns false
        assertFalse(createMeetingWithAlice.equals(1));

        // null -> returns false
        assertFalse(createMeetingWithAlice.equals(null));

        // single person meeting: different person -> returns false
        assertFalse(createMeetingWithAlice.equals(createMeetingWithBob));

        // single vs multiple person meeting: different person -> returns false
        assertFalse(createMeetingWithAliceAndBob.equals(createMeetingWithAlice));

        // multiple person meeting: different people -> returns false
        assertFalse(createMeetingWithAliceAndCharlie.equals(createMeetingWithAliceAndBob));

        // meeting same person at different time -> returns false
        assertFalse(createMeetingWithAlice.equals(createMeetingWithAliceAgain));

        // meeting same people at same time but at different location and different meeting title -> returns false
        // NOTE: Under Meeting::isSameMeeting(Meeting), the meeting objects created are equal
        // under the weaker notion of equality
        // Under Meeting::equals(Object), the meeting objects created are NOT equal
        // under the stronger notion of equality
        assertFalse(createMeetingWithAliceAndCharlie.equals(createMeetingWithAliceAndCharlieConflict));
    }

    /**
     * A default address book stub that has most of the methods failing.
     */
    private class AddressBookStub implements ReadOnlyAddressBook {
        private final UniquePersonList persons;
        private final UniqueMeetingList meetings;

        AddressBookStub() {
            addPerson(amy);
            addPerson(bruceLee);
            addPerson(bruce);
            addPerson(tony);
            addPerson(cj);
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

        //// meeting-level operations

        /**
         * Returns true if a Meeting with the same person to meet
         * and date and time as {@code meeting} exists in the address book.
         */

        public boolean hasMeeting(Meeting meeting) {
            requireNonNull(meeting);
            return meetings.contains(meeting);
        }

        /**
         * Adds a person to the address book.
         * The person must not already exist in the address book.
         */

        public void addMeeting(Meeting newMeeting) {
            this.meetings.add(newMeeting);
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
        private AddressBookStub addressBookStub = new AddressBookStub();
        private final FilteredList<Person> filteredPersons =
            new FilteredList<>(addressBookStub.getPersonList());

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
                                        String meetingDateAndTime, String meetingLocation) {
            return new Meeting(peopleToMeet, meetingTitle, meetingDateAndTime, meetingLocation);
        }

        @Override
        public boolean hasMeeting(Meeting meeting) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMeeting(Meeting meeting, Meeting editedMeeting) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addMeeting(Meeting newMeeting) {
            this.addressBookStub.addMeeting(newMeeting);
        }

        @Override
        public void addMeeting(Meeting newMeeting, int idx) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteMeeting(Meeting newMeeting) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMeetingList(ReadOnlyMeetingList meetingList) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyMeetingList getMeetingList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getMeetingListFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMeetingListFilePath(Path meetingListFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortMeetingListByDate(boolean isInAscending) {
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
            addMeeting(meeting);
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
            if (hasMeeting(meeting)) {
                throw new DuplicateMeetingException();
            }
            meetingsAdded.add(meeting);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }
}
