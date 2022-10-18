package seedu.address.model.person;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.pet.Pet;
import seedu.address.model.tag.Tag;

/**
 * Represents a supplier in the address book.
 */
public class Supplier extends Person {

    private final List<Pet> petsOnSale = new ArrayList<>();

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
                    Collection<? extends Pet> petsOnSale) {
        super(PersonCategory.SUPPLIER, name, phone, email, address, tags);
        if (petsOnSale != null) {
            this.petsOnSale.addAll(petsOnSale);
        }
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
                    Collection<Pet> petsOnSale) {
        super(PersonCategory.SUPPLIER, name, phone, email, address, tags);
        if (petsOnSale != null) {
            this.petsOnSale.addAll(petsOnSale);
        }
    }

    public List<Pet> getPetsOnSale() {
        return petsOnSale;
    }

    /**
     * Adds all pets in a collection to the list.
     *
     * @param pets New pets to be added
     */
    public void addPets(Collection<Pet> pets) {
        if (pets != null) {
            petsOnSale.addAll(pets);
        }
    }

    public void deletePet(int index) {
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
