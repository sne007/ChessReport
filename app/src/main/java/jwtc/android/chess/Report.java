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
    private BarChart barChart;
    private SeekBar seekBarX;
    private TextView tvX;

    private List<BarEntry> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_report);

        setTitle("Your Report");

        pieChart = findViewById(R.id.pieChart);
        pieChart.setBackgroundColor(Color.WHITE);

        moveOffScreen();

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

        setData(4, 100);

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


/*
        data = FileUtils.loadBarEntriesFromAssets(getAssets(), "othersine.txt");

        tvX = findViewById(R.id.tvValueCount);

        seekBarX = findViewById(R.id.seekbarValues);

        barChart = findViewById(R.id.barChart);

        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);

        barChart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        barChart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        barChart.setPinchZoom(false);

        // draw shadows for each bar that show the maximum value
        // chart.setDrawBarShadow(true);

        // chart.setDrawXLabels(false);

        barChart.setDrawGridBackground(false);
        // chart.setDrawYLabels(false);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setEnabled(false);

        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setTypeface(tfLight);
        leftAxis.setLabelCount(6, false);
        leftAxis.setAxisMinimum(-2.5f);
        leftAxis.setAxisMaximum(2.5f);
        leftAxis.setGranularityEnabled(true);
        leftAxis.setGranularity(0.1f);

        YAxis rightAxis = barChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setTypeface(tfLight);
        rightAxis.setLabelCount(6, false);
        rightAxis.setAxisMinimum(-2.5f);
        rightAxis.setAxisMaximum(2.5f);
        rightAxis.setGranularity(0.1f);

        seekBarX.setOnSeekBarChangeListener(this);
        seekBarX.setProgress(150); // set data

        Legend legendBar = barChart.getLegend();
        legendBar.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legendBar.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legendBar.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legendBar.setDrawInside(false);
        legendBar.setForm(Legend.LegendForm.SQUARE);
        legendBar.setFormSize(9f);
        legendBar.setTextSize(11f);
        legendBar.setXEntrySpace(4f);

        barChart.animateXY(1500, 1500);
*/
    }

    private void setData(int count, float range) {

        ArrayList<PieEntry> values = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            values.add(new PieEntry((float) ((Math.random() * range) + range / 5), parties[i % parties.length]));
        }

        PieDataSet dataSet = new PieDataSet(values, "Election Results");
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
    }
    private void setData(int count) {

        ArrayList<BarEntry> entries = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            entries.add(data.get(i));
        }

        BarDataSet set;

        if (barChart.getData() != null &&
                barChart.getData().getDataSetCount() > 0) {
            set = (BarDataSet) barChart.getData().getDataSetByIndex(0);
            set.setValues(entries);
            barChart.getData().notifyDataChanged();
            barChart.notifyDataSetChanged();
        } else {
            set = new BarDataSet(entries, "Sinus Function");
            set.setColor(Color.rgb(240, 120, 124));
        }

        BarData data = new BarData(set);
        data.setValueTextSize(10f);
        data.setValueTypeface(tfLight);
        data.setDrawValues(false);
        data.setBarWidth(0.8f);

        barChart.setData(data);
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

        RelativeLayout.LayoutParams rlParams =
                (RelativeLayout.LayoutParams) pieChart.getLayoutParams();
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