package net.dzultra.jfa.types.statistics;

import org.jetbrains.annotations.Nullable;

import java.time.Month;
import java.time.Year;

public class InitialGamemodeJoinDateStat {
    @Nullable
    private final String string;
    @Nullable
    private final Year year;
    @Nullable
    private final Month month;
    @Nullable
    private final Integer day;

    public InitialGamemodeJoinDateStat(@Nullable String string) {
        if (string == null || string.equals("Never played")) {
            this.string = null;
            this.year = null;
            this.month = null;
            this.day = null;
        } else {
            this.string = string;
            String[] splitString = string.split(" ");
            switch (splitString[2]) {
                case "Jan" -> this.month = Month.JANUARY;
                case "Feb" -> this.month = Month.FEBRUARY;
                case "Mar" -> this.month = Month.MARCH;
                case "Apr" -> this.month = Month.APRIL;
                case "May" -> this.month = Month.MAY;
                case "Jun" -> this.month = Month.JUNE;
                case "Jul" -> this.month = Month.JULY;
                case "Aug" -> this.month = Month.AUGUST;
                case "Sep" -> this.month = Month.SEPTEMBER;
                case "Oct" -> this.month = Month.OCTOBER;
                case "Nov" -> this.month = Month.NOVEMBER;
                case "Dec" -> this.month = Month.DECEMBER;
                default -> this.month = null;
            }

            this.day = Integer.parseInt(splitString[3].split(",")[0]);
            this.year = Year.of(Integer.parseInt(splitString[4]));
        }

    }

    @Nullable
    public Integer getDay() {
        return day;
    }

    @Nullable
    public Month getMonth() {
        return month;
    }

    @Nullable
    public Year getYear() {
        return year;
    }

    @Nullable
    public String asString() {
        return string;
    }
}
