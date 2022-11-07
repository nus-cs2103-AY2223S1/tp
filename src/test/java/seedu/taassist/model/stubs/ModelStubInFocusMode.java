package seedu.taassist.model.stubs;

import seedu.taassist.model.moduleclass.ModuleClass;

/**
 * A Model stub that is focusing on a class.
 */
public class ModelStubInFocusMode extends ModelStub {

    private boolean inFocusMode = true;

    @Override
    public boolean isInFocusMode() {
        return inFocusMode;
    }

    @Override
    public void exitFocusMode() {
        inFocusMode = false;
    }

    @Override
    public ModuleClass getFocusedClass() {
        return new ModuleClass("CS2103T");
    }
}
