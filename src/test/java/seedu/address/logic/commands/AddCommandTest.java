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
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.tutor.Tutor;
import seedu.address.model.tuitionclass.TuitionClass;
import seedu.address.testutil.StudentBuilder;
import seedu.address.testutil.TuitionClassBuilder;
import seedu.address.testutil.TutorBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullEntity_throwsNullPointerException() {
        Person person = null;

        assertThrows(NullPointerException.class, () -> AddCommand.of(person));

        TuitionClass tuitionClass = null;

        assertThrows(NullPointerException.class, () -> AddCommand.of(tuitionClass));

    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingEntityAdded modelStub = new ModelStubAcceptingEntityAdded();

        Student validStudent = new StudentBuilder().build();

        CommandResult commandResult = AddCommand.of(validStudent).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validStudent), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validStudent), modelStub.studentsAdded);

        Tutor validTutor = new TutorBuilder().build();

        commandResult = AddCommand.of(validTutor).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validTutor), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validTutor), modelStub.tutorsAdded);

    }

    @Test
    public void execute_classAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingEntityAdded modelStub = new ModelStubAcceptingEntityAdded();


        TuitionClass validClass = new TuitionClassBuilder().build();

        CommandResult commandResult = AddCommand.of(validClass).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validClass), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validClass), modelStub.classesAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Student validStudent = new StudentBuilder().build();
        AddCommand addCommandForStudent = AddCommand.of(validStudent);
        ModelStub modelStubForStudent = new ModelStubWithPerson(validStudent);

        assertThrows(CommandException.class,
                AddCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommandForStudent.execute(modelStubForStudent));

        Tutor validTutor = new TutorBuilder().build();
        AddCommand addCommandForTutor = AddCommand.of(validTutor);
        ModelStub modelStubForTutor = new ModelStubWithPerson(validTutor);

        assertThrows(CommandException.class,
                AddCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommandForTutor.execute(modelStubForTutor));
    }


    @Test
    public void execute_duplicateClass_throwsCommandException() {
        TuitionClass validClass = new TuitionClassBuilder().build();
        AddCommand addCommandForClass = AddCommand.of(validClass);
        ModelStub modelStubForClass = new ModelStubWithClass(validClass);

        assertThrows(CommandException.class,
                AddCommand.MESSAGE_DUPLICATE_CLASS, () -> addCommandForClass.execute(modelStubForClass));
    }

    @Test
    public void equals() {

        Student student1 = new StudentBuilder().withName("amy").build();
        Student student2 = new StudentBuilder().withName("bob").build();
        AddCommand addStudent1Command = AddCommand.of(student1);
        AddCommand addStudent2Command = AddCommand.of(student2);

        // same object -> returns true
        assertTrue(addStudent1Command.equals(addStudent1Command));

        // same values -> returns true
        AddCommand addStudent1CommandCopy = AddCommand.of(student1);
        assertTrue(addStudent1Command.equals(addStudent1CommandCopy));

        // different types -> returns false
        assertFalse(addStudent1Command.equals(1));

        // null -> returns false
        assertFalse(addStudent1Command.equals(null));

        // different student -> returns false
        assertFalse(addStudent1Command.equals(addStudent2Command));

        Tutor tutor1 = new TutorBuilder().withName("amy").build();
        Tutor tutor2 = new TutorBuilder().withName("bob").build();
        AddCommand addTutor1Command = AddCommand.of(tutor1);
        AddCommand addTutor2Command = AddCommand.of(tutor2);

        // same object -> returns true
        assertTrue(addTutor1Command.equals(addTutor1Command));

        // same values -> returns true
        AddCommand addTutor1CommandCopy = AddCommand.of(tutor1);
        assertTrue(addTutor1Command.equals(addTutor1CommandCopy));

        // different types -> returns false
        assertFalse(addTutor1Command.equals(1));

        // null -> returns false
        assertFalse(addTutor1Command.equals(null));

        // different tutor -> returns false
        assertFalse(addTutor1Command.equals(addTutor2Command));

        TuitionClass class1 = new TuitionClassBuilder().withName("amy").build();
        TuitionClass class2 = new TuitionClassBuilder().withName("bob").build();
        AddCommand addClass1Command = AddCommand.of(class1);
        AddCommand addClass2Command = AddCommand.of(class2);

        // same object -> returns true
        assertTrue(addClass1Command.equals(addClass1Command));

        // same values -> returns true
        AddCommand addClass1CommandCopy = AddCommand.of(class1);
        assertTrue(addClass1Command.equals(addClass1CommandCopy));

        // different types -> returns false
        assertFalse(addClass1Command.equals(1));

        // null -> returns false
        assertFalse(addClass1Command.equals(null));

        // different class -> returns false
        assertFalse(addClass1Command.equals(addClass2Command));

        //between diff entities with same name
        assertFalse(addStudent1Command.equals(addTutor1Command));
        assertFalse(addStudent1Command.equals(addClass1Command));
        assertFalse(addTutor1Command.equals(addStudent1Command));
        assertFalse(addTutor1Command.equals(addClass1Command));
        assertFalse(addClass1Command.equals(addStudent1Command));
        assertFalse(addClass1Command.equals(addTutor1Command));

    }


    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
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
        public Path getTutorAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTutorAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getStudentAddressBookFilePath() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void setStudentAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getTuitionClassAddressBookFilePath() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void setTuitionClassAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setStudents(List<Student> persons) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTutors(List<Tutor> tutors) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTuitionClasses(List<TuitionClass> tuitionClasses) {
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
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTuitionClass(TuitionClass tuitionClass) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTuitionClass(TuitionClass tuitionClass) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTuitionClass(TuitionClass target, TuitionClass editedTuitionClass) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTuitionClass(TuitionClass target) {
            throw new AssertionError("This method should not be called.");
        }

        public ObservableList<Student> getFilteredStudentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredStudentList(Predicate<Student> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Tutor> getFilteredTutorList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTutorList(Predicate<Tutor> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<TuitionClass> getFilteredTuitionClassList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTuitionClassList(Predicate<TuitionClass> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateCurrentListType(ListType type) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ListType getCurrentListType() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public FilteredList<?> getCurrentList() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Person person;

        private ModelStubWithPerson(Person person) {
            requireNonNull(person);
            this.person = person;
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return this.person.isSamePerson(person);
        }
    }

    /**
     * A Model stub that contains a single class.
     */
    private class ModelStubWithClass extends ModelStub {
        private final TuitionClass tuitionClass;

        private ModelStubWithClass(TuitionClass tuitionClass) {
            requireNonNull(tuitionClass);
            this.tuitionClass = tuitionClass;
        }

        @Override
        public boolean hasTuitionClass(TuitionClass tuitionClass) {
            requireNonNull(tuitionClass);
            return this.tuitionClass.isSameTuitionClass(tuitionClass);
        }
    }

    /**
     * A Model stub that always accept the person or class being added.
     */
    private class ModelStubAcceptingEntityAdded extends ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();
        final ArrayList<Student> studentsAdded = new ArrayList<>();
        final ArrayList<Tutor> tutorsAdded = new ArrayList<>();
        final ArrayList<TuitionClass> classesAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return person instanceof Student
                    ? studentsAdded.stream().anyMatch(((Student) person)::isSamePerson)
                    : person instanceof Tutor
                    ? tutorsAdded.stream().anyMatch(((Tutor) person)::isSamePerson)
                    : personsAdded.stream().anyMatch((person::isSamePerson));
        }

        @Override
        public boolean hasTuitionClass(TuitionClass tuitionClass) {
            requireNonNull(tuitionClass);
            return classesAdded.stream().anyMatch(tuitionClass::isSameTuitionClass);
        }

        @Override
        public void addPerson(Person person) {
            requireNonNull(person);
            if (person instanceof Student) {
                studentsAdded.add((Student) person);
            } else if (person instanceof Tutor) {
                tutorsAdded.add((Tutor) person);
            } else {
                personsAdded.add(person);
            }
        }

        @Override
        public void addTuitionClass(TuitionClass tuitionClass) {
            classesAdded.add(tuitionClass);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
