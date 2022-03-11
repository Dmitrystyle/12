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

        val textView: TextView = binding.textGallery
        galleryViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
//___________заполняем содержимое___________________
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    fun getResult():String{
        val a:Double
        val b:Double
        binding.apply {
            a=edA.text.toString().toDouble()
            b=edB.text.toString().toDouble()
        }
        return Math.sqrt((a.pow(2) + b.pow(2))).toString()
    }


    fun isFieldEmpty():Boolean   //проекрка на пустое поле
    {
        binding.apply {
            if(edA.text.isNullOrEmpty()) edA.error="Поле должно быть заполнено"
            if(edB.text.isNullOrEmpty()) edB.error="Поле должно быть заполнено"
            return  edA.text.isNullOrEmpty()||edB.text.isNullOrEmpty()
        }

    }

    binding.button.setOnClickListener {

        if(!isFieldEmpty()) binding.resaltText.text=getResult().take(4)   //вывод результата на четыре символа
    }

    binding.button2.setOnClickListener {

        binding.edA.text.clear()
        binding.edB.text.clear()
        binding.resaltText.text= 0.toString()
        binding.resaltText.text.drop(4)  //обнуление
    }



















        }
    }






    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}