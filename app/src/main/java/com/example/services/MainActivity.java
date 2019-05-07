package com.example.services;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.constraintlayout.widget.ConstraintLayout;

        import android.app.Service;
        import android.content.ComponentName;
        import android.content.Intent;
        import android.content.ServiceConnection;
        import android.os.Bundle;
        import android.os.IBinder;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MyBoundService myBoundService;
    private boolean isBound = false;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder srvInfo) {
            myBoundService = new MyBoundService(srvInfo);
            isBound = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnStartedService = findViewById(R.id.activity_main__btn__started_service);
        btnStartedService.setOnClickListener(this);

        final Button btnBoundService = findViewById(R.id.activity_main__btn__started__bound__service);
        btnBoundService.setOnClickListener(this);

        final ConstraintLayout root = findViewById(R.id.activity_constrain_main_root);
        root.setOnClickListener(this);

        final Button btnIntentService = findViewById(R.id.activity_main__btn__intent_service);
        btnIntentService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_main__btn__started_service:
            final Intent serviceIntent = new Intent(this, MyStartedService.class);
            serviceIntent.putExtra("dummyKey",23);
            Thread workerThread = new Thread() {
                @Override
                public void run() {
                    startService(serviceIntent);
                }
            };
            workerThread.start();

                break;
            case R.id.activity_main__btn__started__bound__service:
                Intent boundIntent = new Intent(this, MyBoundService.class);
                bindService(boundIntent, serviceConnection, Service.BIND_AUTO_CREATE);
                break;

            case R.id.activity_constrain_main_root:
                if (isBound) {
                    String info =  String.valueOf(myBoundService.getInfoFromService());
                    Toast.makeText(this, info, Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.activity_main__btn__intent_service:
                Intent intentService = new Intent(this, MyIntentService.class);
                startService(intentService);
                break;

            default:
                Log.w("MainActivity", "View unknown");
        }

    }
}
