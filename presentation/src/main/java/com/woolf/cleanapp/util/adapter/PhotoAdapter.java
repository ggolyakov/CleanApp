package com.woolf.cleanapp.util.adapter;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.woolf.cleanapp.R;
import com.woolf.cleanapp.base.adapter.AbstractRecyclerViewAdapter;
import com.woolf.cleanapp.domain.model.PhotoDomainModel;
import com.woolf.cleanapp.util.holder.PhotoHolder;

public class PhotoAdapter extends AbstractRecyclerViewAdapter<PhotoDomainModel, PhotoHolder> {

    public PhotoAdapter() {
        super(R.layout.item_photo);
    }

    @Override
    public PhotoHolder onInitViewHolder(ViewGroup parent) {
        return new PhotoHolder(LayoutInflater.from(parent.getContext()).inflate(resource, parent, false));
    }
}
