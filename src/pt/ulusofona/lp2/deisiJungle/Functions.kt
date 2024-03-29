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

    return "${player.id} | ${player.nome} | ${player.especieName} | ${player.energia} | ${player.casaAtual}"
}

fun getPlayersBySpecie(manager: GameManager, args: List<String>): String? {

    if (args.size != 2) {
        return null
    }

    val especies = manager.jogadores.filter { jogador -> jogador.especieId == args[1] }

    if (especies.isEmpty()) {
        return ""
    }

    return especies.map { it.nome }
        .sorted()
        .reversed()
        .joinToString(separator = ",")
}

fun getMostTraveled(manager: GameManager, args: List<String>): String? {

    if (args.size != 1) {
        return null
    }

    if (manager.jogadores == null) {
        return null
    }

    val players = manager.jogadores
        .sortedBy { it.distanciaPercorrida }
        .reversed()

    val distanciaTotal = players.sumOf { it.distanciaPercorrida }

    return players
        .joinToString(separator = "\n") { "${it.nome}:${it.especieId}:${it.distanciaPercorrida}" }
        .plus("\nTotal:$distanciaTotal")

}

fun getTopEnergeticOmnivores(manager: GameManager, args: List<String>): String? {

    if(args.size != 2){
        return null
    }

    val playersOmnivoros = manager.jogadores.filter { it.dieta == "o" }
        .sortedWith { n1,n2 -> n2.energia - n1.energia }
        .take(args[1].toInt())


    return playersOmnivoros
        .joinToString(separator = "\n") { "${it.nome}:${it.energia}"}

}

fun getConsumedFoods(manager: GameManager, args: List<String>): String? {
    // um array para guardar os alimentos que sempre um jogador move

    val foodConsumed = manager.alimentosIngeridos
        .map { it.nome }
        .distinct()
        .sorted()
        .joinToString(separator = "\n")

    return foodConsumed
}

fun postMove(manager: GameManager, args: List<String>): String? {

    if(args.size != 2) {
        return null
    }

    val casaDestino = manager.casaDoJogadorAtual + args[1].toInt()

    if(casaDestino < 1 || casaDestino > manager.nrCasas) {
        manager.updateJogada()
        return "Movimento invalido"
    }

    val movementResult = manager.moveCurrentPlayer(args[1].toInt(),true)


    when(movementResult.code) {
        MovementResultCode.CAUGHT_FOOD -> return "Apanhou comida"
        MovementResultCode.INVALID_MOVEMENT -> return "Movimento invalido"
        MovementResultCode.NO_ENERGY -> return "Sem energia"
        MovementResultCode.VALID_MOVEMENT -> return "OK"
    }

}

fun main() {

    val manager = GameManager()

    val players = arrayOf(
        arrayOf("1", "Pedro", "T"),
        arrayOf("2", "Maria", "P"),
        arrayOf("3", "Ana", "E")
    )

    val foods = arrayOf(
        arrayOf("b", "5"),
        arrayOf("e", "2")
    )


    manager.createInitialJungle(30, players, foods)

    manager.moveCurrentPlayer(1,true)
    manager.moveCurrentPlayer(1,true)
    manager.moveCurrentPlayer(1,true)

    manager.moveCurrentPlayer(3,true)


    val routerFn = router()
    val commandGetFn = routerFn.invoke(CommandType.GET)
    val result = commandGetFn.invoke(manager, listOf("CONSUMED_FOODS"))

    println(result)
}
