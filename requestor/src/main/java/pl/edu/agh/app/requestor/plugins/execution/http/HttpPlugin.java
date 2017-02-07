package pl.edu.agh.app.requestor.plugins.execution.http;


import pl.edu.agh.app.requestor.plugins.execution.interfaces.EntityDefinition;
import pl.edu.agh.app.requestor.plugins.execution.interfaces.Plugin;
import pl.edu.agh.app.requestor.plugins.execution.interfaces.Task;
import pl.edu.agh.app.requestor.util.JsonUtil;

public class HttpPlugin implements Plugin {


    @Override
    public EntityDefinition getEntityDefinition(String json) {
        return JsonUtil.fromJson(json, HttpEntityDefinition.class);
    }

    @Override
    public Task getTask() {
        return new HttpTask();
    }

    @Override
    public String getName() {
        return "HTTP";
    }
}
