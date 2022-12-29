package pt.ulusofona.lp2.deisiJungle;

public final class StringUtil {

    public static String[] separarString(String text) {
        text = text.replace("[", "");
        text = text.replace("]", "");

        return text.split(",");
    }

}
