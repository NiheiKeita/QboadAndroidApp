package com.iggyapp.qboad

import android.content.Intent
import android.graphics.Color
import android.media.Image
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_top.*
import kotlinx.android.synthetic.main.adapter_posting_one_section.view.*


class SearchActivity : BaseActivity() {
    val TAG = "SearchActivity"
    private var mRecyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        findViewById<ImageView>(R.id.search_header_my_image).setImageResource(R.drawable.sample_nihei_icon)
        findViewById<TextView>(R.id.search_footer_button).setBackgroundColor(Color.parseColor("#808080"))
        findViewById<TextView>(R.id.search_footer_button).setTextColor(Color.WHITE)

        //戻るボタンの処理
        findViewById<TextView>(R.id.search_header_back_button).setOnClickListener {
            findViewById<ImageView>(R.id.search_header_my_image).visibility = View.VISIBLE
            findViewById<TextView>(R.id.search_header_back_button).visibility = View.GONE
            findViewById<View>(R.id.recycler_search_posting).visibility = View.GONE
        }
        //検索詳細ボタンの処理
        val detailSearchButton = findViewById<TextView>(R.id.search_header_new_posting_butottn)
        detailSearchButton.setOnClickListener {
            if(detailSearchButton.getTag() == 1){
                detailSearchButton.setTag(0)
                detailSearchButton.text = "▲"
            }else{
                detailSearchButton.setTag(1)
                detailSearchButton.text = "▽"
            }
        }

        //検索欄の処理
        findViewById<EditText>(R.id.search_button).addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                //テキスト変更前
//                Log.e(TAG,"beforeTextChanged:"+s)
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                //テキスト変更中
//                Log.e(TAG,"onTextChanged:"+s)
            }
            override fun afterTextChanged(s: Editable) {
                //テキスト変更後
//                Log.e(TAG,"afterTextChanged:"+s)
                searchPostingAPI()
            }
        })
    }

    private fun searchPostingAPI(){
        //TODO("APIをたたいて検索結果を持ってくる")
        val recyclerSearchPosting = findViewById<View>(R.id.recycler_search_posting)
        recyclerSearchPosting.visibility = View.VISIBLE
        val array = arrayOf("らくだ","あんこ","カルピス","いぎー")
        val adapter = PostingListAdapter(array)
        mRecyclerView = recyclerSearchPosting as RecyclerView
        mRecyclerView!!.layoutManager = LinearLayoutManager(this)
        mRecyclerView!!.setAdapter(adapter)
        findViewById<ImageView>(R.id.search_header_my_image).visibility = View.INVISIBLE
        findViewById<TextView>(R.id.search_header_back_button).visibility = View.VISIBLE
    }


    inner class PostingListAdapter(
        var array: Array<String>,
    ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        inner class PostingListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            fun onBind(position: Int){
                val item = array[position]
                //TODO(投稿をセット)

                var userName = ""
                var answerCorectRate = "正答率："
                var questionContent = ""
                var postingTags = ""
                when(item){
                    "らくだ" ->{
                        userName = item
                        answerCorectRate = "正答率：50％"
                        questionContent = "10＋15は？"
                        postingTags = "#数学 #足し算 #らくだ"
                        itemView.posting_user_image.setImageResource(R.drawable.sample_rakuda_icon)
                        itemView.posting_question_image.visibility = View.GONE
                    }
                    "あんこ" ->{
                        userName = item
                        answerCorectRate = "正答率：20％"
                        questionContent = "下の画像の犬種はなに？"
                        postingTags = "#いぬ #犬種"
                        itemView.posting_user_image.setImageResource(R.drawable.sample_annko_icon)
                        itemView.posting_question_image.visibility = View.VISIBLE
                        itemView.posting_question_image.setImageResource(R.drawable.sample_anko_question)
                    }
                    "カルピス" ->{
                        userName = item
                        answerCorectRate = "正答率：35％"
                        questionContent = "チューリップの花言葉は？"
                        postingTags = "#花言葉 #花言葉"
                        itemView.posting_user_image.setImageResource(R.drawable.sample_karupisu_icon)
                        itemView.posting_question_image.visibility = View.GONE
                    }
                    "いぎー" ->{
                        userName = item
                        answerCorectRate = "正答率：15％"
                        questionContent = "10! の答えは？"
                        postingTags = "#数学"
                        itemView.posting_user_image.setImageResource(R.drawable.sample_nihei_icon)
                        itemView.posting_question_image.visibility = View.GONE
                    }
                    else->{
                        itemView.posting_question_image.visibility = View.GONE
                    }
                }

                itemView.posting_user_name.text = userName
                itemView.posting_correct_answer_rate.text = answerCorectRate
                itemView.posting_question_content.text = questionContent
                itemView.posting_tag.text = postingTags

                itemView.posting_user_image.setOnClickListener {
                    val intent = Intent(this@SearchActivity, UserPageActivity::class.java)
                    intent.putExtra("USERID", item)
                    startActivity(intent)
                }
                itemView.setOnClickListener {
                    val intent = Intent(this@SearchActivity, PostingActivity::class.java)
                    intent.putExtra("POSTINGID", item);
                    startActivity(intent)
                    overridePendingTransition(0, 0)
                }
            }
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val v: View = LayoutInflater.from(this@SearchActivity).inflate(
                R.layout.adapter_posting_one_section,
                parent,
                false
            )
            return PostingListViewHolder(v)
        }
        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            (holder as PostingListViewHolder).onBind(position)
        }
        override fun getItemCount(): Int = array.size
    }
}