package com.lsedillo.Model;

public class Time extends Unit{
    private Double seconds;
    private String niceName;

    public Time(Double value, String timeUnit) {
        super(value, timeUnit.toUpperCase());
        this.seconds = getValue() * TimeUnit.valueOf(this.getUnit()).seconds;
        this.niceName = TimeUnit.valueOf(this.getUnit()).name;
    }

    /**
     * Special constructor that takes a Data and Rate object, and returns the amount of Time in seconds required to
     * transfer an amount of Data at a particular Rate
     * @param data The Data object
     * @param rate The Rate object
     */
    public Time(Data data, Rate rate) {
        this(data.getValue() / (rate.convertTo(data.getUnit() + "/S")).getValue(), "second");
        Rate convertedRate = (Rate) rate.convertTo(data.getUnit() + "/S");
        double value = data.getValue() / convertedRate.getValue();
    }


    public Time convertTo(String other) {
        TimeUnit unit = TimeUnit.valueOf(getUnit());
        TimeUnit otherUnit = TimeUnit.valueOf(other);
        Double newValue = getValue() * unit.seconds / otherUnit.seconds;
        return new Time(newValue, other);
    }

    @Override
    public String toString() {
        return readableTime(seconds);
    }

    /**
     * Converts a raw number of seconds into all the available time units
     * @param seconds The number of seconds
     * @return The human-readable time
     */
    public static String readableTime(double seconds) {
        StringBuilder result = new StringBuilder();
        int monthsLeft = readableAux((int)seconds, result, TimeUnit.YEAR);
        int weeksLeft = readableAux(monthsLeft, result, TimeUnit.MONTH);
        int daysLeft = readableAux(weeksLeft, result, TimeUnit.WEEK);
        int hoursLeft = readableAux(daysLeft, result, TimeUnit.DAY);
        int minutesLeft = readableAux(hoursLeft, result, TimeUnit.HOUR);
        int secondsLeft = readableAux(minutesLeft, result, TimeUnit.MINUTE);
        result.append((secondsLeft < 1) ? "" : secondsLeft + " " + TimeUnit.SECOND.name);
        return result.toString();
    }

    /**
     * An auxiliary method for the <code>readableTime</code> method for use on each time unit
     * @param seconds The raw number of seconds remaining
     * @param result The string of results that is growing by one time unit each time
     * @param unit The current time unit that is being processed
     * @return An integer representing the number of seconds left after some have been
     * reincarnated inside the result string
     */
    private static int readableAux(int seconds, StringBuilder result, TimeUnit unit) {
        int q = seconds / (int)unit.seconds;
        int r = seconds % (int)unit.seconds;
        result.append((q < 1) ? "" : q + " " + unit.name + " ");
        return r;
    }

    public enum TimeUnit {
        SECOND(1, "seconds"),
        MINUTE(60, "minutes"),
        HOUR(3600, "hours"),
        DAY(3600 * 24, "days"),
        WEEK(3600*24*7, "weeks"),
        MONTH(3600 * 24 * 30, "months"),
        YEAR(3600 * 24 * 365, "years");

        public double seconds;
        public String name;

        /**
         * Constructor that takes a value in seconds and a string name
         * @param seconds The number of seconds
         * @param name The name
         */
        TimeUnit(double seconds, String name) {
            this.seconds = seconds;
            this.name = name;
        }
    }
}

