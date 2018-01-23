package com.woolf.cleanapp.ui.photos;

import com.woolf.cleanapp.CleanAppRule;
import com.woolf.cleanapp.domain.interactor.PhotosUseCase;
import com.woolf.cleanapp.domain.model.PhotoDomainModel;
import com.woolf.cleanapp.util.Screens;
import com.woolf.cleanapp.util.helper.ErrorHandler;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import ru.terrakok.cicerone.Router;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PhotosPresenterTest {
    @Rule
    public CleanAppRule cleanAppRule = new CleanAppRule();

    @Mock
    PhotosUseCase photosUseCase;
    @Mock
    IPhotosView iPhotosView;
    @Mock
    IPhotosView$$State view$$State;
    @Mock
    Router router;
    @Mock
    ErrorHandler errorHandler;

    PhotosPresenter photosPresenter;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        photosPresenter = new PhotosPresenter();
        photosPresenter.attachView(iPhotosView);
        photosPresenter.setViewState(view$$State);
        photosPresenter.router = this.router;
        photosPresenter.errorHandler = this.errorHandler;
        photosPresenter.photosUseCase = photosUseCase;
    }

    @Test
    public void shouldLoadPhotosOnFirstViewAttach() throws Exception {
        photosPresenter.onFirstViewAttach();
        verify(photosUseCase,times(1)).execute(any(),any());
    }


    @Test
    public void shouldLoadPhotosOnReloadMethodCall() throws Exception {
        photosPresenter.reload();
        verify(photosUseCase,times(1)).execute(any(),any());
    }

    @Test
    public void shouldOpenDetailScreen() throws Exception {
        PhotoDomainModel model = mock(PhotoDomainModel.class);
        photosPresenter.openDetailScreen(model);
        verify(router).navigateTo(Screens.DETAIL,model);
    }

    @Test
    public void shouldRouterExitOnBackPressedCall() throws Exception {
        photosPresenter.onBackPressed();
        verify(router).exit();
    }

}