package Model

class CarrinhoCompra {
    var email: String? = null
    var quantidade: Number? = null
    var produto: String? = null
    var preco: String? = null

    constructor()
    constructor(email: String?, quantidade: Number? = null,  produuto: String?, preco: String?){
        this.email = email
        this.quantidade = quantidade
        this.produto = produto
        this.preco = preco
    }
}