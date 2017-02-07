package pl.edu.agh.app.requestor.plugins.execution.interfaces;


//design-pattern abstract-factory
public interface Plugin {

    EntityDefinition getEntityDefinition(String Json);
    Task getTask();
    String getName();
}
