package seedu.address.storage;

import org.junit.jupiter.api.Test;
import seedu.address.model.person.Supplier;
import seedu.address.model.pet.PetCertificate;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TypicalPets;
import seedu.address.testutil.TypicalSuppliers;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalSuppliers.BENSON;

public class JsonAdaptedPetTest {

    private final String INVALID_PET_NAME = "@shy";
    private final JsonAdaptedSupplier INVALID_SUPPLIER = new JsonAdaptedSupplier(new PersonBuilder().buildSupplier());
    private final String INVALID_COLOR = "@quam&rine";
    private final String INVALID_COLOR_PATTERN = "$tripe$";
    private final String INVALID_DATE_OF_BIRTH = "2030-16-100";
    private final String INVALID_SPECIES = "&xolotl!";
    private final Double INVALID_WEIGHT = -999999999.99999999999;
    private final Double INVALID_HEIGHT = -0.00000000099994844;
    private final Double INVALID_PRICE = -8888.674;
    private static final String INVALID_TAG = "#friend";

    private final String VALID_PET_NAME = TypicalPets.DOJA.getName().toString();
    private final Supplier VALID_SUPPLIER = TypicalPets.DOJA.getSupplier();
    private final String VALID_COLOR = TypicalPets.DOJA.getColor().getValue();
    private final String VALID_COLOR_PATTERN = TypicalPets.DOJA.getColorPattern().getValue();
    private final String VALID_DATE_OF_BIRTH = TypicalPets.DOJA.getDateOfBirth().toString();
    private final String VALID_SPECIES = TypicalPets.DOJA.getSpecies().getValue();
    private final Double VALID_WEIGHT = TypicalPets.DOJA.getWeight().getValue();
    private final Double VALID_HEIGHT = TypicalPets.DOJA.getHeight().getValue();
    private final Double VALID_PRICE = TypicalPets.DOJA.getPrice().getPrice();
    private final List<PetCertificate> VALID_CERTIFICATES = TypicalPets.DOJA.getCertificates().stream()
                                                                        .collect(Collectors.toList());
    private final List<Tag> VALID_TAGS = TypicalPets.DOJA.getTags().stream().collect(Collectors.toList());

    /*
    @Test
    public void toModelType_validPetDetails_returnsPet() throws Exception {
        JsonAdaptedPet pet = new JsonAdaptedPet(TypicalPets.DOJA);
        assertEquals(TypicalPets.DOJA, pet.toModelType());
    }

     */

}
