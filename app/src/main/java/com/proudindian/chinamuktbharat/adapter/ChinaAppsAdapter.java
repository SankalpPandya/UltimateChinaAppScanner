package com.proudindian.chinamuktbharat.adapter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.proudindian.chinamuktbharat.R;
import com.proudindian.chinamuktbharat.util.FilterChinaAppsHelper;

import java.util.ArrayList;

public class ChinaAppsAdapter extends RecyclerView.Adapter<ChinaAppsAdapter.ViewHolder> {

    private IAdapterInterface iAdapterInterface;
    private ArrayList<FilterChinaAppsHelper.ApplicationInfo> mDataSet;
    private boolean IsSystemApp;

    public ChinaAppsAdapter(ArrayList<FilterChinaAppsHelper.ApplicationInfo> list, boolean isSystemApp) {
        mDataSet = list;
        IsSystemApp = isSystemApp;
    }

    public void setReference(IAdapterInterface reference) {
        iAdapterInterface = reference;
    }


    @Override
    public ChinaAppsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.app_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        FilterChinaAppsHelper.ApplicationInfo appInfo = (FilterChinaAppsHelper.ApplicationInfo) mDataSet.get(position);
        final String packageName = appInfo.getPname();
        Drawable icon = appInfo.getIcon();
        String label = appInfo.getAppname();
        holder.mTextViewLabel.setText(label);
        holder.mImageViewIcon.setImageDrawable(icon);
        if (IsSystemApp) {
            holder.mImageButtonUninstall.setVisibility(View.GONE);
        } else {
            holder.mImageButtonUninstall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iAdapterInterface.deleteChinaApp(packageName, position);
                }
            });
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView mCardView;
        TextView mTextViewLabel;
        ImageView mImageViewIcon;
        AppCompatImageView mImageButtonUninstall;

        ViewHolder(View v) {
            super(v);
            mCardView = (CardView) v.findViewById(R.id.card_view);
            mTextViewLabel = (TextView) v.findViewById(R.id.app_label);
            mImageViewIcon = (ImageView) v.findViewById(R.id.iv_icon);
            mImageButtonUninstall = (AppCompatImageView) v.findViewById(R.id.ib_uninstall);
        }
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public void UpdateData(ArrayList<FilterChinaAppsHelper.ApplicationInfo> data) {
        mDataSet = null;
        mDataSet = data;
        notifyDataSetChanged();
    }
}
