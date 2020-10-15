package com.yyb.myrecyclerview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.Float.min

/**
 *     author : 闫裕波
 *     e-mail : yyb@zlhopesun.com
 *     time   : 2020/10/15
 *     desc   : 类说明
 */
class TopItemDecoration(context: Context) : RecyclerView.ItemDecoration() {

    //间隔高度
    private val mHeight = 100
    //矩形画笔
    private val mPaint: Paint = Paint()
    //标签画笔
    private val textPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mContext: Context = context
    private val mRound: Rect = Rect()
    init {
        mPaint.apply {
            color = ContextCompat.getColor(mContext, R.color.colorPrimary)
        }
        textPaint.apply {
            color = ContextCompat.getColor(mContext, R.color.colorAccent)
            textSize = 40f
        }
    }
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        val left = parent.paddingLeft.toFloat()
        val right = (parent.width - parent.paddingRight).toFloat()
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val childView = parent.getChildAt(i)
            val bottom = childView.top.toFloat()
            val top = bottom - mHeight
            //绘制矩形间隔
            c.drawRect(left, top, right, bottom, mPaint)
            //根据位置获取当前item的标签
            val tag = typeListener(parent.getChildAdapterPosition(childView))
            //绘制标签文本内容
            textPaint.getTextBounds(tag, 0, tag.length, mRound)
            c.drawText(tag, left + textPaint.textSize, bottom - mHeight / 2 + mRound.height() / 2, textPaint)
        }
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        //设置间隔高度
        outRect.top = mHeight
    }

    @SuppressLint("NewApi")
    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        val left = parent.paddingLeft.toFloat()
        val right = (parent.width - parent.paddingRight).toFloat()
        val manager = parent.layoutManager as LinearLayoutManager
        //第一个可见item位置
        val index = manager.findFirstVisibleItemPosition()
        if (index != -1) {
            //获取指定位置item的View信息
            val childView = parent.findViewHolderForLayoutPosition(index)!!.itemView
            val top = parent.paddingTop.toFloat()
            val tag = typeListener(index)
            var bottom = parent.paddingTop + mHeight.toFloat()
            //悬浮置顶判断，其实也就是一直在绘制一个矩形加文本内容(上滑时取值bottom，下滑时取值childView.bottom.toFloat())
            bottom = min(childView.bottom.toFloat(), bottom)
            c.drawRect(0f, top, right, bottom, mPaint)
            textPaint.getTextBounds(tag, 0, tag.length, mRound)
            c.drawText(tag, left + textPaint.textSize, bottom - mHeight / 2 + mRound.height() / 2, textPaint)
        }
    }

    /**
     * 获取悬停标签
     */
    lateinit var typeListener: (Int) -> String

}





































































