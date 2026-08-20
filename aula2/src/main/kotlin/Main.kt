package org.example

class produto (var nome: String, var quant: Int, var descricao:String?){
    init {
        descricao?:descricaoNula()
    }
    private fun descricaoNula(){
        descricao = "Sem Descrição"
    }
    fun imprime(){
        println("Produto:${nome}\n" +
                "Quantidade em estoque: ${quant}\n" +
                "Descrição: ${descricao}")
    }
}

fun ex2(){
    var topPalavras = readln().toInt()
    var frase = readln()

    val palavras = mutableListOf<String>(frase)
    println(palavras)
}

fun main() {
    var p: produto = produto("Caneta",100, null);
    p.imprime()
    //var nome = readln()
    //var idade = readln().toInt()
    //var descricao = readlnOrNull()
    //var p2: produto = produto(nome, idade, descricao)
    //p2.imprime()

    ex2()
}