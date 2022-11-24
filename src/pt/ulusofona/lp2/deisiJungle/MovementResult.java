package pt.ulusofona.lp2.deisiJungle;

class MovementResult {
    MovementResultCode code;
    String message;

    public MovementResult(MovementResultCode code) {
        this.code = code;
    }

    public MovementResult(MovementResultCode code, String message) {
        this.code = code;
        this.message = message;
    }
}
