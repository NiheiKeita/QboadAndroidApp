package com.iggyapp.qboad

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.adapter_posting_one_section.view.*


class MyPageHistoryFragment : Fragment() {
    private var postingID: String? = ""
    lateinit var fragmentView: View
    private var mRecyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            postingID = it.getString("POSTINGID")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_my_page_history, container, false)
        fragmentView =view
        //TODO(投稿をセット)

        val array = arrayOf("あんこ","らくだ","カルピス","いぎー")
        val adapter = PostingListAdapter(array)
        mRecyclerView = view.findViewById<View>(R.id.recycler_my_posting) as RecyclerView
        mRecyclerView!!.layoutManager = LinearLayoutManager(activity)
        mRecyclerView!!.setNestedScrollingEnabled(false)
        mRecyclerView!!.setAdapter(adapter)
        return view
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
                    val intent = Intent(activity, UserPageActivity::class.java)
                    intent.putExtra("USERID", item)
                    startActivity(intent)
                }
                itemView.setOnClickListener {
                    val intent = Intent(activity, PostingActivity::class.java)
                    intent.putExtra("POSTINGID", item);
                    startActivity(intent)
                }
            }
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val v: View = LayoutInflater.from(activity).inflate(
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