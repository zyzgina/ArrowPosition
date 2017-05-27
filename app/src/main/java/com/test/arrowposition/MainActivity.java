package com.test.arrowposition;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.test.arrowposition.view.PricesAssessment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PricesAssessment carDetailsPricesAssessmentProgress = (PricesAssessment) findViewById(R.id.carDetailsPricesAssessmentProgress);
        carDetailsPricesAssessmentProgress.setProgress(0.3f, 0.4f, 0.3f,0.5f,"1.65万","当前报价2.0万","2.56万");
    }
}
