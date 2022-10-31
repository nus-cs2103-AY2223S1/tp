package seedu.address.model.person;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import seedu.address.commons.core.index.UniqueId;

/**
 * Represents a supplier in the address book.
 */
public class Supplier extends Person {

    private final List<UniqueId> pets = new ArrayList<>();

    /**
     * Constructs a supplier object.
     *
     * @param personCategory By default, it should be PersonCategory.Supplier
     * @param name The name of this person.
     * @param phone The phone number in string.
     * @param email The email, which will be checked against regex.
     * @param address The address of this person, which will be checked against the regex.
     * @param pets The pets supplied by this supplier.
     */
    public Supplier(PersonCategory personCategory,
                    Name name,
                    Phone phone,
                    Email email,
                    Address address,
                    Collection<? extends UniqueId> pets) {
        super(PersonCategory.SUPPLIER, name, phone, email, address);
        if (pets != null) {
            this.pets.addAll(pets);
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
     * @param pets The pets supplied by this supplier.
     */
    public Supplier(Name name,
                    Phone phone,
                    Email email,
                    Address address,
                    Collection<UniqueId> pets) {
        super(PersonCategory.SUPPLIER, name, phone, email, address);
        if (pets != null) {
            this.pets.addAll(pets);
        }
    }

    public List<UniqueId> getPetIds() {
        return pets;
    }

    /**
     * Adds all pets in a collection to the list.
     *
     * @param pets New pets to be added
     */
    public void addPets(Collection<UniqueId> pets) {
        if (pets != null) {
            this.pets.addAll(pets);
        }
    }

    /**
     * Deletes a specific pet from the list based on index.
     *
     * @param index The index of the pet to be deleted
     */
    public void deletePet(int index) {
        pets.remove(index);
    }

    /**
     * Compares a supplier with another supplier in default way
     * in terms of the number of pets that are on sale that they have.
     * @param supplier The other buyer being compared.
     * @return The method returns 0 if the supplier and the other supplier has the same number of pets that are on sale.
     *      A value less than 0 is returned if the supplier has lesser pets that are on sale than the other supplier,
     *      and a value greater than 0 if the supplier has more pets that are on sale than the other supplier.
     */
    public int compareTo(Supplier supplier) {
        return this.pets.size() - supplier.pets.size();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof Supplier) {
            Supplier otherSupplier = (Supplier) other;
            return super.equals(otherSupplier) && pets.equals(otherSupplier.getPetIds());
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
                pets);
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
