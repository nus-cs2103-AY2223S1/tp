package seedu.address.logic.commands;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.core.index.ReverseIndexComparator;
import seedu.address.model.Model;
import seedu.address.model.person.Date;
import seedu.address.model.person.DateSlot;
import seedu.address.model.person.HomeVisit;
import seedu.address.model.person.Nurse;
import seedu.address.model.person.Person;

public class InternalHomeVisitRemoverFromDateSlot {

    public final Model model;
    private final List<Person> personList;
    private final List<DateSlot> patientDateSlotList;
    private final List<Index> patientDateSlotIndex;

    InternalHomeVisitRemoverFromDateSlot(Model model, List<Person> personList, List<DateSlot> patientDateSlotList,
                                         List<Index> patientDateSlotIndex) {
        this.model = model;
        this.personList = personList;
        this.patientDateSlotList = patientDateSlotList;
        this.patientDateSlotIndex = patientDateSlotIndex;
    }

    InternalHomeVisitRemoverFromDateSlot(Model model, List<Person> personList, List<DateSlot> patientDateSlotList) {
        this.model = model;
        this.personList = personList;
        this.patientDateSlotList = patientDateSlotList;
        this.patientDateSlotIndex = new ArrayList<>();
    }

    public Boolean removeHomeVisitsForDateSlot() {
        Boolean hasRemoveHomeVisits = false;
        if (patientDateSlotIndex.isEmpty()) {
            hasRemoveHomeVisits = removeHomeVisitsFromAllDateSlot();

        } else {
            hasRemoveHomeVisits = removeHomeVisitsFromSpecificDateSlot();
        }
        return hasRemoveHomeVisits;
    }

    private Boolean removeHomeVisitsFromAllDateSlot() {
        Boolean hasRemoveHomeVisits = false;
        for (DateSlot dateSlot : patientDateSlotList) {
            if (dateSlot.getHasAssigned()) {
                removeHomeVisitFromDateSlot(model, personList, dateSlot);
                hasRemoveHomeVisits = true;
            }
        }
        return hasRemoveHomeVisits;
    }

    private Boolean removeHomeVisitsFromSpecificDateSlot() {
        Boolean hasRemoveHomeVisits = false;
        ReverseIndexComparator comp = new ReverseIndexComparator();
        patientDateSlotIndex.sort(comp);
        for (Index index : patientDateSlotIndex) {
            DateSlot dateSlot = patientDateSlotList.get(index.getZeroBased());
            if (dateSlot.getHasAssigned()) {
                removeHomeVisitFromDateSlot(model, personList, dateSlot);
                hasRemoveHomeVisits = true;
            }
        }
        return hasRemoveHomeVisits;
    }

    private void removeHomeVisitFromDateSlot(Model model, List<Person> personList, DateSlot dateSlot) {
        Long nurseUidNo = dateSlot.getNurseUidNo();
        Person nurse = personList.stream().filter(p -> p.getUid().getUid().equals(nurseUidNo)).findFirst().get();
        List<HomeVisit> nurseHomeVisitList = ((Nurse) nurse).getHomeVisits();
        List<Date> nurseFullyScheduledList = ((Nurse) nurse).getFullyScheduledDates();

        HomeVisitManager remover = new HomeVisitManager(nurseHomeVisitList, nurseFullyScheduledList);
        List<HomeVisit> updatedHomeVisitList = remover.removeHomeVisitFromDateSlot(dateSlot);
        List<Date> updatedFullyScheduledList = remover.getFullyScheduledDateList();

        InternalEditor editor = new InternalEditor(model);
        editor.editNurse(nurse, updatedHomeVisitList, updatedFullyScheduledList);
    }


}


