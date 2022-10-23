/**
 * Name: Yubing Lin
 * PID: A16994291
 */

/**
 * A class containing nine coding tasks  to be complete.
 * @author Yubing Lin
 * @since 25 September, 2022
 */
public class ProgrammingChallenges {
    public static final int DECIMAL_FACTOR = 1000;
    public static final int PAIR_THRESHOLD = 2;
    public static final int HALF_FACTOR = 2;
    public static final int STRONG_LENGTH = 8;
    public static final int WEAK_THRESHOLD = 2;
    public static final int THIRD_SEQUENCE = 2;
    /////// Challenge 1 ///////
    /**
     * Determine if the score sells my wanted items and can I afford
     * @param item the item the score sells
     * @param inWallet the amount of money I have
     * @param needed the amount of money I need to buy the item
     * @return true if my wanted item is provided and I can afford it, false otherwise
     */
    public static boolean store(String item, float inWallet, float needed) {
        if (item == "cake" | item == "ice-cream" | item == "sushi") {
            if (inWallet > needed) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }
    /////// Challenge 2 ///////
    /**
     * Determine if the first digit of number 1 is the same as the last digit of number 2
     * @param num1 the first number being checked its first digit
     * @param num2 the second number being checked its last digit
     * @return true if first digit of num1 equals last digit of num2, false otherwise
     */
    public static boolean sameDigitFirstAndLast (int num1, int  num2) {
        String num1First = String.valueOf(num1).substring(0, 1);
        String num2String = String.valueOf(num2);
        int len = num2String.length();
        String num2Last = num2String.substring(len - 1, len);
        if (num1First.equals(num2Last)) {
            return true;
        }
        else {
            return false;
        }
    }
    /////// Challenge 3 ///////
    /**
     * Determine if there are three consecutive decreasing number in the given array
     * @param elems the given array of numbers
     * @return true if there are three consecutive decreasing number in elems, false otherwise
     */
    public static boolean decreasingOrder (int[] elems) {
        for (int i = 0; i < elems.length - THIRD_SEQUENCE; i++) {
            if (elems[i] > elems[i+1] & elems[i+1] > elems[i+THIRD_SEQUENCE]) {
                return true;
            }
        }
        return false;
    }

    /////// Challenge 4 ///////
    /**
     * Calculate the average gpa of the required homework
     * @param grades an 2-D array of each student as row and each assignment as column
     * @param assignmentIndex the index of the assignment to calculate average gpa
     * @return the average gpa of all students in grades on the required homework
     */
    public static float averageGrade(int[][] grades, int assignmentIndex) {
        float gpa = (float)0.0;
        int num = grades.length;
        for (int i = 0; i < num; i++) {
            gpa += grades[i][assignmentIndex];
        }
        float ave_thousand = gpa / num * DECIMAL_FACTOR;
        float result = (float)Math.round(ave_thousand) / DECIMAL_FACTOR;
        return result;
    }

    /////// Challenge 5 ///////
    /**
     * Find the second large number in the given array
     * @param a the given array of integers to find the second maximum
     * @return the largest integer if array a only contains 0 or 1 elements,
     * the second maximum otherwise
     */
    public static int secondMax(int[] a) {
        // Set the first int in array to be maximum and the minimum number to be second maximum
        // Iterate over the array
        // If an element is larger than maximum then let original maximum be second maximum
        // and the element be the new maximum
        // If an element is larger than second maximum but smaller than maximum, update second max
        int len = a.length;
        int maximum = a[0];
        int secMax = Integer.MIN_VALUE;
        if (len <= 1) {
            return Integer.MAX_VALUE;
        }
        else {
            for (int i = 1; i < len; i++) {
                if (a[i] > maximum) {
                    secMax = maximum;
                    maximum = a[i];
                } else if (a[i] > secMax){
                    secMax = a[i];
                }
                else {
                    continue;
                }
            }
            return secMax;
        }
    }

    /////// Challenge 6 ///////
    /**
     * Delete all the dots in the given string
     * @param str the given string to delete all dots in it
     * @return a string same as str with all dots in it deleted
     */
    public static String noDots(String str) {
        int len = str.length();
        if (len == 1) {
            if (str.equals(".")) {
                return "";
            }
            else {
                return str;
            }
        }
        else {
            String firstChar = str.substring(0, 1);
            if (firstChar.equals(".")) {
                return noDots(str.substring(1, len));
            }
            else {
                return firstChar + noDots(str.substring(1, len));
            }
        }
    }

    /////// Challenge 7 ///////
    /**
     * Return the two middle elements in the given array
     * @param elems the given array containing integers
     * @return an empty array if elems contains less than two elements
     * the first and last elements if elems contains odd numbers of elements
     * the middle two elements if elems contains even numbers of elements
     */
    public static int[] twoElements (int[] elems) {
        int len = elems.length;
        int[] emptyArr = {};
        if (len < PAIR_THRESHOLD) {
            return emptyArr;
        } else if (len % PAIR_THRESHOLD != 0) {
            int[] oddArr = {elems[0], elems[len-1]};
            return oddArr;
        } else {
            int middleIndex = len / HALF_FACTOR;
            int[] evenArr = {elems[middleIndex-1], elems[middleIndex]};
            return evenArr;
        }
    }

    /////// Challenge 8 ///////
    /**
     * Check whether the given password is strong based on letter case, number,
     * length and special characters
     * @param input the given password to be tested
     * @return Not Acceptable if input fulfills 0 criteria, Weak if input fulfills 1-2 criteria,
     * Strong if input fulfills more than 3 criteria
     */
    public static String checkPasswordStrength(String input) {
        // Check each criteria individually and record the number of fulfilled criteria
        int fulfilled = 0;
        int len = input.length();
        if (input.length() >= STRONG_LENGTH) {
            fulfilled += 1;
        }
        if (!input.toLowerCase().equals(input) & !input.toUpperCase().equals(input)) {
            fulfilled += 1;
        }
        for (int i = 0; i < len; i++) {
            if (Character.isDigit(input.charAt(i))) {
                fulfilled += 1;
                break;
            }
        }
        for (int i = 0; i < len; i++) {
            if (!Character.isAlphabetic(input.charAt(i)) &
        !Character.isDigit(input.charAt(i))) {
                fulfilled += 1;
                break;
            }
        }
        if (fulfilled == 0) {
            return "Not Acceptable";
        } else if (fulfilled <= WEAK_THRESHOLD) {
            return "Weak";
        } else {
            return "Strong";
        }
    }

    /////// Challenge 9 ///////
    /**
     * Make subgroups of given int array of given group size and return product of each subgroup,
     * if can't be divided evenly then only return empty array
     * @param arr given array to make subgroups from
     * @param groupSize the size of subgroups
     * @return array of product of each subgroup if arr can be divided into subgroups of groupSize,
     * empty array otherwise
     */
    public static int[] multiplyByGroup (int[] arr, int groupSize) {
        int len = arr.length;
        int product = 1;
        int size = 0;
        int index = -1;
        int[] emptyArr = {};
        if (len % groupSize != 0) {
            return emptyArr;
        }
        int[] result = new int[len / groupSize];
        //Time the numbers until the group size is reached, record the product and calculate
        //that of the next subgroup until every element in the array is timed in the subgroups
        for (int i = 0; i < len; i++) {
            product *= arr[i];
            size += 1;
            if (size == groupSize) {
                index += 1;
                result[index] = product;
                product = 1;
                size = 0;
            }
        }
        return result;
    }
    //If you want to implement part3, you have to create a new java file(see writeup!)
}
