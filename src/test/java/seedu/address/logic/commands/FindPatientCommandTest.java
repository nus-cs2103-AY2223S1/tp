package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PATIENTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPatients.ALICE;
import static seedu.address.testutil.TypicalPatients.BENSON;
import static seedu.address.testutil.TypicalPatients.CARL;
import static seedu.address.testutil.TypicalPatients.DANIEL;
import static seedu.address.testutil.TypicalPatients.ELLE;
import static seedu.address.testutil.TypicalPatients.FIONA;
import static seedu.address.testutil.TypicalPatients.GEORGE;
import static seedu.address.testutil.TypicalPatients.getTypicalPatientsHealthContact;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.patient.Address;
import seedu.address.model.patient.Email;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Phone;
import seedu.address.model.patient.Remark;
import seedu.address.model.tag.Tag;

public class FindPatientCommandTest {
    private Model model = new ModelManager(getTypicalPatientsHealthContact(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalPatientsHealthContact(), new UserPrefs());
    @Test
    public void equals() {
        Set<Tag> tagSet = new HashSet<Tag>();

        tagSet.add(new Tag("friends"));
        tagSet.add(new Tag("colleagues"));

        Optional<Predicate<Name>> firstNamePredicate = Optional.of(name -> name.fullName.toLowerCase()
                .contains("bernice".toLowerCase()));
        Optional<Predicate<Phone>> firstPhonePredicate = Optional.of(phone -> phone.value.toLowerCase()
                .contains("912"));
        Optional<Predicate<Email>> firstEmailPredicate = Optional.of(email -> email.value.toLowerCase()
                .contains("gmail".toLowerCase()));
        Optional<Predicate<Address>> firstAddressPredicate = Optional.of(address -> address.toString()
                .toLowerCase().contains("Blk".toLowerCase()));
        Optional<Predicate<Remark>> firstRemarkPredicate = Optional.of(remark -> remark.toString().toLowerCase()
                .contains("swim".toLowerCase()));
        Optional<Predicate<Set<Tag>>> firstTagPredicate = Optional.of(patientTags -> tagSet.stream()
                .allMatch(inputTag -> patientTags.stream().anyMatch(patientTag -> patientTag.tagName.toLowerCase()
                        .contains(inputTag.tagName.toLowerCase()))));

        Optional<Predicate<Name>> secondNamePredicate = Optional.of(name -> name.fullName.toLowerCase()
                .contains("alex".toLowerCase()));
        Optional<Predicate<Phone>> secondPhonePredicate = Optional.of(phone -> phone.value.toLowerCase()
                .contains("819"));
        Optional<Predicate<Email>> secondEmailPredicate = Optional.of(email -> email.value.toLowerCase()
                .contains("google".toLowerCase()));
        Optional<Predicate<Address>> secondAddressPredicate = Optional.of(address -> address.toString()
                .toLowerCase().contains("Street".toLowerCase()));
        Optional<Predicate<Remark>> secondRemarkPredicate = Optional.of(remark -> remark.toString()
                .toLowerCase().contains("dance".toLowerCase()));
        Optional<Predicate<Set<Tag>>> secondTagPredicate = Optional.empty();

        FindPatientCommand firstCommand = new FindPatientCommand(firstNamePredicate, firstPhonePredicate,
                firstEmailPredicate, firstAddressPredicate, firstTagPredicate, firstRemarkPredicate);
        FindPatientCommand secondCommand = new FindPatientCommand(secondNamePredicate, secondPhonePredicate,
                secondEmailPredicate, secondAddressPredicate, secondTagPredicate, secondRemarkPredicate);

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));


