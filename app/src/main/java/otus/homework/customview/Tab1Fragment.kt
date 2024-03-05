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

//        var animationSet = AnimationSet(true)
//        var  animeRotate = RotateAnimation(360f, 0f,
//            RotateAnimation.RELATIVE_TO_SELF,0.5F,
//            RotateAnimation.RELATIVE_TO_SELF,0.5F)
//        var animeScale = ScaleAnimation(1f,0f,1f,0f,
//            ScaleAnimation.RELATIVE_TO_SELF,0.5F,
//            ScaleAnimation.RELATIVE_TO_SELF,0.5F)
//         animationSet.addAnimation(animeScale)
//        animationSet.addAnimation(animeRotate)
//         animationSet.duration = 2000L
//        image.startAnimation(animationSet)


////        animeRotate = RotateAnimation(0f, 360f,
////            RotateAnimation.RELATIVE_TO_SELF,0.5F,
////            RotateAnimation.RELATIVE_TO_SELF,0.5F)
////        animeScale = ScaleAnimation(0f,1f,0f,1f,
////            ScaleAnimation.RELATIVE_TO_SELF,0.5F,
////            ScaleAnimation.RELATIVE_TO_SELF,0.5F)
////        animationSet.addAnimation(animeScale)
////        animationSet.addAnimation(animeRotate)
////        animationSet.duration = 2000L
////        image.startAnimation(animationSet)
//
////
////            val alphaAnimator = ObjectAnimator.ofFloat(view,"alpha", 1f,0f,1f)
////
////            val rotateAnimator = ObjectAnimator.ofFloat(view,"rotation", 0f,360f)
////
////            val scaleXAnimator = ObjectAnimator.ofFloat(view,"scaleX",1F,0F)
////            val scaleYAnimator = ObjectAnimator.ofFloat(view,"scaleY",1F,0F)
////
////            animatorSet.play(alphaAnimator).after(rotateAnimator).after(scaleXAnimator)
////            animatorSet.duration = 2000
////            animatorSet.start()
//
////        animatorSet.playTogether(animeRotate0, scaleYAnimator, scaleXAnimator)
//
//        val animeRotate1 = ObjectAnimator.ofFloat(image, "rotation",0f, 360f).apply{
//            duration = 2000L
//        }
//
//            val animeScale1 = ScaleAnimation(1f,0f,1f,0f,
//                ScaleAnimation.RELATIVE_TO_SELF,0.5F,
//                ScaleAnimation.RELATIVE_TO_SELF,0.5F).apply {duration =2000L
//        }
////        animeRotate0.start()
////        image.startAnimation(animeScale0)
////        animeRotate1.start()
////        image.startAnimation(animeScale1)

        var animatorSet = AnimatorSet()
        var animatorSet1 = AnimatorSet()
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