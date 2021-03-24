package com.example.fromrxjavatocoroutinesflow

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.fromrxjavatocoroutinesflow.flow.MainFragmentFlow
import com.example.fromrxjavatocoroutinesflow.rx.MainFragmentRx

class SelectionFragment : Fragment(R.layout.fragment_selection) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().title = getString(R.string.app_name)

        view.findViewById<Button>(R.id.rxButton)
            .setOnClickListener { newMainFragment(MainFragmentRx()) }
        view.findViewById<Button>(R.id.flowButton)
            .setOnClickListener { newMainFragment(MainFragmentFlow()) }
    }

    private fun newMainFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.fragment_container_view, fragment)
            addToBackStack(null)
        }
    }

}