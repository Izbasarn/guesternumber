package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.os.Bundle;
import android.gesture.Prediction;
import android.widget.Toast;
import android.gesture.Gesture;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnGesturePerformedListener{
    private GestureLibrary objGestureLib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        objGestureLib=GestureLibraries.fromRawResource(this,R.raw.gestures);
        if (!objGestureLib.load()){
            finish();
            GestureOverlayView objGestureOverlay=(GestureOverlayView) findViewById(R.id.WidgetGesture);
        } else {
            GestureOverlayView objGestureOverlay=(GestureOverlayView) findViewById(R.id.WidgetGesture);
            objGestureOverlay.addOnGesturePerformedListener(this);
        }
    }

    @Override
    public void onGesturePerformed(GestureOverlayView gestureOverlayView, Gesture gesture) {
        ArrayList<Prediction>objPredicition=objGestureLib.recognize(gesture);
        if(objPredicition.size()>0 && objPredicition.get(0).score>1){
            String gestureName=objPredicition.get(0).name;
            Toast.makeText(this,gestureName,Toast.LENGTH_SHORT).show();
        }
    }
}