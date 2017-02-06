package pl.edu.agh.listener;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import lombok.Getter;
import pl.edu.agh.app.util.JsonUtil;

import java.io.IOException;

public class TimersGatherer {

    @Getter
    private Multimap<String, TimerDTO> timers = ArrayListMultimap.create();


    public void addTimers(String json) {
        Multimap<String, TimerDTO> newTimers = convertJsonToMultimap(json);
        if (newTimers != null) {
            timers.putAll(newTimers);
        } else {
            System.err.println("COULD NOT ADD TIMERS");
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
