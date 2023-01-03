package pt.ulusofona.lp2.deisiJungle

enum class CommandType {
    GET, POST
}

fun getPlayerInfo(manager: GameManager, args: List<String>): String? {
    return "ola"
}

fun get(manager : GameManager, args : List<String>) : String? {

    when (args[0]) {
        "PLAYER_INFO" -> getPlayerInfo(manager, args)
    }

    return null
}

fun post(manager : GameManager, args : List<String>) : String? {

    when (args[0]) {
        "PLAYER_INFO" -> getPlayerInfo(manager, args)
    }

    return null
}

fun routeCommandType(comando : CommandType) : (GameManager, List<String>) -> String? {

    if (comando == CommandType.GET) {
        return :: get
    }

    if (comando == CommandType.POST) {
        return :: post
    }

    return {a, b -> null}
}

fun router() : (CommandType) -> (GameManager, List<String>) -> String? {
    return :: routeCommandType
}