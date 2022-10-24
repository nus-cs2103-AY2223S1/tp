package seedu.address.model.person;

import java.util.Optional;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.tag.Tag;

/**
 * Represents a observable Person object when the application is running.
 */
public class TargetPerson {
    private static final String EMPTY_STRING = "";

    private Optional<Person> targetPerson;
    private SimpleStringProperty name;
    private SimpleStringProperty phone;
    private SimpleStringProperty address;
    private SimpleStringProperty email;
    private SimpleStringProperty remark;
    private ObservableSet<Tag> tags;

    /**
     * Initialises a TargetPerson object.
     */
    public TargetPerson() {
        targetPerson = Optional.empty();
        name = new SimpleStringProperty(EMPTY_STRING);
        phone = new SimpleStringProperty(EMPTY_STRING);
        address = new SimpleStringProperty(EMPTY_STRING);
        email = new SimpleStringProperty(EMPTY_STRING);
        remark = new SimpleStringProperty(EMPTY_STRING);
        tags = FXCollections.observableSet();
    }

    public SimpleStringProperty getNameProperty() {
        return name;
    }

    public SimpleStringProperty getPhoneProperty() {
        return phone;
    }

    public SimpleStringProperty getAddressProperty() {
        return address;
    }

    public SimpleStringProperty getEmailProperty() {
        return email;
    }

    public SimpleStringProperty getRemarkProperty() {
        return remark;
    }

    public ObservableSet<Tag> getTagsProperty() {
        return tags;
    }

    /**
     * Set the given person as target.
     * The person must exist in the address book.
     */
    public void set(Person person) {
        targetPerson = Optional.of(person);
        name.set(person.getName().fullName);
        phone.set(person.getPhone().value);
        address.set(person.getAddress().value);
        email.set(person.getEmail().value);
        remark.set(person.getRemark().value);
        tags.clear();
        tags.addAll(person.getTags());
    }

    /** Returns the target {@code Person} */
    public Person get() {
        return targetPerson.orElseThrow(PersonNotFoundException::new);
    }

    /**
     * Set target to none.
     */
    public void clear() {
        targetPerson = Optional.empty();
        name.set(EMPTY_STRING);
        phone.set(EMPTY_STRING);
        address.set(EMPTY_STRING);
        email.set(EMPTY_STRING);
        remark.set(EMPTY_STRING);
        tags.clear();
    }

    /** Returns {@code true} if person is target person, {@code false} otherwise */
    public boolean isSamePerson(Person person) {
        return targetPerson.equals(Optional.of(person));
    }

    /** Returns {@code true} if target person is present, {@code false} otherwise */
    public boolean isPresent() {
        return targetPerson.isPresent();
    }

}
