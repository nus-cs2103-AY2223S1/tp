package seedu.address.model.person;

import java.util.ArrayList;
import java.util.Set;

import seedu.address.model.pet.Pet;
import seedu.address.model.tag.Tag;

public class Supplier extends Person {

    ArrayList<Pet> petsOnSale = new ArrayList<>();

    /**
     *
     * @param name
     * @param phone
     * @param email
     * @param address
     * @param tags
     * @param petsOnSale
     */
    public Supplier(Name name, Phone phone, Email email, Address address, Set<Tag> tags, ArrayList<Pet> petsOnSale) {
        super(name, phone, email, address, tags);
        this.petsOnSale = petsOnSale;
    }

    /**
     *
     * @return
     */
    public ArrayList<Pet> getPetsOnSale() {
        return petsOnSale;
    }

    /**
     *
     * @param pet
     */
    public void addPet(Pet pet) {
        petsOnSale.add(pet);
    }

    /**
     *
     */
    public void deletePet() {
        // TODO: implement this method
    }
}
