package net.dzultra.jfa.types.statistics;

import java.util.concurrent.TimeUnit;

public class TimePlayedStat {
    private final float time;
    private final TimeUnit timeUnit;

    public TimePlayedStat(String string) {
        if (string.equals("N/A")) {
            this.time = 0f;
            this.timeUnit = TimeUnit.HOURS;
        } else {
            String[] splitString = string.split(" ");

            this.time = Float.parseFloat(splitString[0]);
            switch (splitString[1]) {
                case "hours" -> this.timeUnit = TimeUnit.HOURS;
                case "minutes" -> this.timeUnit = TimeUnit.MINUTES;
                default -> this.timeUnit = null;
            }
        }
    }

    public float getTime() {
        return this.time;
    }

    public TimeUnit getUnit() {
        return this.timeUnit;
    }

    public String asString() {
        return getTime() + " " + getUnit().toString().toLowerCase();
    }
}
