package seedu.address.testutil;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.order.Price;
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
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Pet objects.
 */
public class PetBuilder {

    public static final String DEFAULT_NAME = "Fluffy";
    public static final String DEFAULT_COLOR = "White";
    public static final String DEFAULT_COLOR_PATTERN = "None";
    public static final LocalDate DEFAULT_DATE_OF_BIRTH = LocalDate.of(2021, 12, 20);
    public static final String DEFAULT_SPECIES = "Corgi";
    public static final double DEFAULT_WEIGHT = 12.00;
    public static final double DEFAULT_HEIGHT = 40.00;
    public static final boolean DEFAULT_VACCINATION_STATUS = true;
    public static final double DEFAULT_PRICE = 600.00;

    private PetName name;
    private Supplier supplier;
    private Color color;
    private ColorPattern colorPattern;
    private DateOfBirth dateOfBirth;
    private Species species;
    private Weight weight;
    private Height height;
    private VaccinationStatus vaccinationStatus;
    private Price price;
    private Set<PetCertificate> certificates;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PetBuilder() {
        name = new PetName(DEFAULT_NAME);
        supplier = TypicalSuppliers.BENSON;
        color = new Color(DEFAULT_COLOR);
        colorPattern = new ColorPattern(DEFAULT_COLOR_PATTERN);
        dateOfBirth = new DateOfBirth(DEFAULT_DATE_OF_BIRTH);
        species = new Species(DEFAULT_SPECIES);
        weight = new Weight(DEFAULT_WEIGHT);
        height = new Height(DEFAULT_HEIGHT);
        vaccinationStatus = new VaccinationStatus(DEFAULT_VACCINATION_STATUS);
        price = new Price(DEFAULT_PRICE);
        certificates = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PetBuilder(Pet petToCopy) {
        name = petToCopy.getName();
        supplier = petToCopy.getSupplier();
        color = petToCopy.getColor();
        colorPattern = petToCopy.getColorPattern();
        dateOfBirth = petToCopy.getDateOfBirth();
        species = petToCopy.getSpecies();
        weight = petToCopy.getWeight();
        height = petToCopy.getHeight();
        vaccinationStatus = petToCopy.getVaccinationStatus();
        price = petToCopy.getPrice();
        certificates = new HashSet<>(petToCopy.getCertificates());
    }


    /**
     * Sets the {@code Name} of the {@code Pet} that we are building.
     */
    public PetBuilder withName(String name) {
        this.name = new PetName(name);
        return this;
    }

    /**
     * Sets the {@code Supplier} of the {@code Pet} that we are building.
     */
    public PetBuilder withSupplier(Supplier supplier) {
        this.supplier = supplier;
        return this;
    }

    /**
     * Sets the {@code Color} of the {@code Pet} that we are building.
     */
    public PetBuilder withColor(String color) {
        this.color = new Color(color);
        return this;
    }

    /**
     * Sets the {@code ColorPattern} of the {@code Pet} that we are building.
     */
    public PetBuilder withColorPattern(String colorPattern) {
        this.colorPattern = new ColorPattern(colorPattern);
        return this;
    }

    /**
     * Sets the {@code DateOfBirth} of the {@code Pet} that we are building.
     */
    public PetBuilder withDateOfBirth(int year, int month, int day) {
        this.dateOfBirth = new DateOfBirth(LocalDate.of(year, month, day));
        return this;
    }

    /**
     * Sets the {@code Species} of the {@code Pet} that we are building.
     */
    public PetBuilder withSpecies(String species) {
        this.species = new Species(species);
        return this;
    }

    /**
     * Sets the {@code Weight} of the {@code Pet} that we are building.
     */
    public PetBuilder withWeight(double weight) {
        this.weight = new Weight(weight);
        return this;
    }

    /**
     * Sets the {@code Height} of the {@code Pet} that we are building.
     */
    public PetBuilder withHeight(double height) {
        this.height = new Height(height);
        return this;
    }

    /**
     * Sets the {@code Species} of the {@code Pet} that we are building.
     */
    public PetBuilder withVaccinationStatus(boolean vaccinationStatus) {
        this.vaccinationStatus = new VaccinationStatus(vaccinationStatus);
        return this;
    }

    /**
     * Sets the {@code Species} of the {@code Pet} that we are building.
     */
    public PetBuilder withPrice(double price) {
        this.price = new Price(price);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Pet} that we are building.
     */
    public PetBuilder withCertificates(String ... certificates) {
        this.certificates = SampleDataUtil.getCertificateSet(certificates);
        return this;
    }

    /**
     * Builds {@code Pet} with the specified Pet attributes.
     * @return Created Pet object.
     */
    public Pet build() {
        return new Pet(name, supplier, color, colorPattern, dateOfBirth, species, weight, height, vaccinationStatus,
                price, certificates);
    }
}

