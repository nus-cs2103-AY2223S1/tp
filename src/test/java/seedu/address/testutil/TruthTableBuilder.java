package seedu.address.testutil;

import seedu.address.model.TruthTable;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building TruthTable objects.
 * Example usage: <br>
 * {@code TruthTable ab = new TruthTableBuilder().withPerson("John", "Doe").build();}
 */
public class TruthTableBuilder {

    private TruthTable truthTable;

    public TruthTableBuilder() {
        truthTable = TruthTable.createNewTruthTable();
    }

    public TruthTableBuilder(TruthTable truthTable) {
        this.truthTable = truthTable;
    }

    /**
     * Adds a new {@code Person} to the {@code TruthTable} that we are building.
     */
    public TruthTableBuilder withPerson(Person person) {
        truthTable.addPerson(person);
        return this;
    }

    public TruthTable build() {
        return truthTable;
    }
}
