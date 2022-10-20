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

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Student's %s field is missing!";

    private final String name;
    private final String telegramHandle;
    private final LocalDate masteryCheck;
    private boolean masteryCheckIsDone;
    private final LocalDate consultation;
    private final String remark;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedStudent} with the given student details.
     */
    @JsonCreator

    public JsonAdaptedStudent(@JsonProperty("name") String name, @JsonProperty("telegramHandle") String telegramHandle,
                             @JsonProperty("consultation") LocalDate consultation,
                             @JsonProperty("masteryCheck") LocalDate masteryCheck,

                             @JsonProperty("masteryCheckIsDone") boolean masteryCheckIsDone,
                             @JsonProperty("remark") String remark,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {

        this.name = name;
        this.telegramHandle = telegramHandle;
        this.masteryCheck = masteryCheck;
        this.masteryCheckIsDone = masteryCheckIsDone;
        this.consultation = consultation;
        this.remark = remark;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Student} into this class for Jackson use.
     */
    public JsonAdaptedStudent(Student source) {
        name = source.getName().fullName;
        telegramHandle = source.getTelegramHandle().value;
        masteryCheck = source.getMasteryCheck().getValue();
        masteryCheckIsDone = source.getMasteryCheck().getIsPassed();
        consultation = source.getConsultation().getValue();
        remark = source.getRemark().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted student object into the model's {@code Student} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted student.
     */
    public Student toModelType() throws IllegalValueException {
        final List<Tag> studentTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            studentTags.add(tag.toModelType());
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
        if (!TelegramHandle.isValidOrEmptyJson(telegramHandle)) {
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
        if (!Consultation.isValidOrEmptyJson(consultation.toString())) {
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
        if (!MasteryCheck.isValidOrEmptyJson(masteryCheck.toString())) {
            throw new IllegalValueException(MasteryCheck.MESSAGE_CONSTRAINTS);
        }
        final MasteryCheck modelMasteryCheck;
        if (masteryCheck.equals(LocalDate.of(0001, 01, 01))) {
            modelMasteryCheck = MasteryCheck.EMPTY_MASTERYCHECK;
        } else {
            modelMasteryCheck = new MasteryCheck(masteryCheck, masteryCheckIsDone);
        }

        if (remark == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Remark.class.getSimpleName()));
        }
        final Remark modelRemark = new Remark(remark);

        final Set<Tag> modelTags = new HashSet<>(studentTags);
        return new Student(modelName, modelTelegramHandle, modelConsultation, modelMasteryCheck, modelRemark,
                modelTags);
    }

}
