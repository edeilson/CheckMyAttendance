package com.edge.checkmyattendance.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.edge.checkmyattendance.R;
import com.edge.checkmyattendance.Student;

public class DialogInformationOverallAttendance  extends Dialog implements
        android.view.View.OnClickListener {

    private Activity activity;
    private String message;
    private Student student;
    private Button cmdClose, cmdAttendanceDetails;

    public DialogInformationOverallAttendance(@NonNull Context context) {
        super(context);
    }


   /* public DialogInformationOverallAttendance(Activity activity, String message, Student student) {
        super(activity);
        // TODO Auto-generated constructor stub
        this.activity = activity;
        this.message = message;
        this.student = student;
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_dialog_information_overall_attendance);

        TextView txtMessage = (TextView) findViewById(R.id.text_dialog_information);
        txtMessage.setText(message);

        cmdClose = (Button) findViewById(R.id.btn_dialog_information);
        cmdClose.setOnClickListener(this);

        cmdAttendanceDetails = (Button) findViewById(R.id.btn_details_chart);
        cmdAttendanceDetails.setOnClickListener(this);
        cmdAttendanceDetails.setEnabled(false);

        //If there
        if(student.getStartCourseDate()==null){
            cmdAttendanceDetails.setEnabled(false);
        }

    }

    @Override
    public void onClick(View v) {

    }
/*



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dialog_information:
                //c.finish();
                s = "";
                dismiss();
                break;
            case R.id.btn_details_chart:

               *//* Intent intent = new Intent(activity,AttendanceDetailsActivity.class);
                activity.startActivity(intent);
                AttendanceChart ac = new AttendanceChart(MainActivity.barChart);
                ac.buildChart(student);*//*

                //Call the the attendance details
                Intent i = new Intent(activity, AttendanceDetailsActivity.class);
                i.putExtra("student",student);
                activity.startActivity(i);

                break;
            default:
                break;
        }
    }*/
}
