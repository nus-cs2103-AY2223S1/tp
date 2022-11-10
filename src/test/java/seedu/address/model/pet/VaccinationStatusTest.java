package seedu.address.model.pet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class VaccinationStatusTest {

    @Test
    public void defaultStatus() {
        VaccinationStatus vs = VaccinationStatus.defaultStatus();
        assertFalse(vs.getVaccinationStatus());
    }

    @Test
    public void getVaccinationStatus() {
        VaccinationStatus vsTrue = new VaccinationStatus(true);
        VaccinationStatus vsFalse = new VaccinationStatus(false);
        assertFalse(vsFalse.getVaccinationStatus());
        assertTrue(vsTrue.getVaccinationStatus());
    }

    @Test
    public void equals_sameObject() {
        VaccinationStatus vsTrue = new VaccinationStatus(true);
        assertEquals(vsTrue, vsTrue);
    }

    @Test
    public void equals_sameValue() {
        VaccinationStatus vsTrue1 = new VaccinationStatus(true);
        VaccinationStatus vsTrue2 = new VaccinationStatus(true);
        assertEquals(vsTrue2, vsTrue1);
    }

    @Test
    public void equals_diffValue() {
        VaccinationStatus vsTrue = new VaccinationStatus(true);
        VaccinationStatus vsFalse = new VaccinationStatus(false);
        assertNotEquals(vsTrue, vsFalse);
    }

    @Test
    public void toString_vaccinated() {
        VaccinationStatus vsTrue = new VaccinationStatus(true);
        assertEquals(vsTrue.toString(), "Vaccination status: Vaccinated");
    }

    @Test
    public void toString_notVaccinated() {
        VaccinationStatus vsFalse = new VaccinationStatus(false);
        assertEquals(vsFalse.toString(), "Vaccination status: Not Vaccinated");
    }

    @Test
    public void hashCodeTest() {
        VaccinationStatus vsTrue = new VaccinationStatus(true);
        VaccinationStatus vsFalse = new VaccinationStatus(false);
        assertEquals(vsTrue.hashCode(), Boolean.hashCode(true));
        assertEquals(vsFalse.hashCode(), Boolean.hashCode(false));
    }

    @Test
    public void compareTo() {
        VaccinationStatus vsTrue = new VaccinationStatus(true);
        VaccinationStatus vsTrueCopy = new VaccinationStatus(true);
        VaccinationStatus vsFalse = new VaccinationStatus(false);
        VaccinationStatus vsFalseCopy = new VaccinationStatus(false);

        //same booleans
        assertEquals(vsTrue.compareTo(vsTrueCopy), 0);
        assertEquals(vsFalse.compareTo(vsFalseCopy), 0);

        //object is vaccinated, other is not
        assertEquals(vsTrue.compareTo(vsFalse), 1);

        //object is not vaccinated, other is
        assertEquals(vsFalse.compareTo(vsTrue), -1);
    }
}
