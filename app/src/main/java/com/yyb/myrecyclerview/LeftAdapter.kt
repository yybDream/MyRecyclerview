package com.yyb.myrecyclerview

import android.graphics.Color
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 *     author : 闫裕波
 *     e-mail : yyb@zlhopesun.com
 *     time   : 2020/10/15
 *     desc   : 类说明
 */
class LeftAdapter(layoutResId: Int, data: MutableList<Test>?) :
    BaseQuickAdapter<Test, BaseViewHolder>(layoutResId, data) {

    override fun convert(holder: BaseViewHolder, item: Test) {
        item.run {
            holder.setText(R.id.tv_type,type)
           if (isChoose)
            holder.setBackgroundColor(R.id.tv_type,Color.BLUE)
            else
               holder.setBackgroundColor(R.id.tv_type,Color.GRAY)
        }
    }

    fun setChoose(position: Int) {
        data.forEach {
            it.isChoose = false
        }
        data[position].isChoose = true
        notifyDataSetChanged()
    }
}