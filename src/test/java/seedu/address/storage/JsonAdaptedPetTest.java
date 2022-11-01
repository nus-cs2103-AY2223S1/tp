package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.UniqueId;
import seedu.address.commons.core.index.UniqueIdGenerator;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.order.Price;
import seedu.address.model.person.Name;
import seedu.address.model.person.Supplier;
import seedu.address.model.pet.DateOfBirth;
import seedu.address.model.pet.Height;
import seedu.address.model.pet.Pet;
import seedu.address.model.pet.PetCertificate;
import seedu.address.model.pet.Weight;
import seedu.address.testutil.TypicalPets;


public class JsonAdaptedPetTest {
    private static final String MISSING_FIELD_MESSAGE_FORMAT = "Pet's %s field is missing!";

    private static final String DEFAULT_COLOR = "";
    private static final String DEFAULT_COLOR_PATTERN = "";
    private static final String DEFAULT_SPECIES = "";
    private static final Double DEFAULT_WEIGHT = 0.0;
    private static final Double DEFAULT_HEIGHT = 0.0;

    private static final String INVALID_PET_NAME = "@shy";
    private static final String INVALID_COLOR = "!quam&rine";
    private static final String INVALID_COLOR_PATTERN = "$tripe$";
    private static final String INVALID_DATE_OF_BIRTH = "2030-16-100";
    private static final String INVALID_SPECIES = "&xolotl!";
    private static final Double INVALID_WEIGHT = -999999999.99999999999;
    private static final Double INVALID_HEIGHT = -0.00000000099994844;
    private static final Double INVALID_PRICE = -8888.674;
    private static final String INVALID_UNIQUE_ID = TypicalPets.DOJA.getId().getIdToString();

    private static final String VALID_PET_NAME = TypicalPets.DOJA.getName().toString();
    private static final JsonAdaptedSupplier VALID_SUPPLIER = new JsonAdaptedSupplier(TypicalPets.DOJA.getSupplier());
    private static final String VALID_COLOR = TypicalPets.DOJA.getColor().getValue();
    private static final String VALID_COLOR_PATTERN = TypicalPets.DOJA.getColorPattern().getValue();
    private static final String VALID_DATE_OF_BIRTH = TypicalPets.DOJA.getDateOfBirth().getDate().toString();
    private static final String VALID_SPECIES = TypicalPets.DOJA.getSpecies().getValue();
    private static final Double VALID_WEIGHT = TypicalPets.DOJA.getWeight().getValue();
    private static final Double VALID_HEIGHT = TypicalPets.DOJA.getHeight().getValue();
    private static final boolean VALID_VACCINATION_STATUS = TypicalPets.DOJA.getVaccinationStatus()
            .getVaccinationStatus();
    private static final Double VALID_PRICE = TypicalPets.DOJA.getPrice().getPrice();
    private static final List<String> VALID_CERTIFICATES = TypicalPets.DOJA.getCertificates().stream()
            .map(PetCertificate::getCertificate).collect(Collectors.toList());

    private static final String VALID_UNIQUE_ID_BACKUP = TypicalPets.PLUM.getId().getIdToString();

    private static final UniqueIdGenerator PET_ID_GENERATOR = new UniqueIdGenerator();

    @Test
    public void toModelType_validPetDetails_returnsPet() throws Exception {
        JsonAdaptedPet pet = new JsonAdaptedPet(TypicalPets.DOJA);
        Pet temp = TypicalPets.DOJA;
        Pet modelPet = pet.toModelType();
        assertEquals(TypicalPets.DOJA, modelPet);
    }

