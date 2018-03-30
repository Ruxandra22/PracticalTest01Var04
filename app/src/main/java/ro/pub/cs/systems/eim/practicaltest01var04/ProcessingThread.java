package ro.pub.cs.systems.eim.practicaltest01var04;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Random;

/**
 * Created by student on 30.03.2018.
 */

public class ProcessingThread extends Thread {

    private Context context = null;
    private boolean isRunning = true;

//    private Random random = new Random();
    private String action = "action";
    private String text;

    public ProcessingThread(Context context, String text) {
        this.context = context;
        this.text = text;
    }

    @Override
    public void run() {
        Log.d("[ProcessingThread]", "Thread has started!");
        while (isRunning) {
            sendMessage();
            sleep();
        }
        Log.d("[ProcessingThread]", "Thread has stopped!");
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction(action);
        intent.putExtra("text", text);
        context.sendBroadcast(intent);
        Log.d("[sendMessage]", "Message was sent");
    }

    private void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}
