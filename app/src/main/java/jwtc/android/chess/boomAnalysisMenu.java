package jwtc.android.chess;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.Toast;

import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;

public class boomAnalysisMenu extends AppCompatActivity {

    private BoomMenuButton bmb;
    private String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boom_analysis_menu);

        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            result = extras.getString("result");

        }

        setBoomBuilders();
    }

    public void setBoomBuilders(){

        bmb = findViewById(R.id.bmb);
        bmb.setDraggable(true);
        bmb.setShadowEffect(true);

        if(!bmb.isAutoBoom())
            bmb.setAutoBoom(true);
        if(!bmb.isBoomed())
            bmb.setAutoBoom(true);
//        bmb.setUse3DTransformAnimation(true);

        bmb.clearBuilders();

        bmb.addBuilder(new HamButton.Builder()
                .normalImageRes(R.drawable.radarcharticon)
                .normalTextRes(R.string.ham1)
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index){
                        // When the boom-button corresponding this builder is clicked.

                        Intent intent = new Intent();
                        intent.setClass(boomAnalysisMenu.this, RadarChartActivity.class);
                        intent.putExtra("result",result);
                        startActivity(intent);
                    }
                }));

        bmb.addBuilder(new HamButton.Builder()
                .normalImageRes(R.drawable.piecharticon)
                .normalTextRes(R.string.ham2)
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index){
                        // When the boom-button corresponding this builder is clicked.
                        Intent intent = new Intent();
                        intent.setClass(boomAnalysisMenu.this, Report.class);
                        intent.putExtra("result",result);
                        startActivity(intent);
                    }
                }));

        bmb.addBuilder(new HamButton.Builder()
                .normalImageRes(R.drawable.barcharticon)
                .normalTextRes(R.string.ham3)
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index){
                        // When the boom-button corresponding this builder is clicked.
                        Intent intent = new Intent();
                        intent.setClass(boomAnalysisMenu.this, ScrollViewActivity.class);
                        intent.putExtra("result",result);
                        startActivity(intent);
                    }
                }));

        bmb.addBuilder(new HamButton.Builder()
                .normalImageRes(R.drawable.contributeicon)
                .normalTextRes(R.string.ham4)
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index){
                        // When the boom-button corresponding this builder is clicked.
                        Intent intent = new Intent();
                        intent.setClass(boomAnalysisMenu.this, RadarChartActivity.class);
                        intent.putExtra("result",result);
                        startActivity(intent);
                    }
                }));

    }

    @Override
    public void onPause(){
        super.onPause();
        // put your code here...
//        bmb.setAutoBoomImmediately(true);
        setBoomBuilders();
    }

    @Override
    public void onResume(){
        super.onResume();
        // put your code here...
        setBoomBuilders();
    }
}