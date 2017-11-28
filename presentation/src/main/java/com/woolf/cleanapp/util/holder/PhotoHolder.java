package com.woolf.cleanapp.util.holder;


import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.woolf.cleanapp.R;
import com.woolf.cleanapp.base.adapter.AbstractHolder;
import com.woolf.cleanapp.domain.model.PhotoDomainModel;

import butterknife.BindView;

public class PhotoHolder extends AbstractHolder<PhotoDomainModel> {

    @BindView(R.id.sdv_photo)
    protected SimpleDraweeView sdvPhoto;
    @BindView(R.id.sdv_avatar)
    protected SimpleDraweeView sdvAvatar;
    @BindView(R.id.tv_title)
    protected TextView tvTitle;
    @BindView(R.id.tv_likes)
    protected TextView tvLikes;

    public PhotoHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind() {
        Uri image = Uri.parse(model.getUrls().getSmall());
        Uri avatar = Uri.parse(model.getUser().getProfileImage().getMedium());
        sdvPhoto.setImageURI(image);
        sdvAvatar.setImageURI(avatar);
        tvTitle.setText(getUsername());
        tvLikes.setText(String.valueOf(model.getLikes()));
    }

    private String getUsername() {
        return model.getUser().getFirstName() + " " + model.getUser().getLastName();
    }

}
