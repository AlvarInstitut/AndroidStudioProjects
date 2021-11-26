

class Adreca (var carrer: String?, var codipostal: String?, var poblacio: String?)

class Telefon( var mobil : Boolean? , var numero: String?)

class Empleat (
    var nif: String? , var nom: String? , var departament: Int? , var edat: Int? = 0 , var sou: Double? = 0.0 ,
    var foto: Array<Byte>? ,
    var curriculum: Array<Char>? ,
    var adreca: Adreca? ,
    var correus_e: Array<String>? ,
    var telefons : Array<Telefon>?
) {
    constructor(nif: String?) : this(nif,null,null,null,null,null,null,null,null,null)
}

