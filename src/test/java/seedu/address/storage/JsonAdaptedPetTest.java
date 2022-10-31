package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.person.Supplier;
import seedu.address.model.pet.PetCertificate;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TypicalPets;

public class JsonAdaptedPetTest {

    private static final String INVALID_PET_NAME = "@shy";
    private static final JsonAdaptedSupplier INVALID_SUPPLIER = new JsonAdaptedSupplier(new PersonBuilder()
            .buildSupplier());
    private static final String INVALID_COLOR = "@quam&rine";
    private static final String INVALID_COLOR_PATTERN = "$tripe$";
    private static final String INVALID_DATE_OF_BIRTH = "2030-16-100";
    private static final String INVALID_SPECIES = "&xolotl!";
    private static final Double INVALID_WEIGHT = -999999999.99999999999;
    private static final Double INVALID_HEIGHT = -0.00000000099994844;
    private static final Double INVALID_PRICE = -8888.674;
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_PET_NAME = TypicalPets.DOJA.getName().toString();
    private static final Supplier VALID_SUPPLIER = TypicalPets.DOJA.getSupplier();
    private static final String VALID_COLOR = TypicalPets.DOJA.getColor().getValue();
    private static final String VALID_COLOR_PATTERN = TypicalPets.DOJA.getColorPattern().getValue();
    private static final String VALID_DATE_OF_BIRTH = TypicalPets.DOJA.getDateOfBirth().toString();
    private static final String VALID_SPECIES = TypicalPets.DOJA.getSpecies().getValue();
    private static final Double VALID_WEIGHT = TypicalPets.DOJA.getWeight().getValue();
    private static final Double VALID_HEIGHT = TypicalPets.DOJA.getHeight().getValue();
    private static final Double VALID_PRICE = TypicalPets.DOJA.getPrice().getPrice();
    private static final List<PetCertificate> VALID_CERTIFICATES = new ArrayList<>(TypicalPets.DOJA.getCertificates());

    /*
    @Test
    public void toModelType_validPetDetails_returnsPet() throws Exception {
        JsonAdaptedPet pet = new JsonAdaptedPet(TypicalPets.DOJA);
        assertEquals(TypicalPets.DOJA, pet.toModelType());
    }

     */

}
