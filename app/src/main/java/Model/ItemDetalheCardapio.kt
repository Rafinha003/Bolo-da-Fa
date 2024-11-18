package Model

class ItemDetalheCardapio {
    var nome: String? = null
    var descricao: String? = null
    var preco: String? = null

    constructor()
    constructor(nome: String?, descricao: String?, preco: String?){
        this.nome = nome
        this.descricao = descricao
        this.preco = preco
    }
}