package pl.edu.agh.app.plugins.execution.interfaces;


public interface Task<T extends EntityDefinition> {

    /**
     * Should include all steps which should be precessed to run working test.
     */
    String execute(T definition);

}
