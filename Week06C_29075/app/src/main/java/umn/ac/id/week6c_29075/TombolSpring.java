package umn.ac.id.week6c_29075;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatButton;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FlingAnimation;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import androidx.dynamicanimation.animation.SpringAnimation;

import static androidx.dynamicanimation.animation.SpringForce.STIFFNESS_LOW;

public class TombolSpring extends AppCompatButton {

    public TombolSpring(Context context) {
        super(context);
    }
    public TombolSpring(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public TombolSpring(Context context, AttributeSet attrs,
                        int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                FlingAnimation fling = new FlingAnimation(
                        this, DynamicAnimation.ROTATION_X);
                fling.setStartVelocity(150)
                        .setFriction(0.11f)
                        .start();
                break;
            default:
        }
        return super.onTouchEvent(event);
    }

}
