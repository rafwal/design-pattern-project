package pl.edu.agh.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.listener.Listener;

@Service
public class TimersGathererService {

    @Autowired
    private Listener listener;

    public void start() {
        Thread thread = new Thread(() -> listener.doStartListening());
        thread.start();
    }

    public void stop() {
        listener.stopListening();
    }
}
