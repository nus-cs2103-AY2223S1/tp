package seedu.rc4hdb.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.rc4hdb.commons.exceptions.IllegalValueException;
import seedu.rc4hdb.model.ReadOnlyResidentBook;
import seedu.rc4hdb.model.ResidentBook;
import seedu.rc4hdb.model.resident.Resident;

/**
 * An Immutable ResidentBook that is serializable to JSON format.
 */
public class JsonSerializableResidentBook {

    public static final String MESSAGE_DUPLICATE_RESIDENT = "Residents list contains duplicate resident(s).";

    private final List<JsonAdaptedResident> residents = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableResidentBook} with the given residents.
     */
    @JsonCreator
    public JsonSerializableResidentBook(@JsonProperty("residents") List<JsonAdaptedResident> residents) {
        this.residents.addAll(residents);
    }

    /**
     * Converts a given {@code ReadOnlyResidentBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableResidentBook}.
     */
    public JsonSerializableResidentBook(ReadOnlyResidentBook source) {
        residents.addAll(source.getResidentList().stream().map(JsonAdaptedResident::new).collect(Collectors.toList()));
    }

    /**
     * Converts this resident book into the model's {@code ResidentBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ResidentBook toModelType() throws IllegalValueException {
        ResidentBook residentBook = new ResidentBook();
        for (JsonAdaptedResident jsonAdaptedResident : residents) {
            Resident resident = jsonAdaptedResident.toModelType();
            if (residentBook.hasResident(resident)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_RESIDENT);
            }
            residentBook.addResident(resident);
        }
        return residentBook;
    }

}
