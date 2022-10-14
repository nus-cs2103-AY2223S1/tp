package seedu.address.storage;

import static seedu.address.logic.commands.consultation.AddConsultationCommand.MESSAGE_DUPLICATE_CONSULTATION;
import static seedu.address.logic.commands.ta.AddTeachingAssistantCommand.MESSAGE_DUPLICATE_TA;
import static seedu.address.logic.commands.tutorial.AddTutorialCommand.MESSAGE_DUPLICATE_TUTORIAL;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.consultation.Consultation;
import seedu.address.model.student.Student;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.ta.TeachingAssistant;
import seedu.address.model.tutorial.Tutorial;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_REMINDER = "Reminders list contains duplicate reminder(s).";

    private final List<JsonAdaptedStudent> persons = new ArrayList<>();
    private final List<JsonAdaptedTutorial> tutorials = new ArrayList<>();
    private final List<JsonAdaptedConsultation> consultations = new ArrayList<>();
    private final List<JsonAdaptedTeachingAssistant> teachingAssistants = new ArrayList<>();
    private final List<JsonAdaptedReminder> reminders = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedStudent> persons,
                                       @JsonProperty("reminders") List<JsonAdaptedReminder> reminders,
                                       @JsonProperty("tutorials") List<JsonAdaptedTutorial> tutorials,
                                       @JsonProperty("consultations") List<JsonAdaptedConsultation> consultations,
                                       @JsonProperty("teachingAssistants")
                                                   List<JsonAdaptedTeachingAssistant> teachingAssistants) {
        this.persons.addAll(persons);
        this.tutorials.addAll(tutorials);
        this.consultations.addAll(consultations);
        this.teachingAssistants.addAll(teachingAssistants);
        this.reminders.addAll(reminders);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedStudent::new).collect(Collectors.toList()));
        reminders.addAll(source.getReminderList().stream().map(JsonAdaptedReminder::new).collect(Collectors.toList()));
        tutorials.addAll(source.getTutorialList().stream().map(JsonAdaptedTutorial::new).collect(Collectors.toList()));
        consultations.addAll(source.getConsultationList().stream().map(JsonAdaptedConsultation::new)
                .collect(Collectors.toList()));
        teachingAssistants.addAll(source.getTeachingAssistantList().stream().map(JsonAdaptedTeachingAssistant::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedStudent jsonAdaptedStudent : persons) {
            Student student = jsonAdaptedStudent.toModelType();
            if (addressBook.hasPerson(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPerson(student);
        }

        for (JsonAdaptedReminder jsonAdaptedReminder : reminders) {
            Reminder reminder = jsonAdaptedReminder.toModelType();
            if (addressBook.hasReminder(reminder)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_REMINDER);
            }
            addressBook.addReminder(reminder);
        }

        for (JsonAdaptedTutorial jsonAdaptedTutorial : tutorials) {
            Tutorial tutorial = jsonAdaptedTutorial.toModelType();
            if (addressBook.hasTutorial(tutorial)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TUTORIAL);
            }
            addressBook.addTutorial(tutorial);
        }

        for (JsonAdaptedConsultation jsonAdaptedConsultation : consultations) {
            Consultation consultation = jsonAdaptedConsultation.toModelType();
            if (addressBook.hasConsultation(consultation)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CONSULTATION);
            }
            addressBook.addConsulation(consultation);
        }

        for (JsonAdaptedTeachingAssistant jsonAdaptedTeachingAssistant : teachingAssistants) {
            TeachingAssistant teachingAssistant = jsonAdaptedTeachingAssistant.toModelType();
            if (addressBook.hasTeachingAssistant(teachingAssistant)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TA);
            }
            addressBook.addTeachingAssistant(teachingAssistant);
        }

        return addressBook;
    }
}
