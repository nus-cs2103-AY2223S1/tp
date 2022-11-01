package seedu.address.model.pet;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.core.index.UniqueId;
import seedu.address.commons.core.index.UniqueIdGenerator;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.order.Price;
import seedu.address.model.person.Supplier;

/**
 * Represents a pet.
 */
public class Pet {

    private static final UniqueIdGenerator PET_ID_GENERATOR = new UniqueIdGenerator();

    private final UniqueId id;
    private final PetName name;
    private Supplier supplier;
    private final Color color;
    private final ColorPattern colorPattern;
    private final DateOfBirth dateOfBirth;
    private final Species species;
    private final Weight weight;
    private final Height height;
    private final VaccinationStatus vaccinationStatus;
    private final Price price;
    private final Set<PetCertificate> certificates = new HashSet<>();

    /**
     * Constructs a pet completely.
     *
     * @param id The unique id of this pet.
     * @param name The name of this pet.
     * @param supplier The owner of this pet. Could be a Buyer or a Supplier.
     * @param color The color of this pet. Could be red.
     * @param colorPattern The color pattern. Could be stripped.
     * @param dateOfBirth The date of birth.
     * @param species Its species, for example, chihuahua.
     * @param weight Its weight.
     * @param height Its height (or length if it walks on fours).
     * @param vaccinationStatus Its vaccination status (vaccinated or not).
     * @param price Its price for sale.
     * @param certificates Its certificates, for example, noble blood.
     */
    public Pet(UniqueId id,
               PetName name,
               Supplier supplier,
               Color color,
               ColorPattern colorPattern,
               DateOfBirth dateOfBirth,
               Species species,
               Weight weight,
               Height height,
               VaccinationStatus vaccinationStatus,
               Price price,
               Set<PetCertificate> certificates) {
        requireAllNonNull(name, color, colorPattern, dateOfBirth, species, weight, height, vaccinationStatus, price);
        UniqueIdGenerator.addToStoredIdPet(id);
        this.id = id;
        this.name = name;
        this.supplier = supplier;
        this.species = species;
        this.color = color;
        this.colorPattern = colorPattern;
        this.dateOfBirth = dateOfBirth;
        this.weight = weight;
        this.height = height;
        this.vaccinationStatus = vaccinationStatus;
        this.price = price;
        this.certificates.addAll(certificates);
    }

    /**
     * Constructs a pet completely.
     *
     * @param name The name of this pet.
     * @param supplier The owner of this pet. Could be a Buyer or a Supplier.
     * @param color The color of this pet. Could be red.
     * @param colorPattern The color pattern. Could be stripped.
     * @param dateOfBirth The date of birth.
     * @param species Its species, for example, chihuahua.
     * @param weight Its weight.
     * @param height Its height (or length if it walks on fours).
     * @param vaccinationStatus Its vaccination status (vaccinated or not).
     * @param price Its price for sale.
     * @param certificates Its certificates, for example, noble blood.
     */
    public Pet(PetName name,
               Supplier supplier,
               Color color,
               ColorPattern colorPattern,
               DateOfBirth dateOfBirth,
               Species species,
               Weight weight,
               Height height,
               VaccinationStatus vaccinationStatus,
               Price price,
               Set<PetCertificate> certificates) {
        requireAllNonNull(name, color, colorPattern, dateOfBirth, species, weight, height, vaccinationStatus, price);
        UniqueId currId = PET_ID_GENERATOR.next();
        while (UniqueIdGenerator.storedIdPetContains(currId)) {
            currId = PET_ID_GENERATOR.next();
        }
        this.id = currId;
        this.name = name;
        this.supplier = supplier;
        this.species = species;
        this.color = color;
        this.colorPattern = colorPattern;
        this.dateOfBirth = dateOfBirth;
        this.weight = weight;
        this.height = height;
        this.vaccinationStatus = vaccinationStatus;
        this.price = price;
        if (certificates != null) {
            this.certificates.addAll(certificates);
        }
    }

