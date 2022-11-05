package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.CurrentModule;
import seedu.address.model.module.Module;
import seedu.address.model.module.PreviousModule;
import seedu.address.model.person.user.EmptyUser;
import seedu.address.model.person.user.ExistingUser;

/**
 * A command used to show the user what core modules of focus area modules there are left to clear
 */
public class ModulesLeftCommand extends Command {

    public static final String COMMAND_WORD = "modsleft";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows the user what modules they have yet to clear "
            + "in order to fulfill core requirements or focus area requirements.\n"
            + "Parameters: INDEX of core modules/focus area\n"
            + "Index Reference: 1. Core Modules 2. Algorithms and Theory 3. Artificial Intelligence "
            + "4. Computer Graphics and Games 5. Computer Security 6. Database Systems "
            + "7. Multimedia Information Retrieval 8. Networking and Distributed Systems 9. Parallel Computing "
            + "10. Programming Languages 11. Software Engineering\n"
            + "Example: modsleft 1";

    public static final String MESSAGE_SUCCESS = "These are the modules you have yet to clear: %1$s";
    public static final String MESSAGE_NO_USER = "No user to check modules left!";
    public static final String MESSAGE_INVALID_INDEX = "The focus area index provided is invalid.";

    private static Set<Module> modulesChecker = new HashSet<>();

    private final Index index;
    private final EmptyUser emptyUser = new EmptyUser();

    /**
     * @param index of the focus area to check against
     */
    public ModulesLeftCommand(Index index) {
        requireNonNull(index);

        this.index = index;
    }

    /**
     * Uses the {@code focusAreaIndex} given to choose the focus area (or core modules) and generate the list of
     * modules {@code modulesChecker} needed to fulfill core requirements or that focus area
     */
    public void chooseFocusArea() throws CommandException {
        int focusAreaIndex = index.getOneBased();
        switch (focusAreaIndex) {
        case 1:
            createCoreModules();
            break;
        case 2:
            createAlgoModules();
            break;
        case 3:
            createAiModules();
            break;
        case 4:
            createGamesModules();
            break;
        case 5:
            createSecurityModules();
            break;
        case 6:
            createDataModules();
            break;
        case 7:
            createInfoModules();
            break;
        case 8:
            createNetworkModules();
            break;
        case 9:
            createParallelModules();
            break;
        case 10:
            createLanguagesModules();
            break;
        case 11:
            createSweModules();
            break;
        default:
            throw new CommandException(MESSAGE_INVALID_INDEX);
        }
    }

    public static Set<Module> getModulesChecker() {
        return modulesChecker;
    }

    /**
     * Creates a set of core modules that CS students must clear to graduate
     */
    public static void createCoreModules() {
        String[] modStringArray = {"CS1101S", "CS1231S", "IS1103", "MA1521", "MA2001", "GEA1000", "ES2660", "ST2334",
                                   "CS2030S", "CS2040S", "CS2100", "CS2101", "CS2103T", "CS2106", "CS2109S", "CS3230"};
        for (String modString : modStringArray) {
            modulesChecker.add(new Module(modString));
        }
    }

    /**
     * Creates a set of modules that CS students can clear to fulfill the Algorithms and Theory focus area
     */
    public static void createAlgoModules() {
        String[] modStringArray = {"CS3230", "CS3231", "CS3236", "CS4231", "CS4234"};
        for (String modString : modStringArray) {
            modulesChecker.add(new Module(modString));
        }
    }

    /**
     * Creates a set of modules that CS students can clear to fulfill the Artificial Intelligence focus area
     */
    public static void createAiModules() {
        String[] modStringArray = {"CS2109S", "CS3243", "CS3244", "CS3263", "CS3264", "CS4243", "CS4244", "CS4246",
                                   "CS4248"};
        for (String modString : modStringArray) {
            modulesChecker.add(new Module(modString));
        }
    }

