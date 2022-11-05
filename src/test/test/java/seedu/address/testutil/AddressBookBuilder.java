package seedu.realtime.testutil;

import seedu.realtime.model.realTime;
import seedu.realtime.model.person.Person;

/**
 * A utility class to help with building realTime objects.
 * Example usage: <br>
 *     {@code realTime ab = new RealTimeBuilder().withPerson("John", "Doe").build();}
 */
public class RealTimeBuilder {

    private realTime realTime;

    public RealTimeBuilder() {
        realTime = new realTime();
    }

    public RealTimeBuilder(realTime realTime) {
        this.realTime = realTime;
    }

    /**
     * Adds a new {@code Person} to the {@code realTime} that we are building.
     */
    public RealTimeBuilder withPerson(Person person) {
        realTime.addPerson(person);
        return this;
    }

    public realTime build() {
        return realTime;
    }
}
