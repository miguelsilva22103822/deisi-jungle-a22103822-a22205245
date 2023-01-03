package pt.ulusofona.lp2.deisiJungle

enum class CommandType {
    GET, POST
}

fun router(): (CommandType) -> (GameManager, List<String>) -> String? {
    return :: routeCommandType
}

fun routeCommandType(comando : CommandType): (GameManager, List<String>) -> String? {

    if (comando == CommandType.GET) {
        return :: get
    }

    if (comando == CommandType.POST) {
        return :: post
    }

    return {a, b -> null}
}

fun get(manager : GameManager, args : List<String>): String? {

    if (args.isEmpty()) {
        return null
    }

    when (args[0]) {
        "PLAYER_INFO" -> return getPlayerInfo(manager, args)
        "PLAYERS_BY_SPECIE" -> return getPlayersBySpecie(manager, args)
        "MOST_TRAVELED" -> return getMostTraveled(manager, args)
        "TOP_ENERGETIC_OMNIVORES" -> return getTopEnergeticOmnivores(manager, args)
        "CONSUMED_FOODS" -> return getConsumedFoods(manager, args)
    }

    return null
}

fun post(manager : GameManager, args : List<String>) : String? {

    if (args.isEmpty()) {
        return null
    }

    when (args[0]) {
        "MOVE" -> return postMove(manager, args)
    }

    return null
}

fun getPlayerInfo(manager: GameManager, args: List<String>): String? {

    if (args.size != 2) {
        return null
    }

    val players = manager.jogadores.filter { a -> a.nome == args[1] }

    if (players.isEmpty()) {
        return "Inexistent player"
    }

    val player = players[0]

    return "${player.id} | ${player.nome} | ${player.especie.nome} | ${player.energia} | bruh"
}

fun getPlayersBySpecie(manager: GameManager, args: List<String>): String? {
    return null
}

fun getMostTraveled(manager: GameManager, args: List<String>): String? {
    return null
}

fun getTopEnergeticOmnivores(manager: GameManager, args: List<String>): String? {
    return null
}

fun getConsumedFoods(manager: GameManager, args: List<String>): String? {
    return null
}

fun postMove(manager: GameManager, args: List<String>): String? {
    return null
}


fun main() {

    val manager = GameManager()

    val players = arrayOf(
        arrayOf("1", "Pedro", "L"),
        arrayOf("2", "Maria", "E")
    )

    val foods = arrayOf(
        arrayOf("b", "5"),
        arrayOf("e", "2")
    )

    manager.createInitialJungle(30, players, foods)

    val routerFn = router()
    val commandGetFn = routerFn.invoke(CommandType.GET)
    val result = commandGetFn.invoke(manager, listOf("PLAYER_INFO", "Pedro"))

    println(result)
}
