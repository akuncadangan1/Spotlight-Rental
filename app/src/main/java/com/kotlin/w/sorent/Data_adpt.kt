package com.kotlin.w.sorent

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class Data_adpt(val mCtx: Context?, val layoutId:Int, val datlist: MutableList<Data2>)
    : ArrayAdapter<Data2>(mCtx,layoutId,datlist) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(layoutId, null)

        val Nama = view.findViewById<TextView>(R.id.lnama)
        val Acara = view.findViewById<TextView>(R.id.lacara)
        val Estimasi = view.findViewById<TextView>(R.id.lestimasi)
        val Jumlah = view.findViewById<TextView>(R.id.ljumlah)
        val Harga = view.findViewById<TextView>(R.id.lharga)

        val btn_edit = view.findViewById<TextView>(R.id.button_edit)
        val btn_delete = view.findViewById<TextView>(R.id.button_delete)

        val data1 = datlist[position]

        Nama.text = "Nama   : "+data1.nama1
        Acara.text = "Acara   : "+data1.acara1
        Estimasi.text = "Durasi   : "+data1.estimasi1+"hari"
        Jumlah.text = "Jumlah   : "+data1.jumlha1
        Harga.text = "Harga   : "+data1.harga1

        return view
    }
}