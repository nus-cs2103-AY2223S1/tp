package seedu.modquik.storage;

import static seedu.modquik.logic.commands.consultation.AddConsultationCommand.MESSAGE_DUPLICATE_CONSULTATION;
import static seedu.modquik.logic.commands.tutorial.AddTutorialCommand.MESSAGE_DUPLICATE_TUTORIAL;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.modquik.commons.exceptions.IllegalValueException;
import seedu.modquik.model.ModQuik;
import seedu.modquik.model.ReadOnlyModQuik;
import seedu.modquik.model.consultation.Consultation;
import seedu.modquik.model.reminder.Reminder;
import seedu.modquik.model.student.Student;
import seedu.modquik.model.tutorial.Tutorial;

/**
 * An Immutable ModQuik that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableModQuik {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_REMINDER = "Reminders list contains duplicate reminder(s).";

    private final List<JsonAdaptedStudent> persons = new ArrayList<>();
    private final List<JsonAdaptedTutorial> tutorials = new ArrayList<>();
    private final List<JsonAdaptedConsultation> consultations = new ArrayList<>();
    private final List<JsonAdaptedReminder> reminders = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableModQuik} with the given persons.
     */
    @JsonCreator
    public JsonSerializableModQuik(@JsonProperty("persons") List<JsonAdaptedStudent> persons,
                                   @JsonProperty("reminders") List<JsonAdaptedReminder> reminders,
                                   @JsonProperty("tutorials") List<JsonAdaptedTutorial> tutorials,
                                   @JsonProperty("consultations") List<JsonAdaptedConsultation> consultations) {
        this.persons.addAll(persons);
        this.tutorials.addAll(tutorials);
        this.consultations.addAll(consultations);
        this.reminders.addAll(reminders);
    }

    /**
     * Converts a given {@code ReadOnlyModQuik} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableModQuik}.
     */
    public JsonSerializableModQuik(ReadOnlyModQuik source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedStudent::new).collect(Collectors.toList()));
        reminders.addAll(source.getReminderList().stream().map(JsonAdaptedReminder::new).collect(Collectors.toList()));
        tutorials.addAll(source.getTutorialList().stream().map(JsonAdaptedTutorial::new).collect(Collectors.toList()));
        consultations.addAll(source.getConsultationList().stream().map(JsonAdaptedConsultation::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this modquik book into the model's {@code ModQuik} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ModQuik toModelType() throws IllegalValueException {
        ModQuik modQuik = new ModQuik();
        for (JsonAdaptedStudent jsonAdaptedStudent : persons) {
            Student student = jsonAdaptedStudent.toModelType();
            if (modQuik.hasPerson(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            modQuik.addPerson(student);
        }

        for (JsonAdaptedReminder jsonAdaptedReminder : reminders) {
            Reminder reminder = jsonAdaptedReminder.toModelType();
            if (modQuik.hasReminder(reminder)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_REMINDER);
            }
            modQuik.addReminder(reminder);
        }

        for (JsonAdaptedTutorial jsonAdaptedTutorial : tutorials) {
            Tutorial tutorial = jsonAdaptedTutorial.toModelType();
            if (modQuik.hasTutorial(tutorial)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TUTORIAL);
            }
            modQuik.addTutorial(tutorial);
        }

        for (JsonAdaptedConsultation jsonAdaptedConsultation : consultations) {
            Consultation consultation = jsonAdaptedConsultation.toModelType();
            if (modQuik.hasConsultation(consultation)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CONSULTATION);
            }
            modQuik.addConsultation(consultation);
        }

        return modQuik;
    }
}
