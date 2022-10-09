package seedu.workbook.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.workbook.commons.exceptions.IllegalValueException;
import seedu.workbook.model.WorkBook;
import seedu.workbook.model.ReadOnlyWorkBook;
import seedu.workbook.model.person.Person;

/**
 * An Immutable WorkBook that is serializable to JSON format.
 */
@JsonRootName(value = "workbook")
class JsonSerializableWorkBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableWorkBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableWorkBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyWorkBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableWorkBook}.
     */
    public JsonSerializableWorkBook(ReadOnlyWorkBook source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this work book into the model's {@code WorkBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public WorkBook toModelType() throws IllegalValueException {
        WorkBook workBook = new WorkBook();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (workBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            workBook.addPerson(person);
        }
        return workBook;
    }

}
