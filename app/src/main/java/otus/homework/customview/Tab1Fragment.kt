package otus.homework.customview

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import androidx.core.animation.doOnEnd
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import otus.homework.customview.databinding.FragmentTab1Binding
import otus.homework.customview.piechart.ChartModel


private const val TIME_COLLAPSE = 2000L

class Tab1Fragment : Fragment(R.layout.fragment_tab1) {

    private val chartModel: ChartModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentTab1Binding.bind(view)
        binding.chartView.chartModel = chartModel

        val animatorSet = AnimatorSet()
        val animatorSet1 = AnimatorSet()
        animationPLan(animatorSet, animatorSet1, binding.chartView)

        val touchDown = binding.chartView._clickSector
        touchDown.observe(viewLifecycleOwner) {
            animatorSet.start()
            animatorSet1.start()
            animatorSet.doOnEnd { _ ->
                chartModel.setChecked(it)
                chartModel.setScale(it)
                binding.chartView.invalidate()
            }
        }
    }
    private fun animationPLan(
        animatorSet: AnimatorSet,
        animatorSet1: AnimatorSet,
        image: View
    ) {
        val animeRotate1 = ObjectAnimator.ofFloat(image, "rotation", 360f, 0f)
        val scaleXAnimator1 = ObjectAnimator.ofFloat(image, "scaleX", 1F, 0F)
        val scaleYAnimator1 = ObjectAnimator.ofFloat(image, "scaleY", 1F, 0F)
        animatorSet.duration = TIME_COLLAPSE
        animatorSet.playTogether(animeRotate1, scaleXAnimator1, scaleYAnimator1)

        val animeRotate2 = ObjectAnimator.ofFloat(image, "rotation", 0f, 360f)
        val scaleXAnimator2 = ObjectAnimator.ofFloat(image, "scaleX", 0F, 1F)
        val scaleYAnimator2 = ObjectAnimator.ofFloat(image, "scaleY", 0F, 1F)
        animatorSet1.duration = TIME_COLLAPSE
        animatorSet1.startDelay = TIME_COLLAPSE
        animatorSet1.playTogether(animeRotate2, scaleXAnimator2, scaleYAnimator2)
    }
}