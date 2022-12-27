package pt.ulusofona.lp2.deisiJungle;

class Auxiliar {
    static String[] separarString(String text) {
        text = text.replace("[", "");
        text = text.replace("]", "");

        return text.split(",");
    }
}
