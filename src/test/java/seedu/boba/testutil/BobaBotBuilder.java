package seedu.boba.testutil;

import seedu.boba.model.BobaBot;
import seedu.boba.model.customer.Customer;

/**
 * A utility class to help with building BobaBot objects.
 * Example usage: <br>
 *     {@code BobaBot ab = new BobaBotBuilder().withPerson("John", "Doe").build();}
 */
public class BobaBotBuilder {

    private BobaBot bobaBot;

    public BobaBotBuilder() {
        bobaBot = new BobaBot();
    }

    public BobaBotBuilder(BobaBot bobaBot) {
        this.bobaBot = bobaBot;
    }

    /**
     * Adds a new {@code Customer} to the {@code BobaBot} that we are building.
     */
    public BobaBotBuilder withPerson(Customer customer) {
        bobaBot.addPerson(customer);
        return this;
    }

    public BobaBot build() {
        return bobaBot;
    }
}
