package com.iggyapp.qboad

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import de.hdodenhof.circleimageview.CircleImageView

class MyPageActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)
        findViewById<CircleImageView>(R.id.my_page_my_image).setImageResource(R.drawable.sample_nihei_icon)
        findViewById<TextView>(R.id.my_page_footer_button).setBackgroundColor(Color.parseColor("#808080"))
        findViewById<TextView>(R.id.my_page_footer_button).setTextColor(Color.WHITE)

        tabClick()
        countTap()

        myPostingFragmantView()
        findViewById<TextView>(R.id.tab_posting_button).also{bt->
            clearTab()
            bt.setBackgroundColor(Color.parseColor("#808080"))
            bt.setTextColor(Color.WHITE)
        }
    }
    fun countTap(){
        findViewById<TextView>(R.id.user_posting_count).also{ bt->
            bt.setOnClickListener {
                postingPopup()
            }
        }
        findViewById<TextView>(R.id.my_posting_count_text).also{bt->
            bt.setOnClickListener {
                postingPopup()
            }
        }
        findViewById<TextView>(R.id.my_like_count).also{bt->
            bt.setOnClickListener {
                likePopup()
            }
        }
        findViewById<TextView>(R.id.my_like_count_text).also{bt->
            bt.setOnClickListener {
                likePopup()
            }
        }
        findViewById<TextView>(R.id.user_posting_answer_count).also{ bt->
            bt.setOnClickListener {
                answerPopup()
            }
        }
        findViewById<TextView>(R.id.my_posting_answer_count_text).also{bt->
            bt.setOnClickListener {
                answerPopup()
            }
        }
        findViewById<TextView>(R.id.user_follower_count).also{ bt->
            bt.setOnClickListener {
                val intent = Intent(this@MyPageActivity, FolowerActivity::class.java)
                intent.putExtra("USERID", 1)
                intent.putExtra("FLAG", "follow")
                startActivity(intent)
            }
        }
        findViewById<TextView>(R.id.my_follower_count_text).also{bt->
            bt.setOnClickListener {
                val intent = Intent(this@MyPageActivity, FolowerActivity::class.java)
                intent.putExtra("USERID", 1)
                intent.putExtra("FLAG", "follow")
                startActivity(intent)
            }
        }
        findViewById<TextView>(R.id.user_follow_count).also{ bt->
            bt.setOnClickListener {
                val intent = Intent(this@MyPageActivity, FolowerActivity::class.java)
                intent.putExtra("USERID", 1)
                intent.putExtra("FLAG", "follow")
                startActivity(intent)
            }
        }
        findViewById<TextView>(R.id.my_follow_count_text).also{bt->
            bt.setOnClickListener {
                val intent = Intent(this@MyPageActivity, FolowerActivity::class.java)
                intent.putExtra("USERID", 1)
                intent.putExtra("FLAG", "follow")
                startActivity(intent)
            }
        }
    }
    fun tabClick(){
        findViewById<TextView>(R.id.tab_posting_button).also{bt->
            bt.setOnClickListener {
                clearTab()
                bt.setBackgroundColor(Color.parseColor("#808080"))
                bt.setTextColor(Color.WHITE)
                myPostingFragmantView()
            }
        }
        findViewById<TextView>(R.id.tab_history_button).also{bt->
            bt.setOnClickListener {
                clearTab()
                bt.setBackgroundColor(Color.parseColor("#808080"))
                bt.setTextColor(Color.WHITE)
                myHistoryFragmantView()
            }
        }
        findViewById<TextView>(R.id.tab_list_button).also{bt->
            bt.setOnClickListener {
                clearTab()
                bt.setBackgroundColor(Color.parseColor("#808080"))
                bt.setTextColor(Color.WHITE)
                myListFragmantView()
            }
        }
        findViewById<TextView>(R.id.tab_save_button).also{bt->
            bt.setOnClickListener {
                clearTab()
                bt.setBackgroundColor(Color.parseColor("#808080"))
                bt.setTextColor(Color.WHITE)
                mySaveFragmantView()
            }
        }
    }

    fun myPostingFragmantView() {
        val bundle = Bundle()
        bundle.putString("POSTINGID", "1")
        val firstFragment = MyPagePostingFragment()
        firstFragment.setArguments(bundle)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.my_page_screen_fragment, firstFragment)
        fragmentTransaction.commit()
    }
    fun myHistoryFragmantView() {
        val bundle = Bundle()
        bundle.putString("POSTINGID", "1")
        val firstFragment = MyPageHistoryFragment()
        firstFragment.setArguments(bundle)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.my_page_screen_fragment, firstFragment)
        fragmentTransaction.commit()
    }
    fun myListFragmantView() {
        val bundle = Bundle()
        bundle.putString("POSTINGID", "1")
        val firstFragment = MyPageListFragment()
        firstFragment.setArguments(bundle)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.my_page_screen_fragment, firstFragment)
        fragmentTransaction.commit()
    }
    fun mySaveFragmantView() {
        val bundle = Bundle()
        bundle.putString("POSTINGID", "1")
        val firstFragment = MyPageSaveFragment()
        firstFragment.setArguments(bundle)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.my_page_screen_fragment, firstFragment)
        fragmentTransaction.commit()
    }
    fun clearTab(){
        findViewById<TextView>(R.id.tab_posting_button).also{bt->
            bt.setBackgroundColor(Color.parseColor("#EEEEEE"))
            bt.setTextColor(Color.BLACK)
        }
        findViewById<TextView>(R.id.tab_history_button).also{bt->
            bt.setBackgroundColor(Color.parseColor("#EEEEEE"))
            bt.setTextColor(Color.BLACK)
        }
        findViewById<TextView>(R.id.tab_list_button).also{bt->
            bt.setBackgroundColor(Color.parseColor("#EEEEEE"))
            bt.setTextColor(Color.BLACK)
        }
        findViewById<TextView>(R.id.tab_save_button).also{bt->
            bt.setBackgroundColor(Color.parseColor("#EEEEEE"))
            bt.setTextColor(Color.BLACK)
        }
    }
}