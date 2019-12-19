package com.amazing.eye.detial

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.amazing.eye.BaseFragment

import com.amazing.eye.R
import com.amazing.eye.bean.VideoBean
import com.amazing.eye.databinding.FragmentDetailInfosBinding
import com.amazing.eye.utils.getTimeWithDuration
import kotlinx.android.synthetic.main.fragment_detail_infos.*

private const val ARG_PARAM1 = "param1"

/**
 */
class DetailInfosFragment : BaseFragment() {
    private var videoBean: VideoBean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            videoBean = it.getSerializable(ARG_PARAM1) as VideoBean?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDetailInfosBinding.inflate(inflater, container, false)
        binding.videoDetailBean = videoBean
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val time = String().getTimeWithDuration(videoBean?.duration!!)
        val category = videoBean?.category
        tv_video_time_detail_fragment.text = "$category / $time"

        tv_comment_detail_info.setOnClickListener {
            (activity as VideoDetailActivity?)?.toCommentListFragment()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(videoBean: VideoBean) =
            DetailInfosFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, videoBean)
                }
            }
    }

}
