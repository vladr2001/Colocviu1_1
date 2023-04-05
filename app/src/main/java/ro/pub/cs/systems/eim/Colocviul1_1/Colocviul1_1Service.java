package ro.pub.cs.systems.eim.Colocviul1_1;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class Colocviul1_1Service extends Service {

    public ProcessingThread processingThread = null;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String a = intent.getStringExtra("first");
        System.out.println("incepe serviciul");
        processingThread = new ProcessingThread(this, a);
        processingThread.start();
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw null;
    }
}