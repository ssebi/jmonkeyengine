package com.g3d.input.lwjgl;

import com.g3d.input.KeyInput;
import com.g3d.input.event.KeyInputEvent;
import com.g3d.input.RawInputListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;

public class LwjglKeyInput implements KeyInput {

    private static final Logger logger = Logger.getLogger(LwjglKeyInput.class.getName());

    private RawInputListener listener;

    public void initialize() {
        try {
            Keyboard.create();
//            Keyboard.enableRepeatEvents(true);
            logger.info("Keyboard created.");
        } catch (LWJGLException ex) {
            logger.log(Level.SEVERE, "Error while creating keyboard.", ex);
        }
    }

    public boolean isKeyDown(int key){
        return Keyboard.isKeyDown(key);
    }

    public int getKeyCount(){
        return Keyboard.KEYBOARD_SIZE;
    }

    public void update() {
        Keyboard.poll();
        while (Keyboard.next()){
            int keyCode = Keyboard.getEventKey();
            char keyChar = Keyboard.getEventCharacter();
            boolean pressed = Keyboard.getEventKeyState();
            boolean down = false;
//            boolean down = Keyboard.isRepeatEvent();
            long time = Keyboard.getEventNanoseconds();
            KeyInputEvent evt = new KeyInputEvent(keyCode, keyChar, pressed, down);
            evt.setTime(time);
            listener.onKeyEvent(evt);
        }
    }

    public void destroy() {
        Keyboard.destroy();
        logger.info("Keyboard destroyed.");
    }

    public boolean isInitialized() {
        return Keyboard.isCreated();
    }

    public void setInputListener(RawInputListener listener) {
        this.listener = listener;
    }

}