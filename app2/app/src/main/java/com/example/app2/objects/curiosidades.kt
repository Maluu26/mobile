package com.example.app2.objects

object curiosidades {
    private var curiosidadesBelinha : List<String> = mutableListOf(
        "Belinha tem 13 anos",
        "Belinha ama queijo (Ela conhece o barulho da queijeira",
        "Belinha nunca foi mãe")

    private var curiosidadesBranquinho : List<String> = mutableListOf(
        "Branquinho tem olhos azuis",
        "Branquinho odeia água",
        "Braquinho ama árvores de natal")

    fun sorteio(): Int{
        val index = (0 .. (curiosidadesBelinha.size - 1)).random()
        return index
    }
    fun getCuriosidadeBelinha(): String{
        return curiosidadesBelinha[sorteio()]
    }

    fun getCuriosidadesBranquinho(): String{
        return curiosidadesBranquinho[sorteio()]
    }


}