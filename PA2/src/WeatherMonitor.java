/*
    Name: Yubing Lin
    PID:  A16994291
 */

/**
 * A weather monitor that can find how hot a day is compared to other days
 * @author Yubing Lin
 * @since  Oct 8, 2022
 */

public class WeatherMonitor {
    IntStack weather;
    public static final int DEFAULT_CAPACITY = 10;

    /**
     * Constructor for the class. Initialize weather to be a int stack
     */
    public WeatherMonitor() {
        this.weather = new IntStack(DEFAULT_CAPACITY);
    }

    /**
     * Determine the number of continuing days with lower temperature before the new input day
     * @param temp the temp of current day to add into the stack
     * @return number of consecutive days that have lower temperature than current day temp
     */
    public int numDays(int temp) {
        int num = 0;
        IntStack breakNum = new IntStack(DEFAULT_CAPACITY);
        int size = weather.size();
        if (size == 0) {
            weather.push(temp);
            return num;
        }
        IntStack newWeather = new IntStack(DEFAULT_CAPACITY);
        // Pop out each temperature in the stack to count how many are cooler than current day
        // Continuity is determined by adding the number to an int stack when a past temperature
        // is higher than the current one and then take the first value in the stack which means
        // the most recent number of consecutive cooler days
        while (!weather.isEmpty()) {
            int current = weather.pop();
            newWeather.push(current);
            if (current < temp) {
                num += 1;
            } else {
                breakNum.push(num);
            }
        }
        int[] original = newWeather.multiPop(size);
        this.weather.multiPush(original);
        this.weather.push(temp);
        if (breakNum.isEmpty()) {
            return num;
        } else {
            int[] breakNums = breakNum.multiPop(size);
            return breakNums[breakNums.length-1];
        }
    }
}
