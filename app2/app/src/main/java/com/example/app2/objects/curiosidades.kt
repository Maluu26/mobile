package com.example.app2.objects

object curiosidades {
    private var curiosidadesBelinha : MutableList<String> = mutableListOf(
        "Belinha tem 13 anos",
        "Belinha ama queijo (Ela conhece o barulho da queijeira",
        "Belinha nunca foi mãe")

    private var curiosidadesBranquinho :  MutableList<String> = mutableListOf(
        "Branquinho tem olhos azuis",
        "Branquinho odeia água",
        "Braquinho ama árvores de natal")

    private var curiosidadesJuju :  MutableList<String> = mutableListOf(
        "Juju está com Ibama",
        "Juju é de Cachoeira no Recôncavo Baiano",
        "Juju já colocou ovos"
    )


    fun sorteio(): Int{

        val index = (0 .. (curiosidadesBelinha.size - 1)).random()
        return index
    }
    fun getCuriosidadeBelinha(): String{
        if(curiosidadesBelinha.size>0){
            return curiosidadesBelinha.random()
        }
        return " "
    }

    fun getCuriosidadesBranquinho(): String{
        if(curiosidadesBranquinho.size>0) {
            return curiosidadesBranquinho.random()
        }
        return " "
    }

    fun getCuriosidadesJuju(): String{
        if(curiosidadesJuju.size>0) {
            return curiosidadesJuju.random()
        }
        return " "
    }

    fun removeCuriosidades(frase: String){
        if(curiosidadesBelinha.contains(frase)){
            curiosidadesBelinha.remove(frase)
        }
        else if(curiosidadesBranquinho.contains(frase)){
            curiosidadesBranquinho.remove(frase)
        }else{
            curiosidadesJuju.remove(frase)
        }
    }
}