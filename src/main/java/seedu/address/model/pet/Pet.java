package seedu.address.model.pet;

import java.security.cert.Certificate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

public class Pet {

    private final Name name;
    private final Person owner;
    private final Color color;
    private final DateOfBirth dateOfBirth;
    private final Species species;
    private final Weight weight;
    private final Height height;
    private final VaccinationStatus vaccinationStatus;
    private final Set<Tag> tags = new HashSet<>();
    private final Set<Certificate> certificates = new HashSet<>();

    public Pet(Name name,
               Person owner,
               Color color,
               DateOfBirth dateOfBirth,
               Species species,
               Weight weight,
               Height height,
               VaccinationStatus vaccinationStatus,
               Set<Tag> tags,
               Set<Certificate> certificates) {
        this.name = name;
        this.owner = owner;
        this.color = color;
        this.dateOfBirth = dateOfBirth;
        this.species = species;
        this.weight = weight;
        this.height = height;
        this.vaccinationStatus = vaccinationStatus;
        this.tags.addAll(tags);
        this.certificates.addAll(certificates);
    }

    public Pet(Name name,
               Person owner,
               Color color,
               String dateOfBirthString,
               Species species,
               Weight weight,
               Height height,
               Set<Tag> tags) throws IllegalValueException {
        this(name,
                null,
                color,
                DateOfBirth.parseString(dateOfBirthString),
                species,
                weight,
                height,
                VaccinationStatus.defaultStatus()
                tags);
    }

    public Pet(Name name,
               Color color,
               DateOfBirth dateOfBirth,
               Species species,
               Weight weight,
               Height height,
               Set<Tag> tags) throws IllegalValueException {
        this(name,
                null,
                color,
                dateOfBirth,
                species,
                weight,
                height,
                tags);
    }


    public Pet(Name name,
               Color color,
               String dateOfBirthString,
               Species species,
               Weight weight,
               Height height,
               Set<Tag> tags) throws IllegalValueException {
        this(name,
                null,
                color,
                dateOfBirthString,
                species,
                weight,
                height,
                tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; DateOfBirth: ")
                .append(getDateOfBirth())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

    public Name getName() {
        return name;
    }

    public Color getColor() {
        return color;
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

    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }
}
