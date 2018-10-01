/* @Author: Andrew Tagawa
 * CS 301A
 * 9/30/18
 *
 * This class defines the functions of the app and their behaviors.
 *
 * IMPORTANT:
 * For some reason, the program will not recognize the Face cast
 * to the SurfaceView (Line 36).  In addition to independent time, I also went
 * to extra help and neither I nor the tutor could figure out why.
 * Various solutions such as cloning the Face class, mporting the class,
 * clearing the cache, and renaming the id of the SurfaceView were attempted,
 * but none of them worked.  This app  will crash on startup as it
 * currently is.
 */

package com.example.wyzec.facemaker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.wyzec.facemaker.Face;

public class MainActivity extends AppCompatActivity {

    Face myFace;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myFace = (Face)findViewById(R.id.faceView);

        //Color value text set to seekbar progress (debug)
        final TextView redVal = (TextView) findViewById(R.id.redVal);
        final TextView greenVal = (TextView) findViewById(R.id.greenVal);
        final TextView blueVal = (TextView) findViewById(R.id.blueVal);

        //Spinner
        Spinner hairstyles = (Spinner) findViewById(R.id.hairStyles);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.style_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hairstyles.setAdapter(adapter);

        //Spinner listener
        hairstyles.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                myFace.setHairStyle(position);
                Log.d("Hairstyle", "Selected style: "+myFace.getHairStyle());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        //RGB seekbars and listeners
        //redVal, greenVal, and blueVal are debug/design elements that display their
        // associated seekbar values.
        final SeekBar redBar = (SeekBar) findViewById(R.id.redBar);
        redBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                myFace.setRed(myFace.getPartNum(), progress);
                Log.d("RedBar", "Value: "+Integer.toString(progress));
                redVal.setText(Integer.toString(progress));
            }
        });

        final SeekBar greenBar = (SeekBar) findViewById(R.id.greenBar);
        greenBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                myFace.setGreen(myFace.getPartNum(), progress);
                Log.d("GreenBar", "Value: "+Integer.toString(progress));
                greenVal.setText(Integer.toString(progress));
            }
        });

        final SeekBar blueBar = (SeekBar) findViewById(R.id.blueBar);
        blueBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                myFace.setBlue(myFace.getPartNum(), progress);
                Log.d("BlueBar", "Value: "+Integer.toString(progress));
                blueVal.setText(Integer.toString(progress));
            }
        });

        //Radio group
        //0 = skin, 1 = eyes, 2 = hair
        RadioGroup rg = (RadioGroup) findViewById(R.id.partSelect);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.skinButton) {
                    myFace.setPartNum(0);
                    redBar.setProgress(myFace.getRed(0));
                    greenBar.setProgress(myFace.getGreen(0));
                    blueBar.setProgress(myFace.getBlue(0));
                }
                else if(checkedId == R.id.eyesButton) {
                    myFace.setPartNum(1);
                    redBar.setProgress(myFace.getRed(1));
                    greenBar.setProgress(myFace.getGreen(1));
                    blueBar.setProgress(myFace.getBlue(1));
                }
                else if( checkedId == R.id.hairButton) {
                    myFace.setPartNum(2);
                    redBar.setProgress(myFace.getRed(2));
                    greenBar.setProgress(myFace.getGreen(2));
                    blueBar.setProgress(myFace.getBlue(2));
                }
            }
        });

        //Random button
        //Sets Progress bars to the values
        Button randomButton = (Button) findViewById(R.id.randomButton);
        randomButton.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                myFace.randomize();
                Log.d("Random Button", "Action: Randomizing...");
                redBar.setProgress(myFace.getRed(myFace.getPartNum()));
                Log.d("Random Button", "Red set: "+myFace.getRed(myFace.getPartNum()));
                greenBar.setProgress(myFace.getGreen(myFace.getPartNum()));
                Log.d("Random Button", "Green set: "+myFace.getGreen(myFace.getPartNum()));
                blueBar.setProgress(myFace.getBlue(myFace.getPartNum()));
                Log.d("Random Button", "Blue set: "+myFace.getBlue(myFace.getPartNum()));
            }
        });

        //setting initial random face (just in case)
        //DO NOT TOUCH
        myFace.randomize();
        for(int i = 0; i < 3; i++)
        {
            redBar.setProgress(myFace.getRed(i));
            greenBar.setProgress(myFace.getGreen(i));
            blueBar.setProgress(myFace.getBlue(i));
        }


    }
}
