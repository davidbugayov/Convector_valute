package com.convector.david_000.convector_valute

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.convector.david_000.convector_valute.data.ValuteItem

class MainActivity : Activity(), ValuteView, View.OnClickListener {
    private var spinnerValueFrom: Spinner? = null
    private var spinnerValueTo: Spinner? = null
    private var course: EditText? = null
    private var contentValueTo: EditText? = null
    private var contentValueFrom: EditText? = null
    private var adapter: ArrayAdapter<ValuteItem>? = null
    var presenter: ValutePresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Получаем экземпляр элемента Spinner
        spinnerValueFrom = findViewById<View>(R.id.spinner_value_from) as Spinner
        spinnerValueTo = findViewById<View>(R.id.spinner_value_to) as Spinner
        adapter = ValueItemsAdapter(this@MainActivity, android.R.layout.simple_spinner_item)
        spinnerValueFrom!!.adapter = adapter
        spinnerValueTo!!.adapter = adapter
        contentValueFrom = findViewById<View>(R.id.content_value_from) as EditText
        course = findViewById<View>(R.id.course) as EditText
        course!!.isEnabled = false
        contentValueTo = findViewById<View>(R.id.content_value_to) as EditText
        contentValueTo!!.isEnabled = false
        val reset = findViewById<View>(R.id.reset) as Button
        val calculate = findViewById<View>(R.id.calculate) as Button
        presenter = ValutePresenterImpl()
        (presenter as ValutePresenterImpl).setView(this)
        reset.setOnClickListener(this)
        calculate.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.reset -> clickReset()
            R.id.calculate -> clickCalculate()
        }
    }

    private fun clickReset() {
        course!!.setText("")
        contentValueFrom!!.setText("")
        contentValueTo!!.setText("")
    }

    private fun clickCalculate() {
        var countValute = 0.0
        try {
            countValute = contentValueFrom!!.text.toString().replace(',', '.').toDouble()
        } catch (e: NumberFormatException) {
            Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show()
        }
        val valuteItemFrom = adapter!!.getItem(spinnerValueFrom!!.selectedItemPosition)
        val valuteItemTo = adapter!!.getItem(spinnerValueTo!!.selectedItemPosition)
        if (valuteItemTo != null) {
            presenter!!.ConvertValute(valuteItemFrom, valuteItemTo, countValute)
        }
    }

    override fun setResult(result: Double) {
        contentValueTo!!.setText(result.toString())
    }

    override fun setExchangeRate(rate: Double) {
        course!!.setText(rate.toString())
    }

    override fun updateValutes(data: List<ValuteItem>) {
        adapter!!.clear()
        if (data != null) {
            for (valuteItem in data) {
                adapter!!.insert(valuteItem, adapter!!.count)
            }
        }
        adapter!!.notifyDataSetChanged()
    }
}