    /**
     * Creates a set of modules that CS students can clear to fulfill the Computer Graphics and Games focus area
     */
    public static void createGamesModules() {
        String[] modStringArray = {"CS3241", "CS3242", "CS3247", "CS4247", "CS4350"};
        for (String modString : modStringArray) {
            modulesChecker.add(new Module(modString));
        }
    }

    /**
     * Creates a set of modules that CS students can clear to fulfill the Computer Security focus area
     */
    public static void createSecurityModules() {
        String[] modStringArray = {"CS2107", "CS3235", "CS4230", "CS4236", "CS4238", "CS4239"};
        for (String modString : modStringArray) {
            modulesChecker.add(new Module(modString));
        }
    }

    /**
     * Creates a set of modules that CS students can clear to fulfill the Database Systems focus area
     */
    public static void createDataModules() {
        String[] modStringArray = {"CS2102", "CS3223", "CS4221", "CS4224", "CS4225"};
        for (String modString : modStringArray) {
            modulesChecker.add(new Module(modString));
        }
    }

    /**
     * Creates a set of modules that CS students can clear to fulfill the Multimedia Information Retrieval focus area
     */
    public static void createInfoModules() {
        String[] modStringArray = {"CS2108", "CS3245", "CS4242", "CS4248", "CS4347"};
        for (String modString : modStringArray) {
            modulesChecker.add(new Module(modString));
        }
    }

    /**
     * Creates a set of modules that CS students can clear to fulfill the Networking and Distributed Systems focus area
     */
    public static void createNetworkModules() {
        String[] modStringArray = {"CS2105", "CS3103", "CS4222", "CS4226", "CS4231"};
        for (String modString : modStringArray) {
            modulesChecker.add(new Module(modString));
        }
    }

    /**
     * Creates a set of modules that CS students can clear to fulfill the Parallel Computing focus area
     */
    public static void createParallelModules() {
        String[] modStringArray = {"CS3210", "CS3211", "CS4223", "CS4231"};
        for (String modString : modStringArray) {
            modulesChecker.add(new Module(modString));
        }
    }

    /**
     * Creates a set of modules that CS students can clear to fulfill the Programming Languages focus area
     */
    public static void createLanguagesModules() {
        String[] modStringArray = {"CS2104", "CS3211", "CS4212", "CS4215"};
        for (String modString : modStringArray) {
            modulesChecker.add(new Module(modString));
        }
    }

    /**
     * Creates a set of modules that CS students can clear to fulfill the Software Engineering focus area
     */
    public static void createSweModules() {
        String[] modStringArray = {"CS2103T", "CS3213", "CS3219", "CS4211", "CS4218", "CS4239"};
        for (String modString : modStringArray) {
            modulesChecker.add(new Module(modString));
        }
    }

    /**
     * Converts the set of module {@code moduleSet} into a string to be output by the command
     */
    public static String moduleSetToString(Set<Module> moduleSet) {
        StringBuilder result = new StringBuilder();
        String[] array = new String[moduleSet.size()];
        int j = 0;
        for (Module module : moduleSet) {
            array[j] = module.moduleName;
            j++;
        }
        Arrays.sort(array);
        for (int i = 0; i < array.length; i++) {
            if (i == 0) {
                result.append(array[i]);
            } else {
                result.append(", " + array[i]);
            }
        }
        return result.toString();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasUser()) {
            throw new CommandException(MESSAGE_NO_USER);
        }

        assert !model.getUser().equals(emptyUser) : "user to check should not be empty";

        ExistingUser userToCheck = (ExistingUser) model.getUser();
        Set<CurrentModule> userCurrMods = userToCheck.getCurrModules();
        Set<PreviousModule> userPrevMods = userToCheck.getPrevModules();
        modulesChecker.clear();
        chooseFocusArea();
        Set<Module> modulesLeft = modulesChecker;
        modulesLeft.removeAll(userPrevMods);
        modulesLeft.removeAll(userCurrMods);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_SUCCESS, moduleSetToString(modulesLeft)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModulesLeftCommand // instanceof handles nulls
                && index.equals(((ModulesLeftCommand) other).index)); // state check
    }
}
