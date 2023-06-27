package com.odzjeffrey.transparencynow

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.google.android.material.floatingactionbutton.FloatingActionButton

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var chatButton: FloatingActionButton
private lateinit var category_list_of_items: ArrayList<String>
private lateinit var date_list_of_items: ArrayList<String>
private lateinit var year_list_of_items: ArrayList<String>
private lateinit var categorySpinner: Spinner
private lateinit var dateSpinner: Spinner
private lateinit var yearSpinner: Spinner

class PartnerFragment : Fragment(), AdapterView.OnItemSelectedListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_access, container, false)
        chatButton = view.findViewById<FloatingActionButton>(R.id.floatingActionButtonTab2)
        categorySpinner = view.findViewById<Spinner>(R.id.categorySpinner)
        dateSpinner = view.findViewById<Spinner>(R.id.monthSpinner)
        yearSpinner = view.findViewById<Spinner>(R.id.yearSpinner)
        chatButton.setOnClickListener {
            startActivity(Intent(context, Login::class.java))
        }

        categorySpinner.onItemSelectedListener = this
        dateSpinner.onItemSelectedListener = this
        categorySpinner.onItemSelectedListener = this
        category_list_of_items = setUpCategories()
        date_list_of_items = setUpDates()
        year_list_of_items = setUpYears()

        val adapter = ArrayAdapter(context!!, android.R.layout.simple_spinner_item, category_list_of_items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val adapter2 = ArrayAdapter(context!!, android.R.layout.simple_spinner_item, date_list_of_items)
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val adapter3 = ArrayAdapter(context!!, android.R.layout.simple_spinner_item, year_list_of_items)
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categorySpinner.adapter = adapter
        dateSpinner.adapter = adapter2
        yearSpinner.adapter = adapter3
        return view
    }

    private fun setUpYears(): ArrayList<String> {
        val a = "2030"
        val b = "2029"
        val c = "2028"
        val d = "2027"
        val e = "2025"
        val f = "2024"
        val g = "2023"
        val returnList = ArrayList<String>()
        returnList.add(a)
        returnList.add(b)
        returnList.add(c)
        returnList.add(d)
        returnList.add(e)
        returnList.add(f)
        returnList.add(g)

        return returnList
    }

    private fun setUpDates(): ArrayList<String> {
        val a = "January"
        val b = "February"
        val c ="March"
        val d = "April"
        val e = "May"
        val f =  "June"
        val g = "July"
        val h = "August"
        val i ="September"
        val j = "October"
        val k = "November"
        val l =  "December"
        val returnList = ArrayList<String>()
        returnList.add(a)
        returnList.add(b)
        returnList.add(c)
        returnList.add(d)
        returnList.add(e)
        returnList.add(f)
        returnList.add(g)
        returnList.add(h)
        returnList.add(i)
        returnList.add(j)
        returnList.add(k)
        returnList.add(l)

        return returnList
    }

    private fun setUpCategories(): ArrayList<String> {
        val a = "Report"
        val b = "Budget"
        val c = "Public record"
        val d = "Legislation"
        val e = "Public procurement"
        val f = "Bank statement"
        val g = "Contract"
        val returnList = ArrayList<String>()
        returnList.add(a)
        returnList.add(b)
        returnList.add(c)
        returnList.add(d)
        returnList.add(e)
        returnList.add(f)
        returnList.add(g)

        return returnList
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }
}