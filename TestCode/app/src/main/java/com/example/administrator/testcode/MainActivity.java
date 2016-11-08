package com.example.administrator.testcode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewById(R.id.testtext)
    TextView textview;

    @AfterViews
    void afterViews() {
        // TODO: Do something after the views are injected
        textview.setText("this is annotaition test 1.");
    }


}
