package dhiraj.notificationdemoappl;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class NotifyActivity extends AppCompatActivity {

    Button buttonshow,buttoncancle,buttonalert;
    int notificationId=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);
        buttonshow=findViewById(R.id.buttonshownotif);
        buttoncancle=findViewById(R.id.buttoncanclenotif);
        buttonalert=findViewById(R.id.buttonalert);

        buttonshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNotification();
            }
        });

        buttoncancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notificationCancle();
            }
        });
        buttonalert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog();
            }
        });
    }
    private  void  showNotification(){
        int requestCode=111;
        Random random=new Random();
        notificationId=requestCode=random.nextInt();

        NotificationCompat.Builder notifiactionBuilder=new NotificationCompat.Builder(NotifyActivity.this);
        notifiactionBuilder.setContentTitle("Notification demo");
        notifiactionBuilder.setContentText("Hiii DMC 2017");
        notifiactionBuilder.setAutoCancel(true);
        notifiactionBuilder.setSmallIcon(R.mipmap.ic_launcher_round);

        Intent newIntent=new Intent(NotifyActivity.this,AlertActivity.class);
        newIntent.putExtra("id",notificationId);
        PendingIntent pendingIntent=PendingIntent.getActivity(NotifyActivity.this,requestCode,newIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        notifiactionBuilder.setContentIntent(pendingIntent);
        Notification notification=notifiactionBuilder.build();

        NotificationManager notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId, notification);
        notificationId++;


    }
    private void notificationCancle(){
        int notitificatioId=1;
        NotificationManager notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancel(notitificatioId);
    }
    private  void showAlertDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(NotifyActivity.this);
        builder.setTitle("Exit");
        builder.setMessage("Do you Wnats to Exit ?");

        final EditText editText=new EditText(NotifyActivity.this);
        builder.setView(editText);
        
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                String value =editText.getText().toString();
                Toast.makeText(NotifyActivity.this, "Exit "+value, Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(NotifyActivity.this, "Don't Exit", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNeutralButton("Neutral", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(NotifyActivity.this, "Do Not anythig", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }
}
