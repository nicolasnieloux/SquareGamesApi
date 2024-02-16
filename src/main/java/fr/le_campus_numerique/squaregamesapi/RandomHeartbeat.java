package fr.le_campus_numerique.squaregamesapi;

import org.springframework.stereotype.Service;

@Service
public class RandomHeartbeat implements HeartbeatSensor {
    @Override
    public int get() {
        return 0;
    }
}
