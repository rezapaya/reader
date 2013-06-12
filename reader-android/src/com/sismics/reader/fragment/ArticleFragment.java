package com.sismics.reader.fragment;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.sismics.reader.R;

/**
 * Fragment displaying an article.
 * 
 * @author bgamard
 */
public class ArticleFragment extends Fragment {
    /**
     * Create a new instance of ArticleFragment.
     */
    public static ArticleFragment newInstance(JSONObject json) {
        ArticleFragment f = new ArticleFragment();

        // Supply argument
        Bundle args = new Bundle();
        args.putString("json", json.toString());
        f.setArguments(args);

        return f;
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.article_fragment, container, false);
        WebView webView = (WebView) view.findViewById(R.id.articleWebView);
        
        Bundle args = getArguments();
        if (args != null) {
            String jsonStr = args.getString("json");
            if (jsonStr != null) {
                try {
                    JSONObject json = new JSONObject(jsonStr);
                    webView.loadData(json.optString("description"), "text/html; charset=UTF-8", null);
                } catch (JSONException e) {
                    Log.e("ArticleFragment", "Unable to parse JSON", e);
                }
            }
        }
        return view;
    }

    public void onPageSelected() {
        View view = getView();
        if (view != null) {
            WebView webView = (WebView) view.findViewById(R.id.articleWebView);
            webView.scrollTo(0, 0);
        }
    }
}
