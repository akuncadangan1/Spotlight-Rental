package com.kotlin.w.sorent

import android.app.DatePickerDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.internal.BottomNavigationMenu
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.FragmentTransaction
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var tgl: String
    lateinit var home_frag: Home_frag
    lateinit var view_frag: View_frag

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        home_frag = Home_frag()
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.framelayout, home_frag)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()


        bottomnavbar.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    home_frag = Home_frag()
                    supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.framelayout, home_frag)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit()
                }
                R.id.viewdata -> {
                    view_frag = View_frag()
                    supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.framelayout, view_frag)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit()
                }
            }
            true
        }


//        tanggal.setOnClickListener {
//            val now = Calendar.getInstance()
//            val datepick = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
//                tgl = dayOfMonth.toString()+"/"+month.toString()+"/"+year.toString()
//                tanggal.setText(tgl)
//            },
//                    now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH))
//            datepick.show()
//        }
//
//        book.setOnClickListener {
//            val Nama = nama.text.toString().trim()
//            val Acara = acara.text.toString().trim()
//            val Estimasi = estimasi.text.toString().trim()
//            val Tanggal = tanggal.text.toString().trim()
//
//            if (Nama.isEmpty() or Acara.isEmpty() or Estimasi.isEmpty() or Tanggal.isEmpty()){
//                Toast.makeText(applicationContext, "Data belum lengkap!",Toast.LENGTH_SHORT).show()
//            } else{
//                val ref = FirebaseDatabase.getInstance().getReference("Data_Sewa")
//                val data_id = ref.push().key
//
//                val data1 = Data(data_id, Nama, Acara, Estimasi, Tanggal)
//
//                ref.child(data_id).setValue(data1).addOnCompleteListener{
//                    Toast.makeText(applicationContext, "Data berhasil disimpan",Toast.LENGTH_SHORT).show()
//                    nama.setText("")
//                    acara.setText("")
//                    estimasi.setText("")
//                    tanggal.setText("")
//                }
//            }
//        }
    }
}
