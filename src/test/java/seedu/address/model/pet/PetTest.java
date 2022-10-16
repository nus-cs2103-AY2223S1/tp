package seedu.address.model.pet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TypicalPets;

public class PetTest {

    @Test
    public void constructor_invalidPet_throwsIllegalValueException() {
        Name name = new Name("putu");
        Color color = new Color("brown");
        ColorPattern colorPattern = new ColorPattern("stripes");
        String dob = "101010";
        Species species = new Species("cat");
        Weight weight = new Weight(5);
        Height height = new Height(100);
        Set<Tag> tags = new HashSet<>(Arrays.asList(new Tag("cat")));
        Set<PetCertificate> certs = new HashSet<>(Arrays.asList(new PetCertificate("AVA")));

        assertThrows(IllegalValueException.class, () -> new Pet(name, color, colorPattern, dob, species, weight,
                height, tags, certs));
    }

    @Test
    public void toStringTest() {
        String expected = "Doja; Species: cat; Date Of Birth: 2022-10-10; Weight: 10.05 kg; Height: 100.5 cm; Color: "
            + "white; Color pattern: white and brown; Vaccinated; Tags:  [ cat ] ";
        assertEquals(TypicalPets.DOJA.toString(), expected);
    }

    @Test
    public void equals_sameObject() {
        assertEquals(TypicalPets.DOJA, TypicalPets.DOJA);
    }

    @Test
    public void equals_differentObjects() {
        Name name = new Name("putu");
        Color color = new Color("brown");
        ColorPattern colorPattern = new ColorPattern("stripes");
        String dob = "2019-09-09";
        Species species = new Species("cat");
        Weight weight = new Weight(5);
        Height height = new Height(100);
        Set<Tag> tags = new HashSet<>(Arrays.asList(new Tag("cat")));
        Set<PetCertificate> certs = new HashSet<>(Arrays.asList(new PetCertificate("AVA")));

        try {
            Pet pet1 = new Pet(name, color, colorPattern, dob, species, weight, height, tags, certs);
            Pet pet2 = new Pet(name, color, colorPattern, dob, species, weight, height, tags, certs);
            assertEquals(pet1, pet2);
        } catch (IllegalValueException e) {
            assert false;
        }
    }

