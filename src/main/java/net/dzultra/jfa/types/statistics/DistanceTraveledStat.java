package net.dzultra.jfa.types.statistics;

import net.dzultra.jfa.exceptions.UnknownDistanceUnitException;

public class DistanceTraveledStat {
    private final float distanceTraveled;
    private final DistanceUnit distanceUnit;
    private final String string;

    public DistanceTraveledStat(String string) {
        this.string = string;
        if (string.equals("N/A")) {
            this.distanceTraveled = 0f;
            this.distanceUnit = DistanceUnit.KILOMETER;
        } else {
            String[] splitString = string.split(" ");

            this.distanceTraveled = Float.parseFloat(splitString[0]);

            switch (splitString[1]) {
                case "km" -> this.distanceUnit = DistanceUnit.KILOMETER;
                case "blocks" -> this.distanceUnit = DistanceUnit.BLOCKS;
                default -> throw new UnknownDistanceUnitException(splitString[1]);
            }
        }
    }

    public float getDistanceTraveled() {
        return distanceTraveled;
    }

    public DistanceUnit getDistanceUnit() {
        return distanceUnit;
    }

    public String asString() {
        return this.string;
    }
}
