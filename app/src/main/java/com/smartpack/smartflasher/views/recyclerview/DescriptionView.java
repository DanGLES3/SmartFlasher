/*
 * Copyright (C) 2015-2016 Willi Ye <williye97@gmail.com>
 *
 * This file is part of Kernel Adiutor.
 *
 * Kernel Adiutor is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Kernel Adiutor is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Kernel Adiutor.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.smartpack.smartflasher.views.recyclerview;

import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.smartpack.smartflasher.R;

/**
 * Created by willi on 17.04.16.
 */
public class DescriptionView extends RecyclerViewItem {

    private View mRootView;
    private AppCompatImageView mImageView;
    private AppCompatTextView mTitleView;
    private AppCompatTextView mSummaryView;

    private Drawable mImage;
    private CharSequence mTitle;
    private CharSequence mSummary;

    private boolean mGrxIsInitSelected = false;
    private int mGrxColor = 0;

    @Override
    public int getLayoutRes() {
        return R.layout.rv_description_view;
    }

    @Override
    public void onCreateView(View view) {
        mRootView = view;
        mImageView = (AppCompatImageView) view.findViewById(R.id.image);
        mTitleView = (AppCompatTextView) view.findViewById(R.id.title);
        mSummaryView = (AppCompatTextView) view.findViewById(R.id.summary);
        if(mGrxIsInitSelected) this.setTextColor(mGrxColor);

        if (mTitleView != null) {
            mTitleView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        mRootView.requestFocus();
                    }
                }
            });
        }
        if (mSummaryView != null) {
            mSummaryView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        mRootView.requestFocus();
                    }
                }
            });
        }

        super.onCreateView(view);
    }

    public void setDrawable(Drawable drawable) {
        mImage = drawable;
        refresh();
    }

    public void setTitle(CharSequence title) {
        mTitle = title;
        refresh();
    }

    public void setSummary(CharSequence summary) {
        mSummary = summary;
        refresh();
    }

    public void setTextColor(int color) {
        mSummaryView.setTextColor(color);
    }

    public CharSequence getTitle() {
        return mTitle;
    }

    @Override
    protected void refresh() {
        super.refresh();
        if (mImageView != null && mImage != null) {
            mImageView.setImageDrawable(mImage);
            mImageView.setVisibility(View.VISIBLE);
        }
        if (mTitleView != null) {
            if (mTitle != null) {
                mTitleView.setText(mTitle);
                mTitleView.setVisibility(View.VISIBLE);
            } else {
                mTitleView.setVisibility(View.GONE);
            }
        }
        if (mSummaryView != null && mSummary != null) {
            mSummaryView.setText(mSummary);
        }
        if (mRootView != null && getOnItemClickListener() != null && mTitleView != null
                && mSummaryView != null) {
            mTitleView.setTextIsSelectable(false);
            mSummaryView.setTextIsSelectable(false);
            mRootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getOnItemClickListener() != null) {
                        getOnItemClickListener().onClick(DescriptionView.this);
                    }
                }
            });
        }
    }
}
