package seedu.address.model.person;

import java.util.function.Predicate;
/**
 * Tests that a {@code Person} is of the given {@code Patient type}.
 */
public class PatientTypePredicate implements Predicate<Person> {
    private final PatientType.PatientTypes patientType;

    public PatientTypePredicate(PatientType.PatientTypes patientType) {
        this.patientType = patientType;
    }
    @Override
    public boolean test(Person person) {
        return person.getPatientType().value == patientType;
    }
}
