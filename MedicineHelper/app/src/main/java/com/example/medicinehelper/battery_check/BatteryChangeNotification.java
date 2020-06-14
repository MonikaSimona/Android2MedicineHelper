package com.example.medicinehelper.battery_check;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class BatteryChangeNotification {



    public Notification setNotification(Context context , String Channel_id, int Notification_id, String title, String text, int icon){
        Notification notification = null;
        createNotificationChannel(context,Channel_id);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,Channel_id);
        notification = builder.setSmallIcon(icon)
                                .setContentTitle(title)
                                .setContentText(text)
                                .setPriority(NotificationCompat.PRIORITY_HIGH)
                                .setAutoCancel(true)
                                .setDefaults(NotificationCompat.DEFAULT_ALL)
                                .build();
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(Notification_id,notification);



        return notification;
    }

    private void createNotificationChannel(Context context,String Channel_id) {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "Personal Notification";
            String description = "Include all personal notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel notificationChannel = new NotificationChannel(Channel_id,name,importance);
            notificationChannel.setDescription(description);
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            notificationManager.createNotificationChannel(notificationChannel);

        }

    }


}
