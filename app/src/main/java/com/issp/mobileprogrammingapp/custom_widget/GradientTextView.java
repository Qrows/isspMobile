package com.ipss.worldbank.custom_widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.ipss.worldbank.R;
import com.ipss.worldbank.custom_widget.HelveticaNeueTextView;

public class GradientTextView extends HelveticaNeueTextView {
    public GradientTextView(Context context) {
        super(context);
        createDefaultGradient();
    }

    public GradientTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        createDefaultGradient();
    }

    public GradientTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        createDefaultGradient();
    }

    private void createDefaultGradient() {
        int startColor = R.color.textGradientStart;
        int endColor = R.color.textGradientEnd;
        LinearGradient shader = new LinearGradient(0, 0, getPaint().measureText(getText().toString()), 0, new int[]{getResources().getColor(startColor, null), getResources().getColor(endColor, null)}, null, Shader.TileMode.CLAMP);
        // Log.d("MeasureText", Float.toString(getPaint().measureText(getText().toString())) + " VS " + getWidth() + " VS "+ getMeasuredWidth());
        getPaint().setShader(shader);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // draw the gradient filled text
        getPaint().clearShadowLayer();
        createDefaultGradient();
        super.onDraw(canvas);
    }
}
