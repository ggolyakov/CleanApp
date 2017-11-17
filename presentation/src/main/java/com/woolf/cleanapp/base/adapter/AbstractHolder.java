package com.woolf.cleanapp.base.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by Server Kurtnebiev on 02.03.2017.
 * Core
 */

public abstract class AbstractHolder<DATA> extends RecyclerView.ViewHolder {

    protected DATA model;
    protected IItemClickListener<DATA> clickListener;

    public AbstractHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    /**
     * Method for assigning data in view component
     */
    public abstract void bind();


    /**
     * Method for assigning data on holder
     *
     * @param model
     */
    public void setData(DATA model) {
        this.model = model;
        bind();
    }

    /**
     * Method for assigning click actions to the base view
     *
     * @param clickListener @see {@link IItemClickListener}
     */
    public void setOnItemClick(final IItemClickListener<DATA> clickListener) {
        itemView.setOnClickListener(view -> {
            if (clickListener != null) {
                this.clickListener = clickListener;
                clickListener.onItemClick(model);
            }
        });
    }

    /**
     * Method for assigning long click actions to the item view
     *
     * @param clickListener @see {@link IItemLongClickListener}
     */
    public void setOnLongItemClick(final IItemLongClickListener<DATA> clickListener) {
        itemView.setOnLongClickListener(v -> {
            if (clickListener != null) {
                clickListener.onItemLongClick(model);
                return true;
            }
            return false;
        });
    }
}