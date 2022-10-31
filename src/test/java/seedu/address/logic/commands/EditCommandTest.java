package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.STUDENT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PROFESSOR_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CABE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.model.person.Specialisation.EMPTY_SPECIALISATION;
import static seedu.address.testutil.ProfessorBuilder.DEFAULT_OFFICE_HOUR;
import static seedu.address.testutil.ProfessorBuilder.DEFAULT_RATING;
import static seedu.address.testutil.ProfessorBuilder.DEFAULT_SPECIALISATION;
import static seedu.address.testutil.StudentBuilder.DEFAULT_YEAR;
import static seedu.address.testutil.TypicalIndexes.INDEX_ELEVENTH_PERSON_TA;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_TENTH_PERSON_PROFESSOR;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.Professor;
import seedu.address.model.person.Student;
import seedu.address.model.person.TeachingAssistant;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.ProfessorBuilder;
import seedu.address.testutil.StudentBuilder;
import seedu.address.testutil.TeachingAssistantBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    private static final String TEST_USERNAME = "test";
    private static final String TEST_TAG = "friends";

    @Test
    public void executeEditStudent_allFieldsSpecifiedUnfilteredList_success() {
        Student editedStudent = new StudentBuilder().withYear(DEFAULT_YEAR).withGithubUsername(TEST_USERNAME)
                .withTags(TEST_TAG).build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedStudent).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedStudent);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void executeEditProfessor_allFieldsSpecifiedUnfilteredList_success() {
        Professor editedProfessor = new ProfessorBuilder().withSpecialisation(DEFAULT_SPECIALISATION).withRating(DEFAULT_RATING)
                .withTags(TEST_TAG).withOfficeHour(DEFAULT_OFFICE_HOUR).withGithubUsername(TEST_USERNAME).build();

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedProfessor).build();
        EditCommand editCommand = new EditCommand(INDEX_TENTH_PERSON_PROFESSOR, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedProfessor);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(9), editedProfessor);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void executeEditTeachingAssistant_allFieldsSpecifiedUnfilteredList_success() {
        TeachingAssistant editedTeachingAssistant = new TeachingAssistantBuilder().withRating(DEFAULT_RATING)
                .withTags(TEST_TAG).withGithubUsername(TEST_USERNAME).build();

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedTeachingAssistant).build();
        EditCommand editCommand = new EditCommand(INDEX_ELEVENTH_PERSON_TA, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedTeachingAssistant);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(10), editedTeachingAssistant);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedForStudentUnfilteredList_success() {
        Student student = (Student) model.getFilteredPersonList().get(INDEX_SECOND_PERSON_STUDENT.getZeroBased());

        StudentBuilder personInList = new StudentBuilder(student);
        Student editedPerson = personInList.withYear(DEFAULT_YEAR).withModuleCodes("CS1234", "CS9876").build();

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withYear(DEFAULT_YEAR).withModuleCodeSet("CS1234", "CS9876").build();

        EditCommand editCommand = new EditCommand(INDEX_SECOND_PERSON_STUDENT, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(student, editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedForProfessorUnfilteredList_success() {
        Professor professor = (Professor) model.getFilteredPersonList().get(INDEX_TENTH_PERSON_PROFESSOR.getZeroBased());

        ProfessorBuilder personInList = new ProfessorBuilder(professor);
        Professor editedPerson = personInList.withSpecialisation(EMPTY_SPECIALISATION).withRating(DEFAULT_RATING).withOfficeHour(DEFAULT_OFFICE_HOUR).build();

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withSpecialisation(EMPTY_SPECIALISATION)
                .withRating(DEFAULT_RATING).withOfficeHour(DEFAULT_OFFICE_HOUR).build();

        EditCommand editCommand = new EditCommand(INDEX_TENTH_PERSON_PROFESSOR, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(professor, editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedForTeachingAssistantUnfilteredList_success() {
        TeachingAssistant ta = (TeachingAssistant) model.getFilteredPersonList().get(INDEX_ELEVENTH_PERSON_TA.getZeroBased());

        TeachingAssistantBuilder personInList = new TeachingAssistantBuilder(ta);
        TeachingAssistant editedPerson = personInList.withRating(DEFAULT_RATING).withModuleCode(VALID_MODULE_CODE_CABE).build();

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withRating(DEFAULT_RATING).withModuleCode(VALID_MODULE_CODE_CABE).build();

        EditCommand editCommand = new EditCommand(INDEX_ELEVENTH_PERSON_TA, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(ta, editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedStudentUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, new EditPersonDescriptor());
        Student editedStudent = (Student) model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedProfessorUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_TENTH_PERSON_PROFESSOR, new EditPersonDescriptor());
        Professor editedProfessor = (Professor) model.getFilteredPersonList().get(INDEX_TENTH_PERSON_PROFESSOR.getZeroBased());
        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedProfessor);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedTeachingAssistantUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_ELEVENTH_PERSON_TA, new EditPersonDescriptor());
        TeachingAssistant editedTeachingAssistant = (TeachingAssistant) model.getFilteredPersonList().get(INDEX_ELEVENTH_PERSON_TA.getZeroBased());
        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedTeachingAssistant);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }
    @Test
    public void execute_studentFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Student personInFilteredList = (Student) model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Student editedPerson = new StudentBuilder(personInFilteredList).withName(VALID_NAME_BOB).build();

        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_professorFilteredList_success() {
        showPersonAtIndex(model, INDEX_TENTH_PERSON_PROFESSOR);

        Professor personInFilteredList = (Professor) model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Professor editedPerson = new ProfessorBuilder(personInFilteredList).withName(VALID_NAME_BOB)
                .withSpecialisation(EMPTY_SPECIALISATION).build();

        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).withSpecialisation(EMPTY_SPECIALISATION).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()), editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_teachingAssistantFilteredList_success() {
        showPersonAtIndex(model, INDEX_ELEVENTH_PERSON_TA);

        TeachingAssistant personInFilteredList = (TeachingAssistant) model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        TeachingAssistant editedPerson = new TeachingAssistantBuilder(personInFilteredList).withName(VALID_NAME_BOB).build();

        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()), editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    
    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditPersonDescriptor descriptor;
        if (firstPerson instanceof Student) {
            descriptor = new EditPersonDescriptorBuilder((Student) firstPerson).build();
        } else if (firstPerson instanceof Professor) {
            descriptor = new EditPersonDescriptorBuilder((Professor) firstPerson).build();
        } else {
            descriptor = new EditPersonDescriptorBuilder((TeachingAssistant) firstPerson).build();
        }

        EditCommand editCommand = new EditCommand(INDEX_SECOND_PERSON, descriptor);
        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        // edit person in filtered list into a duplicate in address book
        Person personInList = model.getAddressBook().getPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditCommand editCommand;
        if (personInList instanceof Student) {
            editCommand = new EditCommand(INDEX_FIRST_PERSON,
                    new EditPersonDescriptorBuilder((Student) personInList).build());
        } else if (personInList instanceof Professor) {
            editCommand = new EditCommand(INDEX_FIRST_PERSON,
                    new EditPersonDescriptorBuilder((Professor) personInList).build());
        } else {
            editCommand = new EditCommand(INDEX_FIRST_PERSON,
                    new EditPersonDescriptorBuilder((TeachingAssistant) personInList).build());
        }

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }


    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }


    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_PERSON, STUDENT_AMY);

        // same values -> returns true
        EditPersonDescriptor copyDescriptor = new EditPersonDescriptor(STUDENT_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_PERSON, STUDENT_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_PERSON, PROFESSOR_BOB)));
    }
}
