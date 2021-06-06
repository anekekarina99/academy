@file:Suppress("DEPRECATION")

package com.dicoding.motive.ui.favor.t

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.motive.R
import com.dicoding.motive.data.source.local.entity.TvEntity
import com.dicoding.motive.databinding.FragmentTvFavorBinding
import com.dicoding.motive.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar


class TvFavoriteFragment : Fragment(), TvFavorFragmentCallback {

    private var _fragmentBookmarkBinding: FragmentTvFavorBinding? = null
    private val binding get() = _fragmentBookmarkBinding

    private lateinit var viewModel: TvFavorViewModel
    private lateinit var adapter: TvFavorAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _fragmentBookmarkBinding = FragmentTvFavorBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(binding?.rvFavorT)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[TvFavorViewModel::class.java]

            adapter = TvFavorAdapter(this)
            binding?.progressBar?.visibility = View.VISIBLE
            viewModel.getFavorT().observe(viewLifecycleOwner, { courses ->
                binding?.progressBar?.visibility = View.GONE
                adapter.submitList(courses)
            })

            binding?.rvFavorT?.layoutManager = LinearLayoutManager(context)
            binding?.rvFavorT?.setHasFixedSize(true)
            binding?.rvFavorT?.adapter = adapter
        }
    }

    override fun onShareClick(course: TvEntity) {
        if (activity != null) {
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder
                .from(requireActivity())
                .setType(mimeType)
                .setChooserTitle("Bagikan aplikasi ini sekarang.")
                .setText("Segera tonton ${course.originalName} di bioskop")
                .startChooser()
        }
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val courseEntity = adapter.getSwipedData(swipedPosition)
                courseEntity?.let { viewModel.setFavorT(it) }

                val snackbar =
                    Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG)
                snackbar.setAction(R.string.message_ok) { _ ->
                    courseEntity?.let { viewModel.setFavorT(it) }
                }
                snackbar.show()
            }
        }
    })
}
