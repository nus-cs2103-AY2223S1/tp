package nus.climods.model.module;

/**
 * A stub for {@link Module}.
 */
public class ModuleStub extends Module {
    private final String title;
    private final String code;

    /**
     * Constructor for ModuleStub class.
     *
     * @param title module title
     * @param code module code
     */
    public ModuleStub(String title, String code) {
        super(null);
        this.title = title;
        this.code = code;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public String getCode() {
        return this.code;
    }
}
