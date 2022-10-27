package seedu.address.testutil;

import seedu.address.model.FindMyIntern;
import seedu.address.model.internship.Internship;

/**
 * A utility class to help with building FindMyIntern objects.
 */
public class FindMyInternBuilder {

    private FindMyIntern findMyIntern;

    public FindMyInternBuilder() {
        findMyIntern = new FindMyIntern();
    }

    public FindMyInternBuilder(FindMyIntern findMyIntern) {
        this.findMyIntern = findMyIntern;
    }

    /**
     * Adds a new {@code Internship} to the {@code FindMyIntern} that we are building.
     */
    public FindMyInternBuilder withInternship(Internship internship) {
        findMyIntern.addInternship(internship);
        return this;
    }

    public FindMyIntern build() {
        return findMyIntern;
    }
}
