package seedu.modquik.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.modquik.commons.exceptions.IllegalValueException;
import seedu.modquik.model.commons.ModuleCode;
import seedu.modquik.model.commons.Venue;
import seedu.modquik.model.datetime.WeeklyTimeslot;
import seedu.modquik.model.tutorial.Tutorial;
import seedu.modquik.model.tutorial.TutorialName;
import seedu.modquik.storage.datetime.JsonAdaptedWeeklyTimeslot;

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
        module = source.getModule().moduleCode;
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
                    ModuleCode.class.getSimpleName()));
        }
        if (!ModuleCode.isValidModule(module)) {
            throw new IllegalValueException(ModuleCode.MESSAGE_CONSTRAINTS);
        }
        final ModuleCode modelModule = new ModuleCode(module);

        if (venue == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Venue.class.getSimpleName()));
        }
        if (!Venue.isValidVenue(venue)) {
            throw new IllegalValueException(Venue.MESSAGE_CONSTRAINTS);
        }
        final Venue modelVenue = new Venue(venue);

        final WeeklyTimeslot weeklyTimeslot = timeslot.toModelType();

        return new Tutorial(modelName, modelModule, modelVenue, weeklyTimeslot);
    }

}
