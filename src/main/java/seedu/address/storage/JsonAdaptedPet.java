package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.order.Price;
import seedu.address.model.person.Name;
import seedu.address.model.person.Supplier;
import seedu.address.model.pet.Color;
import seedu.address.model.pet.ColorPattern;
import seedu.address.model.pet.DateOfBirth;
import seedu.address.model.pet.Height;
import seedu.address.model.pet.Pet;
import seedu.address.model.pet.PetCertificate;
import seedu.address.model.pet.Species;
import seedu.address.model.pet.VaccinationStatus;
import seedu.address.model.pet.Weight;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Pet}.
 */
public class JsonAdaptedPet {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Pet's %s field is missing!";

    private final String name;
    private final JsonAdaptedSupplier supplier;
    private final String color;
    private final String colorPattern;
    private final String dateOfBirth;
    private final String species;
    private final Double weight;
    private final Double height;
    private final boolean vaccinationStatus;
    private final Double price;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final List<String> certificates = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPet} with the given buyer details.
     */
    @JsonCreator
    public JsonAdaptedPet(@JsonProperty("name") String name,
                          @JsonProperty("supplier") JsonAdaptedSupplier supplier,
                          @JsonProperty("color") String color,
                          @JsonProperty("colorPattern") String colorPattern,
                          @JsonProperty("dateOfBirth") String dateOfBirth,
                          @JsonProperty("species") String species,
                          @JsonProperty("weight") Double weight,
                          @JsonProperty("height") Double height,
                          @JsonProperty("vaccinationStatus") boolean vaccinationStatus,
                          @JsonProperty("price") Double price,
                          @JsonProperty("tags") List<JsonAdaptedTag> tagged,
                          @JsonProperty("certificates") List<String> certificates) {
        this.name = name;
        this.supplier = supplier;
        this.color = color;
        this.colorPattern = colorPattern;
        this.dateOfBirth = dateOfBirth;
        this.species = species;
        this.weight = weight;
        this.height = height;
        this.vaccinationStatus = vaccinationStatus;
        this.price = price;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        if (certificates != null) {
            this.certificates.addAll(certificates);
        }
    }

    /**
     * Converts a given {@code Pet} into this class for Jackson use.
     */
    public JsonAdaptedPet(Pet source) {
        name = source.getName().toString();
        supplier = new JsonAdaptedSupplier(source.getSupplier());
        color = source.getColor().getValue();
        colorPattern = source.getColorPattern().getValue();
        dateOfBirth = source.getDateOfBirth().getPreferredDateInString();
        species = source.getSpecies().getValue();
        weight = source.getWeight().getValue();
        height = source.getHeight().getValue();
        vaccinationStatus = source.getVaccinationStatus().getVaccinationStatus();
        price = source.getPrice().getPrice();
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        certificates.addAll(source.getCertificates().stream()
                .map(PetCertificate::toString).collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted Pet object into the model's {@code Pet} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted pet.
     */
    public Pet toModelType() throws IllegalValueException {
        final List<Tag> petTags = new ArrayList<>();

        for (JsonAdaptedTag tag : tagged) {
            petTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (supplier == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Supplier.class.getSimpleName()));
        }
        final Supplier modelSupplier = supplier.toModelType();

        if (color == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Color.class.getSimpleName()));
        }
        final Color modelColor = new Color(color);

        if (colorPattern == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ColorPattern.class.getSimpleName()));
        }
        final ColorPattern modelColorPattern = new ColorPattern(colorPattern);

        if (dateOfBirth == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    DateOfBirth.class.getSimpleName()));
        }
        final DateOfBirth modelDateOfBirth = DateOfBirth.parseString(dateOfBirth);

        if (species == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Species.class.getSimpleName()));
        }
        final Species modelSpecies = new Species(species);

        if (weight == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Weight.class.getSimpleName()));
        }
        final Weight modelWeight = new Weight(weight);

        if (height == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Height.class.getSimpleName()));
        }
        final Height modelHeight = new Height(height);

        final VaccinationStatus modelVax = new VaccinationStatus(vaccinationStatus);
        final Price modelPrice = new Price(price);
        final Set<Tag> modelTags = new HashSet<>();
        if (tagged == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Tag.class.getSimpleName()));
        }
        for (JsonAdaptedTag jsonTag : tagged) {
            modelTags.add(jsonTag.toModelType());
        }

        final Set<PetCertificate> modelCerts = new HashSet<>();
        if (certificates == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    PetCertificate.class.getSimpleName()));
        }
        for (String cert : certificates) {
            modelCerts.add(new PetCertificate(cert));
        }

        return new Pet(modelName, modelSupplier, modelColor, modelColorPattern, modelDateOfBirth, modelSpecies,
                modelWeight, modelHeight, modelVax, modelPrice, modelTags, modelCerts);
    }
}
