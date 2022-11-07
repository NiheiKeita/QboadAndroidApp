package com.iggyapp.qboad

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_top.*
import kotlinx.android.synthetic.main.adapter_posting_one_section.view.*

class TopActivity : BaseActivity() {
    private var mRecyclerView: RecyclerView? = null
    private var fRecyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top)
        findViewById<ImageView>(R.id.top_header_my_image).setImageResource(R.drawable.sample_nihei_icon)
        findViewById<TextView>(R.id.top_footer_button).setBackgroundColor(Color.parseColor("#808080"))
        findViewById<TextView>(R.id.top_footer_button).setTextColor(Color.WHITE)

        //TODO(APIをたたいて投稿を取ってくる処理)
        val array = arrayOf("らくだ","あんこ","カルピス","いぎー")
        val adapter = PostingListAdapter(array)
        mRecyclerView = findViewById<View>(R.id.recycler_posting) as RecyclerView
        mRecyclerView!!.layoutManager = LinearLayoutManager(this)
        mRecyclerView!!.setAdapter(adapter)

        //TODO(APIをたたいて投稿を取ってくる処理)
        val arrayf = arrayOf("らくだ","らくだ","らくだ","らくだ")
        val adapterf = PostingListFollowAdapter(arrayf)
        fRecyclerView = findViewById<View>(R.id.recycler_follow_posting) as RecyclerView
        fRecyclerView!!.layoutManager = LinearLayoutManager(this)
        fRecyclerView!!.setAdapter(adapterf)


        mRecyclerView?.visibility = View.VISIBLE
        fRecyclerView?.visibility = View.GONE
        mRecyclerView?.visibility = View.VISIBLE
        fRecyclerView?.visibility = View.GONE
        clearTab()

        findViewById<TextView>(R.id.tab_follow_top_button).also{bt->
            bt.setOnClickListener {
                clearTab()
                fRecyclerView?.visibility = View.VISIBLE
                mRecyclerView?.visibility = View.GONE
                bt.setTextColor(Color.BLACK)
                bt.setTypeface(null, Typeface.BOLD)
                bt.setTextSize(14.0f)
            }
        }
        findViewById<TextView>(R.id.tab_recommendation_top_button).also{bt->
            bt.setOnClickListener {
                clearTab()
                mRecyclerView?.visibility = View.VISIBLE
                fRecyclerView?.visibility = View.GONE
                bt.setTextColor(Color.BLACK)
                bt.setTypeface(null, Typeface.BOLD)
                bt.setTextSize(14.0f)
            }
            bt.setTextColor(Color.BLACK)
            bt.setTypeface(Typeface.DEFAULT_BOLD)
            bt.setTextSize(14.0f)
        }
    }
    fun clearTab(){
        findViewById<TextView>(R.id.tab_follow_top_button).also{bt->
            bt.setTextColor(Color.GRAY)
            bt.setTypeface(null, Typeface.NORMAL)
            bt.setTextSize(12.0f)
        }
        findViewById<TextView>(R.id.tab_recommendation_top_button).also{bt->
            bt.setTextColor(Color.GRAY)
            bt.setTypeface(null, Typeface.NORMAL)
            bt.setTextSize(12.0f)
        }
    }
    inner class PostingListFollowAdapter(
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
                    val intent = Intent(this@TopActivity, UserPageActivity::class.java)
                    intent.putExtra("USERID", item)
                    startActivity(intent)
                    overridePendingTransition(0, 0)
                }
                itemView.setOnClickListener {
                    val intent = Intent(this@TopActivity, PostingActivity::class.java)
                    intent.putExtra("POSTINGID", item);
                    startActivity(intent)
                    overridePendingTransition(0, 0)
                }
            }
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val v: View = LayoutInflater.from(this@TopActivity).inflate(
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
                    val intent = Intent(this@TopActivity, UserPageActivity::class.java)
                    intent.putExtra("USERID", item)
                    startActivity(intent)
                    overridePendingTransition(0, 0)
                }
                itemView.setOnClickListener {
                    val intent = Intent(this@TopActivity, PostingActivity::class.java)
                    intent.putExtra("POSTINGID", item);
                    startActivity(intent)
                    overridePendingTransition(0, 0)
                }
            }
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val v: View = LayoutInflater.from(this@TopActivity).inflate(
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