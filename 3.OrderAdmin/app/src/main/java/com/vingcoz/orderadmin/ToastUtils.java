package com.vingcoz.orderadmin;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {

    public static void ShowToast(Context ctc, String strMessage){
        Toast.makeText(ctc, strMessage, Toast.LENGTH_SHORT).show();
    }

}
