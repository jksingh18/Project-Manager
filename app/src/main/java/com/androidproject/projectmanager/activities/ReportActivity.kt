package com.androidproject.projectmanager.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.androidproject.projectmanager.R
import com.androidproject.projectmanager.models.Board
import com.androidproject.projectmanager.utils.Constants
import kotlinx.android.synthetic.main.activity_report.*
import kotlinx.android.synthetic.main.activity_task_list.*

class ReportActivity : BaseActivity() {
    private lateinit var mBoardDetails: Board
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)
        //setupActionBar()

        if (intent.hasExtra(Constants.BOARD_DETAIL)) {
            mBoardDetails = intent.getParcelableExtra(Constants.BOARD_DETAIL) as Board
        }

        loadReport(mBoardDetails)
    }
    private fun setupActionBar() {
        setSupportActionBar(toolbar_report_activity)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_white_color_back_24dp)
            actionBar.title = mBoardDetails.name

        }

        toolbar_report_activity.setNavigationOnClickListener { onBackPressed() }
    }
    fun loadReport(mBoardDetails:Board)
    {
        var tcount:Int=0

        var result:String=""
        var result1:String=""

        var completedCards:Float=0F
        var totalCards:Float=0F

        for (task in mBoardDetails.taskList)
        {
            result+=""+(tcount+1)+" "+task.title+"\n"
            result1+="\n"

            var ccount:Int=0
            for(card in task.cards)
            {

                var cardcomplete=""
                if(card.taskDone==1)
                {
                    cardcomplete="Completed"
                    completedCards+=1
                }
                else
                {
                    cardcomplete="Not Completed"
                }
                result+="   "+(ccount+1)+" "+card.name+" "+"\n"
                result1+=cardcomplete+"\n"

                ccount+=1
                totalCards+=1
            }
            tcount+=1

        }
        result+="Task complete Percentage "+"\n"
        result1+=""+((completedCards/totalCards)*100)+"\n"

        tv_report_board.text=result
        tv_report1_board.text=result1


    }
}