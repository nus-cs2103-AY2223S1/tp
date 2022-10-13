package seedu.address.testutil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Name;
import seedu.address.model.pet.Color;
import seedu.address.model.pet.ColorPattern;
import seedu.address.model.pet.DateOfBirth;
import seedu.address.model.pet.Height;
import seedu.address.model.pet.Pet;
import seedu.address.model.pet.Species;
import seedu.address.model.pet.VaccinationStatus;
import seedu.address.model.pet.Weight;
import seedu.address.model.tag.Tag;

/**
 * A utility class containing a list of {@code Pets} objects to be used in tests.
 */
public class TypicalPets {
    public static final Pet DOJA = new Pet(new Name("Doja"), TypicalBuyers.ALICE,
            new Color("white"),
            new ColorPattern("white and brown"),
            new DateOfBirth(LocalDate.parse("2022-10-10")), new Species("cat"), new Weight(10.05),
            new Height(100.5), new VaccinationStatus(true), getTagSet("cat"),
            new HashSet<>());

    public static final Pet PLUM = new Pet(new Name("Plum"), TypicalBuyers.CARL,
            new Color("brown"),
            new ColorPattern("grey and brown"),
            new DateOfBirth(LocalDate.parse("2020-10-10")), new Species("cat"), new Weight(10.75),
            new Height(110.1), new VaccinationStatus(true), getTagSet("cat"),
            new HashSet<>());

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

    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
