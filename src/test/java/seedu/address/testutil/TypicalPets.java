package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.pet.Pet;

/**
 * A utility class containing a list of {@code Pets} objects to be used in tests.
 */
public class TypicalPets {
    public static final Pet DOJA = new PetBuilder().withName("Doja").withSupplier(TypicalSuppliers.ALICE)
            .withColor("white").withColorPattern("none").withDateOfBirth(2022, 10, 10)
            .withSpecies("cat").withWeight(10.05).withHeight(50).withVaccinationStatus(true).withPrice(100.00)
            .build();

    public static final Pet PLUM = new PetBuilder().withName("Plum").withSupplier(TypicalSuppliers.CARL)
            .withColor("brown").withColorPattern("striped").withDateOfBirth(2022, 1, 20)
            .withSpecies("cat").withWeight(12.00).withHeight(25.00).withVaccinationStatus(true).withPrice(600.00)
            .build();

    private TypicalPets() {}

    /**
     * Returns an {@code AddressBook} with all the typical pets.
     */
    public static AddressBook getTypicalPetsAddressBook() {
        AddressBook ab = new AddressBook();
        for (Pet pet : getTypicalPets()) {
            ab.addPet(pet);
        }
        return ab;
    }

    public static List<Pet> getTypicalPets() {
        return new ArrayList<>(Arrays.asList(DOJA, PLUM));
    }
}
