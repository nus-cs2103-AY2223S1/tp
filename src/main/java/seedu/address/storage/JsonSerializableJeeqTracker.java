package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.JeeqTracker;
import seedu.address.model.ReadOnlyJeeqTracker;
import seedu.address.model.client.Client;

/**
 * An Immutable JeeqTracker that is serializable to JSON format.
 */
@JsonRootName(value = "jeeqtracker")
class JsonSerializableJeeqTracker {

    public static final String MESSAGE_DUPLICATE_COMPANY = "Companies list contains duplicate company(s).";

    private final List<JsonAdaptedCompany> companies = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableJeeqTracker} with the given companies.
     */
    @JsonCreator
    public JsonSerializableJeeqTracker(@JsonProperty("companies") List<JsonAdaptedCompany> companies) {
        this.companies.addAll(companies);
    }

    /**
     * Converts a given {@code ReadOnlyJeeqTracker} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableJeeqTracker}.
     */
    public JsonSerializableJeeqTracker(ReadOnlyJeeqTracker source) {
        companies.addAll(source.getCompanyList().stream().map(JsonAdaptedCompany::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code JeeqTracker} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public JeeqTracker toModelType() throws IllegalValueException {
        JeeqTracker jeeqTracker = new JeeqTracker();
        for (JsonAdaptedCompany jsonAdaptedCompany : companies) {
            Client client = jsonAdaptedCompany.toModelType();
            if (jeeqTracker.hasCompany(client)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_COMPANY);
            }
            jeeqTracker.addCompany(client);
        }
        return jeeqTracker;
    }

}
