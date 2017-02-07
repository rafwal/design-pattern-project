package pl.edu.agh.app.requestor.plugins.execution.interfaces;

//design-pattern strategy
public interface Task<T extends EntityDefinition> {

    /**
     * Should include all steps which should be precessed to run working test.
     */
    String execute(T definition);

}
