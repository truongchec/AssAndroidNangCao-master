package com.example.dung.ass

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.example.dung.ass.map.MapActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnBangTin.setOnClickListener(this)
        btnBao.setOnClickListener(this)
        btnChiaSe.setOnClickListener(this)
        btnMap.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        when (v!!.id) {
            R.id.btnMap -> {
                val intent1: Intent = Intent(this@MainActivity, MapActivity::class.java)
                startActivity(intent1)

            }
            R.id.btnBao -> {
                val intent: Intent = Intent(this@MainActivity, DocBaoActivity::class.java)
                startActivity(intent)
            }
            R.id.btnBangTin -> {
                val intent1: Intent = Intent(this@MainActivity, HocTapActivity::class.java)
                startActivity(intent1)


            }
            R.id.btnChiaSe -> {
                val intent: Intent = Intent(this@MainActivity, Social::class.java)
                startActivity(intent)

            }
        }

    }


    /**
     *
     */
    fun addFragment(f: Fragment, rooId: Int, isAddToBackTask: Boolean) {
        val tag = f.javaClass.name
        val fm = supportFragmentManager
        var fragment = fm.findFragmentByTag(tag)

        if (fragment != null) {
            val frms: ArrayList<Fragment> = fm.fragments as ArrayList<Fragment>
            for (frm: Fragment in frms) {
                fm.beginTransaction()
                    .setCustomAnimations(
                        R.anim.fragment_slide_left_enter,
                        R.anim.fragment_slide_left_exit,
                        R.anim.fragment_slide_right_enter,
                        R.anim.fragment_slide_right_exit
                    )
                    .hide(frm)
                    .commit()
            }
            fm.beginTransaction()
                .show(f)
                .commit()

        } else {
            fragment = f
            if (isAddToBackTask) {
                fm.beginTransaction()
                    .add(rooId, fragment, tag)
                    .addToBackStack(null)
                    .commit()
            } else {
                fm.beginTransaction()
                    .add(rooId, fragment, tag)
                    .commit()
            }
        }
    }

    fun popBack() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStackImmediate()
        } else {
            finish()
        }

    }

}
