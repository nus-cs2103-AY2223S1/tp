package seedu.uninurse.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import seedu.uninurse.model.ReadOnlyUninurseBook;
import seedu.uninurse.model.UninurseBook;
import seedu.uninurse.model.condition.Condition;
import seedu.uninurse.model.condition.ConditionList;
import seedu.uninurse.model.medication.Medication;
import seedu.uninurse.model.medication.MedicationList;
import seedu.uninurse.model.person.Address;
import seedu.uninurse.model.person.Email;
import seedu.uninurse.model.person.Name;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.person.Phone;
import seedu.uninurse.model.remark.Remark;
import seedu.uninurse.model.remark.RemarkList;
import seedu.uninurse.model.tag.Tag;
import seedu.uninurse.model.tag.TagList;
import seedu.uninurse.model.task.DateTime;
import seedu.uninurse.model.task.NonRecurringTask;
import seedu.uninurse.model.task.Task;
import seedu.uninurse.model.task.TaskList;

/**
 * Contains utility methods for populating {@code UninurseBook} with sample data.
 */
public class SampleDataUtil {
    public static final ConditionList EMPTY_CONDITION_LIST = new ConditionList();
    public static final MedicationList EMPTY_MEDICATION_LIST = new MedicationList();
    public static final TaskList EMPTY_TASK_LIST = new TaskList();
    public static final RemarkList EMPTY_REMARK_LIST = new RemarkList();

    public static Patient[] getSamplePersons() {
        return new Patient[] {
            new Patient(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"), getTagList("friends"),
                EMPTY_CONDITION_LIST, EMPTY_MEDICATION_LIST, EMPTY_TASK_LIST, EMPTY_REMARK_LIST),
            new Patient(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagList("colleagues", "friends"), getConditionList("ACL tear"),
                getMedicationList(new Medication("Amoxicillin", "0.5 g every 8 hours")),
                getTaskList(new NonRecurringTask("Change dressing on right arm", new DateTime("16-10-22 1015"))),
                getRemarkList("Allergic to Amoxicillin")),
            new Patient(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), getTagList("neighbours"),
                getConditionList("Type I Diabetes", "Hypertension"),
                getMedicationList(new Medication("Ampicillin", "0.5 IV every 6 hours")),
                getTaskList(new NonRecurringTask("Check blood glucose level", new DateTime("25-11-22 1300")),
                        new NonRecurringTask("Administer insulin dose", new DateTime("30-12-22 1845"))),
                        getRemarkList("Requires wheelchair to move around")),
            new Patient(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), getTagList("family"),
                EMPTY_CONDITION_LIST, EMPTY_MEDICATION_LIST, EMPTY_TASK_LIST, EMPTY_REMARK_LIST),
            new Patient(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), getTagList("classmates"),
                EMPTY_CONDITION_LIST, EMPTY_MEDICATION_LIST, EMPTY_TASK_LIST, EMPTY_REMARK_LIST),
            new Patient(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), getTagList("colleagues"),
                EMPTY_CONDITION_LIST, EMPTY_MEDICATION_LIST, EMPTY_TASK_LIST, EMPTY_REMARK_LIST)
        };
    }

    public static ReadOnlyUninurseBook getSampleUninurseBook() {
        UninurseBook sampleUb = new UninurseBook();
        for (Patient samplePerson : getSamplePersons()) {
            sampleUb.addPerson(samplePerson);
        }
        return sampleUb;
    }

    /**
     * Returns a {@code TagList} containing the list of strings given.
     */
    public static TagList getTagList(String... tagStrings) {
        List<Tag> tagList = Arrays.stream(tagStrings)
                .map(Tag::new)
                .collect(Collectors.toList());
        return new TagList(tagList);
    }

    /**
     * Creates a {@code ConditionList} using a given list of strings.
     * @param conditionStrings The given list of strings of conditions.
     * @return A {@code ConditionList} of the given conditions.
     */
    public static ConditionList getConditionList(String... conditionStrings) {
        List<Condition> conditionList = Arrays.stream(conditionStrings)
                .map(Condition::new)
                .collect(Collectors.toList());
        return new ConditionList(conditionList);
    }

    /**
     * Creates a {@code MedicationList} using a given list of medications.
     * @param medications The given list of medications.
     * @return A {@code MedicationList} of the given medications.
     */
    public static MedicationList getMedicationList(Medication... medications) {
        List<Medication> medicationList = Arrays.stream(medications).collect(Collectors.toList());
        return new MedicationList(medicationList);
    }

    /**
     * Returns a {@code TaskList} containing the list of tasks given.
     */
    public static TaskList getTaskList(Task... tasks) {
        ArrayList<Task> taskArrayList = Arrays.stream(tasks).collect(Collectors.toCollection(ArrayList::new));
        return new TaskList(taskArrayList);
    }

    /**
     * Creates a {@code RemarkList} using a given list of strings.
     * @param remarkStrings The given list of strings of remarks.
     * @return A {@code RemarkList} of the given remarks.
     */
    public static RemarkList getRemarkList(String... remarkStrings) {
        List<Remark> remarkList = Arrays.stream(remarkStrings)
                .map(Remark::new)
                .collect(Collectors.toList());
        return new RemarkList(remarkList);
    }
}
