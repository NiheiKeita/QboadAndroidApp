package com.iggyapp.qboad

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.adapter_posting_one_section.view.*

class FolowerActivity : AppCompatActivity() {
    var userId = ""
    var flag = ""
    private var mRecyclerView: RecyclerView? = null
    private var fRecyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_folower)
        val i = getIntent()
        userId = i.getStringExtra("USERID").toString()
        flag = i.getStringExtra("FLAG").toString()
        findViewById<TextView>(R.id.follow_header_back_button).setOnClickListener {
            finish()
        }

        val array = arrayOf("らくだ","あんこ","カルピス","いぎー")
        val adapter = PostingListAdapter(array)
        mRecyclerView = findViewById<View>(R.id.recycler_follower) as RecyclerView
        mRecyclerView!!.layoutManager = LinearLayoutManager(this)
        mRecyclerView!!.setAdapter(adapter)

        val arrays = arrayOf("らくだ","らくだ","らくだ","らくだ")
        val adapters = PostingListAdapter(arrays)
        fRecyclerView = findViewById<View>(R.id.recycler_follow) as RecyclerView
        fRecyclerView!!.layoutManager = LinearLayoutManager(this)
        fRecyclerView!!.setAdapter(adapters)

        if (flag == "follow"){
            mRecyclerView?.visibility = View.VISIBLE
            fRecyclerView?.visibility = View.GONE
        }else{
            fRecyclerView?.visibility = View.VISIBLE
            mRecyclerView?.visibility = View.GONE
        }
        findViewById<TextView>(R.id.tab_follow_button).also{bt->
            bt.setOnClickListener {
                clearTab()
                mRecyclerView?.visibility = View.VISIBLE
                fRecyclerView?.visibility = View.GONE
                bt.setBackgroundColor(Color.parseColor("#808080"))
                bt.setTextColor(Color.WHITE)
            }
            if (flag == "follow"){
                mRecyclerView?.visibility = View.VISIBLE
                fRecyclerView?.visibility = View.GONE
                bt.setBackgroundColor(Color.parseColor("#808080"))
                bt.setTextColor(Color.WHITE)
            }
        }
        findViewById<TextView>(R.id.tab_follower_button).also{bt->
            bt.setOnClickListener {
                clearTab()
                fRecyclerView?.visibility = View.VISIBLE
                mRecyclerView?.visibility = View.GONE
                bt.setBackgroundColor(Color.parseColor("#808080"))
                bt.setTextColor(Color.WHITE)
            }
            if (flag == "follower"){
                fRecyclerView?.visibility = View.VISIBLE
                mRecyclerView?.visibility = View.GONE
                bt.setBackgroundColor(Color.parseColor("#808080"))
                bt.setTextColor(Color.WHITE)
            }
        }
    }
    fun clearTab(){
        findViewById<TextView>(R.id.tab_follow_button).also{bt->
            bt.setBackgroundColor(Color.parseColor("#EEEEEE"))
            bt.setTextColor(Color.BLACK)
        }
        findViewById<TextView>(R.id.tab_follower_button).also{bt->
            bt.setBackgroundColor(Color.parseColor("#EEEEEE"))
            bt.setTextColor(Color.BLACK)
        }
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
                        itemView.findViewById<CircleImageView>(R.id.follow_user_image).setImageResource(R.drawable.sample_rakuda_icon)
//                        itemView.posting_question_image.visibility = View.GONE
                    }
                    "あんこ" ->{
                        userName = item
                        answerCorectRate = "正答率：20％"
                        questionContent = "下の画像の犬種はなに？"
                        postingTags = "#いぬ #犬種"
                        itemView.findViewById<CircleImageView>(R.id.follow_user_image).setImageResource(R.drawable.sample_annko_icon)
//                        itemView.posting_question_image.visibility = View.VISIBLE
//                        itemView.posting_question_image.setImageResource(R.drawable.sample_anko_question)
                    }
                    "カルピス" ->{
                        userName = item
                        answerCorectRate = "正答率：35％"
                        questionContent = "チューリップの花言葉は？"
                        postingTags = "#花言葉 #花言葉"
                        itemView.findViewById<CircleImageView>(R.id.follow_user_image).setImageResource(R.drawable.sample_karupisu_icon)
//                        itemView.posting_question_image.visibility = View.GONE
                    }
                    "いぎー" ->{
                        userName = item
                        answerCorectRate = "正答率：15％"
                        questionContent = "10! の答えは？"
                        postingTags = "#数学"
                        itemView.findViewById<CircleImageView>(R.id.follow_user_image).setImageResource(R.drawable.sample_nihei_icon)
//                        itemView.posting_question_image.visibility = View.GONE
                    }
                    else->{
//                        itemView.posting_question_image.visibility = View.GONE
                    }
                }

                itemView.findViewById<TextView>(R.id.follow_user_name).text = userName
//                itemView.posting_correct_answer_rate.text = answerCorectRate
//                itemView.posting_question_content.text = questionContent
//                itemView.posting_tag.text = postingTags

//                itemView.posting_user_image.setOnClickListener {
//                }
                itemView.setOnClickListener {
                    val intent = Intent(this@FolowerActivity, UserPageActivity::class.java)
                    intent.putExtra("USERID", item)
                    startActivity(intent)
                    overridePendingTransition(0, 0)
                }
            }
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val v: View = LayoutInflater.from(this@FolowerActivity).inflate(
                R.layout.adapter_follower_one_section,
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