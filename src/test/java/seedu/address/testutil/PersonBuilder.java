package seedu.address.testutil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.appointment.PastAppointment;
import seedu.address.model.appointment.UpcomingAppointment;
import seedu.address.model.person.Email;
import seedu.address.model.person.FloorNumber;
import seedu.address.model.person.HospitalWing;
import seedu.address.model.person.Name;
import seedu.address.model.person.NextOfKin;
import seedu.address.model.person.PatientType;
import seedu.address.model.person.PatientType.PatientTypes;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.WardNumber;
import seedu.address.model.tag.Medication;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_NEXT_OF_KIN = "Sugon Bee, Father, 92873764";
    public static final PatientTypes DEFAULT_PATIENT_TYPE = PatientTypes.INPATIENT;
    public static final String DEFAULT_HOSPITAL_WING = "south";
    public static final Integer DEFAULT_FLOOR_NUMBER = 3;
    public static final String DEFAULT_WARD_NUMBER = "D312";

    private Name name;
    private Phone phone;
    private Email email;
    private NextOfKin nextOfKin;
    private PatientType patientType;
    private HospitalWing hospitalWing;
    private FloorNumber floorNumber;
    private WardNumber wardNumber;
    private Set<Medication> medications;
    private List<PastAppointment> pastAppointments;
    private UpcomingAppointment upcomingAppointment;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        nextOfKin = new NextOfKin(DEFAULT_NEXT_OF_KIN);
        patientType = new PatientType(DEFAULT_PATIENT_TYPE);
        hospitalWing = new HospitalWing(DEFAULT_HOSPITAL_WING);
        floorNumber = new FloorNumber(DEFAULT_FLOOR_NUMBER);
        wardNumber = new WardNumber(DEFAULT_WARD_NUMBER);
        medications = new HashSet<>();
        pastAppointments = new ArrayList<>();
        upcomingAppointment = new UpcomingAppointment((LocalDate) null);
    }


    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        nextOfKin = personToCopy.getNextOfKin();
        patientType = personToCopy.getPatientType();
        hospitalWing = personToCopy.getHospitalWing().orElse(null);
        floorNumber = personToCopy.getFloorNumber().orElse(null);
        wardNumber = personToCopy.getWardNumber().orElse(null);
        medications = new HashSet<>(personToCopy.getMedications());
        pastAppointments = personToCopy.getPastAppointments();
        upcomingAppointment = personToCopy.getUpcomingAppointment().orElse(null);
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code NextOfKin} of the {@code Person} that we are building.
     */
    public PersonBuilder withNextOfKin(String nextOfKin) {
        this.nextOfKin = new NextOfKin(nextOfKin);
        return this;
    }

    /**
     * Sets the {@code PatientType} of the {@code Person} that we are building.
     */
    public PersonBuilder withPatientType(PatientTypes patientType) {
        this.patientType = new PatientType(patientType);

        // If inpatient, set default hospital number, floor number and ward number if not present
        if (this.patientType.isInpatient()) {
            if (this.hospitalWing == null) {
                this.hospitalWing = new HospitalWing(DEFAULT_HOSPITAL_WING);
            }
            if (this.floorNumber == null) {
                this.floorNumber = new FloorNumber(DEFAULT_FLOOR_NUMBER);
            }
            if (this.wardNumber == null) {
                this.wardNumber = new WardNumber(DEFAULT_WARD_NUMBER);
            }
        // If outpatient, remove hospital number, floor number and ward number if present
        } else {
            this.hospitalWing = null;
            this.floorNumber = null;
            this.wardNumber = null;
        }
        return this;
    }

    /**
     * Sets the {@code HospitalWing} of the {@code Person} that we are building.
     */
    public PersonBuilder withHospitalWing(String hospitalWing) {
        this.hospitalWing = new HospitalWing(hospitalWing);
        return this;
    }

    /**
     * Sets the {@code FloorNumber} of the {@code Person} that we are building.
     */
    public PersonBuilder withFloorNumber(Integer floorNumber) {
        this.floorNumber = new FloorNumber(floorNumber);
        return this;
    }

    /**
     * Sets the {@code WardNumber} of the {@code Person} that we are building.
     */
    public PersonBuilder withWardNumber(String wardNumber) {
        this.wardNumber = new WardNumber(wardNumber);
        return this;
    }

    /**
     * Parses the {@code medications} into a {@code Set<Medication>}
     * and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withMedication(String ... medications) {
        this.medications = SampleDataUtil.getMedicationSet(medications);
        return this;
    }

    /**
     * Parses the {@code appointmentData} into a {@code PastAppointment}
     * and sets it to the {@code Person} that we are building.
     */
    public PersonBuilder withPastAppointment(String ... appointmentData) {
        this.pastAppointments.add(SampleDataUtil.getPastAppointment(appointmentData));
        return this;
    }

    /**
     * Parses the {@code dateString} into a {@code UpcomingAppointment}
     * and sets it to the {@code Person} that we are building.
     */
    public PersonBuilder withUpcomingAppointment(String dateString) {
        if (dateString == null) {
            this.upcomingAppointment = new UpcomingAppointment((LocalDate) null);
        } else {
            this.upcomingAppointment =
                    new UpcomingAppointment(LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd-MM-uuuu")));
        }
        return this;
    }

    /**
     * Builds the Person.
     */
    public Person build() {
        return new Person(name, phone, email, nextOfKin, patientType, hospitalWing,
                floorNumber, wardNumber, medications, pastAppointments, upcomingAppointment);
    }

}

