package seedu.uninurse.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.task.DateTime;
import seedu.uninurse.model.task.Task;

/**
 * A Schedule that has a list of PatientTaskListPair for a given day.
 */
public class Schedule {
    private final List<PatientTaskListPair> patientTaskListPairList;
    private final DateTime dateTime;

    /**
     * Constructs a Schedule that has a list of PatientTaskListPair on the given day of interest.
     *
     * @param patients the list of patients.
     * @param dayOfInterest the day of the schedule.
     */
    public Schedule(List<Patient> patients, DateTime dayOfInterest) {
        this.patientTaskListPairList = new ArrayList<>();
        this.dateTime = dayOfInterest;

        List<PatientTaskPair> patientTaskPairList = new ArrayList<>();

        patients.forEach(p -> p.getTasks().getAllTasksOnDay(dayOfInterest)
                    .forEach(t -> patientTaskPairList.add(new PatientTaskPair(p, t))));

        patientTaskPairList.sort(new Comparator<PatientTaskPair>() {
            @Override
            public int compare(PatientTaskPair firstPair, PatientTaskPair secondPair) {
                if (firstPair.task.getDateTime().isBefore(secondPair.task.getDateTime())) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });

        if (!patientTaskPairList.isEmpty()) {
            Patient currentPatient = patientTaskPairList.get(0).patient;
            List<Task> currentTaskList = new ArrayList<>();
            for (PatientTaskPair pair : patientTaskPairList) {
                if (pair.patient.equals(currentPatient)) {
                    currentTaskList.add(pair.task);
                } else {
                    this.patientTaskListPairList.add(new PatientTaskListPair(currentPatient, currentTaskList));
                    currentTaskList = new ArrayList<>();
                    currentTaskList.add(pair.task);
                    currentPatient = pair.patient;
                }
            }
            this.patientTaskListPairList.add(new PatientTaskListPair(currentPatient, currentTaskList));
        }
    }

    public String getDate() {
        return dateTime.getDate();
    }

    public boolean isToday() {
        return dateTime.isToday();
    }

    public ObservableList<PatientTaskListPair> getObservableList() {
        return FXCollections.observableList(patientTaskListPairList);
    }

    /**
     * Constructs a PatientTaskPair the stores information about a Patient and a Task.
     */
    private class PatientTaskPair {
        private final Patient patient;
        private final Task task;

        private PatientTaskPair(Patient patient, Task task) {
            this.patient = patient;
            this.task = task;
        }
    }

    /**
     * Constructs a PatientTaskListPair the stores information about a Patient
     * and a list of Tasks.
     */
    public class PatientTaskListPair {
        private final Patient patient;
        private final List<Task> taskList;

        private PatientTaskListPair(Patient patient, List<Task> taskList) {
            this.patient = patient;
            this.taskList = taskList;
        }

        public Patient getPatient() {
            return patient;
        }

        public List<Task> getTaskList() {
            return taskList;
        }
    }
}
