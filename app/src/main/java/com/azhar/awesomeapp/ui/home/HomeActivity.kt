package com.azhar.awesomeapp.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.azhar.awesomeapp.R
import com.azhar.awesomeapp.adapters.PhotoGraphAdapter
import com.azhar.awesomeapp.adapters.LoadPhotographStateAdapter
import com.azhar.awesomeapp.databinding.ActivityMainBinding
import com.azhar.awesomeapp.ui.detailphotograph.DetailPhotographActivity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private var bindingMainActivity : ActivityMainBinding? = null
    private val binding get() = bindingMainActivity!!

    private val homeViewModel: HomeViewModel by viewModel()

    private var layoutManager: GridLayoutManager? = null


    lateinit var photographAdapter : PhotoGraphAdapter

    companion object{
        const val KEY_PHOTO = "photo"
        const val KEY_PHOTOGRAPH_NAME = "photograph_name"
        const val KEY_PHOTO_URL = "photo_url"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingMainActivity = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        layoutManager = GridLayoutManager(this, 1)
         photographAdapter = PhotoGraphAdapter(layoutManager)

        photographAdapter.onItemClick= {

            val bundle = Bundle()
            bundle.putString(KEY_PHOTO, it.imgLarge)
            bundle.putString(KEY_PHOTOGRAPH_NAME, it.photographer)
            bundle.putString(KEY_PHOTO_URL, it.photographer_url)

            val intent = Intent(this, DetailPhotographActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)

        }

        photographAdapter.addLoadStateListener {

            binding.progressBar.isVisible = it.refresh is LoadState.Loading
            binding.layoutError.isVisible = it.refresh is LoadState.Error
            binding.btnTryError.isVisible = it.refresh is LoadState.Error
        }

        binding.btnTryError.setOnClickListener {
            lifecycleScope.launch {
                homeViewModel.getPhotoGraph().collectLatest {
                    photographAdapter.submitData(it)
                }
            }
            binding.layoutError.visibility = View.GONE
            binding.btnTryError.visibility = View.GONE

        }

        binding.rvPhotograph.layoutManager = layoutManager

        lifecycleScope.launch {
            homeViewModel.getPhotoGraph().collectLatest {
                photographAdapter.submitData(it)
            }
        }

        with(binding.rvPhotograph){
            adapter = photographAdapter.withLoadStateFooter(
                footer = LoadPhotographStateAdapter(photographAdapter::retry)

            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuViewGrid -> {
                layoutManager?.spanCount = 2
                photographAdapter.notifyItemRangeChanged(0, photographAdapter.itemCount)
                return true
            }
            R.id.menuViewList -> {
                layoutManager?.spanCount = 1
                photographAdapter.notifyItemRangeChanged(0, photographAdapter.itemCount)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}