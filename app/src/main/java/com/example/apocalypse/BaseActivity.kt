package com.example.apocalypse

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.util.Log
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.core.graphics.ColorUtils
import androidx.palette.graphics.Palette
import com.example.apocalypse.Game.Joueur
import eightbitlab.com.blurview.BlurView
import eightbitlab.com.blurview.RenderScriptBlur
import kotlin.math.roundToInt

/**
 * Activity de base qui gère l'affichage des stats du joueur
 * et fournit des helpers pour la navigation.
 */
open class BaseActivity : AppCompatActivity() {
    lateinit var tvPtsVie : ProgressBar
    lateinit var tvPtsHydra : ProgressBar
    lateinit var tvPtsSasiete : ProgressBar

    /** Met à jour l'affichage des points de vie, d'hydratation et de satiété. */
     fun updateUI() {
        val cibleVie = Joueur.getPtsVie() * 4
        val cibleHydra = Joueur.getPtsHydra() * 4
        val cibleSatiete = Joueur.getPtsSatiete() * 4

        val animVie = ObjectAnimator.ofInt(tvPtsVie, "progress", tvPtsVie.progress, cibleVie)
        val animHydra = ObjectAnimator.ofInt(tvPtsHydra, "progress", tvPtsHydra.progress, cibleHydra)
        val animSatiete = ObjectAnimator.ofInt(tvPtsSasiete, "progress", tvPtsSasiete.progress, cibleSatiete)

        // 3. Regrouper les animations pour les jouer en même temps
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(animVie, animHydra, animSatiete)
        animatorSet.duration = 1000

        animatorSet.start()

        val result = "Hydratation : ${Joueur.getPtsHydra()}, Satiété : ${Joueur.getPtsSatiete()}, Vie : ${Joueur.getPtsVie()}"
        Log.i("Attribut",result)
    }

    fun displayAttributs(){
        tvPtsVie.progress= Joueur.getPtsVie() * 4
        tvPtsHydra.progress= Joueur.getPtsHydra() * 4
        tvPtsSasiete.progress= Joueur.getPtsSatiete() * 4

    }




        fun createIntent(context: Context, activity : Class<out AppCompatActivity>){
        val intent = Intent(context,activity)
        context.startActivity(intent)
    }


    /** Ouvre l'Activity avec un extra "Choix" pour transmettre le choix du joueur. */
    fun createActionIntent(context: Context, activity : Class<out AppCompatActivity>, choix : String){
        val intent = Intent(context,activity).apply { this.putExtra("Choix",choix)}
        context.startActivity(intent)
    }



    fun setupDynamicGlassmorphism(
        activity: Activity,
        blurView: BlurView,
        bgImageView: ImageView,
        blurRadius: Float ,
        alphaOpaqueness: Int,
        isButton: Boolean = false
    ) {
        // 1. Configuration du flou
        val decorView = activity.window.decorView
        val rootView = decorView.findViewById<ViewGroup>(android.R.id.content)

        blurView.setupWith(rootView, RenderScriptBlur(activity))
            .setFrameClearDrawable(decorView.background)
            .setBlurRadius(blurRadius)

        // 2. Application de la couleur dynamique
        val drawable = bgImageView.drawable
        if (drawable is BitmapDrawable) {
            val bitmap = drawable.bitmap

            Palette.from(bitmap).generate { palette ->
                // Récupère une couleur sombre naturelle, ou du noir par défaut
                val dominantColor = palette?.getDarkMutedColor(Color.BLACK) ?: Color.BLACK


                // Applique la transparence désirée
                val glassColorWithAlpha = ColorUtils.setAlphaComponent(dominantColor, alphaOpaqueness)

                // Applique la couleur à l'overlay
                blurView.setOverlayColor(glassColorWithAlpha)

            }
        }
    }

    fun colorizeButtonFromBackground(button: Button, bgImageView: ImageView) {
        val drawable = bgImageView.drawable
        if (drawable is BitmapDrawable) {
            // Extraction de la palette de couleurs de l'image
            Palette.from(drawable.bitmap).generate { palette ->

                // On récupère la couleur "Vibrant sombre" (ou "Muted sombre" selon vos goûts)
                // C'est parfait pour avoir un bouton coloré mais pas fluo
                val dominantColor = palette?.getDarkVibrantColor(Color.DKGRAY) ?: Color.DKGRAY

                // On récupère le background du bouton et on le "mute" pour le rendre modifiable
                val layerDrawable = button.background.mutate() as? LayerDrawable

                // On attrape la 2ème couche (l'index 1 = le corps du bouton)
                val bodyShape = layerDrawable?.getDrawable(1) as? GradientDrawable

                if (bodyShape != null) {
                    // On applique la couleur extraite du fond !
                    bodyShape.setColor(dominantColor)

                    // Optionnel : on peut aussi rendre la bordure encore plus foncée
                    // val darkerBorder = getDarkerColor(dominantColor, 0.5f)
                    // bodyShape.setStroke(4, darkerBorder)

                    // On force la mise à jour visuelle
                    button.background = layerDrawable
                    button.invalidate()
                }
            }
        }
    }







}