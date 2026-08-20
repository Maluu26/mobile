fun e1(){
    var n: Int = readln().toInt()
    var x: Int = 1
    for (i in 1 .. (n)) {
        //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
        for(j in 1 .. i )// for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            print("$x ")
        x++
        println()
    }
}

fun validaSenha(senha: String){
    var hasNum : Boolean = false
    var hasUpper : Boolean = false
    if (senha.length < 8){
        print("SENHA INVÁLIDA")
        return
    }

    for(j in 0 .. (senha.length- 1)){
        if(senha[j].isDigit()){
            hasNum = true
        }
        else if(senha[j].isUpperCase()){
            hasUpper = true
        }
    }

    if(hasNum && hasUpper){
        print("SENHA VÁLIDA")
        return
    }
    print("SENHA INVÁLIDA")
}

// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() { //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
    // to see how IntelliJ IDEA suggests fixing it.
    var senha: String = readln()
    validaSenha(senha)

}