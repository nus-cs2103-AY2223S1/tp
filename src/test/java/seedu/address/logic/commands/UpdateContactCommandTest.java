package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.PersonBuilder.DEFAULT_CONTACT_EMAIL;
import static seedu.address.testutil.PersonBuilder.DEFAULT_CONTACT_NAME;
import static seedu.address.testutil.PersonBuilder.DEFAULT_CONTACT_PHONE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.category.Category;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.NextOfKin;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Physician;
import seedu.address.model.person.Uid;

class UpdateContactCommandTest {

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
        Physician physicianToAssign = new Physician(new Name(DEFAULT_CONTACT_NAME), new Phone(DEFAULT_CONTACT_PHONE),
                new Email(DEFAULT_CONTACT_EMAIL));
        Patient expectedPatientEdited = new Patient(expectedPerson.getUid(), expectedPerson.getName(),
                expectedPerson.getGender(), expectedPerson.getPhone(), expectedPerson.getEmail(),
                expectedPerson.getAddress(), expectedPerson.getTags(), expectedPerson.getDatesSlots(),
                Optional.of(physicianToAssign), Optional.empty());
        expectedModel.setPerson(expectedPerson, expectedPatientEdited);

        Command setPhysCommand = new UpdateContactCommand(new Uid(3L), new Name(DEFAULT_CONTACT_NAME),
                new Phone(DEFAULT_CONTACT_PHONE), new Email(DEFAULT_CONTACT_EMAIL),
                new Category(Category.PHYSICIAN_SYMBOL));

        assertCommandSuccess(setPhysCommand,
                model, String.format(UpdateContactCommand.MESSAGE_UPDATE_CONTACT_SUCCESS, 3, DEFAULT_CONTACT_NAME,
                        DEFAULT_CONTACT_PHONE, DEFAULT_CONTACT_EMAIL, Category.PHYSICIAN_SYMBOL),
                expectedModel);
    }

    @Test
    void execute_setPhysicianOnNurse_fail() {
        Command setPhysCommand = new UpdateContactCommand(new Uid(5L), new Name(DEFAULT_CONTACT_NAME),
                new Phone(DEFAULT_CONTACT_PHONE), new Email(DEFAULT_CONTACT_EMAIL),
                new Category(Category.PHYSICIAN_SYMBOL));

        assertCommandFailure(setPhysCommand,
                model, Messages.MESSAGE_UPDATECONTACT_INVALID_CATEGORY);
    }

    @Test
    void execute_setPhysicianOnInvalidUid_fail() {
        Command setPhysCommand = new UpdateContactCommand(new Uid(9999L), new Name(DEFAULT_CONTACT_NAME),
                new Phone(DEFAULT_CONTACT_PHONE), new Email(DEFAULT_CONTACT_EMAIL),
                new Category(Category.PHYSICIAN_SYMBOL));

        assertCommandFailure(setPhysCommand,
                model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_UID);
    }

    @Test
    void execute_setNokOnPatient_pass() {
        Patient expectedPatient = (Patient) expectedModel.getAddressBook().getPersonList().stream()
                .filter(x -> x.getUid().equals(new Uid(3L))).findAny().get();
        NextOfKin nextOfKin = new NextOfKin(new Name(DEFAULT_CONTACT_NAME), new Phone(DEFAULT_CONTACT_PHONE),
                new Email(DEFAULT_CONTACT_EMAIL));
        Patient expectedPatientEdited = new Patient(expectedPatient.getUid(), expectedPatient.getName(),
                expectedPatient.getGender(), expectedPatient.getPhone(), expectedPatient.getEmail(),
                expectedPatient.getAddress(), expectedPatient.getTags(), expectedPatient.getDatesSlots(),
                expectedPatient.getAttendingPhysician(), Optional.of(nextOfKin));
        expectedModel.setPerson(expectedPatient, expectedPatientEdited);

        Command command = new UpdateContactCommand(new Uid(3L), new Name(DEFAULT_CONTACT_NAME),
                new Phone(DEFAULT_CONTACT_PHONE), new Email(DEFAULT_CONTACT_EMAIL),
                new Category(Category.NEXTOFKIN_SYMBOL));

        assertCommandSuccess(command,
                model, String.format(UpdateContactCommand.MESSAGE_UPDATE_CONTACT_SUCCESS, 3, DEFAULT_CONTACT_NAME,
                        DEFAULT_CONTACT_PHONE, DEFAULT_CONTACT_EMAIL, Category.NEXTOFKIN_SYMBOL),
                expectedModel);
    }

    @Test
    void execute_setNokOnNurse_fail() {
        Command setPhysCommand = new UpdateContactCommand(new Uid(5L), new Name(DEFAULT_CONTACT_NAME),
                new Phone(DEFAULT_CONTACT_PHONE), new Email(DEFAULT_CONTACT_EMAIL),
                new Category(Category.NEXTOFKIN_SYMBOL));

        assertCommandFailure(setPhysCommand,
                model, Messages.MESSAGE_UPDATECONTACT_INVALID_CATEGORY);
    }

    @Test
    void execute_setNokOnInvalidUid_fail() {
        Command setPhysCommand = new UpdateContactCommand(new Uid(9999L), new Name(DEFAULT_CONTACT_NAME),
                new Phone(DEFAULT_CONTACT_PHONE), new Email(DEFAULT_CONTACT_EMAIL),
                new Category(Category.NEXTOFKIN_SYMBOL));

        assertCommandFailure(setPhysCommand,
                model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_UID);
    }
}
