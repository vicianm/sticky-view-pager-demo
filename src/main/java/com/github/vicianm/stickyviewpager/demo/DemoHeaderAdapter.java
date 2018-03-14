package com.github.vicianm.stickyviewpager.demo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

public class DemoHeaderAdapter extends RecyclerView.Adapter<DemoHeaderAdapter.ViewHolder> {

    private List<String> dataset;

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

    public DemoHeaderAdapter() {
        this.dataset = new LinkedList<>();
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
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(dataset.get(position));
    }

//    @Override
//    public long getItemId(int position) {
//        return super.getItemId(position);
//    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return dataset.size();
    }

}
