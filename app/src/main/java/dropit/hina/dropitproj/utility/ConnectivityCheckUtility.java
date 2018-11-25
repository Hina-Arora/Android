package dropit.hina.dropitproj.utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Log;

public class ConnectivityCheckUtility {

    private Context context;
    private ConnectivityManager connectivityManager;
    private TelephonyManager telephonyManager;

    public ConnectivityCheckUtility(Context context) {
        this.context = context;
        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
    }

    public boolean isConnected() {

        if (connectivityManager != null) {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {

                Network[] networks = connectivityManager.getAllNetworks();
                NetworkInfo networkInfo;
                for (Network mNetwork : networks) {
                    networkInfo = connectivityManager.getNetworkInfo(mNetwork);
                    if (networkInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
                        return true;
                    }
                }

            } else {
                if (connectivityManager != null) {

                    NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
                    if (info != null) {
                        for (NetworkInfo anInfo : info) {
                            if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
                                Log.d("Network",
                                        "NETWORKNAME: " + anInfo.getTypeName());
                                return true;
                            }
                        }
                    }
                }
            }

        }
        return false;
    }

    public boolean checkDataOn() {

        NetworkInfo network = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (network != null) {
            if (network.isConnectedOrConnecting()) {
                return true;
            }
        }
        return false;
    }

    public boolean checkWifiOn() {
        NetworkInfo network = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (network != null) {
            if (network.isConnectedOrConnecting()) {
                return true;
            }
        }
        return false;
    }

    public String getServiceProvider() {

        if (telephonyManager != null) {
            return telephonyManager.getNetworkOperatorName();
        }
        return null;
    }

    public String getServiceType() {

        int type = -1;
        String result = "unknown";
        if (telephonyManager != null) {
            type = telephonyManager.getNetworkType();
        }

        switch (type) {
            case 13:
                result = "LTE";
                break;
            case 1:
                result = "GPRS";
                break;

            case 2:
                result = "EDGE";
                break;

            case 10:
                result = "HSPA";
                break;
            case -1:
                break;
        }
        return result;
    }

    public String getDataSignalStrength() {

        if (telephonyManager != null) {
            // telephonyManager.lis
        }
        return null;
    }

    public String getWifiSignalStrength() {

        if (connectivityManager != null) {
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            if (info == null || !info.isConnectedOrConnecting()) {
                return "No Signal";
            } else {
                if (info.getType() == ConnectivityManager.TYPE_WIFI) {
                    WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

                    if (wifiManager != null) {
                        int linkSpeed = wifiManager.getConnectionInfo().getLinkSpeed();
                        int rssi = wifiManager.getConnectionInfo().getRssi();

                        int level = WifiManager.calculateSignalLevel(rssi, 5);
                        if (level > 3) {
                            return "Strong";
                        }
                        if (level > 2) {
                            return "Medium";
                        } else {
                            return "Weak";
                        }

                    } else {
                        return "No Signal";
                    }
                }
            }
        }
        return null;

    }
}