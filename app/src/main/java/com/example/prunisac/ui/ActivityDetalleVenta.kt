package com.example.prunisac.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.prunisac.Modelos.Venta
import com.example.prunisac.R
import com.example.prunisac.databinding.ActivityDetalleVentaBinding

class ActivityDetalleVenta : AppCompatActivity() {

    private lateinit var binding: ActivityDetalleVentaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding= ActivityDetalleVentaBinding.inflate(layoutInflater)
        setContentView(binding.root)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var x = intent
        if (x !=null){
            val vta= x.getSerializableExtra("datos") as Venta

            var nom = x.getStringExtra("nombre")
            var pre = x.getDoubleExtra("precio", 0.0)

            var cadena = "Producto: ${vta.nomprod}\n"+
                         "Precio: ${vta.preprod}\n "+
                         "Cantidad:${vta.cantprod}\n "+
                         "Importe:${vta.importe}\n"+
                         "Nombre:$nom\n"+
                         "Precio2:$pre"

            binding.tvDetalle.text=cadena
        }
    }
}