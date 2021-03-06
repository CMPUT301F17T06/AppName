/*
 * Copyright 2017 Max Schafer, Ammar Mahdi, Riley Dixon, Steven Weikai Lu, Jiaxiong Yang
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.example.lit.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lit.R;
import com.example.lit.exception.HabitFormatException;
import com.example.lit.exception.LoadHabitException;
import com.example.lit.habit.Habit;
import com.example.lit.habit.NormalHabit;
import com.example.lit.habitevent.HabitEvent;
import com.example.lit.habitevent.NormalHabitEvent;
import com.example.lit.location.HabitLocation;
import com.example.lit.saving.DataHandler;
import com.example.lit.saving.NoDataException;
import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.List;

import static org.apache.commons.lang3.BooleanUtils.and;


/**
 * viewHabitActivity
 * Version 1.0
 *
 * Nov.13 2017
 *
 * @see HomePageActivity
 *
 *
 *
 * Copyright 2017 Team 6, CMPUT301, University of Alberta-All Rights Reserved.
 * You may use distribute, or modify this code under terms and conditions of the Code of Student Behaviour at University of Alberta.
 * you may find a copy of the license in the project. Otherwise please contact jiaxiong@ualberta.ca
 */
public class ViewHabitEventActivity extends AppCompatActivity {

    private static final String CLASS_KEY = "com.example.lit.activity.ViewHabitActivity";

    NormalHabitEvent currentEvent;
    String habitTitleString;
    String habitCommentString;
    String habitDateStartedString;
    List<Calendar> habitCalenderList;
    String weeklyString;
    TextView weeklyTextView;
    TextView habitTitle;
    TextView habitComment;
    TextView habitDateStarted;
    Button editHabit;
    Button deleteHabit;
    Button mainMenu;
    Button addHabitEventButton;
    //String username;
    ImageView habitImageView;
    Bitmap habitImage;

    DataHandler eventDataHandler;
    DataHandler dataHandler;
    Integer index;
    ArrayList<NormalHabitEvent> habitArrayList;
    List<Integer> selectedWeekdays;
    int hour;
    int minute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_habit_event);

        try{
            Bundle bundle = getIntent().getExtras();
            currentEvent = (NormalHabitEvent)bundle.getParcelable("habitEvent");
            eventDataHandler = (DataHandler)bundle.getSerializable("eventDataHandler");
            index = bundle.getInt("index");

            habitArrayList = (ArrayList<NormalHabitEvent>) eventDataHandler.loadData();


            if (!(currentEvent instanceof HabitEvent)) throw new LoadHabitException();
        }catch (LoadHabitException e){
            //TODO: handle LoadHabitException
        }catch (NoDataException e2){
            Toast.makeText(ViewHabitEventActivity.this,"Error: Can't load data! code:5",Toast.LENGTH_LONG).show();
        }
        // Retrieve habitEvent info
        habitTitleString = currentEvent.getHabitEventName();
        habitCommentString = currentEvent.getEventComment();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        habitDateStartedString = format.format(currentEvent.getEventDate());
        habitImage = currentEvent.getImage();


        // Set up view components
        habitImageView = (ImageView) findViewById(R.id.viewHabitImage2);
        habitTitle = (TextView) findViewById(R.id.habit_title_TextView);
        habitComment = (TextView) findViewById(R.id.Comment_TextView);
        habitDateStarted = (TextView) findViewById(R.id.habit_schedule_title);
        habitTitle.setText(habitTitleString);
        habitComment.setText(habitCommentString);
        habitDateStarted.setText(habitDateStartedString);
        habitImageView.setImageBitmap(habitImage);


        // Set up buttons
        editHabit = (Button) findViewById(R.id.edit_habit_button);
        deleteHabit = (Button) findViewById(R.id.delete_habit_button);
        mainMenu = (Button) findViewById(R.id.main_menu_button2);

        editHabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //toEditHabitEventActivity(currentEvent);
            }
        });

        deleteHabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteHabit(habitArrayList, index);
            }
        });

        mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    /**
     * This function jump to EditHabitActivity
     * @param habitEvent
     */
    public void toEditHabitEventActivity(HabitEvent habitEvent){
        Log.i("ViewHabitActivity", "Edit button pressed.");

        Intent intent = new Intent(ViewHabitEventActivity.this,EditHabitActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("habitEvent", habitEvent);
        bundle.putSerializable("eventDataHandler",eventDataHandler);
        bundle.putInt("index",index);
        intent.putExtras(bundle);
        startActivityForResult(intent,2);
    }


    public void deleteHabit(ArrayList<NormalHabitEvent> habitArrayList, int index){
        habitArrayList.remove(index);
        dataHandler.saveData(habitArrayList);
        Log.i("ViewHabitActivity", "Delete button pressed.");
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 2) {
            finish();
        }
    }

}
