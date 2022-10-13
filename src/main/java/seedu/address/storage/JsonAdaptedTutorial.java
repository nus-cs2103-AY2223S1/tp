package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tutorial.Tutorial;
import seedu.address.model.tutorial.TutorialDay;
import seedu.address.model.tutorial.TutorialModule;
import seedu.address.model.tutorial.TutorialName;
import seedu.address.model.tutorial.TutorialTimeslot;
import seedu.address.model.tutorial.TutorialVenue;

/**
 * Jackson-friendly version of {@link Tutorial}.
 */
class JsonAdaptedTutorial {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Tutorial's %s field is missing!";

    private final String name;
    private final String module;
    private final String venue;
    private final String timeslot;
    private final String day;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given tutorial details.
     */
    @JsonCreator
    public JsonAdaptedTutorial(@JsonProperty("name") String name, @JsonProperty("module") String module,
                               @JsonProperty("venue") String venue, @JsonProperty("timeslot") String timeslot,
                               @JsonProperty("day") String day) {
        this.name = name;
        this.module = module;
        this.venue = venue;
        this.timeslot = timeslot;
        this.day = day;
    }

    /**
     * Converts a given {@code Tutorial} into this class for Jackson use.
     */
    public JsonAdaptedTutorial(Tutorial source) {
        name = source.getName().fullName;
        module = source.getModule().moduleName;
        venue = source.getVenue().venue;
        timeslot = source.getTimeslot().timeslot;
        day = source.getDay().toIntString();
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

        if (timeslot == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TutorialTimeslot.class.getSimpleName()));
        }
        if (!TutorialTimeslot.isValidTimeslot(timeslot)) {
            throw new IllegalValueException(TutorialTimeslot.MESSAGE_CONSTRAINTS);
        }
        final TutorialTimeslot modelTimeslot = new TutorialTimeslot(timeslot);

        if (!TutorialDay.isValidDay(day)) {
            throw new IllegalValueException(TutorialDay.MESSAGE_CONSTRAINTS);
        }
        final TutorialDay modelDay = new TutorialDay(day);

        return new Tutorial(modelName, modelModule, modelVenue, modelTimeslot, modelDay);
    }

}
