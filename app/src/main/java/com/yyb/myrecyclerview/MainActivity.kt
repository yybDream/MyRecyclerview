package com.yyb.myrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var mPos: Int? = 0
    private val list: MutableList<Test> = mutableListOf(
        Test("1", listOf("第一个分类"), true),
        Test("2", listOf("第二个分类"), false),
        Test("3", listOf("第三个分类"), false),
        Test("4", listOf("第四个分类"), false),
        Test("5", listOf("第五个分类"), false),
        Test("6", listOf("第六个分类"), false),
        Test("7", listOf("第七个分类"), false),
        Test("8", listOf("第八个分类"), false),
        Test("9", listOf("第九个分类"), false)
    )

    //适配器
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val manager = rv_right.layoutManager as LinearLayoutManager
        val leftAdapter = LeftAdapter(R.layout.item_left, list)
        val rightAdapter = RightAdapter(R.layout.item_right, list)
        rv_left.apply {
            adapter = leftAdapter
        }
        //右边联动
        leftAdapter.setOnItemClickListener { _, _, position ->
            leftAdapter.setChoose(position)
            manager.scrollToPositionWithOffset(position, 0)
        }
        //添加分组悬浮效果
        val top = TopItemDecoration(this).apply {
            typeListener={
                list[it].type.toString()
            }

        }
        rv_right.apply {
            adapter = rightAdapter
            addItemDecoration(top)
        }
        //左侧联动
        rv_right.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val firstItemPosition = manager.findFirstVisibleItemPosition()
                if (firstItemPosition != -1 && dy != 0) {
                    rv_left.smoothScrollToPosition(firstItemPosition)
                    leftAdapter.setChoose(firstItemPosition)
                }
            }
        })

    }
}