package seedu.address.testutil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleDescription;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.schedule.Schedule;
import seedu.address.model.person.Student;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;


/**
 * A utility class to help with building Module objects.
 */
public class ModuleBuilder {

    public static final String DEFAULT_MODULE_NAME = "Intro to Software Engineering";
    public static final String DEFAULT_MODULE_CODE = "CS2103T";
    public static final String DEFAULT_MODULE_DESCRIPTION = "Students are to practice software engineering "
            + "skills";
    public static final String DEFAULT_MODULE_TAG = "IMPORTANT";
    public static final Student DEFAULT_STUDENT_1 = new StudentBuilder().build();

    private ModuleName moduleName;
    private ModuleCode moduleCode;
    private ModuleDescription moduleDescription;
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Schedule> schedules = new ArrayList<>();
    private Set<Tag> tags;

    /**
     * Creates a {@code ModuleBuilder} with the default details.
     */
    public ModuleBuilder() {
        moduleName = new ModuleName(DEFAULT_MODULE_NAME);
        moduleCode = new ModuleCode(DEFAULT_MODULE_CODE);
        moduleDescription = new ModuleDescription(DEFAULT_MODULE_DESCRIPTION);
        tags = new HashSet<>();
        tags.add(new Tag(DEFAULT_MODULE_TAG));
    }

    /**
     * Initializes the ModuleBuilder with the data of {@code moduleToCopy}.
     */
    public ModuleBuilder(Module moduleToCopy) {
        moduleName = moduleToCopy.getName();
        moduleCode = moduleToCopy.getCode();
        moduleDescription = moduleToCopy.getDescription();
        tags = new HashSet<>(moduleToCopy.getTags());
    }

    /**
     * Sets the {@code moduleName} of the {@code Module} that we are building.
     */
    public ModuleBuilder withName(String moduleName) {
        this.moduleName = new ModuleName(moduleName);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Module} that we are building.
     */
    public ModuleBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }


    /**
     * Sets the {@code ModuleCode} of the {@code Module} that we are building.
     */
    public ModuleBuilder withModuleCode(String moduleCode) {
        this.moduleCode = new ModuleCode(moduleCode);
        return this;
    }

    /**
     * Sets the {@code moduleDescription} of the {@code Module} that we are building.
     */
    public ModuleBuilder withModuleDescription(String moduleDescription) {
        this.moduleDescription = new ModuleDescription(moduleDescription);
        return this;
    }

    /**
     * Builds a module for testing.
     *
     * @return a Module that we are building
     */
    public Module build() {
        return new Module(moduleName, moduleCode, moduleDescription, tags, students);
    }
}
