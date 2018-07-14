package com.star.demo.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.star.demo.fragment.InteractionListener;

public class BaseFragment extends Fragment {
    private final String TAG = this.getClass().getSimpleName();
    protected InteractionListener interactionListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        assignListener(context);
    }

    //SDK API<23时，onAttach(context)不执行，需要使用onAttach(Activity)
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            assignListener(activity);
        }
    }

    private void assignListener(Context context) {
        if (context instanceof InteractionListener) {
            interactionListener = (InteractionListener) context;
        } else {
            Log.w(TAG, context.toString() + " must implement InteractionListener");
        }
    }
}
