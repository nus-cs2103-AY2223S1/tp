package seedu.address.logic.commands;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.core.index.ReverseIndexComparator;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.Date;
import seedu.address.model.person.DateSlot;
import seedu.address.model.person.HomeVisit;

/**
 * A class to manage all home visit related task.
 */
public class HomeVisitManager {

    public static final String MESSAGE_OUTOFBOUND_HOMEVISIT_INDEX = "The home visit index given is out of bounds.";
    private static final int NUMBER_OF_SLOT_PER_DAY = 4;
    public final List<HomeVisit> homeVisitList;
    public final List<Index> homeVisitIndex;
    public final List<Date> fullyScheduledDateList;

    /**
     * Construct a HomeVisitManager.
     * @param homeVisitList
     * @param homeVisitIndex
     * @param fullyScheduledDateList
     */
    public HomeVisitManager(List<HomeVisit> homeVisitList, List<Index> homeVisitIndex,
                            List<Date> fullyScheduledDateList) {
        this.homeVisitList = new ArrayList<>();
        for (HomeVisit homeVisit : homeVisitList) {
            this.homeVisitList.add(homeVisit.clone());
        }
        this.homeVisitIndex = new ArrayList<>(homeVisitIndex);
        this.fullyScheduledDateList = new ArrayList<>(fullyScheduledDateList);
    }

    /**
     * Construct a HomeVisitManager with empty home visit index list.
     * @param homeVisitList
     * @param fullyScheduledDateList
     */
    public HomeVisitManager(List<HomeVisit> homeVisitList, List<Date> fullyScheduledDateList) {
        this.homeVisitList = new ArrayList<>();
        for (HomeVisit homeVisit : homeVisitList) {
            this.homeVisitList.add(homeVisit.clone());
        }
        this.homeVisitIndex = new ArrayList<>();
        this.fullyScheduledDateList = new ArrayList<>(fullyScheduledDateList);
    }

    /**
     * Create a homevisit list for a nurse.
     * @param dateSlotList
     * @param dateSlotIndexList
     * @param patientUidNo
     * @return homevisit list
     */
    public List<HomeVisit> createHomeVisitList(List<DateSlot> dateSlotList,
                                               List<Index> dateSlotIndexList, Long patientUidNo) {
        if (dateSlotIndexList.isEmpty()) {
            createAllHomeVisit(dateSlotList, patientUidNo);
        } else {
            createSpecificHomeVisit(dateSlotList, dateSlotIndexList, patientUidNo);
        }
        return homeVisitList;
    }

    private void createAllHomeVisit(List<DateSlot> dateSlotList, Long patientUidNo) {
        for (DateSlot dateSlot : dateSlotList) {
            createHomeVisit(dateSlot, patientUidNo);
        }
    }

    private void createSpecificHomeVisit(List<DateSlot> dateSlotList, List<Index> dateSlotIndexList,
                                         Long patientUidNo) {
        List<Index> sortedDateSlotIndexList = sortIndex(dateSlotIndexList);
        for (Index index : sortedDateSlotIndexList) {
            DateSlot dateSlot = dateSlotList.get(index.getZeroBased());
            createHomeVisit(dateSlot, patientUidNo);
        }
    }

    private void createHomeVisit(DateSlot dateSlot, Long patientUidNo) {
        HomeVisit homeVisit = new HomeVisit(dateSlot, patientUidNo);
        homeVisitList.add(homeVisit);
        LocalDate date = dateSlot.getDate();
        addFullyScheduledDate(date);
    }

    private void addFullyScheduledDate(LocalDate date) {
        int frequencyCount = 0;
        for (HomeVisit homevisit : homeVisitList) {
            LocalDate dateToCheck = homevisit.getDateSlot().getDate();
            if (dateToCheck.equals(date)) {
                frequencyCount++;
            }
        }
        if (frequencyCount == NUMBER_OF_SLOT_PER_DAY) {
            fullyScheduledDateList.add(new Date(date));
        }
    }

    private List<Index> sortIndex(List<Index> indexList) {
        ReverseIndexComparator comp = new ReverseIndexComparator();
        indexList.sort(comp);
        return indexList;
    }

