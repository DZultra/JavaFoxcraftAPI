package net.dzultra.jfa.types.statistics;

public enum DistanceUnit {
    METER(1.0),
    KILOMETER(1000.0);

    private final double meters;

   DistanceUnit(double meters) {
        this.meters = meters;
    }

    public double toMeters(double value) {
        return value * meters;
    }

    public double fromMeters(double meters) {
        return meters / this.meters;
    }
}
