package tugasakhir.library.usecase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tugasakhir.library.model.entity.Author;
import tugasakhir.library.model.entity.BookPlacement;
import tugasakhir.library.model.exception.NotFoundException;
import tugasakhir.library.model.request.author.AuthorRq;
import tugasakhir.library.model.request.author.UpdateAuthorRq;
import tugasakhir.library.model.request.bookplacement.BookPlacementRq;
import tugasakhir.library.model.request.bookplacement.UpdateBookPlacementRq;
import tugasakhir.library.model.response.ResponseInfo;
import tugasakhir.library.repository.BookPlacementRepository;
import tugasakhir.library.utils.author.AuthorsMapper;
import tugasakhir.library.utils.bookplacement.BookPlacementsMapper;

import java.util.List;

@Component
@Slf4j
public class BookPlacementUsecase {
    @Autowired
    private BookPlacementRepository bookPlacementRepository;

    public ResponseInfo<List<BookPlacement>> getAllBookPlacements() {
        ResponseInfo<List<BookPlacement>> responseInfo = new ResponseInfo<>();

        try {
            List<BookPlacement> bookPlacements;
            bookPlacements = bookPlacementRepository.getAllBookPlacements();
            bookPlacements.addAll(bookPlacementRepository.getAllBookPlacements());
            responseInfo.setSuccess(bookPlacements);
            log.info("[{}][SUCCESS GET ALL BOOK PLACEMENT][DATA SIZE: {}]", getClass().getSimpleName(), bookPlacements.size());
        } catch (Exception ex) {
            log.info("[{}][FAILED GET ALL BOOK PLACEMENT][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<BookPlacement> getBookPlacementById(String bookPlacementId) {
        ResponseInfo<BookPlacement> responseInfo = new ResponseInfo<>();

        try {
            BookPlacement bookPlacement;
            bookPlacement = bookPlacementRepository.getBookPlacementById(bookPlacementId);
            responseInfo.setSuccess(bookPlacement);
            log.info("[{}][SUCCESS GET BOOK PLACEMENT][ID: {}]", getClass().getSimpleName(), bookPlacementId);
        } catch (Exception ex) {
            log.info("[{}][FAILED GET BOOK PLACEMENT][ID: {}][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), bookPlacementId, ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<BookPlacement> addNewBookPlacement(BookPlacementRq bookPlacementRq) {
        ResponseInfo<BookPlacement> responseInfo = new ResponseInfo<>();

        try {
            BookPlacement bookPlacement;
            bookPlacementRq.setBookPlacementId(bookPlacementRepository.generateBookPlacementId());
            bookPlacement = BookPlacementsMapper.INSTANCE.toBookPlacement(bookPlacementRq);
            bookPlacementRepository.addBookPlacement(bookPlacement);
            responseInfo.setSuccess(bookPlacement);
            log.info("[{}][SUCCESS ADD NEW BOOK PLACEMENT]", getClass().getSimpleName());
        } catch (Exception ex) {
            log.info("[{}][FAILED ADD NEW BOOK PLACEMENT][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Object> updateBookPlacement(UpdateBookPlacementRq updateBookPlacementRq) {
        ResponseInfo<Object> responseInfo = new ResponseInfo<>();

        try {
            BookPlacement bookPlacement = bookPlacementRepository.getBookPlacementById(updateBookPlacementRq.getBookPlacementId());
            if (bookPlacement != null) {
                BookPlacementsMapper.INSTANCE.updateBookPlacementFromUpdateBookPlacementRq(updateBookPlacementRq, bookPlacement);
                bookPlacementRepository.updateBookPlacement(bookPlacement);

                responseInfo.setSuccess();
            } else {
                throw new NotFoundException();
            }
            log.info("[{}][SUCCESS UPDATE BOOK PLACEMENT]", getClass().getSimpleName());
        } catch (Exception ex) {
            log.info("[{}][FAILED UPDATE BOOK PLACEMENT][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }


    public ResponseInfo<Object> deleteBookPlacement(String bookPlacementId) {
        ResponseInfo<Object> responseInfo = new ResponseInfo<>();

        try {
            bookPlacementRepository.deleteBookPlacement(bookPlacementId);
            responseInfo.setSuccess();
            log.info("[{}][SUCCESS DELETE BOOK PLACEMENT][{}]", getClass().getSimpleName(), bookPlacementId);
        } catch (Exception ex) {
            log.info("[{}][FAILED DELETE BOOK PLACEMENT][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

}
