package br.com.mnidersoft.viajabessa.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import br.com.mnidersoft.viajabessa.R;

/**
 * Created by institutoeducar on 26/07/15.
 */
public class GenericUtil {

    public static boolean isOnline(Context context) throws Exception {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
    }

    public static void loadImageByUrl(Context context, String url, ImageView imageView) {
        Picasso.with(context).load(url)
                .fit().centerCrop()
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(imageView);
    }
}
