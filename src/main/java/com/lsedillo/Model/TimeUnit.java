package com.lsedillo.Model;

public enum TimeUnit {
    SECOND(1, "seconds"),
    MINUTE(60, "minutes"),
    HOUR(3600, "hours"),
    DAY(3600 * 24, "days"),
    WEEK(3600*24*7, "weeks"),
    MONTH(3600 * 24 * 30.436875, "months"),
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

    /**
     * Converts from one time unit to another by comparing their second-values
     * @param num The number that is being converted
     * @param from The unit that is being converted
     * @param to The unit to convert to
     * @return The converted number
     */
    public static double convert(double num, TimeUnit from, TimeUnit to) {
        return (num * from.seconds) / to.seconds;
    }

    /**
     * Same as the other convert, but with an implied number of 1
     * @param from The unit to convert from
     * @param to The unit to convert to
     * @return The resulting number value
     */
    public static double convert(TimeUnit from, TimeUnit to) {
        return (from.seconds) / to.seconds;
    }

//
//    public static void main(String[] args) {
//        System.out.println(convert(1, TimeUnit.DAY, TimeUnit.SECOND));
//    }

    /**
     * Converts a raw number of seconds into all the available time units
     * @param seconds The number of seconds
     * @return The human-readable time
     */
    public static String readableTime(double seconds) {
        StringBuilder result = new StringBuilder();
        int monthsLeft = readableAux((int)seconds, result, TimeUnit.YEAR);
        int weeksLeft = readableAux(monthsLeft, result, TimeUnit.WEEK);
        int daysLeft = readableAux(weeksLeft, result, TimeUnit.WEEK);
        int hoursLeft = readableAux(daysLeft, result, TimeUnit.DAY);
        int minutesLeft = readableAux(hoursLeft, result, TimeUnit.HOUR);
        int secondsLeft = readableAux(minutesLeft, result, TimeUnit.MINUTE);
        result.append((secondsLeft < 1) ? "" : secondsLeft + " " + SECOND.name);
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
}
