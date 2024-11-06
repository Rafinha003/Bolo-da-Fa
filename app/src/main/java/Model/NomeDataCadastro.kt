package Model

class NomeDataCadastro {
    var nome: String? = null
    var data: String? = null

    constructor()
    constructor(nome: String?, data: String?){
      this.nome = nome
      this.data = data
    }
}