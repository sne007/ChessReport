package jwtc.android.chess;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class Report extends DemoBase implements SeekBar.OnSeekBarChangeListener {

    private PieChart pieChart;
    private String result = " ";
    private boolean isNull = false;
    private List<BarEntry> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_report);

        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            result = extras.getString("result");
        }



        setTitle("Your Report");


        System.out.println(" wow this is so easy ");
        pieChart = findViewById(R.id.pieChart);
        pieChart.setBackgroundColor(Color.WHITE);

//        moveOffScreen();

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);

        pieChart.setCenterTextTypeface(tfLight);
        pieChart.setCenterText(generateCenterSpannableText());

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);

        pieChart.setTransparentCircleColor(Color.WHITE);
        pieChart.setTransparentCircleAlpha(110);

        pieChart.setHoleRadius(58f);
        pieChart.setTransparentCircleRadius(61f);

        pieChart.setDrawCenterText(true);

        pieChart.setRotationEnabled(false);
        pieChart.setHighlightPerTapEnabled(true);

        pieChart.setMaxAngle(180f); // HALF CHART
        pieChart.setRotationAngle(180f);
        pieChart.setCenterTextOffset(0, -20);

        setData(3, 100);

        pieChart.animateY(1400, Easing.EaseInOutQuad);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // entry label styling
        pieChart.setEntryLabelColor(Color.WHITE);
        pieChart.setEntryLabelTypeface(tfRegular);
        pieChart.setEntryLabelTextSize(12f);


        System.out.println(result + " impres ");
        System.out.println( " helloo ");

    }

    private void setData(int count, float range) {

        ArrayList<PieEntry> values = new ArrayList<>();


        String[] split = result.split("\\s+");
        float accuracy = 0,blunder=0,mistakes=0,goodMoves=0;

        boolean isBlack = false;
        if(split[0] == "b")
            isBlack = true;

        for(int i=1; i < split.length; i++){
            try {
                float f = Float.parseFloat(split[i]) - Float.parseFloat(split[i-1]);
                if(f > 2.0f){
                    blunder++;
                    System.out.println(" tis a blunder " + split[i]);
                }
                else if(f > 1.0f && f <= 2.0){
                    mistakes++;
                    System.out.println(" tis a mistake " + split[i]);
                }
                else{
                    goodMoves++;
                    System.out.println(" tis a goodMove " + split[i]);

                }
//                System.out.println(f + " debugsss1 ");
            }
            catch (Exception e){

            }
        }
        values.add(new PieEntry((float) (((goodMoves/(float)split.length) * range)) , parties[0]));
        values.add(new PieEntry((float) (((mistakes/(float)split.length) * range)), parties[1]));
        values.add(new PieEntry((float) ((((blunder)/(float)split.length) * range)), parties[2]));

        TextView title = findViewById(R.id.pieTitle);
        float _pGoodMove = ((((goodMoves + mistakes)/(float)(split.length - 2)) * range));
        float _pMistakeMove = ((((goodMoves + mistakes)/(float)(split.length - 2)) * range));
        float _pBlunderMove = ((((goodMoves + mistakes)/(float)(split.length - 2)) * range));


        if(_pGoodMove >= 90.0f)
            title.setText("Chess Expert");
        else if(((((goodMoves + mistakes)/(float)(split.length - 2)) * range)) >= 70.0f){
            title.setText("Tournament Player");
        }
        else if(((((goodMoves + mistakes)/(float)(split.length - 2)) * range)) >= 60.0f){
            title.setText("Advanced Beginner");
        }
        else if(((((goodMoves + mistakes)/(float)(split.length - 2)) * range)) >= 50.0f){
            title.setText("Beginner");
        }
        else {
            title.setText("Newbiee");
        }


        PieDataSet dataSet = new PieDataSet(values, "Game Analysis");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        data.setValueTypeface(tfLight);
        pieChart.setData(data);

        pieChart.invalidate();

        if(split.length == 2)
            isNull = true;
    }

    private SpannableString generateCenterSpannableText() {

        SpannableString s = new SpannableString("Game Analysis\n");
        s.setSpan(new RelativeSizeSpan(1.7f), 0, s.length(), 0);

/*
        s.setSpan(new StyleSpan(Typeface.NORMAL), 14, s.length() - 15, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 14, s.length() - 15, 0);
        s.setSpan(new RelativeSizeSpan(.8f), 14, s.length() - 15, 0);
        s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 14, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 14, s.length(), 0);
*/
        return s;
    }

    private void moveOffScreen() {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int height = displayMetrics.heightPixels;

        int offset = (int)(height * 0.65); /* percent to move */

        ScrollView.LayoutParams rlParams =
                (ScrollView.LayoutParams) pieChart.getLayoutParams();
        rlParams.setMargins(0, 0, 0, -offset);
        pieChart.setLayoutParams(rlParams);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }

    @Override
    public void saveToGallery() { /* Intentionally left empty */ }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}