package com.woolf.cleanapp.util.view;


import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.woolf.cleanapp.R;

public class ProgressView extends RelativeLayout {

    private static final int NONE = -1;

    protected ProgressBar pbLoad;
    protected LinearLayout llError;
    protected TextView tvError;
    protected Button btnReload;

    private STATE viewState = STATE.COMPLETE;
    private String errorText;

    private RetryClickListener retryClickListener;

    public interface RetryClickListener {
        void onReload();
    }


    public ProgressView(Context context) {
        super(context);
    }

    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

//    @Nullable
//    @Override
//    protected Parcelable onSaveInstanceState() {
//        Parcelable superState = super.onSaveInstanceState();
//        SavedState ss = new SavedState(superState);
//        ss.state = viewState.getValue();
//        ss.error = errorText;
//        return ss;
//    }
//
//    @Override
//    protected void onRestoreInstanceState(Parcelable state) {
//        SavedState ss = (SavedState) state;
//        super.onRestoreInstanceState(ss.getSuperState());
//        viewState =STATE.getStatusByID(ss.state);
//        errorText = ss.error;
//    }

    public void onStartLoading() {
        viewState = STATE.PROGRESS;
        setVisibility(VISIBLE);
        llError.setVisibility(GONE);
        pbLoad.setVisibility(VISIBLE);
    }

    public void onErrorLoading(String errorText) {
        this.errorText = errorText;
        viewState = STATE.ERROR;
        setVisibility(VISIBLE);
        setErrorText(errorText);
        pbLoad.setVisibility(GONE);
        llError.setVisibility(VISIBLE);
    }

    public void onCompleteLoading() {
        viewState = STATE.COMPLETE;
        setVisibility(GONE);
    }


    private void init(Context context, AttributeSet attrs) {
        removeAllViews();

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ProgressView);
        int backgroundColor = typedArray.getColor(R.styleable.ProgressView_pv_background_color, Color.TRANSPARENT);
        setBackgroundColor(backgroundColor);
        initProgressBar(typedArray, context);
        initLinearLayout(typedArray, context);

        addView(pbLoad);
        addView(llError);

        initView(typedArray);

