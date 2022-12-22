package com.iggyapp.qboad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.iggyapp.qboad.model.UserModel
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.logging.Logger

class LoginActivity : BaseActivity() {
    val TAG="LoginActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        findViewById<TextView>(R.id.login_button).setOnClickListener {
            Log.e(TAG, "start")
            rxQBoadRestInterface.sendLogin()
//            rxQBoadRestInterface.sendLogin("Bearer $apiAccessToken")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object: SingleObserver<UserModel> {
                    override fun onSubscribe(d: Disposable) {}
                    override fun onSuccess(t: UserModel) {
                        Log.e(TAG, "ok")
                        Log.e(TAG, t.status.toString())

                    }
                    override fun onError(e: Throwable) {
                        Log.e(TAG, e.toString())
                    }
                })
        }
    }
}