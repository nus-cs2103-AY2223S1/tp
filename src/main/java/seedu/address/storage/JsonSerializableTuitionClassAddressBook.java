package seedu.address.storage;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.tuitionclass.TuitionClass;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "tuitionclassaddressbook")
class JsonSerializableTuitionClassAddressBook {

    public static final String MESSAGE_DUPLICATE_TUITIONCLASS =
            "TuitionClass list contains duplicate tuitionClasses(s).";

    private final List<JsonAdaptedTuitionClass> tuitionClasses = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given tuitionClasses.
     */
    @JsonCreator
    public JsonSerializableTuitionClassAddressBook(@JsonProperty("tuitionClasses")
                                                       List<JsonAdaptedTuitionClass> tuitionClasses) {
        this.tuitionClasses.addAll(tuitionClasses);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableTuitionClassAddressBook(ReadOnlyAddressBook source) {
        tuitionClasses.addAll(source.getTuitionClassList().sorted((first, second) -> {
            HashMap<Integer, Object> a = first.getUniqueId();
            HashMap<Integer, Object> b = second.getUniqueId();
            Instant t = (Instant) a.get(0);
            int result = t.compareTo((Instant) b.get(0));
            if (result == 0) {
                return ((int) a.get(1)) - ((int) b.get(1));
            }
            return result;
        })
                .stream()
                .map(JsonAdaptedTuitionClass::new)
                .collect(Collectors.toList()));
    }

    /**
     * Returns the list of JsonAdaptedTuitionClasses.
     */
    public List<TuitionClass> getTuitionClassesList() throws IllegalValueException {
        List<TuitionClass> tuitionList = new ArrayList<>();
        for (JsonAdaptedTuitionClass jsonAdaptedTuitionClass : tuitionClasses) {
            TuitionClass tuitionClass = jsonAdaptedTuitionClass.toModelType();
            tuitionList.add(tuitionClass);
        }
        return tuitionList;
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedTuitionClass jsonAdaptedTuitionClass : tuitionClasses) {
            TuitionClass tuitionClass = jsonAdaptedTuitionClass.toModelType();
            if (addressBook.hasTuitionClass(tuitionClass)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TUITIONCLASS);
            }
            addressBook.addTuitionClass(tuitionClass);
        }
        return addressBook;
    }
}
