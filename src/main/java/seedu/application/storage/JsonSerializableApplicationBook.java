package seedu.application.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.application.commons.exceptions.IllegalValueException;
import seedu.application.model.ApplicationBook;
import seedu.application.model.ReadOnlyApplicationBook;
import seedu.application.model.application.Application;

/**
 * An Immutable ApplicationBook that is serializable to JSON format.
 */
@JsonRootName(value = "applicationbook")
class JsonSerializableApplicationBook {

    public static final String MESSAGE_DUPLICATE_APPLICATION = "Application list contains duplicate application(s).";
    public static final String MESSAGE_DUPLICATE_INTERVIEW = "Application list contains duplicate interview(s).";

    private final List<JsonAdaptedApplication> applications = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableApplicationBook} with the given applications.
     */
    @JsonCreator
    public JsonSerializableApplicationBook(@JsonProperty("applications") List<JsonAdaptedApplication> applications) {
        this.applications.addAll(applications);
    }

    /**
     * Converts a given {@code ReadOnlyApplicationBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableApplicationBook}.
     */
    public JsonSerializableApplicationBook(ReadOnlyApplicationBook source) {
        applications.addAll(source.getApplicationList().stream().map(JsonAdaptedApplication::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this application book into the model's {@code ApplicationBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ApplicationBook toModelType() throws IllegalValueException {
        ApplicationBook applicationBook = new ApplicationBook();
        for (JsonAdaptedApplication jsonAdaptedApplication : applications) {
            Application application = jsonAdaptedApplication.toModelType();
            if (applicationBook.hasApplication(application)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_APPLICATION);
            } else if (applicationBook.hasSameInterviewTime(application)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_INTERVIEW);
            }
            applicationBook.addApplication(application);
        }
        return applicationBook;
    }

}
