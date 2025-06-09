package com.example.prunisac.ui

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.prunisac.Modelos.Venta
import com.example.prunisac.R
import com.example.prunisac.databinding.ActivityVentaProductoBinding

class ActivityVentaProducto : AppCompatActivity() {

    var vPrecios = arrayOf(0.0,25.0,750.9,1599.0,340.5,52.4,18.2,68.9)

    private lateinit var binding : ActivityVentaProductoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding= ActivityVentaProductoBinding.inflate(layoutInflater)
        setContentView(binding.root)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // onItemSelectedListener = object : { }
        binding.spinnerProd.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                binding.tvPrecio.text = vPrecios[position].toString()
                MostrarImagenProd(position)

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        binding.btnCalcular.setOnClickListener {
            var importe : Double =
                binding.tvPrecio.text.toString().toDouble() *
                        binding.tiedtCantidad.text.toString().toInt()
            // checkbox de delivery
            var delivery = 0
            if (binding.chkDelivery.isChecked) delivery=25
            // checkbox de garantia
            var garantia = 0.0
            if (binding.chkGarantia.isChecked) garantia = importe * 0.1

            var total = importe + delivery + garantia

            var subtotal:Double = total
            var igv:Double=0.0
            // si es Factura
            if (binding.rbFactura.isChecked)
            {
                subtotal = total / 1.18
                igv = total - subtotal
            }

            MostrarDialogo(total, subtotal, igv)

        }

        binding.btnNuevo.setOnClickListener {
            binding.spinnerProd.setSelection(0)
            binding.tiedtCantidad.setText("")
            binding.chkDelivery.isChecked = false
            binding.chkGarantia.isChecked = false
            binding.rbBoleta.isChecked = true
        }

        binding.btnPagar.setOnClickListener {
            var nprecio = binding.tvPrecio.text.toString().toDouble()
            var ncantidad = binding.tiedtCantidad.text.toString().toInt()
            var nombre = binding.spinnerProd.selectedItem.toString()
            //
            var vta = Venta(nombre, nprecio, ncantidad)
            //
            val i = Intent(this,
                ActivityDetalleVenta::class.java)
            i.putExtra("nombre", nombre)
            i.putExtra("precio", nprecio)

            i.putExtra("datos", vta)
            startActivity(i)
        }


    }

    private fun MostrarDialogo(total: Double, subtotal: Double, igv: Double) {

        val dialogo = AlertDialog.Builder(this)
        var mensaje = "SubTotal: $subtotal \n IGV: $igv \n Total a Pagar: $total"
        //
        dialogo.setTitle("Resumen de Pago")
            .setIcon(R.mipmap.ic_launcher_round)
            .setMessage(mensaje)
            .setPositiveButton("Aceptar", DialogInterface.OnClickListener { dialog, which ->
                dialog.dismiss()
            })
            .show()
    }

    private fun MostrarImagenProd(position: Int) {
        when(position)
        {
            1-> binding.imageProd.setImageResource(R.drawable.p0001)
            2-> binding.imageProd.setImageResource(R.drawable.p0002)
            3-> binding.imageProd.setImageResource(R.drawable.p0003)
            4-> binding.imageProd.setImageResource(R.drawable.p0004)
            5-> binding.imageProd.setImageResource(R.drawable.p0005)
            6-> binding.imageProd.setImageResource(R.drawable.p0006)
            7-> binding.imageProd.setImageResource(R.drawable.p0007)
            else ->
                binding.imageProd.setImageResource(R.drawable.seleccione)
        }

    }
}

