package seedu.address.logic.commands.stubs;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import seedu.address.model.ReadOnlyTruthTable;
import seedu.address.model.TruthTable;
import seedu.address.model.person.Person;

/**
 * A Model stub that always accept the person being added.
 */
public class ModelStubAcceptingPersonAdded extends ModelStub {
    final ArrayList<Person> personsAdded = new ArrayList<>();

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return personsAdded.stream().anyMatch(person::isSamePerson);
    }

    @Override
    public void addPerson(Person person) {
        requireNonNull(person);
        personsAdded.add(person);
    }

    @Override
    public ReadOnlyTruthTable getTruthTable() {
        return TruthTable.createNewTruthTable();
    }
}