        // same values -> returns true
        FindPatientCommand firstCommandCopy = new FindPatientCommand(firstNamePredicate, firstPhonePredicate,
                firstEmailPredicate, firstAddressPredicate, firstTagPredicate, firstRemarkPredicate);
        assertTrue(firstCommand.equals(firstCommandCopy));

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different predicate -> returns false
        assertFalse(firstCommand.equals(secondCommand));
    }


    @Test
    public void execute_findByName_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PATIENTS_LISTED_OVERVIEW, 0);
        Optional<Predicate<Name>> namePredicate = Optional.of(name -> name.fullName.toLowerCase()
                .contains("bernice".toLowerCase()));
        FindPatientCommand command = new FindPatientCommand(namePredicate, Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty());
        expectedModel.updateFilteredPatientList(command.getPredicate());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPatientList());
    }

    @Test
    public void execute_findByName_found() {
        String expectedMessage = String.format(MESSAGE_PATIENTS_LISTED_OVERVIEW, 1);
        Optional<Predicate<Name>> namePredicate = Optional.of(name -> name.fullName.toLowerCase()
                .contains("pauline".toLowerCase()));
        FindPatientCommand command = new FindPatientCommand(namePredicate, Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty());
        expectedModel.updateFilteredPatientList(command.getPredicate());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredPatientList());
    }

    @Test
    public void execute_findByPhone_found() {
        String expectedMessage = String.format(MESSAGE_PATIENTS_LISTED_OVERVIEW, 3);
        Optional<Predicate<Phone>> phonePredicate = Optional.of(phone -> phone.value.toLowerCase()
                .contains("948"));
        FindPatientCommand command = new FindPatientCommand(Optional.empty(), phonePredicate,
                Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty());
        expectedModel.updateFilteredPatientList(command.getPredicate());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ELLE, FIONA, GEORGE), model.getFilteredPatientList());
    }

    @Test
    public void execute_findByAddress_found() {
        String expectedMessage = String.format(MESSAGE_PATIENTS_LISTED_OVERVIEW, 2);
        Optional<Predicate<Address>> addressPredicate = Optional.of(address -> address.toString()
                .toLowerCase().contains("th street".toLowerCase()));
        FindPatientCommand command = new FindPatientCommand(Optional.empty(), Optional.empty(),
                Optional.empty(), addressPredicate, Optional.empty(),
                Optional.empty());
        expectedModel.updateFilteredPatientList(command.getPredicate());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(DANIEL, GEORGE), model.getFilteredPatientList());
    }

    @Test
    public void execute_findByEmail_found() {
        String expectedMessage = String.format(MESSAGE_PATIENTS_LISTED_OVERVIEW, 7);
        Optional<Predicate<Email>> emailPredicate = Optional.of(email -> email.value.toLowerCase()
                .contains("@example.".toLowerCase()));
        FindPatientCommand command = new FindPatientCommand(Optional.empty(), Optional.empty(),
                emailPredicate, Optional.empty(), Optional.empty(),
                Optional.empty());
        expectedModel.updateFilteredPatientList(command.getPredicate());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE), model.getFilteredPatientList());
    }

    @Test
    public void execute_findByRemark_found() {
        String expectedMessage = String.format(MESSAGE_PATIENTS_LISTED_OVERVIEW, 1);
        Optional<Predicate<Remark>> remarkPredicate = Optional.of(remark -> remark.toString().toLowerCase()
                .contains("CAN'T TAKE".toLowerCase()));
        FindPatientCommand command = new FindPatientCommand(Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(),
                remarkPredicate);
        expectedModel.updateFilteredPatientList(command.getPredicate());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON), model.getFilteredPatientList());
    }

    @Test
    public void execute_findByTags_found() {
        String expectedMessage = String.format(MESSAGE_PATIENTS_LISTED_OVERVIEW, 1);
        Set<Tag> testTag = new HashSet<Tag>();
        testTag.add(new Tag("OWESMONEY"));

        Optional<Predicate<Set<Tag>>> tagPredicate = Optional.of(patientTags -> testTag.stream()
                .allMatch(inputTag -> patientTags.stream().anyMatch(patientTag -> patientTag.tagName.toLowerCase()
                        .contains(inputTag.tagName.toLowerCase()))));
        FindPatientCommand command = new FindPatientCommand(Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), tagPredicate, Optional.empty());
        expectedModel.updateFilteredPatientList(command.getPredicate());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON), model.getFilteredPatientList());
    }


    @Test
    public void execute_multipleFields_found() {
        String expectedMessage = String.format(MESSAGE_PATIENTS_LISTED_OVERVIEW, 1);
        Optional<Predicate<Name>> namePredicate = Optional.of(name -> name.fullName.toLowerCase()
                .contains("pauline".toLowerCase()));
        Optional<Predicate<Phone>> phonePredicate = Optional.of(phone -> phone.value.toLowerCase()
                .contains("943"));
        Optional<Predicate<Email>> emailPredicate = Optional.of(email -> email.value.toLowerCase()
                .contains("@example.".toLowerCase()));
        Optional<Predicate<Address>> addressPredicate = Optional.of(address -> address.toString()
                .toLowerCase().contains("west".toLowerCase()));
        Optional<Predicate<Remark>> remarkPredicate = Optional.of(remark -> remark.toString().toLowerCase()
                .contains("likes".toLowerCase()));
        Set<Tag> testTag = new HashSet<Tag>();
        testTag.add(new Tag("FRIENDS"));
        Optional<Predicate<Set<Tag>>> tagPredicate = Optional.of(patientTags -> testTag.stream()
                .allMatch(inputTag -> patientTags.stream().anyMatch(patientTag -> patientTag.tagName.toLowerCase()
                        .contains(inputTag.tagName.toLowerCase()))));
        FindPatientCommand command = new FindPatientCommand(namePredicate, phonePredicate,
                emailPredicate, addressPredicate, tagPredicate, remarkPredicate);
        expectedModel.updateFilteredPatientList(command.getPredicate());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredPatientList());
    }

}
