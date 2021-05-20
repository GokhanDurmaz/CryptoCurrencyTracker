/**
 * Created by gokhan on 5/1/21.
 */

package com.cryptocurrencytracker.demo.ui.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.PixelFormat
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.Gravity
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.cryptocurrencytracker.demo.R
import com.cryptocurrencytracker.demo.data.db.currency.CurrencyData
import com.cryptocurrencytracker.demo.helper.getDisplayHeight
import com.cryptocurrencytracker.demo.helper.getService
import com.cryptocurrencytracker.demo.helper.ui.loadImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@SuppressLint("ViewConstructor")
class CoinFloatInformationView @JvmOverloads constructor(
    private val currencyData: CurrencyData?,
    private val activity: Activity,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(activity, attrs, defStyleAttr) {

    private var view: View = LayoutInflater.from(activity.applicationContext).inflate(
        R.layout.view_coin_float, this
    )
    private var mCoinInformationLayout: ConstraintLayout =
        view.findViewById(R.id.coin_information_layout)
    private var windowManager: WindowManager =
        activity.applicationContext.getService(Context.WINDOW_SERVICE)
    private lateinit var params: WindowManager.LayoutParams
    private var mCoinImage: ImageView = view.findViewById(R.id.coin_image)

    private var mTouchStartX: Float = .0f
    private var mTouchStartY: Float = .0f
    private var x: Int = 0
    private var y: Int = 0

    init {

        setView()
    }

    private fun setView() {
        mCoinInformationLayout.let {
            isClickable = true
            isFocusable = true
        }
        currencyData?.let { currencyData ->
            mCoinImage.loadImage(currencyData.smallImage)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                mTouchStartX = event.rawX
                mTouchStartY = event.rawY
            }
            MotionEvent.ACTION_MOVE -> {
                params.x = x + (event.rawX - mTouchStartX).toInt()
                params.y = y + (event.rawY - mTouchStartY).toInt()

                windowManager.updateViewLayout(this, params)
            }
            MotionEvent.ACTION_UP -> {
                Log.i(TAG, "ACTION_UP event is working.")
            }
            else -> Log.e(TAG, "event not found.")
        }
        return super.onTouchEvent(event)
    }

    override fun performClick(): Boolean {
        Log.i(TAG, "This works")
        return super.performClick()
    }


    /**
     * This method provides to create a float view on the screen
     *
     * @param
     * @return
     */
    @Suppress("DEPRECATION")
    fun addView() {
        val layoutFlag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            WindowManager.LayoutParams.TYPE_PHONE;
        }
        params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            layoutFlag,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
                    WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or
                    WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
            PixelFormat.TRANSLUCENT
        )
        params.let { layoutParams ->
            layoutParams.gravity = Gravity.BOTTOM or Gravity.END
            layoutParams.x = 0
            activity.getDisplayHeight().also { layoutParams.y = (it * .5).toInt() }
            this.x = layoutParams.x
            this.y = layoutParams.y
        }
        windowManager.addView(view, params)
    }

    fun removeView() = GlobalScope.launch(Dispatchers.Main) {
        view.apply {
            if (isAttachedToWindow) {
                windowManager.removeView(this)
                Log.e(TAG, "isAttachedToWindow = true")
            } else {
                Log.e(TAG, "isAttachedToWindow = false")
            }
        }
    }

    companion object {
        private val TAG = CoinFloatInformationView::class.java.name
    }
}