    @Test
    public void hashcode() {
        Person person = new PersonBuilder().build();
        Name name = new Name("putu");
        Color color = new Color("brown");
        ColorPattern colorPattern = new ColorPattern("stripes");
        String dob = "2002-10-10";
        Species species = new Species("cat");
        Weight weight = new Weight(5);
        Height height = new Height(100);
        VaccinationStatus vs = new VaccinationStatus(false);
        Set<Tag> tags = new HashSet<>(Arrays.asList(new Tag("cat")));
        Set<PetCertificate> certs = new HashSet<>(Arrays.asList(new PetCertificate("AVA")));

        try {
            DateOfBirth dateOfBirth = DateOfBirth.parseString(dob);

            int expected = Objects.hash(name, color, dateOfBirth, species, weight, height, vs, tags,
                    certs, person);
            Pet pet = new Pet(name, person, color, colorPattern, DateOfBirth.parseString(dob),
                    species, weight, height, vs, tags, certs);
            int outcome = pet.hashCode();
            System.out.println(expected);
            assertEquals(outcome, expected);
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
        Name name = new Name("putu");
        Color color = new Color("brown");
        ColorPattern colorPattern = new ColorPattern("stripes");
        String dob = "2002-10-10";
        Species species = new Species("cat");
        Weight weight = new Weight(5);
        Height height = new Height(100);
        Set<Tag> tags = new HashSet<>(Arrays.asList(new Tag("cat")));
        Set<PetCertificate> certs = new HashSet<>(Arrays.asList(new PetCertificate("AVA")));

        try {
            Pet pet = new Pet(name, color, colorPattern, dob, species, weight, height, tags, certs);
            assertEquals(pet.getColor(), color);
        } catch (IllegalValueException e) {
            assert false;
        }
    }

    @Test
    public void getColorPattern() {
        Name name = new Name("putu");
        Color color = new Color("brown");
        ColorPattern colorPattern = new ColorPattern("stripes");
        String dob = "2002-10-10";
        Species species = new Species("cat");
        Weight weight = new Weight(5);
        Height height = new Height(100);
        Set<Tag> tags = new HashSet<>(Arrays.asList(new Tag("cat")));
        Set<PetCertificate> certs = new HashSet<>(Arrays.asList(new PetCertificate("AVA")));

        try {
            Pet pet = new Pet(name, color, colorPattern, dob, species, weight, height, tags, certs);
            assertEquals(pet.getColorPattern(), colorPattern);
        } catch (IllegalValueException e) {
            assert false;
        }
    }

    @Test
    public void getHeight() {
        Name name = new Name("putu");
        Color color = new Color("brown");
        ColorPattern colorPattern = new ColorPattern("stripes");
        String dob = "2002-10-10";
        Species species = new Species("cat");
        Weight weight = new Weight(5);
        Height height = new Height(100);
        Set<Tag> tags = new HashSet<>(Arrays.asList(new Tag("cat")));
        Set<PetCertificate> certs = new HashSet<>(Arrays.asList(new PetCertificate("AVA")));

        try {
            Pet pet = new Pet(name, color, colorPattern, dob, species, weight, height, tags, certs);
            assertEquals(pet.getHeight(), height);
        } catch (IllegalValueException e) {
            assert false;
        }
    }

    @Test
    public void getOwner() {
        Person person = new PersonBuilder().build();
        Name name = new Name("putu");
        Color color = new Color("brown");
        ColorPattern colorPattern = new ColorPattern("stripes");
        String dob = "2002-10-10";
        Species species = new Species("cat");
        Weight weight = new Weight(5);
        Height height = new Height(100);
        VaccinationStatus vs = new VaccinationStatus(false);
        Set<Tag> tags = new HashSet<>(Arrays.asList(new Tag("cat")));
        Set<PetCertificate> certs = new HashSet<>(Arrays.asList(new PetCertificate("AVA")));

        try {
            Pet pet = new Pet(name, person, color, colorPattern, DateOfBirth.parseString(dob),
                    species, weight, height, vs, tags, certs);
            assertEquals(pet.getOwner(), person);
        } catch (IllegalValueException e) {
            assert false;
        }
    }

    @Test
    public void getSpecies() {
        Name name = new Name("putu");
        Color color = new Color("brown");
        ColorPattern colorPattern = new ColorPattern("stripes");
        String dob = "2002-10-10";
        Species species = new Species("cat");
        Weight weight = new Weight(5);
        Height height = new Height(100);
        Set<Tag> tags = new HashSet<>(Arrays.asList(new Tag("cat")));
        Set<PetCertificate> certs = new HashSet<>(Arrays.asList(new PetCertificate("AVA")));

        try {
            Pet pet = new Pet(name, color, colorPattern, dob, species, weight, height, tags, certs);
            assertEquals(pet.getSpecies(), species);
        } catch (IllegalValueException e) {
            assert false;
        }
    }

    @Test
    public void getWeight() {
        Name name = new Name("putu");
        Color color = new Color("brown");
        ColorPattern colorPattern = new ColorPattern("stripes");
        String dob = "2002-10-10";
        Species species = new Species("cat");
        Weight weight = new Weight(5);
        Height height = new Height(100);
        Set<Tag> tags = new HashSet<>(Arrays.asList(new Tag("cat")));
        Set<PetCertificate> certs = new HashSet<>(Arrays.asList(new PetCertificate("AVA")));

        try {
            Pet pet = new Pet(name, color, colorPattern, dob, species, weight, height, tags, certs);
            assertEquals(pet.getWeight(), weight);
        } catch (IllegalValueException e) {
            assert false;
        }
    }

    @Test
    public void getVaccinationStatus() {
        Person person = new PersonBuilder().build();
        Name name = new Name("putu");
        Color color = new Color("brown");
        ColorPattern colorPattern = new ColorPattern("stripes");
        String dob = "2002-10-10";
        Species species = new Species("cat");
        Weight weight = new Weight(5);
        Height height = new Height(100);
        VaccinationStatus vs = new VaccinationStatus(false);
        Set<Tag> tags = new HashSet<>(Arrays.asList(new Tag("cat")));
        Set<PetCertificate> certs = new HashSet<>(Arrays.asList(new PetCertificate("AVA")));

        try {
            Pet pet = new Pet(name, person, color, colorPattern, DateOfBirth.parseString(dob),
                    species, weight, height, vs, tags, certs);
            assertEquals(pet.getVaccinationStatus(), vs);
        } catch (IllegalValueException e) {
            assert false;
        }
    }

    @Test
    public void getCertificates() {
        Name name = new Name("putu");
        Color color = new Color("brown");
        ColorPattern colorPattern = new ColorPattern("stripes");
        String dob = "2002-10-10";
        Species species = new Species("cat");
        Weight weight = new Weight(5);
        Height height = new Height(100);
        Set<Tag> tags = new HashSet<>(Arrays.asList(new Tag("cat")));
        Set<PetCertificate> certs = new HashSet<>(Arrays.asList(new PetCertificate("AVA")));

        try {
            Pet pet = new Pet(name, color, colorPattern, dob, species, weight, height, tags, certs);
            assertEquals(pet.getCertificates(), certs);
        } catch (IllegalValueException e) {
            assert false;
        }
    }

    @Test
    public void getTags() {
        Name name = new Name("putu");
        Color color = new Color("brown");
        ColorPattern colorPattern = new ColorPattern("stripes");
        String dob = "2002-10-10";
        Species species = new Species("cat");
        Weight weight = new Weight(5);
        Height height = new Height(100);
        Set<Tag> tags = new HashSet<>(Arrays.asList(new Tag("cat")));
        Set<PetCertificate> certs = new HashSet<>(Arrays.asList(new PetCertificate("AVA")));

        try {
            Pet pet = new Pet(name, color, colorPattern, dob, species, weight, height, tags, certs);
            assertEquals(pet.getTags(), tags);
        } catch (IllegalValueException e) {
            assert false;
        }
    }
}

