//package ir.danyel.customview_sololearning;
//
//import android.content.Context;
//import android.content.res.TypedArray;
//import android.graphics.Color;
//import android.graphics.Typeface;
//import android.text.Editable;
//import android.text.InputType;
//import android.text.TextWatcher;
//import android.util.AttributeSet;
//import android.util.Patterns;
//import android.util.TypedValue;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.animation.Animation;
//import android.view.animation.AnimationUtils;
//import android.widget.EditText;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class YummyEditText extends LinearLayout {
//
//  private String TAG = this.getClass().getName();
//  private View customView;
//  private EditText etText;
//  private TextView tvError;
//  private LinearLayout linearLayout;
//
//  /**
//   * if all conditions passed return true
//   */
//  private boolean isValid = true;
//
//  /**
//   * minimum value
//   * for number input types
//   */
//  private int minValue = Integer.MIN_VALUE;
//  /**
//   * maximum value
//   * for number input types
//   */
//  private int maxValue = Integer.MAX_VALUE;
//  /**
//   * minimum length characters
//   */
//  private int minLength = 0;
//  /**
//   * maximum length characters
//   */
//  private int maxLength = Integer.MAX_VALUE;
//
//  /**
//   * use regex for validation
//   * if null, not applied
//   */
//  private String regex = null;
//  /**
//   * if input type is number
//   * set this true
//   */
//  private boolean isNumeric = false;
//
//  /**
//   * if must this filed completed
//   * it check text length
//   */
//  private boolean isRequired = false;
//
//  /**
//   * set editText text gravity
//   */
//  private int textGravity = Gravity.CENTER | Gravity.RIGHT;
//  /**
//   * set error text gravity
//   */
//  private int errorTextGravity = Gravity.CENTER | Gravity.RIGHT;
//
//    /*
//        UI Settings
//     */
//
//  private int errorTextColor = Color.parseColor("#cc0022");
//  private int errorTextSize = 10;
//  private String errorText = "لطفا این فیلد را تکمیل کنید.";
//  private String errorTextRequired = "این فیلد ضروری است.";
//  private String errorTextMinValue = "مقدار وارد شده نباید کمتر از " + Integer.MIN_VALUE + " باشد.";
//  private String errorTextMaxValue = "مقدار وارد شده نباید بیشتر از " + Integer.MAX_VALUE + " باشد.";
//  private String errorTextMinLength = "تعداد کاراکتر نباید کمتر از " + Integer.MIN_VALUE + " باشد.";
//  private String errorTextMaxLength = "تعداد کاراکتر نباید بیشتر از " + Integer.MAX_VALUE + " باشد.";
//  private String errorTextRegex = "فرمت متن وارد شده اشتباه می باشد.";
//  private String errorTextNumeric = "بایستی عدد باشد.";
//  private boolean isMultiError = true;
//  private boolean isErrorAnimation = true;
//  private int textColor = Color.parseColor("#444444");
//  private int textSize = 14;
//  private boolean isNumberSeparator = false;
//  private String hint = null;
//  private int hintColor = Color.parseColor("#999999");
//  private int type = YUMMY_EDIT_TEXT_TYPE_NORMAL;
//  private int inputType = InputType.TYPE_CLASS_TEXT;
//  private int defaultRegex = 0;
//
//  public static int YUMMY_EDIT_TEXT_TYPE_NORMAL = 0;
//  public static int YUMMY_EDIT_TEXT_TYPE_CURVED = 1;
//
//  public static String YUMMY_DEFAULT_REGEX_NOTHING = "";
//  public static String YUMMY_DEFAULT_REGEX_EMAIL = Patterns.EMAIL_ADDRESS.pattern();
//  public static String YUMMY_DEFAULT_REGEX_PHONE = "(\\+98|0)?[1-9]\\d{9}";
//  public static String YUMMY_DEFAULT_REGEX_MOBILE = "(\\+98|0)?9\\d{9}";
//
//
//  private String errorMessage;
//
//  public YummyEditText(Context context) {
//    super(context);
//  }
//
//  public YummyEditText(final Context context, AttributeSet attrs) {
//    super(context, attrs);
//
//    TypedArray a = context.obtainStyledAttributes(attrs,
//      R.styleable.YummyEditText, 0, 0);
//
//    //Validation
//    isNumeric = a.getBoolean(R.styleable.YummyEditText_numeric, false);
//    isRequired = a.getBoolean(R.styleable.YummyEditText_required, false);
//    minValue = a.getInt(R.styleable.YummyEditText_min_value, Integer.MIN_VALUE);
//    maxValue = a.getInt(R.styleable.YummyEditText_max_value, Integer.MAX_VALUE);
//    minLength = a.getInt(R.styleable.YummyEditText_min_length, Integer.MIN_VALUE);
//    maxLength = a.getInt(R.styleable.YummyEditText_max_length, Integer.MAX_VALUE);
//    regex = a.getString(R.styleable.YummyEditText_regex);
//
//    //Settings
//    isErrorAnimation = a.getBoolean(R.styleable.YummyEditText_error_animation, true);
//    isMultiError = a.getBoolean(R.styleable.YummyEditText_multi_error, false);
//    errorText = a.getString(R.styleable.YummyEditText_error_text);
//    errorTextRequired = a.getString(R.styleable.YummyEditText_error_text_gravity);
//    errorTextRegex = a.getString(R.styleable.YummyEditText_error_text_regex);
//    errorTextMinValue = a.getString(R.styleable.YummyEditText_error_text_min_value);
//    errorTextMaxValue = a.getString(R.styleable.YummyEditText_error_text_max_value);
//    errorTextMinLength = a.getString(R.styleable.YummyEditText_error_text_min_length);
//    errorTextMaxLength = a.getString(R.styleable.YummyEditText_error_text_max_length);
//    errorTextNumeric = a.getString(R.styleable.YummyEditText_error_text_numeric);
//    errorTextColor = a.getColor(R.styleable.YummyEditText_error_text_color, Color.parseColor("#cc0022"));
//    errorTextSize = a.getInt(R.styleable.YummyEditText_error_text_size, 10);
//    textColor = a.getColor(R.styleable.YummyEditText_text_color, Color.parseColor("#444444"));
//    textSize = a.getInt(R.styleable.YummyEditText_text_size, 14);
//    isNumberSeparator = a.getBoolean(R.styleable.YummyEditText_number_separator, false);
//    hint = a.getString(R.styleable.YummyEditText_hint);
//    hintColor = a.getInt(R.styleable.YummyEditText_hint_color, Color.parseColor("#999999"));
//    type = a.getInteger(R.styleable.YummyEditText_type, YUMMY_EDIT_TEXT_TYPE_NORMAL);
//    inputType = a.getInteger(R.styleable.YummyEditText_input_type, InputType.TYPE_CLASS_TEXT);
//    textGravity = a.getInteger(R.styleable.YummyEditText_text_gravity, Gravity.CENTER | Gravity.RIGHT);
//    errorTextGravity = a.getInteger(R.styleable.YummyEditText_error_text_gravity, Gravity.CENTER | Gravity.RIGHT);
//    defaultRegex = a.getInteger(R.styleable.YummyEditText_default_regex, -1);
//
//    // ready to Garbage collection
//    a.recycle();
//
//    setOrientation(LinearLayout.VERTICAL);
//    setGravity(Gravity.CENTER_VERTICAL);
//    LayoutInflater inflater = (LayoutInflater) context
//      .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//    customView = inflater.inflate(R.layout.yummy_edittext, this, true);
//    etText = customView.findViewById(R.id.etText);
//    tvError = customView.findViewById(R.id.tvError);
//    linearLayout = customView.findViewById(R.id.tvError);
//
//    errorMessage = errorText;
//    etText.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
//    etText.setTextColor(textColor);
//    etText.setHint(hint);
//    etText.setHintTextColor(hintColor);
//    tvError.setTextSize(TypedValue.COMPLEX_UNIT_SP, errorTextSize);
//    tvError.setText(errorText);
//    tvError.setTextColor(errorTextColor);
//    etText.setInputType(inputType);
//    etText.setGravity(textGravity);
//    tvError.setGravity(textGravity);
//    if (type == YUMMY_EDIT_TEXT_TYPE_NORMAL) {
//      etText.setBackground(context.getResources().getDrawable(R.drawable.selector_et));
//    } else if (type == YUMMY_EDIT_TEXT_TYPE_CURVED) {
//      etText.setBackground(context.getResources().getDrawable(R.drawable.selector_et_curved));
//    }
//
//    switch (defaultRegex) {
//      case 0: {
//        regex = YUMMY_DEFAULT_REGEX_EMAIL;
//        break;
//      }
//      case 1: {
//        regex = YUMMY_DEFAULT_REGEX_PHONE;
//        break;
//      }
//      case 2: {
//        regex = YUMMY_DEFAULT_REGEX_MOBILE;
//        break;
//      }
//    }
//
//
//    etText.addTextChangedListener(new TextWatcher() {
//      @Override
//      public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//      }
//
//      @Override
//      public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//        checkConditions();
//        if (isValid) {
//          if (type == YUMMY_EDIT_TEXT_TYPE_NORMAL) {
//            etText.setBackground(context.getResources().getDrawable(R.drawable.selector_et));
//          } else if (type == YUMMY_EDIT_TEXT_TYPE_CURVED) {
//            etText.setBackground(context.getResources().getDrawable(R.drawable.selector_et_curved));
//          }
//          tvError.setVisibility(INVISIBLE);
//        } else {
//          boolean isTextChanged = errorMessage.equals(tvError.getText().toString());
//          tvError.setText(errorMessage);
//          if (type == YUMMY_EDIT_TEXT_TYPE_NORMAL) {
//            etText.setBackground(context.getResources().getDrawable(R.drawable.selector_et_error));
//          } else if (type == YUMMY_EDIT_TEXT_TYPE_CURVED) {
//            etText.setBackground(context.getResources().getDrawable(R.drawable.selector_et_error_curved));
//          }
//          tvError.setVisibility(VISIBLE);
//          if (isErrorAnimation && isTextChanged) {
//            Animation animZoomIn = AnimationUtils.loadAnimation(context, R.anim.animation_shake);
//            tvError.startAnimation(animZoomIn);
//          }
//        }
//      }
//
//      @Override
//      public void afterTextChanged(Editable editable) {
//        if (isNumberSeparator) {
//
//          String text = etText.getText().toString();
//          try {
//            String t1 = FinanceHelper.convertWithCommaToWithout(text);
//            if (t1.length() > 3) {
//              String n = FinanceHelper.convertMoneyToWithComma(t1);
//              if (!n.equals(text)) {
//                etText.setText(n);
//                etText.setSelection(n.length());
//              }
//            } else if (!t1.equals(text)) {
//              etText.setText(t1);
//              etText.setSelection(t1.length());
//            }
//          } catch (Exception e) {
//            e.printStackTrace();
//          }
//        }
//      }
//    });
//
//  }
//
//  public void addTextChangedListener(TextWatcher textWatcher) {
//    etText.addTextChangedListener(textWatcher);
//  }
//
//  public EditText getEditText() {
//    return this.etText;
//  }
//
//  public TextView getTextViewError() {
//    return this.tvError;
//  }
//
//  public void noPadding(int left, int top, int right, int bottom) {
//    this.linearLayout.setPadding(left, top, right, bottom);
//  }
//
//  public String getText() {
//    if (isNumberSeparator) {
//      return etText.getText().toString().replace(",", "");
//
//    } else {
//      return etText.getText().toString();
//    }
//  }
//
//  private void checkConditions() {
//    this.isValid = true;
//    if (isMultiError) {
//      errorMessage = "";
//    } else {
//      errorMessage = errorText;
//    }
//    String text = etText.getText().toString();
//    if (text.length() == 0 && isRequired) {
//      this.isValid = false;
//      if (isMultiError) {
//        addErrorMessage(errorTextRequired);
//      }
//    }
//    if (isRequired || text.length() > 0) {
//      if (text.length() < minLength) {
//        this.isValid = false;
//        if (isMultiError) {
//          addErrorMessage(errorTextMinLength);
//        }
//      }
//      if (text.length() > maxLength) {
//        this.isValid = false;
//        if (isMultiError) {
//          addErrorMessage(errorTextMaxLength);
//        }
//      }
//
//      if (isNumeric) {
//        try {
//          double number = Double.parseDouble(text);
//        } catch (Exception e) {
//          e.printStackTrace();
//          this.isValid = false;
//          if (isMultiError) {
//            addErrorMessage(errorTextNumeric);
//          }
//        }
//      }
//
//      if (isNumeric && minValue != Integer.MIN_VALUE) {
//        try {
//          double number = Double.parseDouble(text);
//          if (number < minValue) {
//            this.isValid = false;
//            if (isMultiError) {
//              addErrorMessage(errorTextMinValue);
//            }
//          }
//        } catch (Exception e) {
//          e.printStackTrace();
//          this.isValid = false;
//          if (isMultiError) {
//            addErrorMessage(errorTextNumeric);
//          }
//        }
//      }
//
//      if (isNumeric && maxValue != Integer.MAX_VALUE) {
//        try {
//          double number = Double.parseDouble(text);
//          if (number > maxValue) {
//            this.isValid = false;
//            if (isMultiError) {
//              addErrorMessage(errorTextMaxValue);
//            }
//          }
//        } catch (Exception e) {
//          e.printStackTrace();
//          this.isValid = false;
//          if (isMultiError) {
//            addErrorMessage(errorTextNumeric);
//          }
//        }
//      }
//      if (regex != null) {
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(text);
//        if (!matcher.matches()) {
//          this.isValid = false;
//          if (isMultiError) {
//            addErrorMessage(errorTextRegex);
//          }
//        }
//      }
//    }
//
//  }
//
//  private void addErrorMessage(String error) {
//    if (error != null) {
//      if (errorMessage.length() == 0) {
//        errorMessage += " \u002a " + error;
//      } else {
//        errorMessage += "\n" + " \u002a " + error;
//      }
//    }
//  }
//
//  public boolean isValid() {
//    checkConditions();
//    return this.isValid;
//  }
//
//  public void setTypeFace(Typeface typeFace) {
//    this.etText.setTypeface(typeFace);
//    this.tvError.setTypeface(typeFace);
//  }
//
//  public int getMinValue() {
//    return minValue;
//  }
//
//  public void setMinValue(int minValue) {
//    this.minValue = minValue;
//  }
//
//  public int getMaxValue() {
//    return maxValue;
//  }
//
//  public void setMaxValue(int maxValue) {
//    this.maxValue = maxValue;
//  }
//
//  public int getMinLength() {
//    return minLength;
//  }
//
//  public void setMinLength(int minLength) {
//    this.minLength = minLength;
//  }
//
//  public int getMaxLength() {
//    return maxLength;
//  }
//
//  public void setMaxLength(int maxLength) {
//    this.maxLength = maxLength;
//  }
//
//  public String getRegex() {
//    return regex;
//  }
//
//  public void setRegex(String regex) {
//    this.regex = regex;
//  }
//
//  public void setValid(boolean valid) {
//    isValid = valid;
//  }
//
//  public boolean isNumeric() {
//    return isNumeric;
//  }
//
//  public void setNumeric(boolean numeric) {
//    isNumeric = numeric;
//  }
//
//  public boolean isRequired() {
//    return isRequired;
//  }
//
//  public void setRequired(boolean required) {
//    isRequired = required;
//  }
//
//  public int getTextGravity() {
//    return textGravity;
//  }
//
//  public void setTextGravity(int textGravity) {
//    this.textGravity = textGravity;
//  }
//
//  public int getErrorTextGravity() {
//    return errorTextGravity;
//  }
//
//  public void setErrorTextGravity(int errorTextGravity) {
//    this.errorTextGravity = errorTextGravity;
//  }
//
//  public int getErrorTextColor() {
//    return errorTextColor;
//  }
//
//  public void setErrorTextColor(int errorTextColor) {
//    this.errorTextColor = errorTextColor;
//  }
//
//  public int getErrorTextSize() {
//    return errorTextSize;
//  }
//
//  public void setErrorTextSize(int errorTextSize) {
//    this.errorTextSize = errorTextSize;
//  }
//
//  public String getErrorText() {
//    return errorText;
//  }
//
//  public void setErrorText(String errorText) {
//    this.errorText = errorText;
//  }
//
//  public boolean isErrorAnimation() {
//    return isErrorAnimation;
//  }
//
//  public void setErrorAnimation(boolean errorAnimation) {
//    isErrorAnimation = errorAnimation;
//  }
//
//  public int getTextColor() {
//    return textColor;
//  }
//
//  public void setTextColor(int textColor) {
//    this.textColor = textColor;
//  }
//
//  public int getTextSize() {
//    return textSize;
//  }
//
//  public void setTextSize(int textSize) {
//    this.textSize = textSize;
//  }
//
//  public boolean isNumberSeparator() {
//    return isNumberSeparator;
//  }
//
//  public void setNumberSeparator(boolean numberSeparator) {
//    isNumberSeparator = numberSeparator;
//  }
//
//  public String getHint() {
//    return hint;
//  }
//
//  public void setHint(String hint) {
//    this.hint = hint;
//  }
//
//  public int getHintColor() {
//    return hintColor;
//  }
//
//  public void setHintColor(int hintColor) {
//    this.hintColor = hintColor;
//  }
//
//  public int getType() {
//    return type;
//  }
//
//  public void setType(int type) {
//    this.type = type;
//  }
//
//  public int getInputType() {
//    return inputType;
//  }
//
//  public void setInputType(int inputType) {
//    this.inputType = inputType;
//  }
//
//  public String getErrorTextRequired() {
//    return errorTextRequired;
//  }
//
//  public void setErrorTextRequired(String errorTextRequired) {
//    this.errorTextRequired = errorTextRequired;
//  }
//
//  public String getErrorTextMinValue() {
//    return errorTextMinValue;
//  }
//
//  public void setErrorTextMinValue(String errorTextMinValue) {
//    this.errorTextMinValue = errorTextMinValue;
//  }
//
//  public String getErrorTextMaxValue() {
//    return errorTextMaxValue;
//  }
//
//  public void setErrorTextMaxValue(String errorTextMaxValue) {
//    this.errorTextMaxValue = errorTextMaxValue;
//  }
//
//  public String getErrorTextMinLength() {
//    return errorTextMinLength;
//  }
//
//  public void setErrorTextMinLength(String errorTextMinLength) {
//    this.errorTextMinLength = errorTextMinLength;
//  }
//
//  public String getErrorTextMaxLength() {
//    return errorTextMaxLength;
//  }
//
//  public void setErrorTextMaxLength(String errorTextMaxLength) {
//    this.errorTextMaxLength = errorTextMaxLength;
//  }
//
//  public String getErrorTextRegex() {
//    return errorTextRegex;
//  }
//
//  public void setErrorTextRegex(String errorTextRegex) {
//    this.errorTextRegex = errorTextRegex;
//  }
//
//  public String getErrorTextNumeric() {
//    return errorTextNumeric;
//  }
//
//  public void setErrorTextNumeric(String errorTextNumeric) {
//    this.errorTextNumeric = errorTextNumeric;
//  }
//
//  public boolean isMultiError() {
//    return isMultiError;
//  }
//
//  public void setMultiError(boolean multiError) {
//    isMultiError = multiError;
//  }
//
//  public int getDefaultRegex() {
//    return defaultRegex;
//  }
//
//  public void setDefaultRegex(int defaultRegex) {
//    this.defaultRegex = defaultRegex;
//  }
//
//  public String getErrorMessage() {
//    return errorMessage;
//  }
//
//  public void setErrorMessage(String errorMessage) {
//    this.errorMessage = errorMessage;
//  }
//
//  public void setText(CharSequence charSequence) {
//    this.etText.setText(charSequence);
//  }
//}
