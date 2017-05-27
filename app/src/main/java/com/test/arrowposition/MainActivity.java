package com.test.arrowposition;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.test.arrowposition.view.PricesAssessment;


public class MainActivity extends AppCompatActivity {
    PricesAssessment carDetailsPricesAssessmentProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carDetailsPricesAssessmentProgress = (PricesAssessment) findViewById(R.id.carDetailsPricesAssessmentProgress);
        carDetailsPricesAssessmentProgress.setProgress(0.3f, 0.4f, 0.3f,0.5f);
        carDetailsPricesAssessmentProgress.setProgressDownText("3.00万");
        carDetailsPricesAssessmentProgress.setProgressNormalText("当前报价5.00万");
        carDetailsPricesAssessmentProgress.setProgressUpText("3.00万");
    }
}
