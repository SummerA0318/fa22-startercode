public class ThreeSixNine {

    public static int[] the369Clap(int n) {
        int[] result = new int[n];
        for (int i = 1; i <= n; i++) {
            String nStr = String.valueOf(i);
            if (i % 3 == 0 | nStr.contains("3") | nStr.contains("6") | nStr.contains("9")) {
                result[i-1] = -1;
            }
            else {
                result[i-1] = i;
            }
        }
        return result;
    }

    /* for test purpose */
    public static void main(String[] args) {
        int n = 40;
        int[] output;
        output = the369Clap(n);

        System.out.println(java.util.Arrays.toString(output));
        // Should print [1, 2, -1, 4, 5, -1, 7, 8, -1, 10, 11, -1,
        // -1, 14, -1, -1, 17, -1, -1, 20, -1, 22, -1, -1, 25, -1,
        // -1, 28, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 40]
    }

}
