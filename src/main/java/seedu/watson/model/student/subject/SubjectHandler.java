package seedu.watson.model.student.subject;

import java.util.HashMap;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.watson.commons.util.StringUtil;

/**
 * Handles a Student's subjects in the application.
 */
public class SubjectHandler {
    private final HashMap<String, Subject> subjectsTaken;

    /**
     * Constructs a {@code SubjectHandler}.
     */
    public SubjectHandler() {
        subjectsTaken = new HashMap<>();
    }

    /**
     * Constructs a {@code SubjectHandler} with a specified Set of Subjects.
     */
    public SubjectHandler(Set<Subject> subjects) {
        subjectsTaken = new HashMap<>();
        for (Subject subject : subjects) {
            subjectsTaken.put(subject.getSubjectName(), subject);
        }
    }

    /**
     * Constructs a {@code SubjectHandler} with a String data from json.
     */
    public SubjectHandler(String subjectData) {
        subjectsTaken = new HashMap<>();
        dataToSubject(subjectData);
    }

    /**
     * Adds a subject to the list of subjects taken by the student
     *
     * @param subject the subject to be added
     */
    public void addSubject(Subject subject) {
        subjectsTaken.put(subject.getSubjectName(), subject);
    }

    /**
     * Gets a subject from the list of subjects taken by the student
     *
     * @param subjectName the name of the subject to be retrieved
     */
    public Subject getSubject(String subjectName) {
        return subjectsTaken.get(subjectName);
    }

    public Set<Subject> getSubjectsTaken() {
        return subjectsTaken.keySet().stream().map(subjectsTaken::get).collect(
            Collectors.toSet());
    }

    /**
     * Checks if the HashMap containing subjects taken by a student contains the subject(s) given as
     * keyword(s) in the find command.
     *
     * @param keywords Keywords separated by spaces and are given by user using the find command.
     * @return A boolean for if keyword(s) is contained in sentence.
     */
    public boolean subjectsTakenContainsWord(String keywords) {
        Set<String> subjectsSet = subjectsTaken.keySet();
        String subjectsString = String.join(" ", subjectsSet);

        String[] keywordArr = keywords.split(" ");
        for (String keyword : keywordArr) {
            if (StringUtil.containsWordIgnoreCase(subjectsString, keyword)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Converts the String datatype stored in json into subjects.
     *
     * @param subjectData String data of the subjects taken by Student.
     */
    public void dataToSubject(String subjectData) {
        String[] dataString = subjectData.trim().split("%%");
        if (dataString.length == 0) {
            return;
        }
        for (int i = 0; i < dataString.length; i++) {
            //System.out.println(dataString[i] + "start");
            String subjectName;
            subjectName = dataString[i].substring(0, dataString[i].indexOf(":")).toUpperCase(Locale.ROOT);
            if (dataString[i].substring(subjectName.length()).length() > 2) {
                dataString[i] =
                    dataString[i].substring(dataString[i].indexOf(":") + 2); //dataString without the subject name
            } else {
                dataString[i] = "";
            }
            //System.out.println(subjectName + ": " + dataString[i] + "after subjectname");
            Subject subject = new Subject(subjectName);
            while (dataString[i].length() > 5) {
                String assessmentName = dataString[i].substring(0, dataString[i].indexOf(":"));
                dataString[i] = dataString[i].substring(dataString[i].indexOf(":") + 2);
                //System.out.println(assessmentName + ": " + dataString[i] + "after assessName");
                Double assessmentScore = Double.parseDouble(dataString[i].substring(0, dataString[i].indexOf(",")));
                dataString[i] = dataString[i].substring(dataString[i].indexOf(",") + 2);
                //System.out.println(assessmentScore + ": " + dataString[i] + "after assessScore");
                Double assessmentTotalScore =
                    Double.parseDouble(dataString[i].substring(0, dataString[i].indexOf(",")));
                dataString[i] = dataString[i].substring(dataString[i].indexOf(",") + 2);
                //System.out.println(assessmentTotalScore + ": " + dataString[i] + "after assessTotalScore");
                Double assessmentWeightage = Double.parseDouble(dataString[i].substring(0, dataString[i].indexOf(",")));
                dataString[i] = dataString[i].substring(dataString[i].indexOf(",") + 2);
                //System.out.println(assessmentWeightage + ": " + dataString[i] + "after assessWeightage");
                Double assessmentDifficulty =
                    Double.parseDouble(dataString[i].substring(0, dataString[i].indexOf("]")));
                if (dataString[i].substring(dataString[i].indexOf("]")).length() > 2) {
                    dataString[i] = dataString[i].substring(dataString[i].indexOf("]") + 3);
                } else {
                    dataString[i] = "";
                }
                Assessment assessment = new Assessment(assessmentName, assessmentScore, assessmentTotalScore,
                                                       assessmentWeightage, assessmentDifficulty);
                subject.updateGradeAssessment(assessment);
                //System.out.println(assessmentDifficulty + ": " + dataString[i] + "after assess");
            }
            subjectsTaken.put(subjectName, subject);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SubjectHandler) {
            return subjectsTaken.equals(((SubjectHandler) obj).subjectsTaken);
        }
        return false;
    }

    /**
     * Returns the subjects into a String datatype to be stored in Json.
     *
     * @return a String which represents the data of the subjects taken by the student.
     */
    public String dataString() {
        StringBuilder str = new StringBuilder();
        Set<String> keys = subjectsTaken.keySet();
        if (subjectsTaken.isEmpty()) {
            return str.toString();
        }
        for (String key : keys) {
            Subject keyValue = subjectsTaken.get(key);
            str.append(keyValue.dataString());
        }
        str = new StringBuilder(str.substring(0, str.length() - 2));
        return str.toString();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        Set<String> keys = subjectsTaken.keySet();
        if (subjectsTaken.isEmpty()) {
            return str.toString();
        }
        for (String key : keys) {
            Subject keyValue = subjectsTaken.get(key);
            str.append(keyValue.toString() + "\n");
        }
        return str.toString();
    }

}
