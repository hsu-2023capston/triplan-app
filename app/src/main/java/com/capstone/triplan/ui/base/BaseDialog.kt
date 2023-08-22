package com.devsurfer.devtodonote_cleanarchitecture.base

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.capstone.triplan.di.CommonUtil.getScreenWidth

abstract class BaseDialog<T: ViewDataBinding>(
    @LayoutRes val layoutRes: Int
): DialogFragment() {

    protected lateinit var binding: T

    override fun onStart() {
        super.onStart()
        if(dialog != null && isAttachInActivity()){
            val fullWidth = getScreenWidth(requireActivity()) * .9
            dialog?.window?.setLayout(fullWidth.toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
            dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    protected fun loge(msg: String,tag:String = javaClass.simpleName, ){
        Log.e(tag, msg)
    }

    abstract fun initView()

    protected fun showShortToast(message: String?){
        if(isAttachInActivity()){
            Toast.makeText(context, message ?: "", Toast.LENGTH_SHORT).show()
        }
    }

    protected fun showLongToast(message: String?){
        if(isAttachInActivity()){
            Toast.makeText(context, message ?: "", Toast.LENGTH_LONG).show()
        }
    }

    override fun dismiss() {
        if (isAttachInActivity()) {
            super.dismiss()
        }
    }

    fun isAttachInActivity(): Boolean = activity !=null && isAdded
}