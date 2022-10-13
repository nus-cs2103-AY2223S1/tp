package seedu.address.model.person;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.pet.Pet;
import seedu.address.model.tag.Tag;

/**
 * Represents a supplier in the address book.
 */
public class Supplier extends Person {

    private final ArrayList<Pet> petsOnSale;

    /**
     * Constructs a supplier object.
     *
     * @param personCategory By default, it should be PersonCategory.Supplier
     * @param name The name of this person.
     * @param phone The phone number in string.
     * @param email The email, which will be checked against regex.
     * @param address The address of this person, which will be checked against the regex.
     * @param tags The tags of this person.
     * @param petsOnSale The pets supplied by this supplier.
     */
    public Supplier(PersonCategory personCategory,
                    Name name,
                    Phone phone,
                    Email email,
                    Address address,
                    Set<Tag> tags,
                    ArrayList<Pet> petsOnSale) {
        super(PersonCategory.SUPPLIER, name, phone, email, address, tags);
        this.petsOnSale = petsOnSale;
    }

    /**
     * Constructs a supplier object.
     * By default, it should be PersonCategory.Supplier
     *
     * @param name The name of this person.
     * @param phone The phone number in string.
     * @param email The email, which will be checked against regex.
     * @param address The address of this person, which will be checked against the regex.
     * @param tags The tags of this person.
     * @param petsOnSale The pets supplied by this supplier.
     */
    public Supplier(Name name,
                    Phone phone,
                    Email email,
                    Address address,
                    Set<Tag> tags,
                    ArrayList<Pet> petsOnSale) {
        super(PersonCategory.SUPPLIER, name, phone, email, address, tags);
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
        return Objects.hash(getPersonCategory(),
                getName(),
                getPhone(),
                getEmail(),
                getAddress(),
                getTags(),
                petsOnSale);
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
