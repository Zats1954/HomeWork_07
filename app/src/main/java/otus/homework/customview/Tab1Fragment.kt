package otus.homework.customview

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import otus.homework.customview.databinding.FragmentTab1Binding
import otus.homework.customview.piechart.ChartModel

class Tab1Fragment : Fragment(R.layout.fragment_tab1) {
    lateinit var image: ImageView
    private val chartModel: ChartModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentTab1Binding.bind(view)
        binding.chartView.chartModel = chartModel
        image = binding.imageView
        val animatorSet = AnimatorSet()
        val animatorSet1 = AnimatorSet()
        var animeRotate1 = ObjectAnimator.ofFloat(image, "rotation",360f, 0f)
         var scaleXAnimator = ObjectAnimator.ofFloat(image,"scaleX",1F,0F)
         var scaleYAnimator = ObjectAnimator.ofFloat(image,"scaleY",1F,0F)
        animatorSet.duration =2000L
        animatorSet.playTogether(animeRotate1, scaleXAnimator, scaleYAnimator)
        animatorSet.start()
        animeRotate1 = ObjectAnimator.ofFloat(image, "rotation",0f, 360f)
        scaleXAnimator = ObjectAnimator.ofFloat(image,"scaleX",0F,1F)
        scaleYAnimator = ObjectAnimator.ofFloat(image,"scaleY",0F,1F)
        animatorSet1.duration =2000L
        animatorSet1.startDelay = 2000L
        animatorSet1.playTogether(animeRotate1, scaleXAnimator, scaleYAnimator)
        animatorSet1.start()

        val touchDown = binding.chartView._clickSector
        touchDown.observe(viewLifecycleOwner) {

            chartModel.setChecked(it)
            chartModel.setScale(it)
            binding.chartView.invalidate()
        }
    }
}