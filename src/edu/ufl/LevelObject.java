package edu.ufl;

import android.graphics.RectF;
import android.graphics.Paint;
import android.graphics.Canvas;

public class LevelObject {

    private RectF rectf;
    public  Paint color;

    // Physic constants
    private static final float GRAVITY = 30f/1000f;    // 0.009 pixels/millisecond^2 (remember +y moves down)
    private static final float SPEED = 250f/1000f; // 250 pixels/millisecond
    private static final float JUMP_SPEED = 500f/1000f;
    private static final float FPS_PERIOD = (float)GameThread.FPS_PERIOD;

    private float x;
    private float y;
    private float h;
    private float w;

    // Velocity dx, dy
    private float dx;
    private float dy;
    
    private boolean canJump;

    LevelObject(float x, float y, float w, float h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        rectf  = new RectF(x,y,x+w,y+h);

        color  = new Paint();
        color.setARGB(255, 0x04, 0x5f, 0x18);
    }

    public float getX()      { return x;  }
    public float getY()      { return y;  }
    public void  setX(float x) { this.x = x; commitPosition(); }
    public void  setY(float y) { this.y = y; commitPosition(); }

    public float getDX()     { return dx; }
    public float getDY()     { return dy; }
    public void  setDX(float dx) {this.dx = dx; }
    public void  setDY(float dy) {this.dy = dy; }

    public float getWidth()  { return w;  }
    public float getHeight() { return h;  }

    public RectF getRectF() { return rectf; }
    
    public void setCanJump(boolean canJump) { this.canJump = canJump; }


    public void update(GameController controller) {
        if      (controller.isLeftPressed())  { dx = -SPEED; }
        else if (controller.isRightPressed()) { dx =  SPEED; }
        else    { dx = 0; }

        if (controller.isJumpPressed() && canJump) { dy = -JUMP_SPEED; }

        dy += GRAVITY;
        
        this.setX(rectf.left + dx * FPS_PERIOD);
        this.setY(rectf.top  + dy * FPS_PERIOD);
        
    }

    public void draw(Canvas canvas,Camera camera) {
        camera.draw(this,canvas);
    }

    private void commitPosition() {
        rectf.offsetTo(Math.round(x),Math.round(y));
    }


}
