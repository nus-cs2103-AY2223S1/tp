package friday.testutil;

import friday.logic.commands.GradeCommand.EditGradeDescriptor;
import friday.model.grades.Grade;
import friday.model.grades.GradesList;

/**
 * A utility class to help with building EditGradeDescriptor objects.
 */
public class EditGradeDescriptorBuilder {

    private EditGradeDescriptor descriptor;

    public EditGradeDescriptorBuilder() {
        descriptor = new EditGradeDescriptor();
    }

    public EditGradeDescriptorBuilder(EditGradeDescriptor descriptor) {
        this.descriptor = new EditGradeDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditGradeDescriptor} with fields containing {@code gradesList}'s grades
     */
    public EditGradeDescriptorBuilder(GradesList gradesList) {
        descriptor = new EditGradeDescriptor();
        descriptor.setRa1(gradesList.getGrade("RA1"));
        descriptor.setRa2(gradesList.getGrade("RA2"));
        descriptor.setMt(gradesList.getGrade("Mid-Term"));
        descriptor.setFt(gradesList.getGrade("Finals"));
        descriptor.setPa(gradesList.getGrade("Practical"));
    }

    /**
     * Sets the {@code Grade} of the {@code EditGradeDescriptor} that we are building.
     *
     * @param examName the name of the exam
     * @param score the score of the exam
     * @return the EditGradeDescriptor to build
     */
    public EditGradeDescriptorBuilder withGrade(String examName, String score) {
        Grade newGrade = new Grade(examName, score);
        switch (examName) {

        case "RA1":
            descriptor.setRa1(newGrade);
            break;
        case "RA2":
            descriptor.setRa2(newGrade);
            break;
        case "Practical":
            descriptor.setPa(newGrade);
            break;
        case "Mid-Term":
            descriptor.setMt(newGrade);
            break;
        case "Finals":
            descriptor.setFt(newGrade);
            break;
        default:
            break;
        }
        return this;
    }

    public EditGradeDescriptor build() {
        return descriptor;
    }
}
