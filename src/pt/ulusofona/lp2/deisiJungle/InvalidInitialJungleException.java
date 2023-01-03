package pt.ulusofona.lp2.deisiJungle;

public class InvalidInitialJungleException extends Exception {
    private final String erro;
    private final boolean invalidPlayer;
    private final boolean isInvalidFood;

    public InvalidInitialJungleException(String erro, boolean invalidPlayer, boolean invalidFood) {
        this.erro = erro;
        this.invalidPlayer = invalidPlayer;
        this.isInvalidFood = invalidFood;
    }

    public String getMessage() {
        return erro;
    }

    public boolean isInvalidPlayer() {
        return invalidPlayer;
    }

    public boolean isInvalidFood() {
        return isInvalidFood;
    }
}
