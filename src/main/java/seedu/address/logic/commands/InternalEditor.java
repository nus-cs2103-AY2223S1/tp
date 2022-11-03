package seedu.address.logic.commands;

import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Date;
import seedu.address.model.person.DateSlot;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.HomeVisit;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nurse;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Uid;
import seedu.address.model.tag.Tag;

public class InternalEditor {

    public final Model model;

    public InternalEditor(Model model) {
        this.model = model;
    }

    public void editPatient(Person patient, List<DateSlot> dateSlotList) {
        Uid patientUid = patient.getUid();
        List<Person> lastShownList = model.getFilteredPersonList();
        Optional<Person> personToEdit = lastShownList.stream().filter(p -> p.getUid().equals(patientUid)).findFirst();
        Person confirmedPersonToEdit = personToEdit.get();
        Uid uid = confirmedPersonToEdit.getUid();
        Name name = confirmedPersonToEdit.getName();
        Gender gender = confirmedPersonToEdit.getGender();
        Phone phone = confirmedPersonToEdit.getPhone();
        Email email = confirmedPersonToEdit.getEmail();
        Address address = confirmedPersonToEdit.getAddress();
        Set<Tag> tags = confirmedPersonToEdit.getTags();
        Person newPerson = new Patient(uid, name, gender, phone, email, address, tags, dateSlotList);
        model.setPerson(confirmedPersonToEdit, newPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    public void editNurse(Person nurse, List<HomeVisit> homeVisitList, List<Date> fullyScheduledDateList) {
        Uid nurseUid = nurse.getUid();
        List<Person> lastShownList = model.getFilteredPersonList();
        Optional<Person> personToEdit = lastShownList.stream().filter(p -> p.getUid().equals(nurseUid)).findFirst();
        Person confirmedPersonToEdit = personToEdit.get();
        Uid uid = confirmedPersonToEdit.getUid();
        Name name = confirmedPersonToEdit.getName();
        Gender gender = confirmedPersonToEdit.getGender();
        Phone phone = confirmedPersonToEdit.getPhone();
        Email email = confirmedPersonToEdit.getEmail();
        Address address = confirmedPersonToEdit.getAddress();
        Set<Tag> tags = confirmedPersonToEdit.getTags();
        List<Date> unavailableDates = ((Nurse) confirmedPersonToEdit).getUnavailableDates();
        Person newPerson = new Nurse(uid, name, gender, phone, email, address, tags, unavailableDates,
                homeVisitList, fullyScheduledDateList);
        model.setPerson(confirmedPersonToEdit, newPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }
}
