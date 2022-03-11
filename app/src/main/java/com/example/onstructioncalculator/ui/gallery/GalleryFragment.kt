package com.example.onstructioncalculator.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.onstructioncalculator.R
import com.example.onstructioncalculator.databinding.FragmentGalleryBinding
import kotlin.math.pow

class GalleryFragment : Fragment() {

    private lateinit var galleryViewModel: GalleryViewModel
    private var _binding: FragmentGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

       // val textView: TextView = binding.textGallery
      //  galleryViewModel.text.observe(viewLifecycleOwner, Observer {
      //      textView.text = it
     //   })
        return root
    }
//___________заполняем содержимое___________________
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    fun getResult():String{
        val a:Double
        val b:Double
        val h:Double
        val l:Double


        binding.apply {
            a=blockL.text.toString().toDouble()
            b=blockH.text.toString().toDouble()
            h=wallHight.text.toString().toDouble()
            l=wallLength.text.toString().toDouble()

        }
        return ((h*l)/(a*b)).toString()
    }


    fun isFieldEmpty():Boolean   //проекрка на пустое поле
    {
        binding.apply {
            if(wallHight.text.isNullOrEmpty()) wallHight.error="Поле должно быть заполнено"
            if(wallLength.text.isNullOrEmpty()) wallLength.error="Поле должно быть заполнено"
            if(blockH.text.isNullOrEmpty()) blockH.error="Поле должно быть заполнено"
            if(blockL.text.isNullOrEmpty()) blockL.error="Поле должно быть заполнено"

            return  wallLength.text.isNullOrEmpty()||wallHight.text.isNullOrEmpty()||blockL.text.isNullOrEmpty()||blockH.text.isNullOrEmpty()
        }

    }

    binding.button.setOnClickListener {

        if(!isFieldEmpty()) binding.resaltText.text=getResult().take(4)   //вывод результата на четыре символа
    }

    binding.button2.setOnClickListener {

        binding.wallLength.text.clear()
        binding.wallHight.text.clear()
        binding.blockL.text.clear()
        binding.blockH.text.clear()
        binding.resaltText.text= 0.toString()
        binding.resaltText.text.drop(4)  //обнуление
    }



        }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}