    /**
     * Remove homevisit from a nurse's homevisit list.
     * @return updated homevisit list
     * @throws CommandException
     */
    public List<HomeVisit> removeHomeVisits() throws CommandException {
        if (homeVisitIndex.isEmpty()) {
            removeAllHomeVisit();
        } else {
            removeSpecificHomeVisit();
        }
        return homeVisitList;
    }

    private void removeAllHomeVisit() throws CommandException {
        for (HomeVisit homeVisit : homeVisitList) {
            LocalDate date = homeVisit.getDateSlot().getDate();
            removeFullyScheduledDate(date);
            removeCheck(homeVisit);
        }
        homeVisitList.clear();
    }

    private void removeSpecificHomeVisit() throws CommandException {
        List<Index> sortedHomeVisitIndexList = sortIndex(homeVisitIndex);
        checkIndexOutOfBound();
        for (Index index : sortedHomeVisitIndexList) {
            HomeVisit homeVisit = homeVisitList.get(index.getZeroBased());
            removeCheck(homeVisit);
            homeVisitList.remove(homeVisit);
            LocalDate date = homeVisit.getDateSlot().getDate();
            removeFullyScheduledDate(date);
        }
    }

    private void removeCheck(HomeVisit homeVisit) throws CommandException {
        DateSlot dateSlot = homeVisit.getDateSlot();
        DateSlotChecker checker = new DateSlotChecker(dateSlot);
        checker.checkVisited();
    }

    private void checkIndexOutOfBound() throws CommandException {
        if (homeVisitIndex.get(0).getZeroBased() >= homeVisitList.size()) {
            throw new CommandException(MESSAGE_OUTOFBOUND_HOMEVISIT_INDEX);
        }
    }

    /**
     * Remove corresponding home visit of the given dateSlot from the homevisit list.
     * @param dateSlot
     * @return updated home visit list
     */
    public List<HomeVisit> removeHomeVisitFromDateSlot(DateSlot dateSlot) {
        HomeVisit homeVisitToBeDeleted = homeVisitList.stream().filter(
                h -> h.getDateSlot().getDateTime().equals(dateSlot.getDateTime())).findFirst().get();

        homeVisitList.remove(homeVisitToBeDeleted);
        LocalDate date = dateSlot.getDate();
        removeFullyScheduledDate(date);
        return this.homeVisitList;
    }

    private void removeFullyScheduledDate(LocalDate date) {
        Optional<Date> dateToBeDeleted = fullyScheduledDateList.stream().filter(
                h -> h.getDate().equals(date)).findFirst();

        if (!dateToBeDeleted.isEmpty()) {
            fullyScheduledDateList.remove(dateToBeDeleted.get());
        }
    }

    /**
     * To get the fully scheduled date list.
     * @return fullyScheduledDateList
     */
    public List<Date> getFullyScheduledDateList() {
        return this.fullyScheduledDateList;
    }

    /**
     * Remove corresponding home visit that have time clash with the unavailable date.
     * @param unavailableDateList
     * @return updated home visit list
     */
    public List<HomeVisit> removeHomeVisitFromUnavailableDates(List<Date> unavailableDateList) {
        for (Date date : unavailableDateList) {
            removeHomeVisitFromUnavailableDate(date);
        }
        return homeVisitList;
    }

    private void removeHomeVisitFromUnavailableDate(Date unavailableDate) {
        List<HomeVisit> toBeUpdatedHomeVisitList = new ArrayList<>(homeVisitList);
        for (HomeVisit homeVisit : homeVisitList) {
            Boolean isSameDate = checkSameDate(homeVisit, unavailableDate);
            if (isSameDate) {
                toBeUpdatedHomeVisitList.remove(homeVisit);
            }
        }
        homeVisitList.clear();
        homeVisitList.addAll(toBeUpdatedHomeVisitList);
        removeFullyScheduledDate(unavailableDate.getDate());
    }

    private Boolean checkSameDate(HomeVisit homeVisit, Date unavailableDate) {
        return homeVisit.getDateSlot().getDate().equals(unavailableDate.getDate());
    }

}

