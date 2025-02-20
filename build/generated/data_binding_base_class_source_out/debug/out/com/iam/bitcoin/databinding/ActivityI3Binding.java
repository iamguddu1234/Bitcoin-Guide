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

public final class ActivityI3Binding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final AdView adViewi3;

  @NonNull
  public final RecyclerView i3rec;

  @NonNull
  public final RecyclerView i3reci;

  @NonNull
  public final NestedScrollView scroll;

  private ActivityI3Binding(@NonNull RelativeLayout rootView, @NonNull AdView adViewi3,
      @NonNull RecyclerView i3rec, @NonNull RecyclerView i3reci, @NonNull NestedScrollView scroll) {
    this.rootView = rootView;
    this.adViewi3 = adViewi3;
    this.i3rec = i3rec;
    this.i3reci = i3reci;
    this.scroll = scroll;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityI3Binding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityI3Binding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_i3, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityI3Binding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.adViewi3;
      AdView adViewi3 = ViewBindings.findChildViewById(rootView, id);
      if (adViewi3 == null) {
        break missingId;
      }

      id = R.id.i3rec;
      RecyclerView i3rec = ViewBindings.findChildViewById(rootView, id);
      if (i3rec == null) {
        break missingId;
      }

      id = R.id.i3reci;
      RecyclerView i3reci = ViewBindings.findChildViewById(rootView, id);
      if (i3reci == null) {
        break missingId;
      }

      id = R.id.scroll;
      NestedScrollView scroll = ViewBindings.findChildViewById(rootView, id);
      if (scroll == null) {
        break missingId;
      }

      return new ActivityI3Binding((RelativeLayout) rootView, adViewi3, i3rec, i3reci, scroll);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
