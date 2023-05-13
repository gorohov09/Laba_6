import java.awt.*;
import java.util.Observable;

public class TextObject extends Observable implements Runnable {
    Thread thr;
    private boolean xplus;
    private boolean yplus;
    int x;
    int y;
    Color col;

    int _numObj;

    String _text;

    int _speed;

    //private int incX;

    private Application _window = null;

    public TextObject(Color col, String text, int speed, int numObj, Application window) {
        xplus = true;
        yplus = true;
        x = 0;
        y = 30;
        this._text = text;
        this._speed = speed;
        this.col = col;
        this._numObj = numObj;
        this._window = window;

        //Test.count++;
        thr = new Thread(this, _numObj + ":" + text + ":");
        thr.start();
    }

    public void run() {
        while (true) {
            if (x >= _window.getSize().width - 20) xplus = false;
            if (x <= -1) xplus = true;

            if (y >= _window.getSize().height - 20) yplus = false;
            if (y <= 29) yplus = true;

            if (xplus) x += _speed;
            else x -= _speed;
            if (yplus) y += _speed;
            else y -= _speed;
            setChanged();
            notifyObservers(this);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
        }
    }
}
