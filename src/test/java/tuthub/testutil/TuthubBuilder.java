package tuthub.testutil;

import tuthub.model.Tuthub;
import tuthub.model.tutor.Tutor;

/**
 * A utility class to help with building Tuthub objects.
 * Example usage: <br>
 *     {@code Tuthub ab = new TuthubBuilder().withTutor("John", "Doe").build();}
 */
public class TuthubBuilder {

    private Tuthub tuthub;

    public TuthubBuilder() {
        tuthub = new Tuthub();
    }

    public TuthubBuilder(Tuthub tuthub) {
        this.tuthub = tuthub;
    }

    /**
     * Adds a new {@code Tutor} to the {@code Tuthub} that we are building.
     */
    public TuthubBuilder withTutor(Tutor tutor) {
        tuthub.addTutor(tutor);
        return this;
    }

    public Tuthub build() {
        return tuthub;
    }
}
