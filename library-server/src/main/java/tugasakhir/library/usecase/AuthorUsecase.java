package tugasakhir.library.usecase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tugasakhir.library.model.entity.Author;
import tugasakhir.library.model.exception.NotFoundException;
import tugasakhir.library.model.request.author.AuthorRq;
import tugasakhir.library.model.request.author.UpdateAuthorRq;
import tugasakhir.library.model.response.ResponseInfo;
import tugasakhir.library.repository.AuthorRepository;
import tugasakhir.library.utils.author.AuthorsMapperImpl;

import java.util.List;

@Component
@Slf4j
public class AuthorUsecase {
    @Autowired
    private AuthorRepository authorRepository;

    public ResponseInfo<List<Author>> getAllAuthors() {
        ResponseInfo<List<Author>> responseInfo = new ResponseInfo<>();

        try {
            List<Author> authors;
            authors = authorRepository.getAllAuthors();
            authors.addAll(authorRepository.getAllAuthors());
            responseInfo.setSuccess(authors);
            log.info("[{}][SUCCESS GET ALL AUTHOR][DATA SIZE: {}]", getClass().getSimpleName(), authors.size());
        } catch (Exception ex) {
            log.info("[{}][FAILED GET ALL AUTHOR][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Author> getAuthorById(String authorId) {
        ResponseInfo<Author> responseInfo = new ResponseInfo<>();

        try {
            Author author;
            author = authorRepository.getAuthorById(authorId);
            responseInfo.setSuccess(author);
            log.info("[{}][SUCCESS GET AUTHOR][ID: {}]", getClass().getSimpleName(), authorId);
        } catch (Exception ex) {
            log.info("[{}][FAILED GET AUTHOR][ID: {}][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), authorId, ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<List<Author>> getAuthorsByName(String authorName) {
        ResponseInfo<List<Author>> responseInfo = new ResponseInfo<>();

        try {
            List <Author> author;
            author = authorRepository.getAllAuthorsByName(authorName);
            responseInfo.setSuccess(author);
            log.info("[{}][SUCCESS GET AUTHORS BY NAME][{}]", getClass().getSimpleName(), authorName);
        } catch (Exception ex) {
            log.info("[{}][FAILED GET AUTHORS BY NAME][{}][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), authorName, ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Author> addNewAuthor(AuthorRq authorRq) {
        ResponseInfo<Author> responseInfo = new ResponseInfo<>();

        try {
            Author author;
            authorRq.setAuthorId(authorRepository.generateAuthorId());
            author = AuthorsMapperImpl.toAuthor(authorRq);
            authorRepository.addAuthor(author);
            responseInfo.setSuccess(author);
            log.info("[{}][SUCCESS ADD NEW AUTHOR]", getClass().getSimpleName());
        } catch (Exception ex) {
            log.info("[{}][FAILED ADD NEW AUTHOR][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Object> updateAuthor(UpdateAuthorRq updateAuthorRq) {
        ResponseInfo<Object> responseInfo = new ResponseInfo<>();

        try {
            Author author = authorRepository.getAuthorById(updateAuthorRq.getAuthorId());
            if (author != null) {
                AuthorsMapperImpl.updateAuthorFromUpdateAuthorRq(updateAuthorRq, author);
                authorRepository.updateAuthor(author);

                responseInfo.setSuccess();
            } else {
                throw new NotFoundException();
            }
            log.info("[{}][SUCCESS UPDATE AUTHOR]", getClass().getSimpleName());
        } catch (Exception ex) {
            log.info("[{}][FAILED UPDATE AUTHOR][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }


    public ResponseInfo<Object> deleteAuthor(String authorId) {
        ResponseInfo<Object> responseInfo = new ResponseInfo<>();

        try {
            authorRepository.deleteAuthor(authorId);
            responseInfo.setSuccess();
            log.info("[{}][SUCCESS DELETE AUTHOR][{}]", getClass().getSimpleName(), authorId);
        } catch (Exception ex) {
            log.info("[{}][FAILED DELETE AUTHOR][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

}
