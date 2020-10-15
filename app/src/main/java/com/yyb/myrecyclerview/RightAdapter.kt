package com.yyb.myrecyclerview

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 *     author : 闫裕波
 *     e-mail : yyb@zlhopesun.com
 *     time   : 2020/10/15
 *     desc   : 类说明
 */
class RightAdapter(layoutResId: Int, data: MutableList<Test>?) :
    BaseQuickAdapter<Test, BaseViewHolder>(layoutResId, data) {
    override fun convert(holder: BaseViewHolder, item: Test) {
        item?.run {
                holder.setText(R.id.tv_content,content.get(0))
        }
    }
}