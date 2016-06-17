package fc.fcstudio;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;

import org.apache.cordova.PluginResult;

import java.util.ArrayList;
import java.util.List;

public class packagemanager extends CordovaPlugin {

    public Context context = null;
    private static final boolean IS_AT_LEAST_LOLLIPOP = Build.VERSION.SDK_INT >= 21;
    public boolean instApp = false;

    @Override
    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {

        context = IS_AT_LEAST_LOLLIPOP ? cordova.getActivity().getWindow().getContext() : cordova.getActivity().getApplicationContext();

        if (action.equals("start")) {

            ArrayList<String> list = new ArrayList<String>();

            for (int i = 0; i < args.length(); i++) {
                JSONObject jsonobject = args.getJSONObject(i);
                instApp = jsonobject.getBoolean("installedApps");
            }

            if (instApp) {
                final PackageManager pm = cordova.getActivity().getPackageManager();
                Intent intent = new Intent(Intent.ACTION_MAIN, null);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                List<ResolveInfo> apps = pm.queryIntentActivities(intent, PackageManager.GET_META_DATA);
                for (ResolveInfo packageInfo : apps) {
                    list.add(packageInfo.activityInfo.applicationInfo.uid + ";" + packageInfo.activityInfo.applicationInfo.dataDir + ";" + packageInfo.activityInfo.applicationInfo.packageName);
                }
            } else {
                List<ApplicationInfo> listInstalledApps = getInstalledApps(context);
                for (ApplicationInfo packageInfo : listInstalledApps) {
                    list.add(packageInfo.uid + ";" + packageInfo.dataDir + ";" + packageInfo.packageName);
                }
            }

            JSONArray jResult = new JSONArray(list);
            PluginResult pr = new PluginResult(PluginResult.Status.OK, jResult);
            callbackContext.sendPluginResult(pr);
            return true;
        }

        callbackContext.error("PackageManager " + action + " is not a supported function.");
        return false;
    }

    public static List<ApplicationInfo> getInstalledApps(Context ctx) {
        final PackageManager pm = ctx.getPackageManager();
        //get a list of installed apps.
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
        return packages;
    }
}