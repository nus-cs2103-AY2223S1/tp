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
import friday.model.student.Consultation;
import friday.model.student.MasteryCheck;
import friday.model.student.Name;
import friday.model.student.Remark;
import friday.model.student.Student;
import friday.model.student.TelegramHandle;
import friday.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Student}.
 */
class JsonAdaptedStudent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String telegramHandle;
    private final LocalDate masteryCheck;
    private final LocalDate consultation;
    private final String remark;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedStudent} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedStudent(@JsonProperty("name") String name, @JsonProperty("telegramHandle") String telegramHandle,
                              @JsonProperty("consultation") LocalDate consultation,
                              @JsonProperty("masteryCheck") LocalDate masteryCheck,
                              @JsonProperty("remark") String remark,
                              @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.telegramHandle = telegramHandle;
        this.masteryCheck = masteryCheck;
        this.consultation = consultation;
        this.remark = remark;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedStudent(Student source) {
        name = source.getName().fullName;
        telegramHandle = source.getTelegramHandle().value;
        masteryCheck = source.getMasteryCheck().getValue();
        consultation = source.getConsultation().getValue();
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
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TelegramHandle.class.getSimpleName()));
        }
        if (!TelegramHandle.isValidOrEmpty(telegramHandle)) {
            throw new IllegalValueException(TelegramHandle.MESSAGE_CONSTRAINTS);
        }
        final TelegramHandle modelTelegramHandle;
        if (telegramHandle.equals("")) {
            modelTelegramHandle = TelegramHandle.EMPTY_TELEGRAMHANDLE;
        } else {
            modelTelegramHandle = new TelegramHandle(telegramHandle);
        }

        if (consultation == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Consultation.class.getSimpleName()));
        }
        if (!Consultation.isValidOrEmpty(consultation.toString())) {
            throw new IllegalValueException(Consultation.MESSAGE_CONSTRAINTS);
        }
        final Consultation modelConsultation;
        if (consultation.equals(LocalDate.of(0001, 01, 01))) {
            modelConsultation = Consultation.EMPTY_CONSULTATION;
        } else {
            modelConsultation = new Consultation(consultation);
        }

        if (masteryCheck == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    MasteryCheck.class.getSimpleName()));
        }
        if (!MasteryCheck.isValidOrEmpty(masteryCheck.toString())) {
            throw new IllegalValueException(MasteryCheck.MESSAGE_CONSTRAINTS);
        }
        final MasteryCheck modelMasteryCheck;
        if (masteryCheck.equals(LocalDate.of(0001, 01, 01))) {
            modelMasteryCheck = MasteryCheck.EMPTY_MASTERYCHECK;
        } else {
            modelMasteryCheck = new MasteryCheck(masteryCheck);
        }

        if (remark == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Remark.class.getSimpleName()));
        }
        final Remark modelRemark = new Remark(remark);

        final Set<Tag> modelTags = new HashSet<>(personTags);
        return new Student(modelName, modelTelegramHandle, modelConsultation, modelMasteryCheck, modelRemark,
                modelTags);
    }

}
