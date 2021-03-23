package com.example.fromrxjavatocoroutinesflow

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit

class SelectionFragment : Fragment(R.layout.fragment_selection) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.rxButton).setOnClickListener { newMainFragment() }
        view.findViewById<Button>(R.id.flowButton).setOnClickListener { newMainFragment() }
    }

    private fun newMainFragment() {
        requireActivity().supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.fragment_container_view, MainFragment())
            addToBackStack("123")
        }
    }

}