package com.mtelnet.myview.zoomanim;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mtelnet.myview.R;

import java.util.List;

/**
 * Describe
 * Author Joe
 * created at 16/11/25.
 */

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedViewHolder> {
    private List<FeedItem> mItems;
    private Context mContext;
    private OnItemClickListener mListener;

    public FeedAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<FeedItem> list) {
        if (list == null) return;
        mItems = list;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }
    @Override
    public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_feed, null);
        return new FeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FeedViewHolder holder,  int position) {
        final int tempPos = position;
        final FeedItem item = mItems.get(position);
        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(v, tempPos, item);
                }
            }
        });
        int width = mContext.getResources().getDisplayMetrics().widthPixels/2;
        int height = (int) (width*(item.getHeight()*1f/item.getWidth()));
        Glide.with(mContext)
                .load(item.getImageUrl())
//                .resize(width, height)//picas
                .override(width, height) // resizes the image to these dimensions (in pixel). does not respect aspect ratio
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mItems == null ? 0:mItems.size();
    }


    class FeedViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        FeedViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_feed);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position, FeedItem item);
    }

}
