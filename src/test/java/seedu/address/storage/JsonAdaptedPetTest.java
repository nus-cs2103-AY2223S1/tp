package seedu.address.storage;

import org.junit.jupiter.api.Test;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Name;
import seedu.address.model.pet.Color;
import seedu.address.model.pet.DateOfBirth;
import seedu.address.model.pet.PetCertificate;
import seedu.address.testutil.TypicalPets;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class JsonAdaptedPetTest {
    public static String MISSING_FIELD_MESSAGE_FORMAT = "Pet's %s field is missing!";

    private static final String INVALID_PET_NAME = "@shy";
    private static final String INVALID_COLOR = "@quam&rine";
    private static final String INVALID_COLOR_PATTERN = "$tripe$";
    private static final String INVALID_DATE_OF_BIRTH = "2030-16-100";
    private static final String INVALID_SPECIES = "&xolotl!";
    private static final Double INVALID_WEIGHT = -999999999.99999999999;
    private static final Double INVALID_HEIGHT = -0.00000000099994844;
    private static final Double INVALID_PRICE = -8888.674;
    private static final String INVALID_UNIQUE_ID = "*&%%&*((;))";

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
    private static final String VALID_UNIQUE_ID = TypicalPets.DOJA.getId().getIdToString();


    @Test
    public void toModelType_validPetDetails_returnsPet() throws Exception {
        JsonAdaptedPet pet = new JsonAdaptedPet(TypicalPets.DOJA);
        assertEquals(TypicalPets.DOJA, pet.toModelType());
    }

    @Test
    public void toModelType_invalidPetName_throwsIllegalValueException() {
        JsonAdaptedPet pet = new JsonAdaptedPet(INVALID_PET_NAME, VALID_SUPPLIER, VALID_COLOR, VALID_COLOR_PATTERN,
                VALID_DATE_OF_BIRTH, VALID_SPECIES, VALID_WEIGHT, VALID_HEIGHT, VALID_VACCINATION_STATUS, VALID_PRICE,
                VALID_CERTIFICATES, VALID_UNIQUE_ID);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, pet::toModelType);
    }

    @Test
    public void toModelType_nullPetName_throwsIllegalValueException() {
        JsonAdaptedPet pet = new JsonAdaptedPet(null, VALID_SUPPLIER, VALID_COLOR, VALID_COLOR_PATTERN,
                VALID_DATE_OF_BIRTH, VALID_SPECIES, VALID_WEIGHT, VALID_HEIGHT, VALID_VACCINATION_STATUS, VALID_PRICE,
                VALID_CERTIFICATES, VALID_UNIQUE_ID);
        String expectedMessage = "Pet's Name field is missing!";
        assertThrows(IllegalValueException.class, expectedMessage, pet::toModelType);
    }

    @Test
    public void toModelType_invalidColor_defaultColor() {
        JsonAdaptedPet pet = new JsonAdaptedPet(VALID_PET_NAME, VALID_SUPPLIER, INVALID_COLOR, VALID_COLOR_PATTERN,
                VALID_DATE_OF_BIRTH, VALID_SPECIES, VALID_WEIGHT, VALID_HEIGHT, VALID_VACCINATION_STATUS, VALID_PRICE,
                VALID_CERTIFICATES, VALID_UNIQUE_ID);
        JsonAdaptedPet expected = new JsonAdaptedPet(VALID_PET_NAME, VALID_SUPPLIER, "", VALID_COLOR_PATTERN,
                VALID_DATE_OF_BIRTH, VALID_SPECIES, VALID_WEIGHT, VALID_HEIGHT, VALID_VACCINATION_STATUS, VALID_PRICE,
                VALID_CERTIFICATES, VALID_UNIQUE_ID);
        try {
            assertEquals(pet.toModelType(), expected.toModelType());
        } catch ( IllegalValueException e) {
            assert false;
        }
    }

    @Test
    public void toModelType_nullColor_throwsIllegalValueException() {
        JsonAdaptedPet pet = new JsonAdaptedPet(VALID_PET_NAME, VALID_SUPPLIER, null, VALID_COLOR_PATTERN,
                VALID_DATE_OF_BIRTH, VALID_SPECIES, VALID_WEIGHT, VALID_HEIGHT, VALID_VACCINATION_STATUS, VALID_PRICE,
                VALID_CERTIFICATES, VALID_UNIQUE_ID);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Color.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, pet::toModelType);
    }

    @Test
    public void toModelType_invalidDateOfBirth_throwsIllegalValueException() {
        JsonAdaptedPet pet = new JsonAdaptedPet(VALID_PET_NAME, VALID_SUPPLIER, VALID_COLOR, VALID_COLOR_PATTERN,
                INVALID_DATE_OF_BIRTH, VALID_SPECIES, VALID_WEIGHT, VALID_HEIGHT, VALID_VACCINATION_STATUS, VALID_PRICE,
                VALID_CERTIFICATES, VALID_UNIQUE_ID);
        String expectedMessage = DateOfBirth.MESSAGE_USAGE;
        assertThrows(IllegalValueException.class, expectedMessage, pet::toModelType);
    }

}
