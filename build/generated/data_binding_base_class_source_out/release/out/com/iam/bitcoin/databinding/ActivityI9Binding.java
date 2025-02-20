// Generated by view binder compiler. Do not edit!
package com.iam.bitcoin.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.gms.ads.AdView;
import com.iam.bitcoin.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityI9Binding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final AdView adViewi9;

  @NonNull
  public final RecyclerView i9rec;

  @NonNull
  public final NestedScrollView scroll;

  @NonNull
  public final TextView subtitle1;

  private ActivityI9Binding(@NonNull RelativeLayout rootView, @NonNull AdView adViewi9,
      @NonNull RecyclerView i9rec, @NonNull NestedScrollView scroll, @NonNull TextView subtitle1) {
    this.rootView = rootView;
    this.adViewi9 = adViewi9;
    this.i9rec = i9rec;
    this.scroll = scroll;
    this.subtitle1 = subtitle1;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityI9Binding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityI9Binding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_i9, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityI9Binding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.adViewi9;
      AdView adViewi9 = ViewBindings.findChildViewById(rootView, id);
      if (adViewi9 == null) {
        break missingId;
      }

      id = R.id.i9rec;
      RecyclerView i9rec = ViewBindings.findChildViewById(rootView, id);
      if (i9rec == null) {
        break missingId;
      }

      id = R.id.scroll;
      NestedScrollView scroll = ViewBindings.findChildViewById(rootView, id);
      if (scroll == null) {
        break missingId;
      }

      id = R.id.subtitle1;
      TextView subtitle1 = ViewBindings.findChildViewById(rootView, id);
      if (subtitle1 == null) {
        break missingId;
      }

      return new ActivityI9Binding((RelativeLayout) rootView, adViewi9, i9rec, scroll, subtitle1);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
