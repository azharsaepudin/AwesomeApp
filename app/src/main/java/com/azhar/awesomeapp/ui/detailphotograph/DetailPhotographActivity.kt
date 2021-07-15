package com.azhar.awesomeapp.ui.detailphotograph

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.azhar.awesomeapp.databinding.ActivityDetailPhotographBinding
import com.azhar.awesomeapp.ui.home.HomeActivity.Companion.KEY_PHOTO
import com.azhar.awesomeapp.ui.home.HomeActivity.Companion.KEY_PHOTOGRAPH_NAME
import com.azhar.awesomeapp.ui.home.HomeActivity.Companion.KEY_PHOTO_URL
import com.bumptech.glide.Glide

class DetailPhotographActivity : AppCompatActivity() {

    private var bindingDetailPhotographActivity : ActivityDetailPhotographBinding? = null
    private val binding get() = bindingDetailPhotographActivity!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingDetailPhotographActivity = ActivityDetailPhotographBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)



        if (intent.extras !=null){
            val bundle = intent.extras

            val mPhotographName:String = bundle?.getString(KEY_PHOTOGRAPH_NAME).toString()
            binding.tvPhotoUrl.text = bundle?.getString(KEY_PHOTO_URL)
            binding.tvPhotographNameDetail.text = mPhotographName

            supportActionBar?.title = mPhotographName

            Glide.with(this)
                .load(bundle?.getString(KEY_PHOTO))
                .into(binding.imgImageDetailPhotograph)

        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}