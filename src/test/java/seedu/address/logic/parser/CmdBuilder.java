package seedu.address.logic.parser;

import java.util.function.Predicate;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.FunctionalInterfaces.Changer;
import seedu.address.commons.util.FunctionalInterfaces.Getter;
import seedu.address.commons.util.FunctionalInterfaces.Retriever;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.model.group.Group;
import seedu.address.model.item.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

/**
 * Class to build commands that are harder to construct
 */
public class CmdBuilder {

    public static final String P_DELETE = "person delete";
    public static final String T_DELETE = "task delete";
    public static final String G_DELETE = "team delete";

    private static final Changer<Predicate<Person>> P_CHANGER = ((model, item) -> model.updateFilteredPersonList(item));
    private static final Retriever<Integer> P_SIZE = ((model) -> model.getFilteredPersonList().size());

    private static final Changer<Predicate<Task>> T_CHANGER = ((model, item) -> model.updateFilteredTaskList(item));
    private static final Retriever<Integer> T_SIZE = ((model) -> model.getFilteredTaskList().size());


    private static final Changer<Predicate<Group>> G_CHANGER = ((model, item) -> model.updateFilteredTeamList(item));
    private static final Retriever<Integer> G_SIZE = ((model) -> model.getFilteredTeamList().size());

    private static final Getter<Person> P_GETTER = (m, i) -> m.getFromFilteredPerson(i);
    private static final Changer<Person> P_DELETER = (m, item) -> m.deletePerson(item);
    private static final Predicate<Object> P_TESTER = o -> o instanceof Person;

    private static final Getter<Task> T_GETTER = (m, i) -> m.getFromFilteredTasks(i);
    private static final Changer<Task> T_DELETER = (m, item) -> m.deleteTask(item);
    private static final Predicate<Object> T_TESTER = o -> o instanceof Task;

    private static final Getter<Group> G_GETTER = (m, i) -> m.getFromFilteredTeams(i);
    private static final Changer<Group> G_DELETER = (m, item) -> m.deleteTeam(item);
    private static final Predicate<Object> G_TESTER = o -> o instanceof Task;

    // ================= delete ===============================================
    /**
     * Returns a delete command for person
     */
    public static final DeleteCommand<Person> makeDelPerson(Index index) {
        return new DeleteCommand<>(index, P_GETTER, P_DELETER, P_TESTER);
    }

    /**
     * Returns a delete command for task
     */
    public static final DeleteCommand<Task> makeDelTask(Index index) {
        return new DeleteCommand<>(index, T_GETTER, T_DELETER, T_TESTER);
    }

    /**
     * Returns a delete command for group
     */
    public static final DeleteCommand<Group> makeDelGrp(Index index) {
        return new DeleteCommand<>(index, G_GETTER, G_DELETER, G_TESTER);
    }

    // ===========================delete parser ===============================

    /**
     * Returns a Parser for delete command for person
     */
    public static final Parser<DeleteCommand<Person>> makeDelParserPerson() {
        return DeleteCommand.parser(P_GETTER, P_DELETER, P_TESTER);
    }

    /**
     * Returns a Parser for delete command for task
     */
    public static final Parser<DeleteCommand<Task>> makeDelParserTask() {
        return DeleteCommand.parser(T_GETTER, T_DELETER, T_TESTER);
    }

    /**
     * Returns a Parser for delete command for group
     */
    public static final Parser<DeleteCommand<Group>> makeDelParserGroup() {
        return DeleteCommand.parser(G_GETTER, G_DELETER, G_TESTER);
    }

    // ================= find ===============================================
    /**
     * Returns a find command for person
     */
    public static final FindCommand<Person> makeFindPerson(NameContainsKeywordsPredicate<Person> predicate) {
        return new FindCommand<>(predicate, P_CHANGER, P_SIZE);
    }

    /**
     * Returns a find command for task
     */
    public static final FindCommand<Task> makeFindTask(NameContainsKeywordsPredicate<Task> predicate) {
        return new FindCommand<>(predicate, T_CHANGER, T_SIZE);
    }

    /**
     * Returns a find command for group
     */
    public static final FindCommand<Group> makeFindGrp(NameContainsKeywordsPredicate<Group> predicate) {
        return new FindCommand<>(predicate, G_CHANGER, G_SIZE);
    }

    // ===========================find parser ===============================

    /**
     * Returns a Parser for find command for person
     */
    public static final FindCommandParser<Person> makeFindParserPerson() {
        return new FindCommandParser<>(P_CHANGER, P_SIZE);
    }

    /**
     * Returns a Parser for find command for task
     */
    public static final FindCommandParser<Task> makeFindParserTask() {
        return new FindCommandParser<>(T_CHANGER, T_SIZE);
    }

    /**
     * Returns a Parser for find command for group
     */
    public static final FindCommandParser<Group> makeFindParserGroup() {
        return new FindCommandParser<>(G_CHANGER, G_SIZE);
    }


}
