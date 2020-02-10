package com.martynhaigh.galileo.testapp.forms

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.martynhaigh.galileo.testapp.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.android.viewmodel.ext.android.viewModel


@InternalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.showNextFragment()
        next_button.setOnClickListener {
            viewModel.onNextFragment(supportFragmentManager.fragments.last())
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadFragmentLiveData.observe {
            it?.let { showFragment(it) }
        }
    }

    private fun showFragment(fragment: Fragment) {
        val fm: FragmentManager = this.supportFragmentManager
        val ft: FragmentTransaction = fm.beginTransaction()
        ft.replace(R.id.container, fragment)
        ft.addToBackStack("")
        ft.commit()
    }

    override fun onBackPressed() {
        if (viewModel.onBackPressed(supportFragmentManager.backStackEntryCount)) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

    private fun <T> LiveData<T>.observe(observe: (T?) -> Unit) =
        observe(this@MainActivity, Observer { observe(it) })
}

