package com.woolf.cleanapp.util;

import com.woolf.cleanapp.domain.model.PhotoDomainModel;
import com.woolf.cleanapp.ui.detail.PhotoDetailFragment;
import com.woolf.cleanapp.ui.favorites.FavoritesFragment;
import com.woolf.cleanapp.ui.info.InfoFragment;
import com.woolf.cleanapp.ui.photos.PhotosFragment;
import com.woolf.cleanapp.ui.tab.TabFragment;

import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class FragmentFactoryTest {

    @Test
    public void shouldReturnTabFragment() throws Exception {
        String screenName = FragmentFactory.getFragmentByKey(Screens.TAB, null).getClass().getSimpleName();
        Assert.assertEquals(screenName, TabFragment.class.getSimpleName());
    }

    @Test
    public void shouldReturnMainFragment() throws Exception {
        String screenName = FragmentFactory.getFragmentByKey(Screens.MAIN, null).getClass().getSimpleName();
        Assert.assertEquals(screenName, PhotosFragment.class.getSimpleName());
    }

    @Test
    public void shouldReturnInfoFragment() throws Exception {
        String screenName = FragmentFactory.getFragmentByKey(Screens.INFO, null).getClass().getSimpleName();
        Assert.assertEquals(screenName, InfoFragment.class.getSimpleName());
    }

    @Test
    public void shouldReturnFavoritesFragment() throws Exception {
        String screenName = FragmentFactory.getFragmentByKey(Screens.FAVORITES, null).getClass().getSimpleName();
        Assert.assertEquals(screenName, FavoritesFragment.class.getSimpleName());
    }

    @Test
    public void shouldReturnDetailFragment() throws Exception {
        String screenName = FragmentFactory.getFragmentByKey(Screens.DETAIL, mock(PhotoDomainModel.class)).getClass().getSimpleName();
        Assert.assertEquals(screenName, PhotoDetailFragment.class.getSimpleName());
    }

}