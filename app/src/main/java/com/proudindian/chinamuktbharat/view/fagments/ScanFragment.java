package com.proudindian.chinamuktbharat.view.fagments;

import android.animation.Animator;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.proudindian.chinamuktbharat.R;
import com.proudindian.chinamuktbharat.util.FilterChinaAppsHelper;
import com.proudindian.chinamuktbharat.util.Utils;
import com.proudindian.chinamuktbharat.view.MainActivity;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;

import java.util.ArrayList;

public class ScanFragment extends Fragment {

    ConstraintLayout constraintLayout;
    private ImageView scanButton;
    ProgressBar progressBar;
    ArrayList<FilterChinaAppsHelper.ApplicationInfo> chinaApps;
    private boolean isScanInProgress;
    HandlerThread handlerThread;
    Handler handler;
    public boolean isDistroyed;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.scan_layout, container, false);
        return view;
    }

    public static ScanFragment NewInstance() {
        return new ScanFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        constraintLayout = view.findViewById(R.id.root);
        scanButton = view.findViewById(R.id.scan);
        progressBar = view.findViewById(R.id.spin_kit);
        progressBar.setVisibility(View.INVISIBLE);
        setHasOptionsMenu(false);

        setUpHandlerThread();

        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isScanInProgress) return;
                isScanInProgress = true;
                Sprite doubleBounce = new DoubleBounce();
                progressBar.setIndeterminateDrawable(doubleBounce);
                progressBar.animate();
                progressBar.setVisibility(View.VISIBLE);
                RotateAnimation rotate = new RotateAnimation(0, 720,
                        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                        0.5f);
                rotate.setDuration(4500);
                rotate.setRepeatCount(Animation.INFINITE);
                scanButton.setAnimation(rotate);
                fireHandlerThread();
            }
        });
    }

    private void setUpHandlerThread() {
        handlerThread = new HandlerThread("MyHandlerThread");
        handlerThread.start();
        Looper looper = handlerThread.getLooper();
        handler = new Handler(looper);
    }

    private void fireHandlerThread() {
        handler.postAtFrontOfQueue(new Runnable() {
            @Override
            public void run() {
                chinaApps = ScanDeviceForChinaApps();
                navigateToNextScreen();
            }
        });
    }

    private void navigateToNextScreen() {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isDistroyed) return;

                progressBar.setVisibility(View.INVISIBLE);
                if (chinaApps == null) {
                    Log.v("MainActivity", "ScanDeviceForChinaApps apps called...retry ");
                    chinaApps = ScanDeviceForChinaApps();
                }
                if (chinaApps.size() == 0) {
                    RunRevealAnimation(R.color.green);
                    ((AppCompatActivity) getActivity()).getSupportActionBar().setBackgroundDrawable
                            (new ColorDrawable(Color.parseColor("#00e676")));
                } else {
                    RunRevealAnimation(R.color.red);
                    ((AppCompatActivity) getActivity()).getSupportActionBar().setBackgroundDrawable
                            (new ColorDrawable(Color.parseColor("#ff193a")));
                }
                if (getActivity() != null) {
                    ((MainActivity) getActivity()).iFragmentTransactionListner.displayScanResult(chinaApps,
                            FilterChinaAppsHelper.getInstalledChinaSystemApps()
                            , FilterChinaAppsHelper.CheckDeviceMenuFacturer(),
                            FilterChinaAppsHelper.getDeviceMenufacturerName());
                }
            }
        }, 500);
    }

    private void RunRevealAnimation(final int color) {
        constraintLayout.post(new Runnable() {
            @Override
            public void run() {
                animateRevealColor(constraintLayout, color);
            }
        });
    }

    private void animateRevealColor(ConstraintLayout viewRoot, @ColorRes int color) {
        int cx = (viewRoot.getLeft() + viewRoot.getRight()) / 2;
        int cy = (viewRoot.getTop() + viewRoot.getBottom()) / 2;
        animateRevealColorFromCoordinates(viewRoot, color, cx, cy);
    }

    private ArrayList<FilterChinaAppsHelper.ApplicationInfo> ScanDeviceForChinaApps() {
        ArrayList<FilterChinaAppsHelper.ApplicationInfo> apps = FilterChinaAppsHelper.getInstalledChinaApps
                (getActivity(), Utils.getRestrictedAppsList());
        Log.v("MainActivity", "ScanDeviceForChinaApps apps called..." + apps.size());
        return apps;
    }


    private Animator animateRevealColorFromCoordinates(final ConstraintLayout viewRoot, @ColorRes final int color, int x, int y) {
        float finalRadius = (float) Math.hypot(viewRoot.getWidth(), viewRoot.getHeight());

        Animator anim = ViewAnimationUtils.createCircularReveal(viewRoot, x, y, 0, finalRadius);
        viewRoot.setBackgroundColor(ContextCompat.getColor(getActivity(), color));
        anim.setDuration(getResources().getInteger(R.integer.anim_duration_long));
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.start();
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        return anim;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isDistroyed = true;
        if (handlerThread != null) {
            handlerThread.quitSafely();
        }
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            handler = null;
        }
    }
}
