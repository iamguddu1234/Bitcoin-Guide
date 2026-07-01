package com.iam.bitcoin; // change this to your actual package

import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.TypefaceSpan;

public class CustomTypefaceSpan extends TypefaceSpan {
    private final Typeface newType;

    public CustomTypefaceSpan(Typeface type) {
        super(""); // use empty string "" instead of null
        this.newType = type;
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        applyCustomTypeface(ds, newType);
    }

    @Override
    public void updateMeasureState(TextPaint paint) {
        applyCustomTypeface(paint, newType);
    }

    private static void applyCustomTypeface(Paint paint, Typeface tf) {
        Typeface old = paint.getTypeface();
        int oldStyle = (old == null) ? 0 : old.getStyle();

        int fake = oldStyle & ~tf.getStyle();
        if ((fake & Typeface.BOLD) != 0) {
            paint.setFakeBoldText(true);
        }
        if ((fake & Typeface.ITALIC) != 0) {
            paint.setTextSkewX(-0.25f);
        }
        paint.setTypeface(tf);
    }
}