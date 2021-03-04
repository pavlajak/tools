package util;

public class PrettyPrinter {

    public static void main(String[] args) {
        System.out.println(bytesToPretty(1038));
    }

    private final static long[] units = {
            1L,
            1024L,
            1024L * 1024,
            1024L * 1024 * 1024,
            1024L * 1024 * 1024 * 1024
    };
    private final static String[] unitsStr = {
            "B",
            "kB",
            "MB",
            "GB",
            "TB"
    };

    public static String bytesToPretty(long bytes){
        if(bytes <= 0) return "";
        int i = 0;
        for (; i < units.length; i++) {
            if(bytes / (long) units[i] < 1) break;
        }
        return bytes / units[i - 1] + "" + unitsStr[i - 1];
    }
}
