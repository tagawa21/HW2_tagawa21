/* @Author: Andrew Tagawa
 * CS 301A
 * 9/30/18
 * This class defines the Face class and all associated methods.
 */

package com.example.wyzec.facemaker;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceView;
import java.util.Random;

public class Face extends SurfaceView
{
    private int skinRed = 0;
    private int skinGreen = 0;
    private int skinBlue = 0;
    private int skinColor;

    private int eyeRed = 0;
    private int eyeGreen = 0;
    private int eyeBlue = 0;
    private int eyeColor;

    private int hairRed = 0;
    private int hairGreen = 0;
    private int hairBlue = 0;
    private int hairColor;

    private int hairStyle = 0;
    //0 = bald, 1 = balding, 2 = mohawk

    private int partNum = 0;
    //0 = skin, 1 = eyes, 2 = hair

    private Random rnd = new Random();

    //constructors for Face object
    public Face(Context context) {
        super(context);
        setWillNotDraw(false);
        randomize();
    }

    public Face(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        setWillNotDraw(false);
        randomize();
    }

    public Face(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);
        randomize();
    }

    //part number set/get methods
    public void setPartNum(int n){
        partNum = n;
    }
    public int getPartNum() {
        return partNum;
    }

    //getRGB (Skin, Eye, Hair)
    //n = partNum
    public int getRed(int n)
    {
        int out = 0;
        if(n == 0) out = skinRed;
        else if(n == 1) out = eyeRed;
        else if(n == 2) out = hairRed;

        return out;
    }
    public int getGreen(int n)
    {
        int out = 0;
        if(n == 0) out = skinGreen;
        else if(n == 1) out = eyeGreen;
        else if(n == 2) out = hairGreen;

        return out;
    }
    public int getBlue(int n)
    {
        int out = 0;

        if(n == 0) out = skinBlue;
        else if(n == 1) out = eyeBlue;
        else if(n == 2) out = hairBlue;

        return out;
    }

    //setRGB (Skin, Eye, Hair)
    //n = partNum
    public void setRed(int n, int input)
    {
        if(n == 0) skinRed = input;
        else if(n == 1) eyeRed = input;
        else if(n == 2) hairRed = input;
    }

    public void setGreen(int n, int input)
    {
        if(n == 0) skinGreen = input;
        else if(n == 1) eyeGreen = input;
        else if(n == 2) hairGreen = input;
    }

    public void setBlue(int n, int input)
    {
        if(n == 0) skinBlue = input;
        else if(n == 1) eyeBlue = input;
        else if(n == 2) hairBlue = input;
    }

    //set RGB
    //n = partNum
    public void setRGB(int n, int r, int g, int b)
    {
        if(n == 0) //skin
        {
            skinColor = Color.rgb(r,g,b);
        }

        else if(n == 1) //eyes
        {
            eyeColor = Color.rgb(r,g,b);
        }
        else if(n == 2)//hair
        {
            hairColor = Color.rgb(r,g,b);
        }
    }

    //get RGB
    //n = partNum
    public int getRGB(int n)
    {
        int out = Color.rgb(0,0,0);
        if(n == 0) //skin
        {
            out = skinColor;
        }
        else if(n == 1) //eyes
        {
            out = eyeColor;
        }
        else if(n == 2)//hair
        {
            out = hairColor;
        }
        return out;
    }

    //randomize method
    public void randomize()
    {
        //skin
        setRed(0,rnd.nextInt(256-0));
        setGreen(0, rnd.nextInt(256-0));
        setBlue(0,rnd.nextInt(256-0));
        setRGB(0, getRed(0), getGreen(0), getBlue(0));

        //eyes
        setRed(1,rnd.nextInt(256-0));
        setGreen(1, rnd.nextInt(256-0));
        setBlue(1,rnd.nextInt(256-0));
        setRGB(1, getRed(1), getGreen(1), getBlue(1));

        //hair
        setRed(2,rnd.nextInt(256-0));
        setGreen(2, rnd.nextInt(256-0));
        setBlue(2,rnd.nextInt(256-0));
        setRGB(2, getRed(2), getGreen(2), getBlue(2));

        //hairstyle
        hairStyle = rnd.nextInt(3-0);
    }

    //set hairstyle
    public void setHairStyle(int n)
    {
        hairStyle = n;
    }

    //get hairstyle
    public String getHairStyle()
    {
        String out = "";
        if(hairStyle == 0) out = "Bald";
        else if(hairStyle == 1) out = "Balding";
        else if(hairStyle == 2) out = "Mohawk";

        return out;
    }

    //onDraw
    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        Paint skin = new Paint(Color.rgb(getRed(0), getGreen(0), getBlue(0)));
        skin.setStyle(Paint.Style.STROKE);
        Paint eyes = new Paint(Color.rgb(getRed(1), getGreen(1), getBlue(1)));
        eyes.setStyle(Paint.Style.STROKE);
        Paint hair = new Paint(Color.rgb(getRed(2), getGreen(2), getBlue(2)));
        hair.setStyle(Paint.Style.STROKE);

        //face
        canvas.drawOval(canvas.getWidth()/4, canvas.getHeight()/4, canvas.getWidth()/4,canvas.getHeight()/4, skin);
        invalidate();
        //left eye
        canvas.drawOval(2*canvas.getWidth()/7, canvas.getHeight()/3, 4*canvas.getWidth()/7,2*canvas.getHeight()/3, eyes);
        invalidate();
        //right eye
        canvas.drawOval(4*canvas.getWidth()/7, canvas.getHeight()/3, 2*canvas.getWidth()/7,2*canvas.getHeight()/3, eyes);
        invalidate();
        //hair
        if(getHairStyle().equals("Balding"))
        {
            canvas.drawRect(canvas.getWidth()/4, canvas.getHeight()/3, 5*canvas.getWidth()/16, 4*canvas.getHeight()/9, hair);
            invalidate();
            canvas.drawRect(11*canvas.getWidth()/16, canvas.getHeight()/3, 3*canvas.getWidth()/4, 4*canvas.getHeight()/9, hair);
            invalidate();
        }
        else if(getHairStyle().equals("Mohawk"))
        {
            canvas.drawRect(3*canvas.getWidth()/8, canvas.getWidth(), 5*canvas.getWidth()/8, 4*canvas.getHeight()/5, hair);
            invalidate();
        }

    }
}
