package seedu.address.testutil;

import javafx.util.converter.LocalDateTimeStringConverter;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.person.*;
import seedu.address.model.pet.*;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.TypicalBuyers;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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

    private TypicalPets(){};

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
