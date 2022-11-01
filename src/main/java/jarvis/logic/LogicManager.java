package jarvis.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Logger;

import jarvis.commons.core.GuiSettings;
import jarvis.commons.core.LogsCenter;
import jarvis.logic.commands.Command;
import jarvis.logic.commands.CommandResult;
import jarvis.logic.commands.exceptions.CommandException;
import jarvis.logic.parser.JarvisParser;
import jarvis.logic.parser.exceptions.ParseException;
import jarvis.model.Lesson;
import jarvis.model.Model;
import jarvis.model.ReadOnlyLessonBook;
import jarvis.model.ReadOnlyStudentBook;
import jarvis.model.ReadOnlyTaskBook;
import jarvis.model.Student;
import jarvis.model.Task;
import jarvis.storage.Storage;
import javafx.collections.ObservableList;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final JarvisParser jarvisParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        jarvisParser = new JarvisParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        model.updateFilteredLessonList(Model.PREDICATE_SHOW_ALL_LESSONS);
        List<Lesson> allLessonList = model.getFilteredLessonList();
        for (Lesson l : allLessonList) {
            if (l.hasTimingConflict()) {
                l.unmarkClash();
                model.setLesson(l, l);
            }
        }

        CommandResult commandResult;
        Command command = jarvisParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveStudentBook(model.getStudentBook());
            storage.saveTaskBook(model.getTaskBook());
            storage.saveLessonBook(model.getLessonBook());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyStudentBook getStudentBook() {
        return model.getStudentBook();
    }

    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return model.getFilteredStudentList();
    }

    @Override
    public Path getStudentBookFilePath() {
        return model.getStudentBookFilePath();
    }

    @Override
    public ReadOnlyTaskBook getTaskBook() {
        return model.getTaskBook();
    }

    @Override
    public ObservableList<Task> getFilteredTaskList() {
        return model.getFilteredTaskList();
    }

    @Override
    public Path getTaskBookFilePath() {
        return model.getTaskBookFilePath();
    }

    @Override
    public ReadOnlyLessonBook getLessonBook() {
        return model.getLessonBook();
    }

    @Override
    public ObservableList<Lesson> getFilteredLessonList() {
        return model.getFilteredLessonList();
    }

    @Override
    public Path getLessonBookFilePath() {
        return model.getLessonBookFilePath();
    }


    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
