package seedu.address.logic.commands;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.core.index.ReverseIndexComparator;
import seedu.address.model.Model;
import seedu.address.model.person.Date;
import seedu.address.model.person.DateSlot;
import seedu.address.model.person.HomeVisit;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;

/**
 * A class to do the internal unmarking of date slot when required.
 */
public class InternalUnmarkerFromHomeVisit {

    public final Model model;
    private final List<Person> personList;
    private final List<HomeVisit> nurseHomeVisitList;
    private final List<Index> nurseHomeVisitIndex;

    /**
     * Construct an InternalUnmarkerFromHomeVisit.
     * @param model
     * @param personList
     * @param nurseHomeVisitList
     * @param nurseHomeVisitIndex
     */
    InternalUnmarkerFromHomeVisit(Model model, List<Person> personList, List<HomeVisit> nurseHomeVisitList,
                                  List<Index> nurseHomeVisitIndex) {
        this.model = model;
        this.personList = personList;
        this.nurseHomeVisitList = nurseHomeVisitList;
        this.nurseHomeVisitIndex = nurseHomeVisitIndex;
    }

    /**
     * Construct an InternalUnmarkerFromHomeVisit with empty home visit index list.
     * @param model
     * @param personList
     * @param nurseHomeVisitList
     */
    InternalUnmarkerFromHomeVisit(Model model, List<Person> personList, List<HomeVisit> nurseHomeVisitList) {
        this.model = model;
        this.personList = personList;
        this.nurseHomeVisitList = nurseHomeVisitList;
        this.nurseHomeVisitIndex = new ArrayList<>();
    }

    /**
     * Unmark the respective date slots.
     */
    public void unmarkDateSlotForHomeVisit() {
        if (nurseHomeVisitIndex.isEmpty()) {
            unmarkDateSlotFromAllHomeVisit();

        } else {
            unmarkDateSlotFromSpecificHomeVisit();
        }
    }

    private void unmarkDateSlotFromAllHomeVisit() {
        for (HomeVisit homeVisit : nurseHomeVisitList) {
            unmarkDateSlotFromHomeVisit(model, personList, homeVisit);
        }
    }

    private void unmarkDateSlotFromSpecificHomeVisit() {
        ReverseIndexComparator comp = new ReverseIndexComparator();
        nurseHomeVisitIndex.sort(comp);
        for (Index index : nurseHomeVisitIndex) {
            HomeVisit homeVisit = nurseHomeVisitList.get(index.getZeroBased());
            unmarkDateSlotFromHomeVisit(model, personList, homeVisit);
        }
    }

    private void unmarkDateSlotFromHomeVisit(Model model, List<Person> personList, HomeVisit homeVisit) {
        Long patientUidNo = homeVisit.getHomeVisitPatientUidNo();
        Person patient = personList.stream().filter(p -> p.getUid().getUid().equals(patientUidNo)).findFirst().get();
        List<DateSlot> patientDateSlotList = ((Patient) patient).getDatesSlots();
        DateSlotManager unmarker = new DateSlotManager(patientDateSlotList);
        List<DateSlot> updatedDateSlotList = unmarker.unmarkSpecificAssignedFromHomeVisit(homeVisit.getDateSlot());

        InternalEditor editor = new InternalEditor(model);
        editor.editPatient(patient, updatedDateSlotList);
    }

    /**
     * Unmark the respective date slots.
     * @param unavailableDateList
     */
    public void unmarkDateSlotForUnavailableDates(List<Date> unavailableDateList) {
        for (Date date : unavailableDateList) {
            unmarkDateSlotForUnavailableDate(date);
        }
    }

    private void unmarkDateSlotForUnavailableDate(Date date) {
        for (HomeVisit homeVisit : nurseHomeVisitList) {
            Boolean isSameDate = checkSameDate(homeVisit, date);
            if (isSameDate) {
                unmarkDateSlotFromHomeVisit(model, personList, homeVisit);
            }
        }
    }

    private Boolean checkSameDate(HomeVisit homeVisit, Date unavailableDate) {
        return homeVisit.getDateSlot().getDate().equals(unavailableDate.getDate());
    }


}



