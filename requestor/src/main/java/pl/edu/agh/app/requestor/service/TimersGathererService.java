package pl.edu.agh.app.requestor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.app.acceptor.TimersRegistryAcceptor;

@Service
public class TimersGathererService {

    @Autowired
    private TimersRegistryAcceptor timersRegistryAcceptor;

    public void start() {
        Thread thread = new Thread(() -> timersRegistryAcceptor.doStartListening());
        thread.start();
    }

    public void stop() {
        timersRegistryAcceptor.stopListening();
    }
}
