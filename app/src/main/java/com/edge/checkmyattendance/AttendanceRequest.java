package com.edge.checkmyattendance;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.edge.checkmyattendance.Dialogs.DialogInformationOverallAttendance;
import com.edge.checkmyattendance.Dialogs.DialogsUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Edeilson Silva on 24/04/2017.
 */

public class AttendanceRequest extends AsyncTask<Student,Void, Void> {

    private JSONObject jsonObj;
    private String FEEDBACK = null;
    private DialogsUtil pDialog;
    private Activity activity;
    private Context context;
    private Student student = null;

    public AttendanceRequest(Context c) {
        context = c;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new DialogsUtil();
        pDialog.progressDialogs(context);
        //student = new Student();
        activity = new MainActivity().getMainActivity;
    }


    @Override
    protected Void doInBackground(Student... params) {


        String jsonStr = "";
        BufferedReader reader = null;

        try {
            // student.setIdStudent(params[0].getIdStudent());
            // student.setBirthDate(params[0].getBirthDate());
            student = new Student(params[0].getIdStudent(),params[0].getBirthDate());


            //Building string to send to webservice
            String data = URLEncoder.encode("studentCode", "UTF-8")
                    + "=" + URLEncoder.encode(student.getIdStudent(), "UTF-8")
                    + "&" + URLEncoder.encode("birthDate", "UTF-8")
                    + "=" + URLEncoder.encode(student.getBirthDate(), "UTF-8");


            //Creating a URL connection
            //URL url = new URL("https://ise.000webhostapp.com/result.php");
            URL url = new URL("http://iseireland.ie/cms/api/attendance");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //Sending data to webservice
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();

            //Receive the response from server
            InputStream in = new BufferedInputStream(conn.getInputStream());
            InputStreamReader inReader = new InputStreamReader(in);
            reader = new BufferedReader(inReader);
            StringBuilder sb = new StringBuilder();
            String line = "";

            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }

            reader.close();

            //Create a JSONObject from string received from webservice
            jsonObj = new JSONObject(sb.toString());
            this.setFeedback(jsonObj);

        } catch (Exception ex) {
            ex.printStackTrace();
            //_sTempMessage = "Sorry no internet connection.";
            Log.i(MainActivity.CMALog, "Error: " + ex);
        } finally {
            try {
                //reader.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                //_sTempMessage = ex.getMessage();
                Log.i(MainActivity.CMALog, "Error: " + ex);
            }
        }

        return null;
    }

    /**
     * Receive the JSON and create a new Student
     */
    private void setFeedback(JSONObject jsonObject) {

            JSONObject jsonObjAux;

            try {

                if (jsonObj.getString("code").equals("200")) {

                    jsonObjAux = jsonObj.getJSONObject("data");

                    student.setNameStudent("StudentName");
                    float perc = Float.parseFloat(jsonObjAux.getString("overall_percentage"));
                    student.setOveralAttendance(String.format("%.0f%%", perc));
                    student.setStartCourseDate(Util.convertDate(jsonObjAux.getString("end_date")));//TODO change it to start_date
                    student.setEndCourseDate(Util.convertDate(jsonObjAux.getString("end_date")));

                    //Unecessary information
                    jsonObjAux.getString("number_of_days");
                    jsonObjAux.getString("number_of_weeks");
                    jsonObjAux.getString("attendance_expected");
                    jsonObjAux.getString("attendance_total");

                    this.buildMessage(jsonObj.getString("message"),student);
                } else {
                    this.buildMessage(jsonObj.getString("message"),student);
                }

            } catch (JSONException e1) {
                e1.printStackTrace();
            }
    }

    private void buildMessage(String message, Student student) {

        if (student != null) {
            FEEDBACK = "ID: " + student.getIdStudent().toString()
                        //TODO add name and photo of student
                        //+"\nStudent: " + student.getNameStudent()
                        + "\nAttendance: " + student.getOveralAttendance().toString()
                        + "\nLast update: " + Util.getWeekAgo()
                        //+ "\nStart Date: --/--/----"
                        //+ "\nEnd Date: " + student.getEndCourseDate();
                        + "\n\nNot valid as a legal document. Any questions please go to ISE reception.";
        } else {
            FEEDBACK = "Sorry, I haven't found any information.\n\nReason: " + message + ". \n\nPlease try again.";
        }

    }


    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        new DialogInformationOverallAttendance(activity, FEEDBACK, student).show();
        pDialog.finishDialog();
        //MainActivity.cleanInputFields();
        //MainActivity.setFocus();
        //student = null;*/
    }

}
