package com.example.inspectmasterproject

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.ToggleButton
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.io.File
import java.io.IOException

// TODO: Rename parameter arguments, choose names that match

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class Inspection : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_inspection, container, false)

        val empNumLayout = view.findViewById<TextInputLayout>(R.id.employeeNumberLayout)
        val empNumEditText = view.findViewById<TextInputEditText>(R.id.editTextNumber)

        empNumEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                empNumLayout.helperText = "Please enter the 5-digit number from your employee card"
            } else {
                empNumLayout.helperText = null
            }
        }
        val toggleButton = view.findViewById<ToggleButton>(R.id.toggleButton)
        val editTextComments = view.findViewById<EditText>(R.id.editTextComments)
        val label = view.findViewById<TextView>(R.id.label)

        toggleButton.setOnCheckedChangeListener { _, isChecked ->
            if (!isChecked) {
                label.visibility = View.VISIBLE
                editTextComments.visibility = View.VISIBLE
            } else {
                label.visibility = View.GONE
                editTextComments.visibility = View.GONE
            }
        }

        val cancel = view.findViewById<Button>(R.id.buttonCancel)
        cancel.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Cancel Inspection")
            builder.setMessage("Are you sure you want to cancel the inspection? Any unsaved data will be lost.")
            builder.setPositiveButton("Yes") { _, _ ->

                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.frame, Inspection.newInstance("param1", "param2"))
                    .addToBackStack(null)
                    .commit()
            }
            builder.setNegativeButton("No") { dialog, _ ->

                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()
        }
        val sendButton = view.findViewById<Button>(R.id.buttonSend)
        sendButton.setOnClickListener {

            val employeeNumber = empNumEditText.text.toString()
            val managerEmail =
                view.findViewById<EditText>(R.id.editTextTextEmailAddress).text.toString()
            val address = view.findViewById<EditText>(R.id.editTextAddress).text.toString()
            val roomsInspected = StringBuilder()
            val checkBox1 = view.findViewById<CheckBox>(R.id.checkBox1)
            val checkBox2 = view.findViewById<CheckBox>(R.id.checkBox2)
            val checkBox3 = view.findViewById<CheckBox>(R.id.checkBox3)
            val checkBox4 = view.findViewById<CheckBox>(R.id.checkBox4)
            val checkBox5 = view.findViewById<CheckBox>(R.id.checkBox5)
            if (checkBox1.isChecked) roomsInspected.append("Kitchen, ")
            if (checkBox2.isChecked) roomsInspected.append("Bathroom, ")
            if (checkBox3.isChecked) roomsInspected.append("Office 1, ")
            if (checkBox4.isChecked) roomsInspected.append("Office 2, ")
            if (checkBox5.isChecked) roomsInspected.append("Storage room, ")
            val passFail = if (toggleButton.isChecked) "PASS" else "FAIL"
            val comments = editTextComments.text.toString()


            val inspectionData = "Employee Number: $employeeNumber\n" +
                    "Manager's Email: $managerEmail\n" +
                    "Address: $address\n" +
                    "Rooms Inspected: ${roomsInspected.dropLast(2)}\n" +
                    "Pass/Fail: $passFail\n" +
                    "Comments: $comments\n"

            try {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "message/rfc822"
                intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(managerEmail))
                intent.putExtra(Intent.EXTRA_SUBJECT, "Inspection Report for $address")
                intent.putExtra(Intent.EXTRA_TEXT, inspectionData)
                startActivity(Intent.createChooser(intent, "Send Email"))
            } catch (e: IOException) {
                e.printStackTrace()

            }
        }

        // Inflate the layout for this fragment
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Inspection.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Inspection().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}