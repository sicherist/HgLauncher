package mono.hg.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import mono.hg.LauncherActivity;

public class PackageChangesReceiver extends BroadcastReceiver {

    @Override public void onReceive(Context context, Intent intent) {
        if (intent.getAction() != null && intent.getData() != null) {
            String packageName = intent.getData().getEncodedSchemeSpecificPart();

            // Receive intent from broadcast and simply let the launcher know it needs a refresh.
            if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")
                    || intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")
                    || intent.getAction().equals("android.intent.action.PACKAGE_CHANGED")
                    && !packageName.contains(context.getPackageName())) {
                Intent mainIntent = new Intent(context, LauncherActivity.class);
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(mainIntent);
            }
        }
    }
}
