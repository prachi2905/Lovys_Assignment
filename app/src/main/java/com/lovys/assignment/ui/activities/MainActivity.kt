package com.lovys.assignment.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.lovys.assignment.R
import com.lovys.assignment.databinding.ActivityMainBinding
import com.lovys.assignment.domain.localdb.Beer
import com.lovys.assignment.ui.adapter.PunkAdapter
import com.lovys.assignment.ui.utils.Data
import com.lovys.assignment.ui.utils.Status
import com.lovys.assignment.ui.viewmodels.PunkViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModel<PunkViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        callViewModel()


    }

    private fun updateDataOnUi(beersData: Data<List<Beer>>) {
        when (beersData.responseType) {
            Status.ERROR -> {
                hideLoading()
                beersData.error?.message?.let { showMessage(it) }
                beersData.data?.let { setBeerListOnRecyclerView(it) }
            }
            Status.LOADING -> {
                showLoading()
            }
            Status.SUCCESSFUL -> {
                beersData.data?.let { setBeerListOnRecyclerView(it) }
                hideLoading()
            }
        }
    }

    private fun updateDetailUI(beersData: Data<List<Beer>>) {
        when (beersData.responseType) {
            Status.ERROR -> {
                hideLoading()
                beersData.error?.message?.let { showMessage(it) }
            }
            Status.LOADING -> {
                showLoading()
            }
            Status.SUCCESSFUL -> {
                //navigate to detail screen
                hideLoading()
            }
        }
    }

    private fun callViewModel() {
        viewModel.mainStateList.observe(::getLifecycle, ::updateDataOnUi)
        viewModel.mainStateDetail.observe(::getLifecycle, ::updateDetailUI)
        showLoading()
        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.onStartHome(1, 80)
        }, MINIMUM_LOADING_TIME)
    }

    private fun showLoading() {
        binding.animLoader.visibility = View.VISIBLE
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
    private fun hideLoading() {
        binding.animLoader.visibility = View.GONE
    }

    private fun setBeerListOnRecyclerView(beerList: List<Beer>) {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)
        val punkAdapter = PunkAdapter(beerList)
        punkAdapter.setOnItemClickListener(itemClickListener())
        binding.recyclerView.adapter = punkAdapter
    }
    private fun itemClickListener() = object : PunkAdapter.OnItemClickListener {
        override fun onItemClick(item: Beer) {
            callOnDetailView(item)
        }
    }

    private fun callOnDetailView(item: Beer) {
        viewModel.onClickToBeerDetails(item.id, this@MainActivity.applicationContext)
    }


    companion object {
        const val MINIMUM_LOADING_TIME = 1000L
    }

}