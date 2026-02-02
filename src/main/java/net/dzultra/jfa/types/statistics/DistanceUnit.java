package net.dzultra.jfa.types.statistics;

public enum DistanceUnit {
    BLOCKS(1.0),
    KILOMETER(1000.0);

    private final double blocks;

    DistanceUnit(double blocks) {
        this.blocks = blocks;
    }

    public double toBlocks(double value) {
        return value * blocks;
    }

    public double fromBlocks(double meters) {
        return meters / this.blocks;
    }
}
