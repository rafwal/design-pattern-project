package pl.edu.agh.app.acceptor;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import lombok.Getter;
import org.springframework.util.StringUtils;
import pl.edu.agh.app.requestor.util.JsonUtil;

import java.io.IOException;

public class TimersGatherer {

    @Getter
    private Multimap<String, TimerDTO> timers = ArrayListMultimap.create();


    public void addTimers(String json, String appName) {
        Multimap<String, TimerDTO> newTimers = convertJsonToMultimap(json);

        if (!newTimers.isEmpty()) {
            timers.putAll(newTimers);
            System.err.println("TIMERS ADDED");
        } else {
            System.err.println("TIMERS MISSING");
        }
    }

    private Multimap<String, TimerDTO> convertJsonToMultimap(String json) {
        try {
            return JsonUtil.MAPPER.readValue(
                    JsonUtil.MAPPER.treeAsTokens(JsonUtil.MAPPER.readTree(json)),
                    JsonUtil.MAPPER.getTypeFactory().constructMapLikeType(
                            Multimap.class, String.class, TimerDTO.class));
        } catch (IOException e) {
            System.err.println("COULD NOT PROCESS TIMERS FROM JSON");
            return null;
        }
    }
}
