package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.core.index.UniqueId;
import seedu.address.commons.core.index.UniqueIdGenerator;
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
import seedu.address.model.pet.PetName;
import seedu.address.model.pet.Species;
import seedu.address.model.pet.VaccinationStatus;
import seedu.address.model.pet.Weight;

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
    private final List<String> certificates = new ArrayList<>();
    private final String uniqueId;

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
                          @JsonProperty("certificates") List<String> certificates,
                          @JsonProperty("uniqueId") String uniqueId) {
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
        if (certificates != null) {
            this.certificates.addAll(certificates);
        }
        this.uniqueId = uniqueId;
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
        certificates.addAll(source.getCertificates().stream()
                .map(PetCertificate::toString).collect(Collectors.toList()));
        uniqueId = source.getId().getIdToString();
    }

    /**
     * Converts this Jackson-friendly adapted Pet object into the model's {@code Pet} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted pet.
     */
    public Pet toModelType() throws IllegalValueException {

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!PetName.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final PetName modelName = new PetName(name);

        if (supplier == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Supplier.class.getSimpleName()));
        }
        final Supplier modelSupplier = supplier.toModelType();

        final Color modelColor = new Color(color);

        final ColorPattern modelColorPattern = new ColorPattern(colorPattern);

        if (dateOfBirth == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    DateOfBirth.class.getSimpleName()));
        }
        final DateOfBirth modelDateOfBirth = DateOfBirth.parseString(dateOfBirth);

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

        if (price == null || (!Price.isNotApplicablePrice(new Price(price)) && price < 0)) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Price.class.getSimpleName()));
        }
        final Price modelPrice = new Price(price);

        final Set<PetCertificate> modelCerts = new HashSet<>();
        if (certificates == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    PetCertificate.class.getSimpleName()));
        }
        for (String cert : certificates) {
            modelCerts.add(new PetCertificate(cert));
        }

        if (uniqueId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    UniqueId.class.getSimpleName()));
        }
        UniqueId modelUniqueId = new UniqueId(uniqueId);
        if (UniqueIdGenerator.storedIdPetContains(modelUniqueId)) {
            throw new IllegalValueException("Repeated unique id for pet");
        }

        return new Pet(modelUniqueId, modelName, modelSupplier, modelColor, modelColorPattern, modelDateOfBirth,
                modelSpecies, modelWeight, modelHeight, modelVax, modelPrice, modelCerts);
    }
}
