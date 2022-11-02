package seedu.address.testutil;

import seedu.address.model.module.Module;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A utility class containing a list of {@code Module} objects to be used in tests.
 */
public class TypicalModules {

    public static final Module CS2030 = new ModuleBuilder().withModuleCode("cs2030")
            .withModuleName("Programming Methodology")
            .withModuleCredit(4)
            .build();
    public static final Module CS2040 = new ModuleBuilder().withModuleCode("cs2040")
            .withModuleName("Data and Algorithms")
            .withModuleCredit(4)
            .build();
    public static final Module CS2100 = new ModuleBuilder().withModuleCode("cs2100")
            .withModuleName("Computer Organisation")
            .withModuleCredit(4)
            .build();
    public static final Module CS2103T = new ModuleBuilder().withModuleCode("cs2103t")
            .withModuleName("Software Engineering")
            .withModuleCredit(4)
            .build();
    public static final Module MA1521 = new ModuleBuilder().withModuleCode("MA1521")
            .withModuleName("Math")
            .withModuleCredit(4)
            .build();

    public static final Module CS2030S = new ModuleBuilder().withModuleCode("CS2030S")
        .withModuleName("Programming Methodology for CS students").withModuleCredit(4).build();
    public static final Module CS2040S = new ModuleBuilder().withModuleCode("CS2040S")
            .withModuleName("Data and Algorithms for CS students").withModuleCredit(4).build();


    public static List<Module> getTypicalModules() {
        return new ArrayList<>(Arrays.asList(CS2030, CS2040, CS2100, CS2103T, MA1521, CS2030S, CS2040S));
    }
}

//    public static List<Module> getTypicalModules() {
//        return new ArrayList<>(Arrays.asList(CS2030, CS2040, CS2100, CS2103T));
//    }
//}
//>>>>>>> master
