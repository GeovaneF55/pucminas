package com.example.geova.grafico;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createGraph(1,1,0,5);
    }

    protected void createGraph(int a, int b, int c, int n){
        GraphView graph = (GraphView) findViewById(R.id.graph);
        // first series is a line
        DataPoint[] points = new DataPoint[2*n];
        for (int i = -n, x=0; i < n; i++, x++) {
            points[x] = new DataPoint(i, (a*Math.pow(i, 2) + b*i + c));
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(points);

        // set manual X bounds
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(-n);
        graph.getViewport().setMaxY(n);

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(-n);
        graph.getViewport().setMaxX(n);

        // enable scaling and scrolling
        graph.getViewport().setScalable(true);
        graph.getViewport().setScalableY(true);

        graph.addSeries(series);
    }
}
