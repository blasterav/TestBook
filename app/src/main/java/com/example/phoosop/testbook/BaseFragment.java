package com.example.phoosop.testbook;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.view.Window;

/**
 * Created by Phoosop on 10.02.2016.
 */
public class BaseFragment extends Fragment {
    private ProgressDialog dialog;

    public void showProgress(){
        dialog = new ProgressDialog(getActivity());
        dialog.setCancelable(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        dialog.setContentView(R.layout.dialog_loading);
    }

    public void dismissProgress(){
        if (dialog!=null)
            dialog.dismiss();
    }
}
