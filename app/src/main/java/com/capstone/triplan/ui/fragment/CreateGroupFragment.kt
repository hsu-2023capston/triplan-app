package com.capstone.triplan.ui.fragment

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.capstone.triplan.BaseFragment
import com.capstone.triplan.R
import com.capstone.triplan.databinding.FragmentCreateGroupBinding
import com.capstone.triplan.presentation.viewModel.CreateGroupViewModel
import com.capstone.triplan.presentation.viewModel.MainHomeViewModel
import com.capstone.triplan.presentation.viewModel.MainViewModel
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileOutputStream


@AndroidEntryPoint
class CreateGroupFragment : BaseFragment<FragmentCreateGroupBinding>(R.layout.fragment_create_group) {
    private val mainViewModel: MainViewModel by activityViewModels()
    private val createGroupViewModel: CreateGroupViewModel by viewModels()

    lateinit var img : File
    val OPEN_GALLERY = 1

    override fun initView() {
        binding.apply {
            btnCgBack.setOnClickListener {
                findNavController().popBackStack()
            }
            btnCgCreate.setOnClickListener {
                loge("${etCgName.text.toString()}")
                mainViewModel.user.value?.user_id?.let { it1 ->
                    createGroupViewModel.postGroup(etCgName.text.toString(),etCgPw.text.toString(),
                        it1,img)
                }
            }
            btnCgGroupImg.tvGroupitemName.visibility = View.INVISIBLE

            btnCgGroupImg.view2.setOnClickListener {
                checkPermission()
            }
        }
        setObserver()
    }

    private fun checkPermission(){
        val permissionChecker: PermissionListener = object : PermissionListener {
            override fun onPermissionGranted() {
                loge("하이뿡빵")
                val i = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(i, OPEN_GALLERY)
            }
            override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                Toast.makeText(context, "저장소 접근 권한을 설정해 주세요", Toast.LENGTH_SHORT).show()
            }
        }
        TedPermission.create()
            .setPermissionListener(permissionChecker)
            .setDeniedMessage("저장소 접근 권한 설정 해주세요")
            .setPermissions(android.Manifest.permission.READ_EXTERNAL_STORAGE)
            .check()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, @Nullable data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            OPEN_GALLERY -> if (data != null && resultCode == RESULT_OK) {
                val selectedImage = data.data
                val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                val cursor: Cursor? = selectedImage?.let {
                    requireActivity().contentResolver.query(
                        it,
                        filePathColumn, null, null, null
                    )
                }
                if (cursor == null || cursor.count < 1) {
                    return  // no cursor or no record. DO YOUR ERROR HANDLING
                }
                cursor.moveToFirst()
                val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                if (columnIndex < 0) // no column index
                    return  // DO YOUR ERROR HANDLING

                //선택한 파일 경로
                val picturePath = cursor.getString(columnIndex)
                loge("$picturePath")
                val bitmap : Bitmap = BitmapFactory.decodeFile(picturePath)
                img = getUploadFile(bitmap)
                binding.btnCgGroupImg.imageView3.visibility = View.INVISIBLE
                Glide.with(this)
                    .load(picturePath)
                    .into(binding.btnCgGroupImg.ivGaImg)
                cursor.close()
            }
        }
    }

    private fun getUploadFile(bitmap: Bitmap): File {
        val fileName = System.currentTimeMillis().toString() + ".png"
        val cachePath = File(requireActivity().cacheDir, "images")
        cachePath.mkdirs()
        val stream = FileOutputStream("$cachePath/$fileName")
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        stream.close()
        return File(cachePath, fileName)
    }

    private fun setObserver() {
        createGroupViewModel.message.observe(viewLifecycleOwner){
            if(it == "성공"){
                loge("바뀜")
                findNavController().navigate(R.id.action_createGroupFragment_to_mainHomeFragment)
            }
        }
    }
}