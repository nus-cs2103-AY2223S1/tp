package seedu.address.model.person;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.pet.Pet;
import seedu.address.model.tag.Tag;

public class Supplier extends Person {

    private final ArrayList<Pet> petsOnSale;

    public Supplier(PersonCategory personCategory, Name name, Phone phone, Email email, Address address, Set<Tag> tags,
                    ArrayList<Pet> petsOnSale) {
        super(personCategory, name, phone, email, address, tags);
        this.petsOnSale = petsOnSale;
    }

    public ArrayList<Pet> getPetsOnSale() {
        return petsOnSale;
    }

    public void addPet(Pet pet) {
        petsOnSale.add(pet);
    }

    public void deletePet() {
        // TODO: implement this method
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof Supplier) {
            Supplier otherSupplier = (Supplier) other;
            return super.equals(otherSupplier) && petsOnSale.equals(otherSupplier.getPetsOnSale());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPersonCategory(), getName(), getPhone(), getEmail(), getAddress(), getTags(), petsOnSale);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int i = 1;
        builder.append(super.toString()).append(System.lineSeparator()).append(System.lineSeparator())
                .append("Summary of pets on sale").append(System.lineSeparator());

        if (petsOnSale != null) {
            for (Pet pet : petsOnSale) {
                builder.append("======== Pet ").append(i).append(" ========").append(System.lineSeparator())
                        .append(pet.toString()).append(System.lineSeparator());
                i++;
            }
        }

        return builder.toString();
    }

}
