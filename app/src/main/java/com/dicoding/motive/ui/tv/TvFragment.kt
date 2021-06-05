package com.dicoding.motive.ui.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.motive.data.source.local.entity.TvEntity
import com.dicoding.motive.databinding.FragmentTvBinding
import com.dicoding.motive.viewmodel.ViewModelFactory
import com.dicoding.motive.vo.Status


@Suppress("DEPRECATION")
class TvFragment : Fragment(), TvFragmentCallback {

    private lateinit var fragmentTvBinding: FragmentTvBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        fragmentTvBinding = FragmentTvBinding.inflate(inflater, container, false)
        return fragmentTvBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TvViewModel::class.java]

            val adapter = TvAdapter(this)

            fragmentTvBinding.progressBar.visibility = View.VISIBLE
            viewModel.getTv().observe(viewLifecycleOwner, {
                if (it != null) {
                    when(it.status) {
                        Status.LOADING -> fragmentTvBinding.progressBar.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            fragmentTvBinding.progressBar.visibility = View.GONE
                            it.data?.let { C->
                                adapter.setCourses(C)
                                adapter.notifyDataSetChanged()
                                fragmentTvBinding.rvBookmark.visibility = View.VISIBLE
                            }
                        }
                        Status.ERROR -> {
                            fragmentTvBinding.progressBar.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            with(fragmentTvBinding.rvBookmark) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = adapter
            }
        }
    }

    override fun onShareClick(detailTv: TvEntity) {
        if (activity != null) {
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder.from(requireActivity()).apply {
                setType(mimeType)
                setChooserTitle("Bagikan aplikasi ini sekarang.")
                setText("Segera tonton ${detailTv.originalName} di tmdbb")
            }.startChooser()
        }
    }


}