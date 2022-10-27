package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.HomeVisit;

/**
 * Jackson-friendly version of {@link HomeVisit}.
 */
class JsonAdaptedHomeVisit {

    private final String homeVisit;

    /**
     * Constructs a {@code JsonAdaptedHomeVisit} with the given {@code HomeVisit}.
     */
    @JsonCreator
    public JsonAdaptedHomeVisit(String homeVisit) {
        this.homeVisit = homeVisit;
    }

    /**
     * Converts a given {@code HomeVisit} into this class for Jackson use.
     */
    public JsonAdaptedHomeVisit(HomeVisit source) {
        homeVisit = source.getString();
    }

    @JsonValue
    public String getHomeVisit() {
        return homeVisit;
    }

    /**
     * Converts this Jackson-friendly adapted homeVisit object into the model's
     * {@code HomeVisit} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in
     *                               the adapted home visit slot.
     */
    public HomeVisit toModelType() throws IllegalValueException {
        if (!HomeVisit.isValidHomeVisit(homeVisit)) {
            throw new IllegalValueException(HomeVisit.MESSAGE_CONSTRAINTS);
        }
        return new HomeVisit(homeVisit);
    }

}
