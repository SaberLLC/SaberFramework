package pw.saber.framework.utils;

public class NumberUtils {
    public static String format(double number, DecimalFormatType type) {
        return type.getFormat().format(number);
    }

    public static String formatMoney(double number) {
        return format(number, DecimalFormatType.MONEY);
    }

    public static String formatSeconds(double number) {
        return format(number, DecimalFormatType.SECONDS);
    }

    public static Long parseLongOrNull(String val) {
        try {
            return Long.parseLong(val);
        } catch (Exception exception) {
            return null;
        }
    }

    public static Integer parseIntOrNull(String val) {
        try {
            return Integer.parseInt(val);
        } catch (Exception exception) {
            return null;
        }
    }

    public static Double parseDoubleOrNull(String val) {
        try {
            return Double.parseDouble(val);
        } catch (Exception exception) {
            return null;
        }
    }

    public static Float parseFloatOrNull(String val) {
        try {
            return Float.parseFloat(val);
        } catch (Exception exception) {
            return null;
        }
    }

    public static Number parseNumberOrNull(String val) {
        Integer found = parseIntOrNull(val);
        if (found != null)
            return found;
        Long longFound = parseLongOrNull(val);
        if (longFound != null)
            return longFound;
        Double doubleFound = parseDoubleOrNull(val);
        if (doubleFound != null)
            return doubleFound;
        return parseFloatOrNull(val);
    }
}
