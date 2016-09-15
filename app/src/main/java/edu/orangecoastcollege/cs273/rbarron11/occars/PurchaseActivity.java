package edu.orangecoastcollege.cs273.rbarron11.occars;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class PurchaseActivity extends AppCompatActivity {
    private EditText carPriceEditText;
    private EditText downPaymentEditText;
    private RadioButton threeYearsRadioButton;
    private RadioButton fourYearRadioButton;
    private RadioButton fiveYearRadioButton;

    private Car currentCar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        carPriceEditText = (EditText) findViewById (R.id. carPriceEditText);
        downPaymentEditText = (EditText) findViewById (R.id.downPaymentEditText);
        threeYearsRadioButton = (RadioButton) findViewById (R.id.threeYearsRadioButton);
        fourYearRadioButton = (RadioButton) findViewById (R.id.fourYearRadioButton);
        fiveYearRadioButton = (RadioButton) findViewById (R.id.fiveYearRadioButton);

        currentCar = new Car();
    }
    public void activateLoadReport(View view)
    {
        double price, downPayment;
        try {
            price = Double.parseDouble(carPriceEditText.getText().toString());
            downPayment = Double.parseDouble(downPaymentEditText.getText().toString());

        }
        catch(NumberFormatException e){
            price = 0.0;
            downPayment = 0.0;
        }

        int loanTerm;
        if(fiveYearRadioButton.isChecked())
            loanTerm = 5;
        else if (fourYearRadioButton.isChecked())
            loanTerm = 4;
        else
            loanTerm = 3;

        currentCar.setPrice(price);
        currentCar.setDownPayment(downPayment);
        currentCar.setLoadTerm (loanTerm);

        //create the intent to share data with LoanSummary
        Intent loanSummaryIntent = new Intent(this, LoanSummaryActivty.class);
        loanSummaryIntent.putExtra("MonthlyPayment" , monthlyPaymentText );
        loanSummaryIntent.putExtra("LoanSummary" , loanSummaryText)
        //start new activity
        startActivity(loanSummaryIntent);

    }

    private void constructLoanSummaryText()
    {
        monthlyPaymentText = getString(R.string.report_line1) + currentCar.monthlyPaymentText();

        loanSummaryText = getString(R.string.report_line2) + currentCar.getPrice()
                + getString(R.string.report_line3) + currentCar.getDownPayment()

    }
}

}
