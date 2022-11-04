package seedu.address.model.pet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.order.Price;
import seedu.address.testutil.PetBuilder;
import seedu.address.testutil.TypicalPets;

public class PetTest {

    @Test
    public void constructor_invalidPet_throwsIllegalValueException() {
        PetName name = new PetName("putu");
        Color color = new Color("brown");
        ColorPattern colorPattern = new ColorPattern("stripes");
        String dob = "101010";
        Species species = new Species("cat");
        Weight weight = new Weight(5);
        Height height = new Height(100);
        Set<PetCertificate> certs = new HashSet<>(Arrays.asList(new PetCertificate("AVA")));

        assertThrows(IllegalValueException.class, () -> new Pet(name, color, colorPattern, dob, species, weight,
                height, new Price(66.6), certs));
    }

    @Test
    public void toStringTest() {
        String expected = "Doja; Species: cat; Date of birth: 2022-10-10; Weight: 10.05 kg; Height: 50.0 cm; "
                + "Color: white; Color pattern: none; Vaccination status: Vaccinated";
        assertEquals(expected, TypicalPets.DOJA.toString());
    }

    @Test
    public void equals_sameObject() {
        assertEquals(TypicalPets.DOJA, TypicalPets.DOJA);
    }

    @Test
    public void equals_differentObjects_false() {
        PetName name = new PetName("putu");
        Color color = new Color("brown");
        ColorPattern colorPattern = new ColorPattern("stripes");
        String dob = "2019-09-09";
        Species species = new Species("cat");
        Price price = new Price(66.66);
        Weight weight = new Weight(5);
        Height height = new Height(100);
        Set<PetCertificate> certs = new HashSet<>(Arrays.asList(new PetCertificate("AVA")));

        try {
            Pet pet1 = new Pet(name, color, colorPattern, dob, species, weight, height, price,
                    certs);
            Pet pet2 = new Pet(name, color, colorPattern, dob, species, weight, height, price,
                    certs);
            assertNotEquals(pet1, pet2);
        } catch (IllegalValueException e) {
            assert false;
        }
    }


    @Test
    public void isSamePet() {
        assertTrue(TypicalPets.PLUM.isSamePet(TypicalPets.PLUM));
        assertFalse(TypicalPets.PLUM.isSamePet(TypicalPets.DOJA));
    }

    @Test
    public void getColor() {
        String colorString = "green";
        Color color = new Color(colorString);
        Pet firstPet = new PetBuilder().withColor(colorString).build();
        assertEquals(firstPet.getColor(), color);
    }

    @Test
    public void getColorPattern() {
        String colorPatternString = "dots";
        ColorPattern colorPattern = new ColorPattern(colorPatternString);
        Pet firstPet = new PetBuilder().withColorPattern(colorPatternString).build();
        assertEquals(firstPet.getColorPattern(), colorPattern);
    }

    @Test
    public void getHeight() {
        Double heightVal = 85.45;
        Height height = new Height(heightVal);
        Pet firstPet = new PetBuilder().withHeight(heightVal).build();
        assertEquals(firstPet.getHeight(), height);
    }


    @Test
    public void getSpecies() {
        String speciesString = "stingray";
        Species species = new Species(speciesString);
        Pet firstPet = new PetBuilder().withSpecies(speciesString).build();
        assertEquals(firstPet.getSpecies(), species);
    }

    @Test
    public void getWeight() {
        Double weightVal = 85.45;
        Weight weight = new Weight(weightVal);
        Pet firstPet = new PetBuilder().withWeight(weightVal).build();
        assertEquals(firstPet.getWeight(), weight);
    }

    @Test
    public void getVaccinationStatus() {
        boolean status = true;
        VaccinationStatus vaccinationStatus = new VaccinationStatus(status);
        Pet firstPet = new PetBuilder().withVaccinationStatus(status).build();
        assertEquals(firstPet.getVaccinationStatus(), vaccinationStatus);
    }

    @Test
    public void getCertificates() {
        Set<PetCertificate> petCertificates = Arrays.asList(new PetCertificate("AVA"), new PetCertificate("kennel"))
                .stream().collect(Collectors.toSet());
        Pet firstPet = new PetBuilder().withCertificates("AVA", "kennel").build();
        assertEquals(firstPet.getCertificates(), petCertificates);
    }

    @Test
    public void compareCertificate() {
        Pet firstPet = new PetBuilder().withCertificates("AVA", "kennel").build();
        Pet secondPet = new PetBuilder().withCertificates("AVA", "kennel").build();

        //same size
        int expected = 0;
        int result = firstPet.compareCertificate(secondPet);
        assertEquals(expected, result);

        //larger list -> -1
        firstPet = new PetBuilder().withCertificates("AVA").build();
        secondPet = new PetBuilder().withCertificates("AVA", "kennel").build();
        expected = -1;
        result = firstPet.compareCertificate(secondPet);
        assertEquals(expected, result);

        //smaller list -> 1
        firstPet = new PetBuilder().withCertificates("AVA", "PET MENSA", "kennel").build();
        secondPet = new PetBuilder().withCertificates("AVA", "kennel").build();
        expected = 1;
        result = firstPet.compareCertificate(secondPet);
        assertEquals(expected, result);
    }

}

