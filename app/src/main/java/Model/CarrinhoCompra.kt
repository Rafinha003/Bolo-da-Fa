package Model

class CarrinhoCompra {
    var email: String? = null
    var preco: String? = null
    var produto: String? = null
    var quantidade: Long? = null

    constructor()
    constructor(email: String?,  produto: String?, preco: String?,  quantidade: Long? = null){
        this.email = email
        this.preco = preco
        this.produto = produto
        this.quantidade = quantidade
    }
}