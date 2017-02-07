package pl.edu.agh.app.requestor.plugins.execution.interfaces;


import pl.edu.agh.app.requestor.util.JsonUtil;

public interface EntityDefinition {


    /**
     * Describes payload saved in db to retrieve and repeat test.
     * MUST be in JSON inculding definition fields except for id and name.
     */
    default String toJson(EntityDefinition entity) {
        return JsonUtil.toJson(entity);
    }

}
