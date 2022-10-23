package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.PersonBuilder.DEFAULT_PHYS_EMAIL;
import static seedu.address.testutil.PersonBuilder.DEFAULT_PHYS_NAME;
import static seedu.address.testutil.PersonBuilder.DEFAULT_PHYS_PHONE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Physician;
import seedu.address.model.person.Uid;

class SetPhysicianCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    void execute_setPhysicianOnPatient_pass() {
        Patient expectedPerson = (Patient) expectedModel.getAddressBook().getPersonList().stream()
                .filter(x -> x.getUid().equals(new Uid(3L))).findAny().get();
        Physician physicianToAssign = new Physician(new Name(DEFAULT_PHYS_NAME), new Phone(DEFAULT_PHYS_PHONE),
                new Email(DEFAULT_PHYS_EMAIL));
        Patient expectedPatientEdited = new Patient(expectedPerson.getUid(), expectedPerson.getName(),
                expectedPerson.getGender(), expectedPerson.getPhone(), expectedPerson.getEmail(),
                expectedPerson.getAddress(), expectedPerson.getTags(), expectedPerson.getDatesTimes(),
                expectedPerson.getVisitStatus(), Optional.of(physicianToAssign), Optional.empty());
        expectedModel.setPerson(expectedPerson, expectedPatientEdited);

        Command setPhysCommand = new SetPhysicianCommand(new Uid(3L), new Name(DEFAULT_PHYS_NAME),
                new Phone(DEFAULT_PHYS_PHONE), new Email(DEFAULT_PHYS_EMAIL));

        assertCommandSuccess(setPhysCommand,
                model, String.format(SetPhysicianCommand.MESSAGE_ADD_PHYS_SUCCESS, 3, DEFAULT_PHYS_NAME,
                        DEFAULT_PHYS_PHONE, DEFAULT_PHYS_EMAIL), expectedModel);
    }

    @Test
    void execute_setPhysicianOnNurse_fail() {
        Command setPhysCommand = new SetPhysicianCommand(new Uid(5L), new Name(DEFAULT_PHYS_NAME),
                new Phone(DEFAULT_PHYS_PHONE), new Email(DEFAULT_PHYS_EMAIL));

        assertCommandFailure(setPhysCommand,
                model, Messages.MESSAGE_SETPHYS_INVALID_CATEGORY);
    }

    @Test
    void execute_setPhysicianOnInvalidUid_fail() {
        Command setPhysCommand = new SetPhysicianCommand(new Uid(9999L), new Name(DEFAULT_PHYS_NAME),
                new Phone(DEFAULT_PHYS_PHONE), new Email(DEFAULT_PHYS_EMAIL));

        assertCommandFailure(setPhysCommand,
                model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_UID);
    }
}
