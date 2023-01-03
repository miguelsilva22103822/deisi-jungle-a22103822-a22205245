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

fun xpto(comando : CommandType) : (GameManager, List<String>) -> String? {

    if (comando == CommandType.GET) {
        return :: get
    }

    if (comando == CommandType.POST) {
        return :: post
    }

    return {a, b -> null}
}

fun router() : (CommandType) -> (GameManager, List<String>) -> String? {
    return :: xpto
}







fun test {
    val routerFn = router()
    val commandGetFn = routerFn.invoke(CommandType.GET)
    val result = commandGetFn.invoke(manager, listOf("PLAYER_INFO", "Pedro"))
}
