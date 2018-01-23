package com.woolf.cleanapp.ui.favorites;

import com.woolf.cleanapp.CleanAppRule;
import com.woolf.cleanapp.domain.interactor.FavoritesUseCase;
import com.woolf.cleanapp.domain.model.PhotoDomainModel;
import com.woolf.cleanapp.util.Screens;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import ru.terrakok.cicerone.Router;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class FavoritesPresenterTest {

    @Rule
    public CleanAppRule cleanAppRule = new CleanAppRule();

    @Mock
    FavoritesUseCase favoritesUseCase;
    @Mock
    Router router;
    @Mock
    IFavoritesView iFavoritesView;
    @Mock
    IFavoritesView$$State iFavoritesView$$State;

    @Mock
    PhotoDomainModel model;


    FavoritesPresenter favoritesPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        favoritesPresenter = new FavoritesPresenter();
        favoritesPresenter.attachView(iFavoritesView);
        favoritesPresenter.setViewState(iFavoritesView$$State);
        favoritesPresenter.favoritesUseCase = favoritesUseCase;
        favoritesPresenter.router = router;
    }


    @Test
    public void shouldLoadFavoritesOnFirstViewAttach() throws Exception {
        favoritesPresenter.onFirstViewAttach();
        verify(favoritesUseCase,times(1)).execute(any());
    }

    @Test
    public void shouldLoadFavoritesOnReloadCall() throws Exception {
        favoritesPresenter.reload();
        verify(favoritesUseCase,times(1)).execute(any());
    }
    @Test
    public void shouldOpenDetailScreen() throws Exception {
        favoritesPresenter.openDetailScreen(model);
        verify(router).newScreenChain(Screens.DETAIL,model);
    }

    @Test
    public void shouldRouterExitOnBackPressedCall() throws Exception {
        favoritesPresenter.onBackPressed();
        verify(router).exit();
    }

}