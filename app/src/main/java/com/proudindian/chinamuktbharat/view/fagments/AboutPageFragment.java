package com.proudindian.chinamuktbharat.view.fagments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.proudindian.chinamuktbharat.R;
import com.proudindian.chinamuktbharat.view.MainActivity;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class AboutPageFragment extends Fragment {


    public static AboutPageFragment NewInstance() {
        return new AboutPageFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Element adsElement = new Element();
        adsElement.setTitle("Android Developer &  Enthusiast");
        View aboutPage = new AboutPage(getActivity())
                .isRTL(false)
                .setImage(R.drawable.ic_ban_china)
                .addItem(new Element().setTitle("Sankalp Pandya"))
                .addItem(adsElement)
                .addGroup("Reach out to me here!")
                .addWebsite("https://www.linkedin.com/in/sankalp-pandya-3a470159/")
                .addEmail("sankalpPandyaAndr0ID@gmail.com")
                .create();

        aboutPage.setBackgroundColor(getResources().getColor(R.color.white));
        aboutPage.setClickable(true);
        return aboutPage;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(false);
        TextView textView = view.findViewById(R.id.description);
        textView.setText("This app is created ONLY for educational purpose , it does not promote or monetize other apps by any " +
                "means when it recommends alternatives to china apps nor it tries to defame or degrade china apps. " +
                "This app is just a tool for whom are curious about finding details about apps they haven been using and looking for good alternatives. " +
                "If you have downloaded this app mistakenly or you do no wish to uninstall any or list of china apps installed in your device , UNINSTALL it NOW!");
    }

    @Override
    public void onStart() {
        super.onStart();
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
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
