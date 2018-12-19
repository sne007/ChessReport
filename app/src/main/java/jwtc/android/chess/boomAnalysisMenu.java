package jwtc.android.chess;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;

public class boomAnalysisMenu extends AppCompatActivity {

    private BoomMenuButton bmb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boom_analysis_menu);

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
        bmb.setUse3DTransformAnimation(true);
        bmb.setDelay(100);

        bmb.clearBuilders();

        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            HamButton.Builder builder = new HamButton.Builder()
                    .normalImageRes(R.drawable.butterfly)
                    .normalTextRes(R.string.text_ham_button_text_normal)
                    .subNormalTextRes(R.string.text_ham_button_sub_text_highlighted)
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index){
                            // When the boom-button corresponding this builder is clicked.
                            Intent intent = new Intent();
                            intent.setClass(boomAnalysisMenu.this, Report.class);
                            startActivity(intent);
                        }
                    });
            bmb.addBuilder(builder);
            bmb.setBuilder(i,builder);
        }

        System.out.println(" builder at 0 " + bmb.getBuilder(0));
        System.out.println(" builder at 1 " + bmb.getBuilder(1));
        System.out.println(" builder at 2 " + bmb.getBuilder(2));
        System.out.println(" builder at 3 " + bmb.getBuilder(3));
    }

    @Override
    public void onPause(){
        super.onPause();
        // put your code here...
//        bmb.setAutoBoomImmediately(true);
    }

    @Override
    public void onResume(){
        super.onResume();
        // put your code here...
        setBoomBuilders();
    }
}
