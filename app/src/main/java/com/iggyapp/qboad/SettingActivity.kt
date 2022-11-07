package com.iggyapp.qboad

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SettingActivity : AppCompatActivity() {
    private var mRecyclerView: RecyclerView? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        findViewById<TextView>(R.id.setting_back_button).setOnClickListener {
            finish()
        }
        
        val array = arrayOf("プロフィール","ヘルプ","通知","ログアウト","退会")
        val adapter = PostingListAdapter(array)
        mRecyclerView = findViewById<View>(R.id.recycler_setting) as RecyclerView
        mRecyclerView!!.layoutManager = LinearLayoutManager(this)
        mRecyclerView!!.setAdapter(adapter)
    }

    inner class PostingListAdapter(
        var array: Array<String>,
    ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        inner class PostingListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            fun onBind(position: Int){
                val item = array[position]

                itemView.findViewById<TextView>(R.id.setting_title_name).text = item
                itemView.setOnClickListener {
                    when(item){
                        "プロフィール" ->{
                            val intent = Intent(this@SettingActivity, UserPageActivity::class.java)
                            intent.putExtra("USERID", item)
                            startActivity(intent)
                            overridePendingTransition(0, 0)
                        }
                        "ヘルプ" ->{
                            val intent = Intent(this@SettingActivity, UserPageActivity::class.java)
                            intent.putExtra("USERID", item)
                            startActivity(intent)
                            overridePendingTransition(0, 0)
                        }
                        "通知" ->{

                        }
                        "ログアウト" ->{

                        }
                        "退会" ->{
                            val intent = Intent(this@SettingActivity, UserPageActivity::class.java)
                            intent.putExtra("USERID", item)
                            startActivity(intent)
                            overridePendingTransition(0, 0)
                        }
                        else->{

                        }
                    }
                }
            }
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val v: View = LayoutInflater.from(this@SettingActivity).inflate(
                R.layout.adapter_setting_section,
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