package com.woolf.cleanapp.ui.main;

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
public class MainPresenterTest {

    @Rule
    public CleanAppRule cleanAppRule = new CleanAppRule();

    @Mock
    Router router;
    @Mock
    IMainView iMainView;


    MainPresenter mainPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mainPresenter = new MainPresenter();
        mainPresenter.attachView(iMainView);
        mainPresenter.router = router;
    }

    @Test
    public void shouldOpenMainScreenOnFirstViewAttach() throws Exception {
        mainPresenter.onFirstViewAttach();
        verify(router).newRootScreen(Screens.TAB);
    }

}