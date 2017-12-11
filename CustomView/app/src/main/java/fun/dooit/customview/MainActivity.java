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

       /* ImageButton imgBtn=new ImageButton(this);
        ConstraintLayout.LayoutParams params=new ConstraintLayout.LayoutParams(panelRoot.getLayoutParams());
        params.width= 800;
        params.height=400;


        TypedValue tv = new TypedValue();
        this.getTheme().resolveAttribute(android.R.attr.selectableItemBackground, tv, true);

        imgBtn.setImageResource(R.drawable.ic_g_translate_black_36dp);
        imgBtn.setBackgroundResource(tv.resourceId);
        imgBtn.setScaleType(ImageView.ScaleType.FIT_CENTER);

        imgBtn.setLayoutParams(params);
        panelRoot.addView(imgBtn);*/

        XToolbar xToolbar = findViewById(R.id.xtoolbar);
//        xToolbar.buildButton(R.drawable.ic_message_black_36dp);
//        xToolbar.buildButton(R.drawable.ic_alarm_black_36dp);
//        xToolbar.buildButton(R.drawable.ic_description_black_36dp);
//        xToolbar.buildButton(R.drawable.ic_g_translate_black_36dp);
       /* xToolbar.setOnClickListener(new XToolbar.OnToolbarClickListener() {
            @Override
            public void onMainClick(View view) {
                Log.d(TAG, "onMainClick() called with: view = [" + view + "]");
            }

            @Override
            public void onExtraClick(View view) {
                Log.d(TAG, "onExtraClick() called with: view = [" + view + "]");
            }



            @Override
            public void onBtn1Click(View view) {
                Log.d(TAG, "onBtn1Click() called with: view = [" + view + "]");
            }

            @Override
            public void onBtn2Click(View view) {
                Log.d(TAG, "onBtn2Click() called with: view = [" + view + "]");
            }

            @Override
            public void onBtn3Click(View view) {
                Log.d(TAG, "onBtn3Click() called with: view = [" + view + "]");
            }
        });*/

        //addView 方法1
//        layoutInflater.inflate(R.layout.button_submit, panelRoot, true);

        //addView 方法2
//        View btnSubmit = layoutInflater.inflate(R.layout.button_submit, panelRoot,false);
//        panelRoot.addView(btnSubmit);


//        View btnSubmit = layoutInflater.inflate(R.layout.button_submit, null);
//        panelRoot.addView(btnSubmit);
    }
}
