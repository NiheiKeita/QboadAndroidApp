package com.iggyapp.qboad

import android.os.Bundle

class PostingActivity : BaseActivity() {
    var postingID = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posting)
        val i = getIntent()
        postingID = i.getStringExtra("POSTINGID").toString()
        fragmantView()
    }

    fun fragmantView() {
        val bundle = Bundle()
        bundle.putString("POSTINGID", postingID)
        val firstFragment = PostingQuestionFragment()
        firstFragment.setArguments(bundle)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.posting_screen_fragment, firstFragment)
        fragmentTransaction.commit()
    }
}