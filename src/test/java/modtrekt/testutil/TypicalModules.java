package modtrekt.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import modtrekt.model.ModuleList;
import modtrekt.model.module.Module;

/**
 * A utility class containing a list of {@code Module} objects to be used in tests.
 */
public class TypicalModules {

    public static final Module MA2001 = new ModuleBuilder().withName("Linear Algebra 1")
            .withCredit("4").withCode("MA2001").build();
    public static final Module ST2334 = new ModuleBuilder().withName("Probability and Statistics")
            .withCredit("4").withCode("ST2334").build();
    public static final Module MA1521 = new ModuleBuilder().withName("Calculus for Computing")
            .withCredit("4").withCode("MA1521").build();
    public static final Module CS2103T = new ModuleBuilder().withName("Software Engineering")
            .withCredit("4").withCode("CS2103T").build();

    private TypicalModules() {
    } // prevents instantiation

    /**
     * Returns an {@code ModuleList} with all the typical modules.
     */
    public static ModuleList getTypicalModuleList() {
        ModuleList ab = new ModuleList();
        for (Module mod : getTypicalModules()) {
            ab.addModule(mod);
        }
        return ab;
    }

    public static List<Module> getTypicalModules() {
        return new ArrayList<>(Arrays.asList(MA1521, ST2334, MA2001, CS2103T));
    }
}
