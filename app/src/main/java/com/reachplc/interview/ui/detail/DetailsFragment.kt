package com.reachplc.interview.ui.detail

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.reachplc.interview.R
import com.reachplc.interview.databinding.FragmentDetailBinding

class DetailsFragment : Fragment(R.layout.fragment_detail) {

//    private val args by navArgs<DetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentDetailBinding.bind(view)

        val args: DetailsFragmentArgs by this.navArgs()
//        val product = args.selectedProduct

        binding.apply {
            val product = args.selectedProduct

            activity?.title = product.name

            Glide.with(this@DetailsFragment)
                .load(product.image)
//                .error(R.drawable.ic_error)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
//                        progressBar.isVisible = false
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
//                        progressBar.isVisible = false
//                        textViewCreator.isVisible = true
//                        textViewDescription.isVisible = photo.description != null
                        return false
                    }

                })
                .into(descriptionImageView)

            textViewDescription.text = product.description

//            val uri = Uri.parse(photo.user.attributionUrl)
//            val intent = Intent(Intent.ACTION_VIEW, uri)
//
//            textViewCreator.apply {
//                text = "Photo by ${photo.user.name} on Unsplash"
//                setOnClickListener {
//                    context.startActivity(intent)
//                }
//                paint.isUnderlineText = true
//            }
        }
    }
}