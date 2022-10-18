package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.company.Company;
import seedu.address.model.company.CompanyAddress;
import seedu.address.model.company.CompanyName;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Company}.
 */
class JsonAdaptedCompany {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Company's %s field is missing!";

    private final String name;
    private final String address;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedCompany} with the given company details.
     */
    @JsonCreator
    public JsonAdaptedCompany(@JsonProperty("name") String name, @JsonProperty("address") String address,
                              @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
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
        final List<Tag> clientTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            clientTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    CompanyName.class.getSimpleName()));
        }
        if (!CompanyName.isValidName(name)) {
            throw new IllegalValueException(CompanyName.MESSAGE_CONSTRAINTS);
        }
        final CompanyName modelName = new CompanyName(name);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    CompanyAddress.class.getSimpleName()));
        }
        if (!CompanyAddress.isValidCompanyAddress(address)) {
            throw new IllegalValueException(CompanyAddress.MESSAGE_CONSTRAINTS);
        }
        final CompanyAddress modelAddress = new CompanyAddress(address);

        final Set<Tag> modelTags = new HashSet<>(clientTags);
        return new Company(modelName, modelAddress, modelTags);
    }

}
