package nus.climods.model.module;

import java.util.HashMap;

import org.openapitools.client.model.SemestersEnum;

/**
 * Class representing module a chosen by a user
 */
public class UserModule {

    private String code;
    private SemestersEnum selectedSemester;

    /**
     * The Tutorial and Lecture attributes will be stored in lessons hashmap instead, since there can be other types
     * of lessons.
     */
    private String tutorial = "<Not Selected>"; // TODO placeholder
    private String lecture = "<Not Selected>"; // TODO placeholder

    //Hashmap to store Lesson Data
    private HashMap<LessonTypeEnum, String> lessons = new HashMap<>();

    /**
     * Creates a UserModule
     *
     * @param moduleCode module code
     * @param selectedSemester semester
     */
    public UserModule(String moduleCode, SemestersEnum selectedSemester) {
        this.code = moduleCode;
        this.selectedSemester = selectedSemester;
    }

    /**
     * Creates a UserModule
     *
     * @param moduleCode           string for the module code
     * @param lecture              selected lecture slot
     * @param tutorial             selected tutorial slot
     * @param selectedSemesterStr  selected semester
     */
    public UserModule(String moduleCode, String lecture, String tutorial, String selectedSemesterStr) {
        this.code = moduleCode;
        this.selectedSemester = SemestersEnum.fromValue(selectedSemesterStr);

        this.lecture = lecture;
        this.tutorial = tutorial;
    }

    /**
     * Constructor used for UserModuleStub
     */
    protected UserModule() {}

    /**
     * Returns the module code.
     *
     * @return module code
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Returns the semester chosen by user to take this module
     *
     * @return selected semester
     */
    public SemestersEnum getSelectedSemester() {
        return this.selectedSemester;
    }

    /**
     * Allows user to set Lessons type, storing and updating them in the HashMap.
     * @param lessonType
     * @param classNo
     */
    public void setLessons(LessonTypeEnum lessonType, String classNo) {
        if (lessons.containsKey(lessonType)) {
            lessons.replace(lessonType, classNo);
        } else {
            lessons.put(lessonType, classNo);
        }
    }

    /**
     * Returns list of lessons.
     * @return List of lessons
     */
    //TODO: Change this later when fixing UI
    public String getLessons() {
        String str = "";
        for (LessonTypeEnum k : lessons.keySet()) {
            str += k.toString() + " | " + lessons.get(k) + "\n";
        }
        return str;
    }

    //TODO: Can remove all these afterwards if we can display with hashmap, gotta check with Chengyi

    // TEMP
    public String getTutorial() {
        return this.tutorial;
    }

    // TEMP
    public void setTutorial(String tutorial) {
        this.tutorial = tutorial;
    }

    // TEMP
    public String getLecture() {
        return this.lecture;
    }

    // TEMP
    public void setLecture(String lecture) {
        this.lecture = lecture;
    }

    /**
     * Returns true if both modules have the same code. This defines a weaker notion of equality between two modules.
     *
     * @param otherModule other user module
     * @return true if both module has the same module code else false
     */
    public boolean isSameUserModule(UserModule otherModule) {
        return (otherModule == this) || otherModule != null && code.equals(otherModule.code);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof UserModule)) {
            return false;
        }

        UserModule otherModule = (UserModule) other;
        return code.equals(otherModule.getCode()) && selectedSemester.equals(otherModule.selectedSemester)
            && tutorial.equals(otherModule.tutorial) && lecture.equals(otherModule.lecture);
    }
}
