// Generated by view binder compiler. Do not edit!
package com.iam.bitcoin.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
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

public final class ActivityB9Binding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final AdView adViewb9;

  @NonNull
  public final RecyclerView b9rec;

  @NonNull
  public final NestedScrollView scroll;

  private ActivityB9Binding(@NonNull RelativeLayout rootView, @NonNull AdView adViewb9,
      @NonNull RecyclerView b9rec, @NonNull NestedScrollView scroll) {
    this.rootView = rootView;
    this.adViewb9 = adViewb9;
    this.b9rec = b9rec;
    this.scroll = scroll;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityB9Binding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityB9Binding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_b9, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityB9Binding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.adViewb9;
      AdView adViewb9 = ViewBindings.findChildViewById(rootView, id);
      if (adViewb9 == null) {
        break missingId;
      }

      id = R.id.b9rec;
      RecyclerView b9rec = ViewBindings.findChildViewById(rootView, id);
      if (b9rec == null) {
        break missingId;
      }

      id = R.id.scroll;
      NestedScrollView scroll = ViewBindings.findChildViewById(rootView, id);
      if (scroll == null) {
        break missingId;
      }

      return new ActivityB9Binding((RelativeLayout) rootView, adViewb9, b9rec, scroll);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
