package com.example.sayefreyadh.austhub.ui.results;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.sayefreyadh.austhub.R;
import com.example.sayefreyadh.austhub.user.UserData;

/**
 * Created by SayefReyadh on 2/3/2017.
 */
public class CarryResultFragment extends Fragment {
    public CarryResultFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.carry_result_layout, container , false);
        String url = UserData.getCarryResultBaseUrl(getContext());
        WebView webView = (WebView) view.findViewById(R.id.carry_result_webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
        return view;
    }
}