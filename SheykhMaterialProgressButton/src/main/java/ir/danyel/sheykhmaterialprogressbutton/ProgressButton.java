package ir.danyel.sheykhmaterialprogressbutton;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

@SuppressLint("AppCompatCustomView")
public class ProgressButton extends LinearLayout {

  private View customView;
  private ConstraintLayout constraintLayout;
  private CardView cardView;
  private CoordinatorLayout root;
  private TextView textView;
  private ProgressBar progressBar;


  public ProgressButton(Context context) {
    super(context);
  }

  public ProgressButton(final Context context, AttributeSet attrs) {
    super(context, attrs);
    LayoutInflater inflater = (LayoutInflater) context
      .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    customView = inflater.inflate(R.layout.progress_button_layout, this, true);
    textView = customView.findViewById(R.id.textView);
    root = customView.findViewById(R.id.root);
    cardView = customView.findViewById(R.id.card_view);
    constraintLayout = customView.findViewById(R.id.constrainLayout);
    progressBar = customView.findViewById(R.id.progressBar);

  }

  public ProgressButton(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  public ProgressButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  public String getText() {
    return (String) textView.getText();
  }

  public void setText(String text) {
    textView.setText(text);
  }

  public void showLoading() {
    progressBar.setVisibility(VISIBLE);
  }

  public void hideLoading() {
    progressBar.setVisibility(GONE);
  }

  public boolean isProgressBarShowing() {
    if (progressBar.getVisibility() == VISIBLE) {
      return true;
    } else {
      return false;
    }
  }
}
