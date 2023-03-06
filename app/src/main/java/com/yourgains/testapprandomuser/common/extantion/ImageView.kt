package com.yourgains.testapprandomuser.common.extantion

import android.annotation.SuppressLint
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.yourgains.testapprandomuser.R

val cacheOptions = RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)

@SuppressLint("CheckResult")
fun ImageView.loadWithGlide(
    url: String? = null,
    @DrawableRes placeHolder: Int = 0,
    requestOptions: RequestOptions? = null
) {
    url?.also { link ->
        val options = requestOptions ?: RequestOptions()
        options.also { it.placeholder(placeHolder) }
        Glide.with(this)
            .load(link)
            .centerCrop()
            .apply(cacheOptions)
            .apply(options)
            .placeholder(R.mipmap.ic_launcher)
            .into(this)
    } ?: setImageResource(placeHolder)
}