// Generated by view binder compiler. Do not edit!
package com.iam.bitcoin.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.card.MaterialCardView;
import com.iam.bitcoin.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentAABasicBinding implements ViewBinding {
  @NonNull
  private final NestedScrollView rootView;

  @NonNull
  public final MaterialCardView advance;

  @NonNull
  public final MaterialCardView b1;

  @NonNull
  public final MaterialCardView b10;

  @NonNull
  public final MaterialCardView b11;

  @NonNull
  public final MaterialCardView b2;

  @NonNull
  public final MaterialCardView b3;

  @NonNull
  public final MaterialCardView b4;

  @NonNull
  public final MaterialCardView b5;

  @NonNull
  public final MaterialCardView b6;

  @NonNull
  public final MaterialCardView b7;

  @NonNull
  public final MaterialCardView b8;

  @NonNull
  public final MaterialCardView b9;

  @NonNull
  public final MaterialCardView ewapCliq;

  @NonNull
  public final MaterialCardView fairCliq;

  @NonNull
  public final MaterialCardView klhtCliq;

  @NonNull
  public final MaterialCardView klraCliq;

  @NonNull
  public final MaterialCardView mainimage;

  private FragmentAABasicBinding(@NonNull NestedScrollView rootView,
      @NonNull MaterialCardView advance, @NonNull MaterialCardView b1,
      @NonNull MaterialCardView b10, @NonNull MaterialCardView b11, @NonNull MaterialCardView b2,
      @NonNull MaterialCardView b3, @NonNull MaterialCardView b4, @NonNull MaterialCardView b5,
      @NonNull MaterialCardView b6, @NonNull MaterialCardView b7, @NonNull MaterialCardView b8,
      @NonNull MaterialCardView b9, @NonNull MaterialCardView ewapCliq,
      @NonNull MaterialCardView fairCliq, @NonNull MaterialCardView klhtCliq,
      @NonNull MaterialCardView klraCliq, @NonNull MaterialCardView mainimage) {
    this.rootView = rootView;
    this.advance = advance;
    this.b1 = b1;
    this.b10 = b10;
    this.b11 = b11;
    this.b2 = b2;
    this.b3 = b3;
    this.b4 = b4;
    this.b5 = b5;
    this.b6 = b6;
    this.b7 = b7;
    this.b8 = b8;
    this.b9 = b9;
    this.ewapCliq = ewapCliq;
    this.fairCliq = fairCliq;
    this.klhtCliq = klhtCliq;
    this.klraCliq = klraCliq;
    this.mainimage = mainimage;
  }

  @Override
  @NonNull
  public NestedScrollView getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentAABasicBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentAABasicBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_a_a__basic, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentAABasicBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.advance;
      MaterialCardView advance = ViewBindings.findChildViewById(rootView, id);
      if (advance == null) {
        break missingId;
      }

      id = R.id.b1;
      MaterialCardView b1 = ViewBindings.findChildViewById(rootView, id);
      if (b1 == null) {
        break missingId;
      }

      id = R.id.b10;
      MaterialCardView b10 = ViewBindings.findChildViewById(rootView, id);
      if (b10 == null) {
        break missingId;
      }

      id = R.id.b11;
      MaterialCardView b11 = ViewBindings.findChildViewById(rootView, id);
      if (b11 == null) {
        break missingId;
      }

      id = R.id.b2;
      MaterialCardView b2 = ViewBindings.findChildViewById(rootView, id);
      if (b2 == null) {
        break missingId;
      }

      id = R.id.b3;
      MaterialCardView b3 = ViewBindings.findChildViewById(rootView, id);
      if (b3 == null) {
        break missingId;
      }

      id = R.id.b4;
      MaterialCardView b4 = ViewBindings.findChildViewById(rootView, id);
      if (b4 == null) {
        break missingId;
      }

      id = R.id.b5;
      MaterialCardView b5 = ViewBindings.findChildViewById(rootView, id);
      if (b5 == null) {
        break missingId;
      }

      id = R.id.b6;
      MaterialCardView b6 = ViewBindings.findChildViewById(rootView, id);
      if (b6 == null) {
        break missingId;
      }

      id = R.id.b7;
      MaterialCardView b7 = ViewBindings.findChildViewById(rootView, id);
      if (b7 == null) {
        break missingId;
      }

      id = R.id.b8;
      MaterialCardView b8 = ViewBindings.findChildViewById(rootView, id);
      if (b8 == null) {
        break missingId;
      }

      id = R.id.b9;
      MaterialCardView b9 = ViewBindings.findChildViewById(rootView, id);
      if (b9 == null) {
        break missingId;
      }

      id = R.id.ewap_cliq;
      MaterialCardView ewapCliq = ViewBindings.findChildViewById(rootView, id);
      if (ewapCliq == null) {
        break missingId;
      }

      id = R.id.fair_cliq;
      MaterialCardView fairCliq = ViewBindings.findChildViewById(rootView, id);
      if (fairCliq == null) {
        break missingId;
      }

      id = R.id.klht_cliq;
      MaterialCardView klhtCliq = ViewBindings.findChildViewById(rootView, id);
      if (klhtCliq == null) {
        break missingId;
      }

      id = R.id.klra_cliq;
      MaterialCardView klraCliq = ViewBindings.findChildViewById(rootView, id);
      if (klraCliq == null) {
        break missingId;
      }

      id = R.id.mainimage;
      MaterialCardView mainimage = ViewBindings.findChildViewById(rootView, id);
      if (mainimage == null) {
        break missingId;
      }

      return new FragmentAABasicBinding((NestedScrollView) rootView, advance, b1, b10, b11, b2, b3,
          b4, b5, b6, b7, b8, b9, ewapCliq, fairCliq, klhtCliq, klraCliq, mainimage);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}