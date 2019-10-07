package com.kotlin.w.sorent


import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class Home_frag : Fragment() {

    lateinit var tgl: String
    var jumlah: Int = 0
    var jumlah2: Int = 0
    var hrghalogen: Int = 0
    var hrgtot: Int = 0
    var temp_jmlhalogen : Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_home, container, false)

        var nama1:EditText = view.findViewById(R.id.nama)
        var acara1:EditText = view.findViewById(R.id.acara)
        var estimasi1:EditText = view.findViewById(R.id.estimasi)
        var tanggal1:EditText = view.findViewById(R.id.tanggal)
        var book1:Button = view.findViewById(R.id.book)
        var btninc:Button = view.findViewById(R.id.button_increase)
        var btndec:Button = view.findViewById(R.id.button_decrease)
        var jmlhalogen:TextView = view.findViewById(R.id.jumlahhalogen)
        var hrgttl:TextView = view.findViewById(R.id.hargatotal)

        tanggal1.setOnClickListener {
            val now = Calendar.getInstance()
            val datepick = DatePickerDialog(context, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                tgl = dayOfMonth.toString()+"/"+month.toString()+"/"+year.toString()
                tanggal1.setText(tgl)
            },
                    now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH))
            datepick.show()
        }

        book1.setOnClickListener {
            val Nama = nama1.text.toString().trim()
            val Acara = acara1.text.toString().trim()
            val Estimasi = estimasi1.text.toString().trim()
            val Tanggal = tanggal1.text.toString().trim()
            val Jumlah = jmlhalogen.text.toString().trim()
            val Harga = hrgttl.text.toString().trim()

            if (Nama.isEmpty() or Acara.isEmpty() or Estimasi.isEmpty() or Tanggal.isEmpty() or Jumlah.isEmpty() or Harga.isEmpty()){
                Toast.makeText(context, "Data belum lengkap!",Toast.LENGTH_SHORT).show()
            } else{
                val ref = FirebaseDatabase.getInstance().getReference("Data_Sewa")
                val data_id = ref.push().key

                val data1 = Data(data_id, Nama, Acara, Estimasi, Tanggal, Jumlah, Harga)

                ref.child(data_id).setValue(data1).addOnCompleteListener{
                    Toast.makeText(context, "Data berhasil disimpan",Toast.LENGTH_SHORT).show()
                    nama1.setText("")
                    acara1.setText("")
                    estimasi1.setText("")
                    tanggal1.setText("")
                    jmlhalogen.setText("0")
                    hrgttl.setText("-")
                }
            }
        }

        btninc.setOnClickListener {
            jumlah++
            jmlhalogen.setText(jumlah.toString())
            val Estimasi2 = estimasi1.text.toString().trim()
            if (Estimasi2.isNotEmpty()){
                var hari:Int = estimasi1.text.toString().toInt()
                hrghalogen = (jumlah*25000)*hari
                hrgttl.setText("Rp "+hrghalogen.toString())
            } else{
                hrghalogen = (jumlah*25000)
                hrgttl.setText("Rp "+hrghalogen.toString())
            }
        }
        btndec.setOnClickListener {
            if (jumlah > 0) {
                jumlah--
                jmlhalogen.setText(jumlah.toString())
                val Estimasi2 = estimasi1.text.toString().trim()
                if (Estimasi2.isNotEmpty()){
                    var hari:Int = estimasi1.text.toString().toInt()
                    hrghalogen = (jumlah*25000)*hari
                    hrgttl.setText("Rp "+hrghalogen.toString())
                }else{
                    hrghalogen = (jumlah*25000)
                    hrgttl.setText("Rp "+hrghalogen.toString())
                }
            }
        }
//
//        jmlhalogen.addTextChangedListener(object : TextWatcher{
//            override fun afterTextChanged(s: Editable?) {}
//
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                temp_jmlhalogen = s.toString().toInt()
//                if (temp_jmlhalogen>0){
//                    hrghalogen = temp_jmlhalogen*25000
//                }else{
//                    hrghalogen = 0
//                }
//                hrgttl.setText("Rp "+hrghalogen.toString())
//            }
//        })
        return view
    }
}
