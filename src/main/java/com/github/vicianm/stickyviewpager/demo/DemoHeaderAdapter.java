package com.github.vicianm.stickyviewpager.demo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

public class DemoHeaderAdapter extends RecyclerView.Adapter<DemoHeaderAdapter.ViewHolder> {

    private List<String> dataset;

    private Context context;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public ViewHolder(View v) {
            super(v);
            mTextView = v.findViewById(R.id.text_view);
        }
    }

    public DemoHeaderAdapter(Context context) {
        this.dataset = new LinkedList<>();
        this.context = context;
        setHasStableIds(true);
    }

    public void setDataset(List<String> dataset) {
        this.dataset = dataset;
        notifyDataSetChanged();
    }

    public void addDatasetItem(String item) {
        this.dataset.add(item);
        notifyItemInserted(dataset.size()-1);
    }

    public void removeDatasetItem(int position) {
        this.dataset.remove(position);
        notifyItemRemoved(position);
    }

    public List<String> getDataset() {
        return dataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public DemoHeaderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        // create a new view
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.header_item_layout, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return dataset.size();
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        holder.mTextView.setText(dataset.get(position));

        // Here you apply the animation when the view is bound
        setAnimation(holder.itemView, position);
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull ViewHolder holder) {
        holder.mTextView.clearAnimation();
    }

    /**
     * Here is the key method to apply the animation
     */
    private void setAnimation(final View viewToAnimate, int position)
    {
        Log.d("DemoHeaderAdapter", "setAnimation, pos: " + position);
        viewToAnimate.post(new Runnable() {
            @Override
            public void run() {
                Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
                viewToAnimate.startAnimation(animation);
            }
        });
    }

}
