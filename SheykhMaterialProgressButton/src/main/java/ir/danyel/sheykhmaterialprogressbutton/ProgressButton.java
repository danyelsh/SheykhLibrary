package ir.danyel.sheykhmaterialprogressbutton;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

@SuppressLint("AppCompatCustomView")
public class ProgressButton extends LinearLayout {

  private String TAG = getClass().getName();

  private View customView;
  private ConstraintLayout constraintLayout;
  private CardView cardView;
  private CoordinatorLayout root;
  private TextView textView;
  private ProgressBar progressBar;

  private String text;
  private Integer textSize;
  private Integer color;
  private Drawable backgroundDrawable;


  public ProgressButton(Context context) {
    super(context);

  }


  public ProgressButton(final Context context, AttributeSet attrs) {
    super(context, attrs);

    TypedArray a = context.getTheme().obtainStyledAttributes(
      attrs, R.styleable.ProgressButton, 0, 0);


    text = a.getString(R.styleable.ProgressButton_text);
    textSize = a.getInteger(R.styleable.ProgressButton_text_size, 20);
    color = a.getColor(R.styleable.ProgressButton_background_color, Color.parseColor("#ffaaffaa"));
    backgroundDrawable = a.getDrawable(R.styleable.ProgressButton_background);

    LayoutInflater inflater = (LayoutInflater) context
      .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    customView = inflater.inflate(R.layout.progress_button_layout, this, true);
    textView = customView.findViewById(R.id.textView);
    root = customView.findViewById(R.id.root);
    cardView = customView.findViewById(R.id.card_view);
    constraintLayout = customView.findViewById(R.id.constrainLayout);
    progressBar = customView.findViewById(R.id.progressBar);
    Log.d(TAG, "ProgressButton: -------------");
    Log.d(TAG, "ProgressButton: " + text);

    textView.setText(text);
    textView.setTextSize(textSize);
    constraintLayout.setBackgroundColor(color);
    if (backgroundDrawable != null) {
      constraintLayout.setBackground(backgroundDrawable);
    }


  }

  public ProgressButton(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);

    TypedArray a = context.getTheme().obtainStyledAttributes(
      attrs, R.styleable.ProgressButton, 0, 0);
    text = a.getString(R.styleable.ProgressButton_text);
    Log.d(TAG, "ProgressButton: " + text);
    textView.setText(text);
  }

  public ProgressButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);

    TypedArray a = context.getTheme().obtainStyledAttributes(
      attrs, R.styleable.ProgressButton, 0, 0);
    text = a.getString(R.styleable.ProgressButton_text);
    Log.d(TAG, "ProgressButton: " + text);
    textView.setText(text);
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
    progressBar.setVisibility(INVISIBLE);
  }

  public boolean isProgressBarShowing() {
    if (progressBar.getVisibility() == VISIBLE) {
      return true;
    } else {
      return false;
    }
  }

  public void setTextSize(Float textSize) {
    textView.setTextSize(textSize);
  }

  public Float getTextSize() {
    return textView.getTextSize();
  }

  public void setBackground(Drawable drawable) {
    constraintLayout.setBackground(drawable);
  }

  public Drawable getBackground() {
    return constraintLayout.getBackground();

  }

    
}