    @Test
    public void toModelType_invalidPetName_throwsIllegalValueException() {
        UniqueId id = PET_ID_GENERATOR.next();
        JsonAdaptedPet pet = new JsonAdaptedPet(INVALID_PET_NAME, VALID_SUPPLIER, VALID_COLOR, VALID_COLOR_PATTERN,
                VALID_DATE_OF_BIRTH, VALID_SPECIES, VALID_WEIGHT, VALID_HEIGHT, VALID_VACCINATION_STATUS, VALID_PRICE,
                VALID_CERTIFICATES, id.toString());
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, pet::toModelType);
    }

    @Test
    public void toModelType_nullPetName_throwsIllegalValueException() {
        UniqueId id = PET_ID_GENERATOR.next();
        JsonAdaptedPet pet = new JsonAdaptedPet(null, VALID_SUPPLIER, VALID_COLOR, VALID_COLOR_PATTERN,
                VALID_DATE_OF_BIRTH, VALID_SPECIES, VALID_WEIGHT, VALID_HEIGHT, VALID_VACCINATION_STATUS, VALID_PRICE,
                VALID_CERTIFICATES, id.toString());
        String expectedMessage = "Pet's Name field is missing!";
        assertThrows(IllegalValueException.class, expectedMessage, pet::toModelType);
    }

    @Test
    public void toModelType_nullSupplier_throwsIllegalValueException() {
        UniqueId id = PET_ID_GENERATOR.next();
        JsonAdaptedPet pet = new JsonAdaptedPet(VALID_PET_NAME, null, VALID_COLOR, VALID_COLOR_PATTERN,
                VALID_DATE_OF_BIRTH, VALID_SPECIES, VALID_WEIGHT, VALID_HEIGHT, VALID_VACCINATION_STATUS, VALID_PRICE,
                VALID_CERTIFICATES, id.toString());
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Supplier.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, pet::toModelType);
    }

    @Test
    public void toModelType_invalidColor_defaultColor() {
        UniqueId firstId = PET_ID_GENERATOR.next();
        UniqueId secondId = PET_ID_GENERATOR.next();

        JsonAdaptedPet pet = new JsonAdaptedPet(VALID_PET_NAME, VALID_SUPPLIER, INVALID_COLOR, VALID_COLOR_PATTERN,
                VALID_DATE_OF_BIRTH, VALID_SPECIES, VALID_WEIGHT, VALID_HEIGHT, VALID_VACCINATION_STATUS, VALID_PRICE,
                VALID_CERTIFICATES, firstId.toString());
        JsonAdaptedPet expected = new JsonAdaptedPet(VALID_PET_NAME, VALID_SUPPLIER, DEFAULT_COLOR, VALID_COLOR_PATTERN,
                VALID_DATE_OF_BIRTH, VALID_SPECIES, VALID_WEIGHT, VALID_HEIGHT, VALID_VACCINATION_STATUS, VALID_PRICE,
                VALID_CERTIFICATES, secondId.toString());

        try {
            assertTrue(pet.toModelType().isSamePet(expected.toModelType()));
        } catch (IllegalValueException e) {
            assert false;
        }
    }

    @Test
    public void toModelType_nullColor_defaultColor() {
        UniqueId firstId = PET_ID_GENERATOR.next();
        UniqueId secondId = PET_ID_GENERATOR.next();

        JsonAdaptedPet pet = new JsonAdaptedPet(VALID_PET_NAME, VALID_SUPPLIER, null, VALID_COLOR_PATTERN,
                VALID_DATE_OF_BIRTH, VALID_SPECIES, VALID_WEIGHT, VALID_HEIGHT, VALID_VACCINATION_STATUS, VALID_PRICE,
                VALID_CERTIFICATES, firstId.toString());
        JsonAdaptedPet expected = new JsonAdaptedPet(VALID_PET_NAME, VALID_SUPPLIER, DEFAULT_COLOR, VALID_COLOR_PATTERN,
                VALID_DATE_OF_BIRTH, VALID_SPECIES, VALID_WEIGHT, VALID_HEIGHT, VALID_VACCINATION_STATUS, VALID_PRICE,
                VALID_CERTIFICATES, secondId.toString());
        try {
            assertTrue(pet.toModelType().isSamePet(expected.toModelType()));
        } catch (IllegalValueException e) {
            assert false;
        }
    }

    @Test
    public void toModelType_invalidColorPattern_defaultColorPattern() {
        UniqueId firstId = PET_ID_GENERATOR.next();
        UniqueId secondId = PET_ID_GENERATOR.next();

        JsonAdaptedPet pet = new JsonAdaptedPet(VALID_PET_NAME, VALID_SUPPLIER, VALID_COLOR, INVALID_COLOR_PATTERN,
                VALID_DATE_OF_BIRTH, VALID_SPECIES, VALID_WEIGHT, VALID_HEIGHT, VALID_VACCINATION_STATUS, VALID_PRICE,
                VALID_CERTIFICATES, firstId.toString());
        JsonAdaptedPet expected = new JsonAdaptedPet(VALID_PET_NAME, VALID_SUPPLIER, VALID_COLOR, DEFAULT_COLOR_PATTERN,
                VALID_DATE_OF_BIRTH, VALID_SPECIES, VALID_WEIGHT, VALID_HEIGHT, VALID_VACCINATION_STATUS, VALID_PRICE,
                VALID_CERTIFICATES, secondId.toString());
        try {
            assertTrue(pet.toModelType().isSamePet(expected.toModelType()));
        } catch (IllegalValueException e) {
            assert false;
        }

    }

    @Test
    public void toModelType_mullColorPattern_defaultColorPattern() {
        UniqueId firstId = PET_ID_GENERATOR.next();
        UniqueId secondId = PET_ID_GENERATOR.next();

        JsonAdaptedPet pet = new JsonAdaptedPet(VALID_PET_NAME, VALID_SUPPLIER, VALID_COLOR, null,
                VALID_DATE_OF_BIRTH, VALID_SPECIES, VALID_WEIGHT, VALID_HEIGHT, VALID_VACCINATION_STATUS, VALID_PRICE,
                VALID_CERTIFICATES, firstId.toString());
        JsonAdaptedPet expected = new JsonAdaptedPet(VALID_PET_NAME, VALID_SUPPLIER, VALID_COLOR, DEFAULT_COLOR_PATTERN,
                VALID_DATE_OF_BIRTH, VALID_SPECIES, VALID_WEIGHT, VALID_HEIGHT, VALID_VACCINATION_STATUS, VALID_PRICE,
                VALID_CERTIFICATES, secondId.toString());
        try {
            assertTrue(pet.toModelType().isSamePet(expected.toModelType()));
        } catch (IllegalValueException e) {
            assert false;
        }
    }

    @Test
    public void toModelType_invalidDateOfBirth_throwsIllegalValueException() {
        UniqueId id = PET_ID_GENERATOR.next();
        JsonAdaptedPet pet = new JsonAdaptedPet(VALID_PET_NAME, VALID_SUPPLIER, VALID_COLOR, VALID_COLOR_PATTERN,
                INVALID_DATE_OF_BIRTH, VALID_SPECIES, VALID_WEIGHT, VALID_HEIGHT, VALID_VACCINATION_STATUS, VALID_PRICE,
                VALID_CERTIFICATES, id.toString());
        String expectedMessage = DateOfBirth.MESSAGE_USAGE;
        assertThrows(IllegalValueException.class, expectedMessage, pet::toModelType);
    }

    @Test
    public void toModelType_nullDateOfBirth_throwsIllegalValueException() {
        UniqueId id = PET_ID_GENERATOR.next();
        JsonAdaptedPet pet = new JsonAdaptedPet(VALID_PET_NAME, VALID_SUPPLIER, VALID_COLOR, VALID_COLOR_PATTERN,
                 null, VALID_SPECIES, VALID_WEIGHT, VALID_HEIGHT, VALID_VACCINATION_STATUS, VALID_PRICE,
                VALID_CERTIFICATES, id.toString());
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, DateOfBirth.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, pet::toModelType);
    }

    @Test
    public void toModelType_invalidSpecies_defaultSpecies() {
        UniqueId firstId = PET_ID_GENERATOR.next();
        UniqueId secondId = PET_ID_GENERATOR.next();

        JsonAdaptedPet pet = new JsonAdaptedPet(VALID_PET_NAME, VALID_SUPPLIER, VALID_COLOR, VALID_COLOR_PATTERN,
                VALID_DATE_OF_BIRTH, INVALID_SPECIES, VALID_WEIGHT, VALID_HEIGHT, VALID_VACCINATION_STATUS, VALID_PRICE,
                VALID_CERTIFICATES, firstId.toString());
        JsonAdaptedPet expected = new JsonAdaptedPet(VALID_PET_NAME, VALID_SUPPLIER, VALID_COLOR, VALID_COLOR_PATTERN,
                VALID_DATE_OF_BIRTH, DEFAULT_SPECIES, VALID_WEIGHT, VALID_HEIGHT, VALID_VACCINATION_STATUS, VALID_PRICE,
                VALID_CERTIFICATES, secondId.toString());
        try {
            assertTrue(pet.toModelType().isSamePet(expected.toModelType()));
        } catch (IllegalValueException e) {
            assert false;
        }
    }

    @Test
    public void toModelType_nullSpecies_defaultSpecies() {
        UniqueId firstId = PET_ID_GENERATOR.next();
        UniqueId secondId = PET_ID_GENERATOR.next();

        JsonAdaptedPet pet = new JsonAdaptedPet(VALID_PET_NAME, VALID_SUPPLIER, VALID_COLOR, VALID_COLOR_PATTERN,
                VALID_DATE_OF_BIRTH, null, VALID_WEIGHT, VALID_HEIGHT, VALID_VACCINATION_STATUS, VALID_PRICE,
                VALID_CERTIFICATES, firstId.toString());
        JsonAdaptedPet expected = new JsonAdaptedPet(VALID_PET_NAME, VALID_SUPPLIER, VALID_COLOR, VALID_COLOR_PATTERN,
                VALID_DATE_OF_BIRTH, DEFAULT_SPECIES, VALID_WEIGHT, VALID_HEIGHT, VALID_VACCINATION_STATUS, VALID_PRICE,
                VALID_CERTIFICATES, secondId.toString());
        try {
            assertTrue(pet.toModelType().isSamePet(expected.toModelType()));
        } catch (IllegalValueException e) {
            assert false;
        }
    }

    @Test
    public void toModelType_invalidWeight_defaultWeight() {
        UniqueId firstId = PET_ID_GENERATOR.next();
        UniqueId secondId = PET_ID_GENERATOR.next();

        JsonAdaptedPet pet = new JsonAdaptedPet(VALID_PET_NAME, VALID_SUPPLIER, VALID_COLOR, VALID_COLOR_PATTERN,
                VALID_DATE_OF_BIRTH, VALID_SPECIES, INVALID_WEIGHT, VALID_HEIGHT, VALID_VACCINATION_STATUS, VALID_PRICE,
                VALID_CERTIFICATES, firstId.toString());
        JsonAdaptedPet expected = new JsonAdaptedPet(VALID_PET_NAME, VALID_SUPPLIER, VALID_COLOR, VALID_COLOR_PATTERN,
                VALID_DATE_OF_BIRTH, VALID_SPECIES, DEFAULT_WEIGHT, VALID_HEIGHT, VALID_VACCINATION_STATUS, VALID_PRICE,
                VALID_CERTIFICATES, secondId.toString());
        try {
            assertTrue(pet.toModelType().isSamePet(expected.toModelType()));
        } catch (IllegalValueException e) {
            assert false;
        }
    }

    @Test
    public void toModelType_nullWeight_throwsIllegalValueException() {
        UniqueId id = PET_ID_GENERATOR.next();

        JsonAdaptedPet pet = new JsonAdaptedPet(VALID_PET_NAME, VALID_SUPPLIER, VALID_COLOR, VALID_COLOR_PATTERN,
                VALID_DATE_OF_BIRTH, VALID_SPECIES, null, VALID_HEIGHT, VALID_VACCINATION_STATUS, VALID_PRICE,
                VALID_CERTIFICATES, id.toString());
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Weight.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, pet::toModelType);
    }

    @Test
    public void toModelType_invalidHeight_defaultHeight() {
        UniqueId firstId = PET_ID_GENERATOR.next();
        UniqueId secondId = PET_ID_GENERATOR.next();

        JsonAdaptedPet pet = new JsonAdaptedPet(VALID_PET_NAME, VALID_SUPPLIER, VALID_COLOR, VALID_COLOR_PATTERN,
                VALID_DATE_OF_BIRTH, VALID_SPECIES, VALID_WEIGHT, INVALID_HEIGHT, VALID_VACCINATION_STATUS, VALID_PRICE,
                VALID_CERTIFICATES, firstId.toString());
        JsonAdaptedPet expected = new JsonAdaptedPet(VALID_PET_NAME, VALID_SUPPLIER, VALID_COLOR, VALID_COLOR_PATTERN,
                VALID_DATE_OF_BIRTH, VALID_SPECIES, VALID_WEIGHT, DEFAULT_HEIGHT, VALID_VACCINATION_STATUS, VALID_PRICE,
                VALID_CERTIFICATES, secondId.toString());
        try {
            assertTrue(pet.toModelType().isSamePet(expected.toModelType()));
        } catch (IllegalValueException e) {
            assert false;
        }
    }

    @Test
    public void toModelType_nullHeight_throwsIllegalValueException() {
        UniqueId id = PET_ID_GENERATOR.next();

        JsonAdaptedPet pet = new JsonAdaptedPet(VALID_PET_NAME, VALID_SUPPLIER, VALID_COLOR, VALID_COLOR_PATTERN,
                VALID_DATE_OF_BIRTH, VALID_SPECIES, VALID_WEIGHT, null, VALID_VACCINATION_STATUS, VALID_PRICE,
                VALID_CERTIFICATES, id.toString());
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Height.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, pet::toModelType);
    }

    @Test
    public void toModelType_invalidPrice_defaultPrice() {
        UniqueId firstId = PET_ID_GENERATOR.next();
        UniqueId secondId = PET_ID_GENERATOR.next();

        JsonAdaptedPet pet = new JsonAdaptedPet(VALID_PET_NAME, VALID_SUPPLIER, VALID_COLOR, VALID_COLOR_PATTERN,
                VALID_DATE_OF_BIRTH, VALID_SPECIES, VALID_WEIGHT, VALID_HEIGHT, VALID_VACCINATION_STATUS, INVALID_PRICE,
                VALID_CERTIFICATES, firstId.toString());

        String expected = String.format(MISSING_FIELD_MESSAGE_FORMAT, Price.class.getSimpleName());

        assertThrows(IllegalValueException.class, expected, pet::toModelType);
    }

    @Test
    public void toModelType_nullPrice_throwsIllegalValueException() {
        UniqueId id = PET_ID_GENERATOR.next();
        JsonAdaptedPet pet = new JsonAdaptedPet(VALID_PET_NAME, VALID_SUPPLIER, VALID_COLOR, VALID_COLOR_PATTERN,
                VALID_DATE_OF_BIRTH, VALID_SPECIES, VALID_WEIGHT, VALID_HEIGHT, VALID_VACCINATION_STATUS, null,
                VALID_CERTIFICATES, id.toString());
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Price.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, pet::toModelType);
    }

    @Test
    public void toModelType_validCertificate_throwsIllegalValueException() {
        UniqueId id = PET_ID_GENERATOR.next();
        JsonAdaptedPet pet = new JsonAdaptedPet(VALID_PET_NAME, VALID_SUPPLIER, VALID_COLOR, VALID_COLOR_PATTERN,
                VALID_DATE_OF_BIRTH, VALID_SPECIES, VALID_WEIGHT, VALID_HEIGHT, VALID_VACCINATION_STATUS, VALID_PRICE,
                VALID_CERTIFICATES, id.toString());

        try {
            List<PetCertificate> certificates = pet.toModelType().getCertificates().stream()
                    .collect(Collectors.toList());
            List<PetCertificate> expected = VALID_CERTIFICATES.stream().map(x -> new PetCertificate(x))
                    .collect(Collectors.toList());
            assertEquals(certificates, expected);
        } catch (IllegalValueException e) {
            assert false;
        }
    }

    @Test
    public void toModelType_invalidUniqueId_throwsIllegalValueException() {
        JsonAdaptedPet pet = new JsonAdaptedPet(VALID_PET_NAME, VALID_SUPPLIER, VALID_COLOR, VALID_COLOR_PATTERN,
                VALID_DATE_OF_BIRTH, VALID_SPECIES, VALID_WEIGHT, VALID_HEIGHT, VALID_VACCINATION_STATUS, null,
                VALID_CERTIFICATES, INVALID_UNIQUE_ID);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Price.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, pet::toModelType);
    }


}
