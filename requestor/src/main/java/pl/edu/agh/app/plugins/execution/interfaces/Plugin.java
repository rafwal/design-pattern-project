package pl.edu.agh.app.plugins.execution.interfaces;

public interface Plugin {

    EntityDefinition getEntityDefinition(String Json);
    Task getTask();
    String getName();
}