    /**
     * Constructs a pet completely.
     *
     * @param name The name of this pet.
     * @param color The color of this pet. Could be red.
     * @param colorPattern The color pattern. Could be stripped.
     * @param dateOfBirthString The date of birth in string.
     * @param species Its species, for example, chihuahua.
     * @param weight Its weight.
     * @param height Its height (or length if it walks on fours).
     * @param price Its price for sale.
     * @param certificates Its certificates, for example, noble blood.
     */
    public Pet(PetName name,
               Color color,
               ColorPattern colorPattern,
               String dateOfBirthString,
               Species species,
               Weight weight,
               Height height,
               Price price,
               Set<PetCertificate> certificates) throws IllegalValueException {
        this(name,
                null,
                color,
                colorPattern,
                DateOfBirth.parseString(dateOfBirthString),
                species,
                weight,
                height,
                VaccinationStatus.defaultStatus(),
                price,
                certificates);

    }

    /**
     * Gets the age of a pet.
     * @return The age
     */
    public int getAge() {
        int currYear = Calendar.getInstance().get(Calendar.YEAR);
        int bornYear = this.dateOfBirth.getDate().getYear();
        return currYear - bornYear;
    }

    /**
     * Compares a pet with another pet in default way in terms of the age.
     * @param pet The other pet being compared.
     * @return The method returns 0 if the pet and the other pet has the same age.
     *      A value less than 0 is returned if the pet is younger than the other pet,
     *      and a value greater than 0 if the pet is older than the other pet.
     */
    public int compareTo(Pet pet) {
        return this.getAge() - pet.getAge();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName().toString())
                .append("; ")
                .append(getSpecies().toString())
                .append("; ")
                .append(getDateOfBirth().toString())
                .append("; ")
                .append(getWeight().toString())
                .append("; ")
                .append(getHeight().toString())
                .append("; ")
                .append(getColor().toString())
                .append("; ")
                .append(getColorPattern().toString())
                .append("; ")
                .append(getVaccinationStatus().toString());

        Set<PetCertificate> certificates = getCertificates();
        if (!certificates.isEmpty()) {
            builder.append("; Certificates: ");
            certificates.forEach(builder::append);
        }
        return builder.toString();
    }

    /**
     * Returns true if both pets have the same identity and data fields.
     * This defines a stronger notion of equality between two pets.
     *
     * @return true iff they have exactly the same fields.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Pet)) {
            return false;
        }

        return id.equals(((Pet) other).id);
    }

    /**
     * Returns a combination of hash codes of all instance objects.
     * Use this method for custom fields hashing instead of implementing your own
     *
     * @return a hash code representing this pet.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Returns true if both sets have the same name.
     * This defines a weaker notion of equality between two pets.
     *
     * @return true iff they have the same name.
     */
    public boolean isSamePet(Pet otherPet) {
        if (otherPet == this) {
            return true;
        }

        return otherPet != null
                && otherPet.getName().equals(getName())
                && otherPet.getPrice().equals(getPrice())
                && otherPet.getCertificates().equals(getCertificates())
                && otherPet.getColor().equals(getColor())
                && otherPet.getDateOfBirth().equals(getDateOfBirth())
                && otherPet.getSpecies().equals(getSpecies())
                && otherPet.getColorPattern().equals(getColorPattern())
                && otherPet.getVaccinationStatus().equals(getVaccinationStatus())
                && otherPet.getWeight().equals(getWeight())
                && otherPet.getHeight().equals(getHeight());
    }

    public PetName getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public ColorPattern getColorPattern() {
        return colorPattern;
    }

    public DateOfBirth getDateOfBirth() {
        return dateOfBirth;
    }

    public Height getHeight() {
        return height;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public Species getSpecies() {
        return species;
    }

    public Weight getWeight() {
        return weight;
    }

    public UniqueId getId() {
        return id;
    }

    public Price getPrice() {
        return price;
    }

    public VaccinationStatus getVaccinationStatus() {
        return vaccinationStatus;
    }

    /**
     * Returns an immutable pet certificate set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<PetCertificate> getCertificates() {
        return Collections.unmodifiableSet(certificates);
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    /**
     * Returns true if the two uniqueId matches.
     */
    public boolean hasId(UniqueId id) {
        return this.id.equals(id);
    }

    /**
     * Compares two pets based on the number of certificates.
     */
    public int compareCertificate(Pet pet) {
        return Integer.compare(this.certificates.size(), pet.certificates.size());
    }

}
