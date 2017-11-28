package com.woolf.cleanapp.base.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public abstract class AbstractRecyclerViewAdapter<DATA, HOLDER extends AbstractHolder<DATA>>
        extends RecyclerView.Adapter<HOLDER> {

    protected int resource;
    protected List<DATA> data;
    protected LayoutInflater inflater;
    protected IItemClickListener<DATA> clickListener;
    protected IItemLongClickListener<DATA> longClickListener;

    public AbstractRecyclerViewAdapter(int resource, List<DATA> data) {
        this.resource = resource;
        this.data = data;
    }

    public AbstractRecyclerViewAdapter(int resource) {
        this.resource = resource;
    }

    @Override
    public HOLDER onCreateViewHolder(ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(parent.getContext());
        HOLDER holder = onInitViewHolder(parent);
        holder.setOnItemClick(clickListener);
        holder.setOnLongItemClick(longClickListener);
        return holder;
    }

    /**
     * @param parent @see {@link ViewGroup}
     * @return holder @see {@link AbstractHolder}
     */

    public HOLDER onInitViewHolder(ViewGroup parent) {
        return null;
    }


    @Override
    public void onBindViewHolder(HOLDER holder, int position) {
        holder.setData(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }


    /**
     * Method set list data on adapter @see {@link AbstractRecyclerViewAdapter}
     *
     * @param data @see {@link List}
     */
    public void setData(List<DATA> data) {
        if (this.data == null) {
            this.data = data;
        } else {
            this.data.addAll(data);
        }
        notifyDataSetChanged();
    }

    /**
     * Method Replaces data in adapter. Old data will removeFromFavorites
     *
     * @param data @see {@link List}
     */
    public void swap(List<DATA> data) {
        if (this.data == null) {
            this.data = new ArrayList<>();
        } else {
            this.data.clear();
        }
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * Method removeFromFavorites unit by data on adapter @see {@link AbstractRecyclerViewAdapter}
     *
     * @param item DATA
     */

    public void remove(DATA item) {
        int position = data.indexOf(item);
        if (position != -1) {
            data.remove(position);
            notifyItemRemoved(position);
        }
    }

    /**
     * Method clear data on adapter
     */
    public void clearData() {
        if (data != null) data.clear();
        notifyDataSetChanged();
    }

    /**
     * Method for assigning click actions to the item view
     *
     * @param clickListener @see {@link IItemLongClickListener}
     */
    public void setClickListener(IItemClickListener<DATA> clickListener) {
        this.clickListener = clickListener;
    }

    /**
     * Method for assigning long click actions to the item view
     *
     * @param clickListener @see {@link IItemLongClickListener}
     */
    public void setLongClickListener(IItemLongClickListener<DATA> clickListener) {
        this.longClickListener = clickListener;
    }


    /**
     * Method return true if data null or empty
     *
     * @return boolean
     */
    public boolean isEmpty() {
        return data == null || data.isEmpty();
    }
}