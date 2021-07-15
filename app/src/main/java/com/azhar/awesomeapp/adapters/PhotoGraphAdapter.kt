package com.azhar.awesomeapp.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.azhar.awesomeapp.R
import com.azhar.awesomeapp.core.domain.model.DataPhotos
import com.azhar.awesomeapp.databinding.GridItemPhotoGraphBinding
import com.azhar.awesomeapp.databinding.ListItemPhotoGraphBinding
import com.bumptech.glide.Glide


class PhotoGraphAdapter(
        private val layoutManager: GridLayoutManager? = null
): PagingDataAdapter<DataPhotos, BaseViewHolder<*>>(Comparator) {

   enum class ViewType {
      LIST,
       GRID
    }

    var onItemClick: ((DataPhotos)-> Unit)? = null


    object Comparator : DiffUtil.ItemCallback<DataPhotos>() {
        override fun areItemsTheSame(oldItem: DataPhotos, newItem: DataPhotos): Boolean {

            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DataPhotos, newItem: DataPhotos): Boolean {
            return oldItem == newItem
        }
    }



    override fun getItemViewType(position: Int): Int {
        return if (layoutManager?.spanCount == 1) ViewType.LIST.ordinal
        else ViewType.GRID.ordinal
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when(viewType){
            ViewType.LIST.ordinal ->{
                val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.list_item_photo_graph, parent, false)
                ViewListViewHolder(view)

            }
            ViewType.GRID.ordinal ->{
                val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.grid_item_photo_graph, parent, false)
                ViewGridViewHolder(view)

            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        getItem(position)?.let {
            when (holder){
                is ViewListViewHolder->
                    holder.bind(it)
                is ViewGridViewHolder->
                    holder.bind(it)
            }
        }
    }

    inner class ViewListViewHolder(itemView: View) : BaseViewHolder<DataPhotos>(itemView) {
        private val binding = ListItemPhotoGraphBinding.bind(itemView)
        override fun bind(item: DataPhotos) {

            with(binding){
                binding.tvPhotographName.text = item.photographer

                Glide.with(itemView.context)
                        .load(item.imgSmall)
                        .into(imagePhotograph)
            }
            binding.root.setOnClickListener {
                onItemClick?.let { it1 -> it1(item) }
            }

        }
    }

    inner class ViewGridViewHolder(itemView: View) : BaseViewHolder<DataPhotos>(itemView) {
        private val binding = GridItemPhotoGraphBinding.bind(itemView)

        override fun bind(item: DataPhotos) {

            with(binding){
                binding.tvPhotographNameGrid.text = item.photographer

                Glide.with(itemView.context)
                        .load(item.imgSmall)
                        .into(imagePhotographGrid)
            }
            binding.root.setOnClickListener {
                onItemClick?.let { it1 -> it1(item) }
            }
        }

    }

}