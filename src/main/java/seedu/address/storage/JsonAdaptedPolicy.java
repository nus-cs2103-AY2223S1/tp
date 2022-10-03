package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.policy.Commission;
import seedu.address.model.policy.Company;
import seedu.address.model.policy.Coverage;
import seedu.address.model.policy.Policy;
import seedu.address.model.policy.Title;
import seedu.address.model.tag.Tag;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Jackson-friendly version of {@link Policy}.
 */
class JsonAdaptedPolicy {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Policy's %s field is missing!";

    private final String title;
    private final String company;
    private final String commission;
    private final List<JsonAdaptedCoverage> covered = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPolicy(@JsonProperty("title") String title, @JsonProperty("company") String company,
                             @JsonProperty("commission") String commission,
                             @JsonProperty("covered") List<JsonAdaptedCoverage> covered) {
        this.title = title;
        this.company = company;
        this.commission = commission;
        if (covered != null) {
            this.covered.addAll(covered);
        }
    }

    /**
     * Converts a given {@code Policy} into this class for Jackson use.
     */
    public JsonAdaptedPolicy(Policy source) {
        title = source.getTitle().fullTitle;
        company = source.getCompany().value;
        commission = source.getCommission().value;
        covered.addAll(source.getCoverages().stream()
                .map(JsonAdaptedCoverage::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Policy} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Policy toModelType() throws IllegalValueException {
        final List<Coverage> policyCoverage = new ArrayList<>();
        for (JsonAdaptedCoverage coverage : covered) {
            policyCoverage.add(coverage.toModelType());
        }

        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName()));
        }
        if (!Title.isValidTitle(title)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Title modelTitle = new Title(title);

        if (company == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Company.class.getSimpleName()));
        }
        if (!Company.isValidCompany(company)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Company modelCompany = new Company(company);

        if (commission == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Commission.class.getSimpleName()));
        }
        if (!Commission.isValidCommission(commission)) {
            throw new IllegalValueException(Commission.MESSAGE_CONSTRAINTS);
        }
        final Commission modelCommission = new Commission(commission);

        final Set<Coverage> modelCoverages = new HashSet<>(policyCoverage);
        return new Policy(modelTitle, modelCompany, modelCommission, modelCoverages);
    }

}
