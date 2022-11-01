package nus.climods.model.module;

import java.util.HashMap;

import org.openapitools.client.model.SemestersEnum;

import javafx.beans.property.SimpleStringProperty;

/**
 * Class representing module a chosen by a user
 */
public class UserModule {

    public final SimpleStringProperty lessonsDataDisplay = new SimpleStringProperty("");
    private String code;
    private SemestersEnum selectedSemester;
    private HashMap<LessonTypeEnum, String> lessons = new HashMap<>();

    /**
     * Creates a user module
     *
     * @param moduleCode module code
     * @param selectedSemester semester
     */
    public UserModule(String moduleCode, SemestersEnum selectedSemester) {
        this.code = moduleCode;
        this.selectedSemester = selectedSemester;
    }

    /**
     * Creates a user module
     *
     * @param moduleCode module code
     * @param selectedSemester semester
     * @param lessons lessons
     */
    public UserModule(String moduleCode, SemestersEnum selectedSemester, HashMap<LessonTypeEnum, String> lessons) {
        this.code = moduleCode;
        this.selectedSemester = selectedSemester;
        this.lessons = lessons;
        updateLessonDataDisplay();
    }


    /**
     * Constructor used for UserModuleStub
     */
    protected UserModule() {
    }

    private void updateLessonDataDisplay() {
        StringBuilder str = new StringBuilder();
        for (LessonTypeEnum k : lessons.keySet()) {
            str.append(k.name()).append(" ").append(lessons.get(k)).append("\n");
        }

        lessonsDataDisplay.set(str.toString());
    }

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

    public HashMap<LessonTypeEnum, String> getLessons() {
        return lessons;
    }

    /**
     * Add a lesson to module
     *
     * @param lessonType       lesson type
     * @param lessonId lesson id
     */
    public void addLesson(LessonTypeEnum lessonType, String lessonInfo) {
        lessons.put(lessonType, lessonInfo);
        updateLessonDataDisplay();
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
            && lessons.equals(otherModule.lessons);
    }
}
