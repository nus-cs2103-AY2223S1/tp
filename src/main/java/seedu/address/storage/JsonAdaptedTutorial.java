package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.datetime.WeeklyTimeslot;
import seedu.address.model.tutorial.Tutorial;
import seedu.address.model.tutorial.TutorialModule;
import seedu.address.model.tutorial.TutorialName;
import seedu.address.model.tutorial.TutorialVenue;
import seedu.address.storage.datetime.JsonAdaptedWeeklyTimeslot;

/**
 * Jackson-friendly version of {@link Tutorial}.
 */
class JsonAdaptedTutorial {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Tutorial's %s field is missing!";

    private final String name;
    private final String module;
    private final String venue;
    private final JsonAdaptedWeeklyTimeslot timeslot;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given tutorial details.
     */
    @JsonCreator
    public JsonAdaptedTutorial(@JsonProperty("name") String name, @JsonProperty("module") String module,
                               @JsonProperty("venue") String venue,
                               @JsonProperty("timeslot") JsonAdaptedWeeklyTimeslot timeslot) {
        this.name = name;
        this.module = module;
        this.venue = venue;
        this.timeslot = timeslot;
    }

    /**
     * Converts a given {@code Tutorial} into this class for Jackson use.
     */
    public JsonAdaptedTutorial(Tutorial source) {
        name = source.getName().fullName;
        module = source.getModule().moduleName;
        venue = source.getVenue().venue;
        timeslot = new JsonAdaptedWeeklyTimeslot(source.getTimeslot());
    }

    /**
     * Converts this Jackson-friendly adapted tutorial object into the model's {@code Tutorial} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tutorial.
     */
    public Tutorial toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TutorialName.class.getSimpleName()));
        }
        if (!TutorialName.isValidName(name)) {
            throw new IllegalValueException(TutorialName.MESSAGE_CONSTRAINTS);
        }
        final TutorialName modelName = new TutorialName(name);

        if (module == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TutorialModule.class.getSimpleName()));
        }
        if (!TutorialModule.isValidModule(module)) {
            throw new IllegalValueException(TutorialModule.MESSAGE_CONSTRAINTS);
        }
        final TutorialModule modelModule = new TutorialModule(module);

        if (venue == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TutorialVenue.class.getSimpleName()));
        }
        if (!TutorialVenue.isValidVenue(venue)) {
            throw new IllegalValueException(TutorialVenue.MESSAGE_CONSTRAINTS);
        }
        final TutorialVenue modelVenue = new TutorialVenue(venue);

        final WeeklyTimeslot weeklyTimeslot = timeslot.toModelType();

        return new Tutorial(modelName, modelModule, modelVenue, weeklyTimeslot);
    }

}
