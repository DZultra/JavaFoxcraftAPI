package net.dzultra.jfa.types;

public enum Period {
    DAILY("daily"),
    WEEKLY("weekly"),
    MONTHLY("monthly"),
    YEARLY("yearly"),
    ALL_TIME("alltime");

    private final String name;

    Period(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
