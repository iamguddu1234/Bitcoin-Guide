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

public final class ActivityA19Binding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final RecyclerView a19rec;

  @NonNull
  public final AdView adViewa19;

  @NonNull
  public final NestedScrollView scroll;

  private ActivityA19Binding(@NonNull RelativeLayout rootView, @NonNull RecyclerView a19rec,
      @NonNull AdView adViewa19, @NonNull NestedScrollView scroll) {
    this.rootView = rootView;
    this.a19rec = a19rec;
    this.adViewa19 = adViewa19;
    this.scroll = scroll;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityA19Binding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityA19Binding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_a19, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityA19Binding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.a19rec;
      RecyclerView a19rec = ViewBindings.findChildViewById(rootView, id);
      if (a19rec == null) {
        break missingId;
      }

      id = R.id.adViewa19;
      AdView adViewa19 = ViewBindings.findChildViewById(rootView, id);
      if (adViewa19 == null) {
        break missingId;
      }

      id = R.id.scroll;
      NestedScrollView scroll = ViewBindings.findChildViewById(rootView, id);
      if (scroll == null) {
        break missingId;
      }

      return new ActivityA19Binding((RelativeLayout) rootView, a19rec, adViewa19, scroll);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}