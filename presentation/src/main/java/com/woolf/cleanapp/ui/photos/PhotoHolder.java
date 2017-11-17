package com.woolf.cleanapp.ui.photos;


import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.woolf.cleanapp.R;
import com.woolf.cleanapp.base.adapter.AbstractHolder;
import com.woolf.cleanapp.domain.model.PhotoDomainModel;

import butterknife.BindView;

public class PhotoHolder extends AbstractHolder<PhotoDomainModel> {

    @BindView(R.id.sdw_photo)
    SimpleDraweeView sdwPhoto;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    public PhotoHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind() {
        Uri image = Uri.parse(model.getUrls().getSmall());
        sdwPhoto.setImageURI(image);
        tvTitle.setText(model.getUser().getUsername());
    }
}
