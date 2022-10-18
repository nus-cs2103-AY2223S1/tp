package seedu.address.model.pet;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.core.index.UniqueId;
import seedu.address.commons.core.index.UniqueIdGenerator;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.order.Price;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Represents a pet.
 */
public class Pet {

    private static final UniqueIdGenerator PET_ID_GENERATOR = new UniqueIdGenerator();

    private final UniqueId id;
    private final Name name;
    private final Person owner;
    private final Color color;
    private final ColorPattern colorPattern;
    private final DateOfBirth dateOfBirth;
    private final Species species;
    private final Weight weight;
    private final Height height;
    private final VaccinationStatus vaccinationStatus;
    private final Price price;
    private final Set<Tag> tags = new HashSet<>();
    private final Set<PetCertificate> certificates = new HashSet<>();

    /**
     * Constructs a pet completely.
     *
     * @param name The name of this pet.
     * @param owner The owner of this pet. Could be a Buyer or a Supplier.
     * @param color The color of this pet. Could be red.
     * @param colorPattern The color pattern. Could be stripped.
     * @param dateOfBirth The date of birth.
     * @param species Its species, for example, chihuahua.
     * @param weight Its weight.
     * @param height Its height (or length if it walks on fours).
     * @param vaccinationStatus Its vaccination status (vaccinated or not).
     * @param price Its price for sale.
     * @param tags Its tags that describe its traits.
     * @param certificates Its certificates, for example, noble blood.
     */
    public Pet(Name name,
               Person owner,
               Color color,
               ColorPattern colorPattern,
               DateOfBirth dateOfBirth,
               Species species,
               Weight weight,
               Height height,
               VaccinationStatus vaccinationStatus,
               Price price,
               Set<Tag> tags,
               Set<PetCertificate> certificates) {
        requireAllNonNull(name, color, colorPattern, dateOfBirth, species, weight, height, vaccinationStatus);
        this.id = PET_ID_GENERATOR.next();
        this.name = name;
        this.owner = owner;
        this.color = color;
        this.colorPattern = colorPattern;
        this.dateOfBirth = dateOfBirth;
        this.species = species;
        this.weight = weight;
        this.height = height;
        this.vaccinationStatus = vaccinationStatus;
        this.price = price;
        this.tags.addAll(tags);
        this.certificates.addAll(certificates);
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
     * @param tags Its tags that describe its traits.
     * @param certificates Its certificates, for example, noble blood.
     */
    public Pet(Name name,
               Color color,
               ColorPattern colorPattern,
               String dateOfBirthString,
               Species species,
               Weight weight,
               Height height,
               Price price,
               Set<Tag> tags,
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
                tags,
                certificates);
    }

    /**
     * Constructs a pet completely.
     *
     * @param name The name of this pet.
     * @param color The color of this pet. Could be red.
     * @param colorPattern The color pattern. Could be stripped.
     * @param dateOfBirth The date of birth.
     * @param species Its species, for example, chihuahua.
     * @param weight Its weight.
     * @param height Its height (or length if it walks on fours).
     * @param price Its price for sale.
     */
    public Pet(Name name,
               Color color,
               ColorPattern colorPattern,
               DateOfBirth dateOfBirth,
               Species species,
               Weight weight,
               Height height,
               Price price) {
        this(name,
                null,
                color,
                colorPattern,
                dateOfBirth,
                species,
                weight,
                height,
                VaccinationStatus.defaultStatus(),
                price,
                null,
                null);
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
     */
    public Pet(Name name,
               Color color,
               ColorPattern colorPattern,
               String dateOfBirthString,
               Species species,
               Weight weight,
               Height height,
               Price price) throws IllegalValueException {
        this(name,
                color,
                colorPattern,
                DateOfBirth.parseString(dateOfBirthString),
                species,
                weight,
                height,
                price);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Species: ")
                .append(getSpecies())
                .append("; Date Of Birth: ")
                .append(getDateOfBirth().toString())
                .append("; Weight: ")
                .append(getWeight())
                .append("; Height: ")
                .append(getHeight())
                .append("; Color: ")
                .append(getColor())
                .append("; Color pattern: ")
                .append(getColorPattern())
                .append("; ")
                .append(getVaccinationStatus());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        Set<PetCertificate> certificates = getCertificates();
        if (!certificates.isEmpty()) {
            builder.append("; Certificates: ");
            tags.forEach(builder::append);
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
        return Objects.hash(name, color, dateOfBirth, species, weight, height, vaccinationStatus, tags,
                certificates, owner);
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
                && otherPet.getName().equals(getName());
    }

    public Name getName() {
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

    public Person getOwner() {
        return owner;
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

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

}
