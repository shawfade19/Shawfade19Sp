package shawfadetech.weston.shawfade19.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

import shawfadetech.weston.shawfade19.R;

/**
 * Created by WestonKimaru on 3/5/2017.
 */
public class SettingsHomeClass extends AppCompactPreference {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getBoolean(PreferenceManager.KEY_HAS_SET_DEFAULT_VALUES, false)) {
            PreferenceManager.setDefaultValues(getApplicationContext(), R.xml.basic_settings, true);
            PreferenceManager.setDefaultValues(getApplicationContext(), R.xml.advanced_settings, true);
        }
        prepareLayout();
    }
    @Override
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.peference_headers, target);
    }

    private void prepareLayout(){
        ViewGroup root = (ViewGroup) findViewById(android.R.id.content);
        View content = root.getChildAt(0);
        LinearLayout toolbarContainer = (LinearLayout) View.inflate(this, R.layout.tool_bar, null);

        root.removeAllViews();
        toolbarContainer.addView(content);
        root.addView(toolbarContainer);

        Toolbar mToolBar = (Toolbar) toolbarContainer.findViewById(R.id.toolbar);
        mToolBar.setTitle(getTitle());
        //mToolBar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backIntent = new Intent(getBaseContext(), SettingsHomeClass.class);
                //Intent intent= new Intent(getBaseContext(),SupervisorLogin.class);
                startActivity(backIntent);
            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}
