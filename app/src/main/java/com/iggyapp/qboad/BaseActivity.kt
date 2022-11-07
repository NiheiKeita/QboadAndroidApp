package com.iggyapp.qboad

import android.app.AlertDialog
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Display
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import com.iggyapp.qboad.common.Define
import com.iggyapp.qboad.common.QBoadRestInterface
import com.iggyapp.qboad.common.Util

open class BaseActivity : AppCompatActivity() {
    val prefs:SharedPreferences get() = getSharedPreferences(Define.SHARED_PREFERENCES_NAME, MODE_PRIVATE)

    val rxQBoadRestInterface: QBoadRestInterface get() = Util.getRxQBoadRestInterface(getString(R.string.url))
//    var apiAccessToken = prefs.getString(Define.API_ACCESS_TOKEN,"")
    var apiAccessToken:String? get() = Util.getApiAccessToken(applicationContext)
        protected set(sApiAccessToken){
            Util.setApiAccessToken(sApiAccessToken, applicationContext)
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val actionBarColor = Color.parseColor("#6200EE")
//        this.window.statusBarColor = actionBarColor

    }

    override fun onStart() {
        super.onStart()
        //フッター処理
        findViewById<TextView>(R.id.top_footer_button)?.setOnClickListener{
            startActivity(Intent(this, TopActivity::class.java))
            overridePendingTransition(0, 0)
            finishAffinity()
        }
        findViewById<TextView>(R.id.search_footer_button)?.setOnClickListener{
            startActivity(Intent(this, SearchActivity::class.java))
            overridePendingTransition(0, 0)
            finishAffinity()
        }
        findViewById<TextView>(R.id.information_footer_button)?.setOnClickListener{
            startActivity(Intent(this, InformationActivity::class.java))
            overridePendingTransition(0, 0)
            finishAffinity()
        }
        findViewById<TextView>(R.id.my_page_footer_button)?.setOnClickListener{
            startActivity(Intent(this, MyPageActivity::class.java))
            overridePendingTransition(0, 0)
            finishAffinity()
        }

        //ヘッダー
        findViewById<ImageView>(R.id.top_header_my_image)?.setOnClickListener{
//            startActivity(Intent(this, MyPageActivity::class.java))
            startActivity(Intent(this, LoginActivity::class.java))
            overridePendingTransition(0, 0)
            finishAffinity()
        }
        findViewById<ImageView>(R.id.search_header_my_image)?.setOnClickListener{
            startActivity(Intent(this, MyPageActivity::class.java))
            overridePendingTransition(0, 0)
            finishAffinity()
        }
        findViewById<ImageView>(R.id.informarion_header_my_image)?.setOnClickListener{
            startActivity(Intent(this, MyPageActivity::class.java))
            overridePendingTransition(0, 0)
            finishAffinity()
        }

        findViewById<TextView>(R.id.posting_header_back_button)?.setOnClickListener{
            finish()
        }
        findViewById<TextView>(R.id.user_page_back_button)?.setOnClickListener{
            finish()
        }

        //新規投稿ボタン
        findViewById<TextView>(R.id.top_header_new_posting_butottn)?.setOnClickListener{
            startActivity(Intent(this, PostingRegisterActivity::class.java))
            overridePendingTransition(0, 0)
        }
        findViewById<TextView>(R.id.my_page_posting_register_button)?.setOnClickListener{
            startActivity(Intent(this, PostingRegisterActivity::class.java))
            overridePendingTransition(0, 0)
        }


        //設定ボタン
        findViewById<TextView>(R.id.my_page_setting)?.setOnClickListener{
            startActivity(Intent(this, SettingActivity::class.java))
            overridePendingTransition(0, 0)
        }
        findViewById<TextView>(R.id.informarion_header_setting)?.setOnClickListener{
            startActivity(Intent(this, SettingActivity::class.java))
            overridePendingTransition(0, 0)
        }


    }
    fun postingPopup(){
        val view = LayoutInflater.from(this@BaseActivity).inflate(R.layout.dialog_positng_count, null, false)
        val dialog = AlertDialog.Builder(this@BaseActivity, R.style.AppTheme_CustomDialog)
            .setView(view)
            .create()

        view.also { rv ->
            rv.findViewById<TextView>(R.id.count_text).text = "1件投稿しました"
            rv.findViewById<TextView>(R.id.close_button).setOnClickListener {
                dialog.dismiss()
            }
        }
        dialog.show()
    }
    fun likePopup(){
        val view = LayoutInflater.from(this@BaseActivity).inflate(R.layout.dialog_positng_count, null, false)
        val dialog = AlertDialog.Builder(this@BaseActivity, R.style.AppTheme_CustomDialog)
            .setView(view)
            .create()

        view.also { rv ->
            rv.findViewById<TextView>(R.id.count_text).text = "1ついいねをもらいました"
            rv.findViewById<TextView>(R.id.close_button).setOnClickListener {
                dialog.dismiss()
            }
        }
        dialog.show()
    }
    fun answerPopup(){
        val view = LayoutInflater.from(this@BaseActivity).inflate(R.layout.dialog_positng_count, null, false)
        val dialog = AlertDialog.Builder(this@BaseActivity, R.style.AppTheme_CustomDialog)
            .setView(view)
            .create()

        view.also { rv ->
            rv.findViewById<TextView>(R.id.count_text).text = "1回解答されました"
            rv.findViewById<TextView>(R.id.close_button).setOnClickListener {
                dialog.dismiss()
            }
        }
        dialog.show()
    }
}