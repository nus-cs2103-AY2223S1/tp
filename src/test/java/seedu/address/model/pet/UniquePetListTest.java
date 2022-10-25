package seedu.address.model.pet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.model.pet.exceptions.DuplicatePetException;
import seedu.address.model.pet.exceptions.PetNotFoundException;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.TypicalPets;

public class UniquePetListTest {
    private final UniquePetList uniquePetList = new UniquePetList();

    @Test
    public void contains_nullPet_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePetList.contains(null));
    }

    @Test
    public void contains_petNotInList_returnsFalse() {
        assertFalse(uniquePetList.contains(TypicalPets.DOJA));
    }

    @Test
    public void contains_petInList_returnsTrue() {
        uniquePetList.add(TypicalPets.DOJA);
        assertTrue(uniquePetList.contains(TypicalPets.DOJA));
    }

    //    @Test
    //    public void contains_petWithSameIdentityFieldsInList_returnsTrue() {
    //        uniquePetList.add(TypicalPets.DOJA);
    //        Pet newDoja = new Pet(new Name("Doja"), TypicalBuyers.ALICE,
    //                new Color("white"),
    //                new ColorPattern("white and brown"),
    //                new DateOfBirth(LocalDate.parse("2022-10-10")), new Species("cat"), new Weight(10.05),
    //                new Height(100.5), new VaccinationStatus(true), getTagSet("cat"),
    //                new HashSet<>());
    //        assertTrue(uniquePetList.contains(newDoja));
    //    }

    @Test
    public void add_nullPet_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePetList.add(null));
    }

    @Test
    public void add_duplicatePet_throwsDuplicatePetException() {
        uniquePetList.add(TypicalPets.DOJA);
        assertThrows(DuplicatePetException.class, () -> uniquePetList.add(TypicalPets.DOJA));
    }

    @Test
    public void setPet_nullTargetPet_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePetList.setPet(null, TypicalPets.DOJA));
    }

    @Test
    public void setPet_nullEditedPet_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePetList.setPet(TypicalPets.DOJA, null));
    }

    @Test
    public void setPet_targetPetNotInList_throwsPetNotFoundException() {
        assertThrows(PetNotFoundException.class, () -> uniquePetList.setPet(TypicalPets.DOJA, TypicalPets.DOJA));
    }

    @Test
    public void setPet_editedPetIsSamePet_success() {
        uniquePetList.add(TypicalPets.DOJA);
        uniquePetList.setPet(TypicalPets.DOJA, TypicalPets.DOJA);
        UniquePetList expectedUniquePetList = new UniquePetList();
        expectedUniquePetList.add(TypicalPets.DOJA);
        assertEquals(expectedUniquePetList, uniquePetList);
    }

    @Test
    public void setPet_editedPetHasSameIdentity_success() {
        uniquePetList.add(TypicalPets.DOJA);
        uniquePetList.setPet(TypicalPets.DOJA, TypicalPets.PLUM);
        UniquePetList expectedUniquePetList = new UniquePetList();
        expectedUniquePetList.add(TypicalPets.PLUM);
        assertEquals(expectedUniquePetList, uniquePetList);
    }

    @Test
    public void setPet_editedPetHasDifferentIdentity_success() {
        uniquePetList.add(TypicalPets.DOJA);
        uniquePetList.setPet(TypicalPets.DOJA, TypicalPets.PLUM);
        UniquePetList expectedUniquePetList = new UniquePetList();
        expectedUniquePetList.add(TypicalPets.PLUM);
        assertEquals(expectedUniquePetList, uniquePetList);
    }

    @Test
    public void setPet_editedPetHasNonUniqueIdentity_throwsDuplicatePetException() {
        uniquePetList.add(TypicalPets.DOJA);
        uniquePetList.add(TypicalPets.PLUM);
        assertThrows(DuplicatePetException.class, () -> uniquePetList.setPet(TypicalPets.DOJA, TypicalPets.PLUM));
    }

    @Test
    public void remove_nullPet_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePetList.remove(null));
    }

    @Test
    public void remove_petDoesNotExist_throwsPetNotFoundException() {
        assertThrows(PetNotFoundException.class, () -> uniquePetList.remove(TypicalPets.DOJA));
    }

    @Test
    public void remove_existingPet_removesPet() {
        uniquePetList.add(TypicalPets.DOJA);
        uniquePetList.remove(TypicalPets.DOJA);
        UniquePetList expectedUniquePetList = new UniquePetList();
        assertEquals(expectedUniquePetList, uniquePetList);
    }

    @Test
    public void setPet_nullUniquePetList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePetList.setPets((UniquePetList) null));
    }

    @Test
    public void setPet_uniquePetList_replacesOwnListWithProvidedUniquePetList() {
        uniquePetList.add(TypicalPets.DOJA);
        UniquePetList expectedUniquePetList = new UniquePetList();
        expectedUniquePetList.add(TypicalPets.PLUM);
        uniquePetList.setPets(expectedUniquePetList);
        assertEquals(expectedUniquePetList, uniquePetList);
    }

    @Test
    public void setPet_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePetList.setPets((List<Pet>) null));
    }

    @Test
    public void setPets_list_replacesOwnListWithProvidedList() {
        uniquePetList.add(TypicalPets.DOJA);
        List<Pet> petList = Collections.singletonList(TypicalPets.PLUM);
        uniquePetList.setPets(petList);
        UniquePetList expectedUniquePetList = new UniquePetList();
        expectedUniquePetList.add(TypicalPets.PLUM);
        assertEquals(expectedUniquePetList, uniquePetList);
    }

    @Test
    public void setPets_listWithDuplicatePets_throwsDuplicatePetException() {
        List<Pet> listWithDuplicatePets = Arrays.asList(TypicalPets.DOJA, TypicalPets.DOJA);
        assertThrows(DuplicatePetException.class, () -> uniquePetList.setPets(listWithDuplicatePets));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniquePetList.asUnmodifiableObservableList().remove(0));
    }

    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }
}
