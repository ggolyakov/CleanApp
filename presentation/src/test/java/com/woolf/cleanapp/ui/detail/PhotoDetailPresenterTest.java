package com.woolf.cleanapp.ui.detail;

import com.woolf.cleanapp.CleanAppRule;
import com.woolf.cleanapp.domain.interactor.AddToFavoritesUseCase;
import com.woolf.cleanapp.domain.interactor.PhotoByIdUseCase;
import com.woolf.cleanapp.domain.interactor.RemoveFromFavoritesUseCase;
import com.woolf.cleanapp.util.helper.ErrorHandler;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import ru.terrakok.cicerone.Router;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PhotoDetailPresenterTest {

    private static final String PHOTO_ID = "100";

    @Rule
    public CleanAppRule cleanAppRule = new CleanAppRule();

    @Mock
    IPhotoDetailView iPhotoDetailView;
    @Mock
    IPhotoDetailView$$State view$$State;

    @Mock
    Router router;
    @Mock
    ErrorHandler errorHandler;

    @Mock
    PhotoByIdUseCase photoByIdUseCase;
    @Mock
    AddToFavoritesUseCase addToFavoritesUseCase;
    @Mock
    RemoveFromFavoritesUseCase removeFromFavoritesUseCase;


    PhotoDetailPresenter detailPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        detailPresenter = new PhotoDetailPresenter(PHOTO_ID);
        detailPresenter.attachView(iPhotoDetailView);
        detailPresenter.setViewState(view$$State);
        detailPresenter.router = router;
        detailPresenter.errorHandler = errorHandler;
        detailPresenter.photoByIdUseCase = photoByIdUseCase;
        detailPresenter.addToFavoritesUseCase = addToFavoritesUseCase;
        detailPresenter.removeFromFavoritesUseCase = removeFromFavoritesUseCase;
    }

    @Test
    public void shouldLoadPhotosOnFirstViewAttach() throws Exception {
        detailPresenter.onFirstViewAttach();
        verify(photoByIdUseCase, times(1)).execute(any(), any());
    }

    @Test
    public void shouldRouterExitOnBackPressedCall() throws Exception {
        detailPresenter.onBackPressed();
        verify(router).exitWithResult(Mockito.anyInt(), Mockito.anyBoolean());
    }

    @Test
    public void shouldLoadPhotosOnReloadCall() throws Exception {
        detailPresenter.reload();
        verify(photoByIdUseCase, times(1)).execute(any(), any());
    }

    @Test
    public void shouldRemovePhotoFromFavoriteOnFavoriteClick() throws Exception {
        detailPresenter.setFavorite(true);
        detailPresenter.onFavoriteClick();
        verify(removeFromFavoritesUseCase,
                times(1)).execute(any(), any());
    }

    @Test
    public void shouldAddPhotoToFavoriteOnFavoriteClick() throws Exception {
        detailPresenter.setFavorite(false);
        detailPresenter.onFavoriteClick();
        verify(addToFavoritesUseCase,
                times(1)).execute(any(), any());
    }


}