package qc.com.framedemo.view.activity;

import android.os.Bundle;
import android.widget.Button;

import butterknife.BindView;
import qc.com.framedemo.R;
import qc.com.framedemo.view.BaseActivity;

public class GuideActivity extends BaseActivity {

    @BindView(R.id.to_mainactivity_id)
    public Button toMainButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
    }

    @Override
    protected void initCustomView() {
//        toMainButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(GuideActivity.this, MainActivity.class));
//            }
//        });
    }

}
