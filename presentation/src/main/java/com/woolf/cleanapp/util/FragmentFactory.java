package com.woolf.cleanapp.util;


import android.support.v4.app.Fragment;

import com.woolf.cleanapp.domain.model.PhotoDomainModel;
import com.woolf.cleanapp.ui.detail.PhotoDetailFragment;
import com.woolf.cleanapp.ui.favorites.FavoritesFragment;
import com.woolf.cleanapp.ui.info.InfoFragment;
import com.woolf.cleanapp.ui.photos.PhotosFragment;
import com.woolf.cleanapp.ui.tab.TabFragment;

public class FragmentFactory {

    private FragmentFactory() {
    }

    public static Fragment getFragmentByKey(final String key, final Object data) {
        switch (key) {
            case Screens.TAB:
                return new TabFragment();
            case Screens.MAIN:
                return new PhotosFragment();
            case Screens.INFO:
                return new InfoFragment();
            case Screens.DETAIL:
                PhotoDomainModel model = (PhotoDomainModel) data;
                return PhotoDetailFragment.newInstance(model.getId(), model.getHeight(), model.getWidth());
            case Screens.FAVORITES:
                return new FavoritesFragment();

            default:
                return null;
        }
    }
}
