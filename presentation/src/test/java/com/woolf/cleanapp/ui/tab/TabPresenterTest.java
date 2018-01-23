package com.woolf.cleanapp.ui.tab;

import com.woolf.cleanapp.CleanAppRule;
import com.woolf.cleanapp.util.Screens;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import ru.terrakok.cicerone.Router;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TabPresenterTest {
    @Rule
    public CleanAppRule cleanAppRule = new CleanAppRule();

    @Mock
    Router localRouter;
    @Mock
    Router globalRouter;
    @Mock
    ITabView tabView;
    @Mock
    ITabView$$State tabViewState;

    TabPresenter tabPresenter;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        tabPresenter = new TabPresenter();
        tabPresenter.attachView(tabView);
        tabPresenter.setViewState(tabViewState);
        tabPresenter.globalRouter = this.globalRouter;
        tabPresenter.router = this.localRouter;
    }

    @Test
    public void shouldOpenMainScreenOnFirstViewAttach() throws Exception {
        tabPresenter.onFirstViewAttach();
        verify(localRouter).replaceScreen(Screens.MAIN);
    }

    @Test
    public void shouldOpenMainScreen() throws Exception {
        tabPresenter.openMainScreen();
        verify(localRouter).replaceScreen(Screens.MAIN);
    }

    @Test
    public void shouldOpenFavoritesScreen() throws Exception {
        tabPresenter.openFavoritesScreen();
        verify(localRouter).replaceScreen(Screens.FAVORITES);
    }

    @Test
    public void shouldOpenInfoScreen() throws Exception {
        tabPresenter.openInfoScreen();
        verify(localRouter).replaceScreen(Screens.INFO);
    }

    @Test
    public void shouldGlobalRouterExitOnBackPressedCall() throws Exception {
        tabPresenter.onBackPressed();
        verify(globalRouter).exit();
    }
}