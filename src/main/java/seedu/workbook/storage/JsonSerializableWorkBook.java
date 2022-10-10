package seedu.workbook.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.workbook.commons.exceptions.IllegalValueException;
import seedu.workbook.model.ReadOnlyWorkBook;
import seedu.workbook.model.WorkBook;
import seedu.workbook.model.internship.Internship;

/**
 * An Immutable WorkBook that is serializable to JSON format.
 */
@JsonRootName(value = "workbook")
class JsonSerializableWorkBook {

    public static final String MESSAGE_DUPLICATE_INTERNSHIP = "Internship list contains duplicate internship(s).";

    private final List<JsonAdaptedInternship> internships = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableWorkBook} with the given internships.
     */
    @JsonCreator
    public JsonSerializableWorkBook(@JsonProperty("internships") List<JsonAdaptedInternship> internships) {
        this.internships.addAll(internships);
    }

    /**
     * Converts a given {@code ReadOnlyWorkBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created
     *               {@code JsonSerializableWorkBook}.
     */
    public JsonSerializableWorkBook(ReadOnlyWorkBook source) {
        internships.addAll(
                source.getInternshipList().stream().map(JsonAdaptedInternship::new).collect(Collectors.toList()));
    }

    /**
     * Converts this work book into the model's {@code WorkBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public WorkBook toModelType() throws IllegalValueException {
        WorkBook workBook = new WorkBook();
        for (JsonAdaptedInternship jsonAdaptedInternship : internships) {
            Internship internship = jsonAdaptedInternship.toModelType();
            if (workBook.hasInternship(internship)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_INTERNSHIP);
            }
            workBook.addInternship(internship);
        }
        return workBook;
    }

}
