package com.kotlin.w.sorent


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.google.firebase.database.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class View_frag : Fragment() {

    lateinit var listview:ListView
    lateinit var ref : DatabaseReference
    lateinit var datalist:MutableList<Data2>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_view_frag, container, false)

        listview = view.findViewById(R.id.listview2)
        datalist = mutableListOf()
        ref = FirebaseDatabase.getInstance().getReference("Data_Sewa")

        ref.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError?) {
            }

            override fun onDataChange(p0: DataSnapshot?) {
                if (p0!!.exists()){
                    datalist.clear()
                    for (e in p0.children){
                        val data = e.getValue(Data2::class.java)
                        datalist.add(data!!)
                    }
                }
                val adapt = Data_adpt(context,R.layout.listview_content,datalist)
                listview.adapter = adapt
            }

        })

        return view
    }


}
