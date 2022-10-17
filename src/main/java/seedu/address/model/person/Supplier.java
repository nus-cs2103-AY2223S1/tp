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

    /**
     * Compares a supplier with another supplier in default way
     * in terms of the number of pets that are on sale that they have.
     * @param supplier The other buyer being compared.
     * @return The method returns 0 if the supplier and the other supplier has the same number of pets that are on sale.
     * A value less than 0 is returned if the supplier has lesser pets that are on sale than the other supplier,
     * and a value greater than 0 if the supplier has more pets that are on sale than the other supplier.
     */
    public int compareTo(Supplier supplier) {
        return this.petsOnSale.size() - supplier.petsOnSale.size();
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
