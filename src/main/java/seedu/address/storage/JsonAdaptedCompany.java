package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.client.Address;
import seedu.address.model.client.Company;
import seedu.address.model.client.Name;
import seedu.address.model.poc.Poc;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Company}.
 */
class JsonAdaptedCompany {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Company's %s field is missing!";
    private static final String MESSAGE_DUPLICATE_POC = "Company contains duplicate poc(s).";

    private final String name;
    private final String address;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final List<JsonAdaptedPoc> pocs = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedCompany} with the given company details.
     */
    @JsonCreator
    public JsonAdaptedCompany(@JsonProperty("name") String name, @JsonProperty("address") String address,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
            @JsonProperty("pocs") List<JsonAdaptedPoc> pocs) {
        this.name = name;
        this.pocs.addAll(pocs);
        this.address = address;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Company} into this class for Jackson use.
     */
    public JsonAdaptedCompany(Company source) {
        name = source.getName().fullName;
        address = source.getAddress().value;
        pocs.addAll(source.getPocList().stream().map(JsonAdaptedPoc::new).collect(Collectors.toList()));
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted company object into the model's {@code Company} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted company.
     */
    public Company toModelType() throws IllegalValueException {
        final List<Tag> companyTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            companyTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        final Set<Tag> modelTags = new HashSet<>(companyTags);

        Company company = new Company(modelName, modelAddress, modelTags);

        for (JsonAdaptedPoc jsonAdaptedPoc : pocs) {
            Poc poc = jsonAdaptedPoc.toModelType();
            if (company.hasPoc(poc)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_POC);
            }
            company.addPoc(poc);
        }
        return company;
    }

}
