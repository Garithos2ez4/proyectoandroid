package com.example.prunisac.Modelos
import java.io.Serializable
class Venta : java.io.Serializable  {
    // propiedades
    var nomprod:String=""
    var preprod:Double=0.0
    var cantprod:Int=0
    var importe:Double=0.0

    // constructor vacío
    constructor()
    // constructor personalizado
    constructor(nom:String,
                pre:Double,
                cant:Int
    )
    {
        nomprod = nom
        preprod = pre
        cantprod = cant
        importe = preprod * cantprod
    }
}