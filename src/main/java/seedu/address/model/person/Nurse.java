package seedu.address.model.person;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.category.Category;
import seedu.address.model.tag.Tag;

/**
 * Represents a nurse that home-visit patients in Healthcare Xpress book.
 */

public class Nurse extends Person {

    private static final String MESSAGE_FOR_EMPTY_HOMEVISITLIST = "No home visit assigned yet.";
    private static final String MESSAGE_FOR_EMPTY_UNAVAILABLEDATE = "No unavailable date.";
    private final List<HomeVisit> homeVisitList = new ArrayList<>();
    private final List<Date> unavailableDateList = new ArrayList<>();
    private final List<Date> fullyScheduledDateList = new ArrayList<>();
    private final Set<Appointment> appointments = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Nurse(Uid uid, Name name, Gender gender, Phone phone, Email email, Address address, Set<Tag> tags,
            List<Date> unavailableDates, List<HomeVisit> homeVisits, List<Date> fullyScheduledDates) {
        super(uid, name, gender, phone, email, address, tags);
        this.unavailableDateList.addAll(unavailableDates);
        this.homeVisitList.addAll(homeVisits);
        this.fullyScheduledDateList.addAll(fullyScheduledDates);
    }

    public Nurse(Uid uid, Name name, Gender gender, Phone phone, Email email, Address address, Set<Tag> tags,
            List<Date> unavailableDates) {
        super(uid, name, gender, phone, email, address, tags);
        this.unavailableDateList.addAll(unavailableDates);
    }

    public Category getCategory() {
        return new Category("N");
    }

    public String getCategoryIndicator() {
        return PersonType.NURSE.toString();
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return super.hashCode() + Objects.hash(unavailableDateList) + Objects.hash(homeVisitList)
                + Objects.hash(fullyScheduledDateList);
    }

    /**
     * Returns a sorted home visit list
     */
    public List<HomeVisit> getHomeVisits() {
        HomeVisitComparator comp = new HomeVisitComparator();
        this.homeVisitList.sort(comp);
        return this.homeVisitList;
    }

    /**
     * Returns a sorted unavailable dates
     */
    public List<Date> getUnavailableDates() {
        DateComparator comp = new DateComparator();
        this.unavailableDateList.sort(comp);
        return this.unavailableDateList;
    }

    /**
     * Returns a sorted fully scheduled dates
     */
    public List<Date> getFullyScheduledDates() {
        DateComparator comp = new DateComparator();
        this.fullyScheduledDateList.sort(comp);
        return this.fullyScheduledDateList;
    }

    public String getHomesVisitsInString() {
        String homeVisitsString = homeVisitList.stream().map(x -> x.toString()).collect(Collectors.joining(","));
        if (homeVisitsString.length() == 0) {
            return MESSAGE_FOR_EMPTY_HOMEVISITLIST;
        }
        return String.format("Home Visits: %s;", homeVisitsString);
    }

    public String getUnavailableDatesInString() {
        String unavailableString = unavailableDateList.stream().map(x -> x.toString()).collect(Collectors.joining(","));
        if (unavailableString.length() == 0) {
            return MESSAGE_FOR_EMPTY_UNAVAILABLEDATE;
        }
        return String.format("Unavailable Dates: %s;", unavailableString);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getCategory().toFormattedString())
                .append(" ")
                .append(super.toString())
                .append(" ")
                .append(getUnavailableDatesInString())
                .append(" ")
                .append(getHomesVisitsInString());

        return builder.toString();
    }

    public boolean isNurse() {
        return true;
    }

    /**
     * Adds new appointment to current appointment set
     *
     * @param newAppointment New appointment to add
     */
    public void addAppointment(Appointment newAppointment) {
        this.appointments.add(newAppointment);
    }

    /**
     * Removes appointment from current appointment set
     *
     * @param appointment Appointment to remove
     */
    public void removeAppointment(Appointment appointment) {
        this.appointments.remove(appointment);
    }

    /**
     * Returns an optional of an appointment filtered from the appointment set
     *
     * @param appointmentDateTime The appointment date time of the requested
     *                            appointment
     * @return The optional of the appointment
     */
    public Optional<Appointment> findAppointment(AppointmentDateTime appointmentDateTime) {
        return appointments.stream()
                .filter(appointment -> appointment.getAppointmentDateTime().equals(appointmentDateTime))
                .findFirst();
    }

    /**
     * Returns an optional of an appointment filtered from the appointment set
     *
     * @param patient             The patient of the request appointment
     * @param appointmentDateTime The appointment date time of the requested
     *                            appointment
     * @return The optional of the appointment
     */
    public Optional<Appointment> findAppointment(Patient patient, AppointmentDateTime appointmentDateTime) {
        return appointments.stream()
                .filter(appointment -> appointment.getAppointmentDateTime().equals(appointmentDateTime)
                        && appointment.getPatient().equals(patient))
                .findFirst();
    }

    /**
     * Returns true if the nurse has an appointment at a given appointment date time
     *
     * @param appointmentDateTime The appointment date time to check
     * @return True if the nurse has an appointment at the given appointment date
     *         time
     */
    public boolean hasAppointment(AppointmentDateTime appointmentDateTime) {
        return appointments.stream().anyMatch(appt -> appt.getAppointmentDateTime().equals(appointmentDateTime));
    }
}
