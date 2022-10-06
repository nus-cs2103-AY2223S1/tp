package friday.storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import friday.commons.exceptions.IllegalValueException;
import friday.model.person.*;
import friday.model.person.Student;
import friday.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Student}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String telegramHandle;
    private final LocalDate masteryCheck;
    private final LocalDate consultation;
    private final String remark;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("telegramHandle") String telegramHandle,
                             @JsonProperty("consultation") LocalDate consultation,
                             @JsonProperty("masteryCheck") LocalDate MasteryCheck,
                             @JsonProperty("remark") String remark, @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.telegramHandle = telegramHandle;
        this.masteryCheck = MasteryCheck;
        this.consultation = consultation;
        this.remark = remark;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Student source) {
        name = source.getName().fullName;
        telegramHandle = source.getTelegramHandle().value;
        masteryCheck = source.getConsultation().value;
        consultation = source.getMasteryCheck().value;
        remark = source.getRemark().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Student toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (telegramHandle == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, TelegramHandle.class.getSimpleName()));
        }
        if (!TelegramHandle.isValidTelegramHandle(telegramHandle)) {
            throw new IllegalValueException(TelegramHandle.MESSAGE_CONSTRAINTS);
        }
        final TelegramHandle modelTelegramHandle = new TelegramHandle(telegramHandle);

        if (masteryCheck == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Consultation.class.getSimpleName()));
        }
        if (!Consultation.isValidConsultation(consultation.toString())) {
            throw new IllegalValueException(Consultation.MESSAGE_CONSTRAINTS);
        }
        final Consultation modelConsultation = new Consultation(masteryCheck);

        if (consultation == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, MasteryCheck.class.getSimpleName()));
        }
        if (!MasteryCheck.isValidMasteryCheck(masteryCheck.toString())) {
            throw new IllegalValueException(MasteryCheck.MESSAGE_CONSTRAINTS);
        }
        final MasteryCheck modelMasteryCheck = new MasteryCheck(consultation);

        if (remark == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Remark.class.getSimpleName()));
        }
        final Remark modelRemark = new Remark(remark);

        final Set<Tag> modelTags = new HashSet<>(personTags);
        return new Student(modelName, modelTelegramHandle, modelConsultation, modelMasteryCheck, modelRemark, modelTags);
    }

}
