package ir.danyel.customview_sololearning;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    final ProgressButton progressButton = findViewById(R.id.progressButton);

    progressButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

//        Toast.makeText(MainActivity.this, ""+"progressButton.isProgressBarShowing()", Toast.LENGTH_SHORT).show();
//        Toast.makeText(MainActivity.this, ""+progressButton.isProgressBarShowing(), Toast.LENGTH_SHORT).show();

        if (progressButton.isProgressBarShowing()) {
          progressButton.hideLoading();
          progressButton.setText("DONE.");
        } else {
          progressButton.showLoading();
          progressButton.setText("loading...");
        }


      }
    });


  }
}