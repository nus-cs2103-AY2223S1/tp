package seedu.address.model.person;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import seedu.address.commons.core.index.UniqueId;
import seedu.address.model.pet.Pet;
import seedu.address.model.tag.Tag;

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
     * @param tags The tags of this person.
     * @param pets The pets supplied by this supplier.
     */
    public Supplier(PersonCategory personCategory,
                    Name name,
                    Phone phone,
                    Email email,
                    Address address,
                    Set<Tag> tags,
                    Collection<? extends UniqueId> pets) {
        super(PersonCategory.SUPPLIER, name, phone, email, address, tags);
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
     * @param tags The tags of this person.
     * @param pets The pets supplied by this supplier.
     */
    public Supplier(Name name,
                    Phone phone,
                    Email email,
                    Address address,
                    Set<Tag> tags,
                    Collection<UniqueId> pets) {
        super(PersonCategory.SUPPLIER, name, phone, email, address, tags);
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

     * Converts the supplier's pets from a {@code List} to a {@code ObservableList} and returns the result.
     *
     * @return An {@code ObservableList} instance containing all the supplier's pets on sale.
     */
    public ObservableList<Pet> getPetsAsObservableList() {
        // TODO: this should be FXCollections.observableList(getPetsOnSale()) but it will cause exception
        return FXCollections.observableList(getPetsOnSale() == null ? new ArrayList<>() : getPetsOnSale());
    }

    public void deletePet(int index) {
        // TODO: implement this method
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
        return this.petsOnSale.size() - supplier.petsOnSale.size();
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
                getTags(),
                pets);
    }

    @Override
    public String toString() {
        // TODO Change the string representation
        //        StringBuilder builder = new StringBuilder();
        //        int i = 1;
        //        builder.append(super.toString()).append(System.lineSeparator()).append(System.lineSeparator())
        //                .append("Summary of pets on sale").append(System.lineSeparator());
        //
        //        if (petsOnSale != null) {
        //            for (Pet pet : petsOnSale) {
        //                builder.append("======== Pet ").append(i).append(" ========").append(System.lineSeparator())
        //                        .append(pet.toString()).append(System.lineSeparator());
        //                i++;
        //            }
        //        }

        return super.toString();
    }

}
