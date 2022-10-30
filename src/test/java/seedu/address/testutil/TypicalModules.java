package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.module.Module;

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

    public static List<Module> getTypicalModules() {
        return new ArrayList<>(Arrays.asList(CS2030, CS2040, CS2100));
    }
}
