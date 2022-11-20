package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.module.ModuleCode;
import seedu.address.model.tag.Tag;

/**
 * Represents a Student in profNus.
 */
public class Student extends Person {

    private final StudentId id;
    private final TelegramHandle telegramHandle;
    private final Set<ModuleCode> studentModuleInfo = new HashSet<>();
    private final Set<ModuleCode> teachingAssistantInfo = new HashSet<>();
    private final Set<String> classGroups = new HashSet<>();

    /**
     * Every field must be present and not null.
     *
     * @param name
     * @param phone
     * @param email
     * @param address
     * @param tags
     * @param id
     * @param telegramHandle
     * @param studentModuleInfo
     * @param teachingAssistantInfo
     * @param classGroups
     */
    public Student(Name name, Phone phone, Email email, Address address, Set<Tag> tags, StudentId id,
                   TelegramHandle telegramHandle, Set<ModuleCode> studentModuleInfo,
                   Set<ModuleCode> teachingAssistantInfo, Set<String> classGroups) {
        super(name, phone, email, address, tags);
        requireAllNonNull(id, telegramHandle, studentModuleInfo);
        this.id = id;
        this.telegramHandle = telegramHandle;
        this.studentModuleInfo.addAll(studentModuleInfo);
        this.teachingAssistantInfo.addAll(teachingAssistantInfo);
        this.classGroups.addAll(classGroups);
    }

    public StudentId getId() {
        return id;
    }

    public TelegramHandle getTelegramHandle() {
        return telegramHandle;
    }

    public Set<ModuleCode> getStudentModuleInfo() {
        return Collections.unmodifiableSet(studentModuleInfo);
    }

    public boolean isTeachingAssistant() {
        return teachingAssistantInfo.size() > 0;
    }

    public Set<ModuleCode> getTeachingAssistantInfo() {
        return Collections.unmodifiableSet(teachingAssistantInfo);
    }

    public Set<String> getClassGroups() {
        return Collections.unmodifiableSet(classGroups);
    }

    /**
     * Compares if students are the same.
     *
     * @param otherStudent
     * @return True if students are the same.
     */
    public boolean isSamePerson(Person otherStudent) {
        if (otherStudent == this) {
            return true;
        }
        if (otherStudent instanceof Student) {
            Student temp = (Student) otherStudent;
            return otherStudent != null
                    && temp.getId().equals(getId());
        }
        return false;
    }

    /**
     * Returns true if both students have the same identity and data fields.
     * This defines a stronger notion of equality between two students.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Student)) {
            return false;
        }

        Student otherStudent = (Student) other;
        return otherStudent.getName().equals(getName())
                && otherStudent.getPhone().equals(getPhone())
                && otherStudent.getEmail().equals(getEmail())
                && otherStudent.getAddress().equals(getAddress())
                && otherStudent.getTags().equals(getTags())
                && otherStudent.getStudentModuleInfo().equals(getStudentModuleInfo())
                && otherStudent.getTeachingAssistantInfo().equals(getTeachingAssistantInfo())
                && otherStudent.getTelegramHandle().equals(getTelegramHandle())
                && otherStudent.getId().equals(getId())
                && otherStudent.getClassGroups().equals(getClassGroups());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress())
                .append("; ID: ")
                .append(getId())
                .append("; Telegram: ")
                .append(getTelegramHandle());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        Set<ModuleCode> moduleInfo = getStudentModuleInfo();
        if (!moduleInfo.isEmpty()) {
            builder.append("; Module Info: ");
            moduleInfo.forEach(builder::append);
        }
        Set<ModuleCode> taInfo = getTeachingAssistantInfo();
        if (!taInfo.isEmpty()) {
            builder.append("; Teaching Assistant Info: ");
            taInfo.forEach(builder::append);
        }
        Set<String> classGroups = getClassGroups();
        if (!classGroups.isEmpty()) {
            builder.append("; Class groups: ");
            classGroups.forEach(builder::append);
        }
        return builder.toString();
    }
}
