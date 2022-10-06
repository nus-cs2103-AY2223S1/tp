package seedu.address.testutil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.HomeworkList;
import seedu.address.model.person.LessonPlan;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_LESSON_PLAN = "Data structures";

    private Name name;
    private Phone phone;
    private LessonPlan lessonPlan;
    private HomeworkList homeworkList;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        lessonPlan = new LessonPlan(DEFAULT_LESSON_PLAN);
        homeworkList = new HomeworkList();
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        lessonPlan = personToCopy.getLessonPlan();
        homeworkList = new HomeworkList(new ArrayList<>(personToCopy.getHomeworkList().homeworkList));
        tags = new HashSet<>(personToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Parses the {@code homeworkList} into a {@code List<Homework>} and set it to the {@code Person}
     * that we are building.
     */
    public PersonBuilder withHomework(String ... homeworkList) {
        this.homeworkList.homeworkList.addAll(SampleDataUtil.getHomeworkList(homeworkList));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code LessonPlan} of the {@code Person} that we are building.
     */
    public PersonBuilder withLessonPlan(String lessonPlan) {
        this.lessonPlan = new LessonPlan(lessonPlan);
        return this;
    }

    public Person build() {
        return new Person(name, phone, lessonPlan, homeworkList, tags);
    }

}
