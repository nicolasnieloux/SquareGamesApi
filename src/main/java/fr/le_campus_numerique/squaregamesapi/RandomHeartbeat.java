package fr.le_campus_numerique.squaregamesapi;

import org.springframework.stereotype.Service;

@Service
public class RandomHeartbeat implements HeartbeatSensor {
    @Override
    public int get() {
        int max = 230;
        int min = 40;
        int range = max - min + 1;
        return (int) (Math.random() * range + min);
    }
}
