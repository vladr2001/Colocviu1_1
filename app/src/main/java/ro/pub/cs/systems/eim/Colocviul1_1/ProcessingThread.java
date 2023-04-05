package ro.pub.cs.systems.eim.Colocviul1_1;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Date;
import java.util.Random;

public class ProcessingThread extends Thread {

    private Context context = null;
    private String first;


    public ProcessingThread(android.content.Context context, String a) {
        this.context = context;
        this.first = a;
    }

    @Override
    public void run() {
        Log.d("THREAD DEBUG", "Thread has started!");
        sleep();
        this.sendMessage();
        Log.d("THREAD DEBUG", "Thread has stopped!");

    }

    private void sendMessage() {
        Intent intent = new Intent();
        System.out.println("ajunge in sendMsg");
        intent.setAction("actiuneaSalbatica");
        intent.putExtra(Constants.BROADCAST_RECEIVER_EXTRA,
                new Date(System.currentTimeMillis()) + " " + first + " abcdef");
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
