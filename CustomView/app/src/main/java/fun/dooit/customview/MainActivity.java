package fun.dooit.customview;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        ConstraintLayout panelRoot = findViewById(R.id.panelRoot);


        final XToolbar xToolbar = findViewById(R.id.xtoolbar);
        xToolbar.setOnClickListener(new XToolbar.OnToolbarClickListener() {
            @Override
            public void onMainClick(View view) {
                Log.d(TAG, "onMainClick() called with: view = [" + view + "]");
            }

            @Override
            public void onExtraClick(View view) {
                Log.d(TAG, "onExtraClick() called with: view = [" + view + "]");
                xToolbar.setRightPanelVisible(true);
                xToolbar.setExtraButtonVisible(false);
            }


            @Override
            public void onBtn1Click(View view) {
                Log.d(TAG, "onBtn1Click() called with: view = [" + view + "]");
                xToolbar.setRightPanelVisible(false);
                xToolbar.setExtraButtonVisible(true);
            }

            @Override
            public void onBtn2Click(View view) {
                Log.d(TAG, "onBtn2Click() called with: view = [" + view + "]");
            }

            @Override
            public void onBtn3Click(View view) {
                Log.d(TAG, "onBtn3Click() called with: view = [" + view + "]");
            }
        });

        //addView 方法1
//        layoutInflater.inflate(R.layout.button_submit, panelRoot, true);

        //addView 方法2
//        View btnSubmit = layoutInflater.inflate(R.layout.button_submit, panelRoot,false);
//        panelRoot.addView(btnSubmit);


//        View btnSubmit = layoutInflater.inflate(R.layout.button_submit, null);
//        panelRoot.addView(btnSubmit);
    }
}
