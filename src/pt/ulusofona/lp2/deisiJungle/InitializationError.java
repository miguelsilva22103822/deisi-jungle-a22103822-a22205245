package pt.ulusofona.lp2.deisiJungle;

class InitializationError {
    private final String erro;

    public InitializationError(String erro) {
        this.erro = erro;
    }

    public String getMessage()  {
        return erro;
    }
}
