package com.iggyapp.qboad

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_top.*
import kotlinx.android.synthetic.main.adapter_posting_one_section.view.*
import java.util.logging.Logger

//private const val POSTINGID = "POSTINGID"

class PostingQuestionFragment : Fragment() {
    private var postingID: String? = ""
    private var mRecyclerView: RecyclerView? = null
    lateinit var fragmentView: View
    var isSelect = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            postingID = it.getString("POSTINGID")
            Log.e("s",postingID.toString())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_posting_question, container, false)
        fragmentView =view
        //TODO(投稿をセット)

        var array = arrayOf("")
        var userName = ""
        var answerCorectRate = "正答率："
        var questionContent = ""
        var postingTags = ""
        val postingQuestionUserName = view.findViewById<TextView>(R.id.posting_question_user_name)
        val postingQuestionCorrectAnswerRate = view.findViewById<TextView>(R.id.posting_question_correct_answer_rate)
        val postingQuestionQuestionContent = view.findViewById<TextView>(R.id.posting_question_question_content)
        val postingQuestionUserImage = view.findViewById<ImageView>(R.id.posting_question_user_image)
        val postingQuestionQuestionImage = view.findViewById<ImageView>(R.id.posting_question_question_image)
        when(postingID){
            "らくだ" ->{
                array = arrayOf("16","25","35","41")
                userName = postingID.toString()
                answerCorectRate = "正答率：50％"
                questionContent = "10＋15は？"
                postingTags = "#数学 #足し算 #らくだ"
                postingQuestionUserImage.setImageResource(R.drawable.sample_rakuda_icon)
                postingQuestionQuestionImage.visibility = View.GONE
            }
            "あんこ" ->{
                array = arrayOf("ポメラニアン","ビジョンフリーゼ","マルチーズ")
                userName = postingID.toString()
                answerCorectRate = "正答率：20％"
                questionContent = "下の画像の犬種はなに？"
                postingTags = "#いぬ #犬種"
                postingQuestionUserImage.setImageResource(R.drawable.sample_annko_icon)
                postingQuestionQuestionImage.visibility = View.VISIBLE
                postingQuestionQuestionImage.setImageResource(R.drawable.sample_anko_question)
            }
            "カルピス" ->{
                array = arrayOf("愛の告白","悲しい","美しい","元気")
                userName = postingID.toString()
                answerCorectRate = "正答率：35％"
                questionContent = "チューリップの花言葉は？"
                postingTags = "#花言葉 #花言葉"
                postingQuestionUserImage.setImageResource(R.drawable.sample_karupisu_icon)
                postingQuestionQuestionImage.visibility = View.GONE
            }
            "いぎー" ->{
                array = arrayOf("552","1400","3628800","58789500")
                userName = postingID.toString()
                answerCorectRate = "正答率：15％"
                questionContent = "10! の答えは？"
                postingTags = "#数学"
                postingQuestionUserImage.setImageResource(R.drawable.sample_nihei_icon)
                postingQuestionQuestionImage.visibility = View.GONE
            }
            else->{
                postingQuestionQuestionImage.visibility = View.GONE
            }
        }

        postingQuestionUserName.text = userName
        postingQuestionCorrectAnswerRate.text = answerCorectRate
        postingQuestionQuestionContent.text = questionContent

        val adapter = PostingListAdapter(array)
        mRecyclerView = view.findViewById<View>(R.id.recycler_posting_choices) as RecyclerView
        mRecyclerView!!.layoutManager = LinearLayoutManager(activity)
        mRecyclerView!!.setAdapter(adapter)

        postingQuestionUserImage.setOnClickListener {
            val intent = Intent(activity, UserPageActivity::class.java)
            intent.putExtra("USERID", postingID)
            startActivity(intent)
        }

        return view
    }
    inner class PostingListAdapter(
        var array: Array<String>,
    ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        inner class PostingListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            fun onBind(position: Int){
                val item = array[position]
                //TODO(投稿をセット)
                itemView.findViewById<TextView>(R.id.posting_quest_choice_name).text = item

                itemView.setOnClickListener {
                    if (!isSelect){
                        isSelect = true
                        var resultBool = false
                        when(postingID) {
                            "らくだ" -> {
                                if("25" == item){
                                    resultBool = true
                                }
                            }
                            "あんこ" -> {
                                if("マルチーズ" == item){
                                    resultBool = true
                                }
                            }
                            "カルピス" -> {
                                if("愛の告白" == item){
                                    resultBool = true
                                }
                            }
                            "いぎー" -> {
                                if("3628800" == item){
                                    resultBool = true
                                }
                            }
                        }
                        val questionResult = fragmentView.findViewById<TextView>(R.id.question_result)
                        questionResult.visibility = View.VISIBLE
                        if(resultBool){
                            questionResult.text = "正解！！"
                            questionResult.setTextColor(Color.parseColor("#FF0000"))
                        }else{
                            questionResult.text = "残念！！"
                            questionResult.setTextColor(Color.parseColor("#0000FF"))
                        }
                    }
                }
            }
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val v: View = LayoutInflater.from(activity).inflate(
                R.layout.adapter_posting_question,
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

//    companion object {
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            PostingQuestionFragment().apply {
//                arguments = Bundle().apply {
////                    putString(POSTINGID, postingID)
////                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}