        typedArray.recycle();
    }

    private void initView(TypedArray typedArray) {
        int status = typedArray.getInt(R.styleable.ProgressView_pv_state, viewState.getValue());
        STATE currentState = STATE.getStatusByID(status);
        switch (currentState) {
            case PROGRESS:
                onStartLoading();
                break;
            case ERROR:
                onErrorLoading(errorText);
                break;
            case COMPLETE:
                onCompleteLoading();
                break;
        }
    }

    private void initProgressBar(TypedArray typedArray, Context context) {
        int progressSize = typedArray.getDimensionPixelSize(R.styleable.ProgressView_pv_pb_size, dpToPx(48, context));
        int progressColor = typedArray.getColor(R.styleable.ProgressView_pv_pb_color, -1);

        pbLoad = new ProgressBar(context);
        RelativeLayout.LayoutParams pbParams = new LayoutParams(progressSize, progressSize);
        pbParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        pbLoad.setLayoutParams(pbParams);

        pbLoad.setIndeterminate(true);
        pbLoad.setIndeterminateTintList(ColorStateList.valueOf(progressColor));
    }

    private void initLinearLayout(TypedArray typedArray, Context context) {
        llError = new LinearLayout(context);
        RelativeLayout.LayoutParams llErrorParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        llErrorParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        llError.setLayoutParams(llErrorParams);
        llError.setOrientation(LinearLayout.VERTICAL);
        llError.setGravity(Gravity.CENTER);

        initErrorTextView(typedArray, context);
        initRetryButton(typedArray, context);

        llError.addView(tvError);
        llError.addView(btnReload);
    }

    private void initRetryButton(TypedArray typedArray, Context context) {
        int buttonWidth = typedArray.getDimensionPixelSize(R.styleable.ProgressView_pv_retry_button_width, dpToPx(160, context));
        int buttonHeight = typedArray.getDimensionPixelSize(R.styleable.ProgressView_pv_retry_button_height, dpToPx(40, context));

        int startMargin = typedArray.getDimensionPixelSize(R.styleable.ProgressView_pv_retry_button_margin_start, dpToPx(0, context));
        int endMargin = typedArray.getDimensionPixelSize(R.styleable.ProgressView_pv_retry_button_margin_end, dpToPx(0, context));
        int bottomMargin = typedArray.getDimensionPixelSize(R.styleable.ProgressView_pv_retry_button_margin_bottom, dpToPx(8, context));
        int topMargin = typedArray.getDimensionPixelSize(R.styleable.ProgressView_pv_retry_button_margin_top, dpToPx(8, context));

        int textColor = typedArray.getColor(R.styleable.ProgressView_pv_retry_button_text_color, Color.BLACK);
        int backgroundResId = typedArray.getResourceId(R.styleable.ProgressView_pv_retry_button_background, NONE);
        int textSize = typedArray.getDimensionPixelSize(R.styleable.ProgressView_pv_retry_button_text_size, spToPx(16, context));

        btnReload = new Button(context);
        LinearLayout.LayoutParams retryParams = new LinearLayout.LayoutParams(buttonWidth, buttonHeight);
        retryParams.setMargins(startMargin, topMargin, endMargin, bottomMargin);
        btnReload.setLayoutParams(retryParams);
        btnReload.setText(typedArray.getString(R.styleable.ProgressView_pv_retry_button_text));
        btnReload.setTextColor(textColor);
        btnReload.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        btnReload.setAllCaps(false);
        btnReload.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (retryClickListener != null) {
                    retryClickListener.onReload();
                }
            }
        });
        if (backgroundResId != NONE) {
            btnReload.setBackgroundResource(backgroundResId);
        }
    }

    private void initErrorTextView(TypedArray typedArray, Context context) {
        int startMargin = typedArray.getDimensionPixelSize(R.styleable.ProgressView_pv_error_text_margin_start, dpToPx(48, context));
        int endMargin = typedArray.getDimensionPixelSize(R.styleable.ProgressView_pv_error_text_margin_end, dpToPx(48, context));
        int bottomMargin = typedArray.getDimensionPixelSize(R.styleable.ProgressView_pv_error_text_margin_bottom, dpToPx(8, context));
        int topMargin = typedArray.getDimensionPixelSize(R.styleable.ProgressView_pv_error_text_margin_top, dpToPx(8, context));

        int textColor = typedArray.getColor(R.styleable.ProgressView_pv_error_text_color, Color.BLACK);
        int textSize = typedArray.getDimensionPixelSize(R.styleable.ProgressView_pv_error_text_size, spToPx(16, context));

        errorText = typedArray.getString(R.styleable.ProgressView_pv_error_text);

        tvError = new TextView(context);
        LinearLayout.LayoutParams tvParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tvParams.setMargins(startMargin, topMargin, endMargin, bottomMargin);
        tvError.setLayoutParams(tvParams);

        tvError.setText(errorText);
        tvError.setTextColor(textColor);
        tvError.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        tvError.setGravity(Gravity.CENTER);
    }

    private int dpToPx(float valueInDp, Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, valueInDp, metrics);
    }

    public static int spToPx(float valueInSp, Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, valueInSp, metrics);
    }

    private void setErrorText(String text) {
        tvError.setText(text);
        requestLayout();
    }


    public void setRetryClickListener(RetryClickListener retryClickListener) {
        this.retryClickListener = retryClickListener;
    }

    public enum STATE {
        PROGRESS(0),
        ERROR(1),
        COMPLETE(2);

        private final int id;

        STATE(int id) {
            this.id = id;
        }

        public int getValue() {
            return id;
        }

        static STATE getStatusByID(int id) {
            for (STATE STATE : values()) {
                if (id == STATE.getValue()) {
                    return STATE;
                }
            }
            throw new IllegalArgumentException();
        }
    }


//    static class SavedState extends BaseSavedState {
//        int state;
//        String error;
//
//        SavedState(Parcelable superState) {
//            super(superState);
//        }
//
//        private SavedState(Parcel in) {
//            super(in);
//            state = in.readInt();
//            error = in.readString();
//        }
//
//        @Override
//        public void writeToParcel(Parcel out, int flags) {
//            super.writeToParcel(out, flags);
//            out.writeInt(state);
//            out.writeString(error);
//        }
//
//        public static final Parcelable.Creator<SavedState> CREATOR
//                = new Parcelable.Creator<SavedState>() {
//            public SavedState createFromParcel(Parcel in) {
//                return new SavedState(in);
//            }
//
//            public SavedState[] newArray(int size) {
//                return new SavedState[size];
//            }
//        };
//    }
}
