package com.iggyapp.qboad

import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.GridLayout
import android.widget.RadioButton
import android.widget.TextView
import androidx.core.text.isDigitsOnly

class PostingRegisterActivity : BaseActivity() {
    val TAG = "PostingRegisterActivity"
    var oneAnswer = ""
    var twoAnswer = ""
    var threeAnswer = ""
    var fourAnswer = ""
//    val handler = Handler(mainLooper)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posting_register)
        findViewById<RadioButton>(R.id.radio2).isChecked = true
        gridLayoutSetFunction(arrayOf("1個","2個"))
        findViewById<TextView>(R.id.posting_header_new_posting_butottn).setOnClickListener {
            //TODO("問題を投稿するAPIをたたく")
        }
    }
    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            val checked = view.isChecked
            when (view.getId()) {
                R.id.radio2 ->
                    if (checked) {
                        // 1つ目のRadioButtonが選択された時の処理
                        gridLayoutSetFunction(arrayOf("1個","2個"))
                    }
                R.id.radio3 ->
                    if (checked) {
                        // 2つ目のRadioButtonが選択された時の処理
                        gridLayoutSetFunction(arrayOf("1個","2個","3個"))
                    }
                R.id.radio4 ->
                    if (checked) {
                        // 3つ目のRadioButtonが選択された時の処理
                        gridLayoutSetFunction(arrayOf("1個","2個","3個","4個"))
                    }
            }
        }
    }
    fun gridLayoutSetFunction(attr: Array<String>){
        findViewById<GridLayout>(R.id.input_question_answer).let { lv ->
            lv.removeAllViews()
            attr.forEachIndexed { index, s ->
                var attrId = ""
                var attrName = ""
                var attrContent = ""

                when(s){
                    "1個" ->{
                        attrId="1attr"
                        attrName = "１個目の答え"
                        attrContent = oneAnswer
                    }
                    "2個" ->{
                        attrId="2attr"
                        attrName = "2個目の答え"
                        attrContent = twoAnswer
                    }
                    "3個" ->{
                        attrId="3attr"
                        attrName = "3個目の答え"
                        attrContent = threeAnswer
                    }
                    "4個" ->{
                        attrId="4attr"
                        attrName = "4個目の答え"
                        attrContent = fourAnswer
                    }
                }
                layoutInflater.inflate(R.layout.input_question_answer_attr, lv, false).also { v ->
//                v.layoutParams = GridLayout.LayoutParams().also { lp ->
//
//                }
                    val titleAttr = v.findViewById<TextView>(R.id.answer_attr_title)
                    titleAttr.text = attrName
                    titleAttr.tag = attrName
                    val editAttr = v.findViewById<EditText>(R.id.answer_attr_content)
                    editAttr.setText(attrContent)
                    editAttr.addTextChangedListener(object : TextWatcher {
                        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                            //テキスト変更前
                        }
                        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                            //テキスト変更中
                        }
                        override fun afterTextChanged(s: Editable) {
                            //テキスト変更後
                            when(attrId){
                                "1attr" ->{
                                    oneAnswer = s.toString()
                                }
                                "2attr" ->{
                                    twoAnswer = s.toString()
                                }
                                "3attr" ->{
                                    threeAnswer = s.toString()
                                }
                                "4attr" ->{
                                    fourAnswer = s.toString()
                                }
                            }
                        }
                    })
                }.also { lv.addView(it) }
            }
        }
    }
}