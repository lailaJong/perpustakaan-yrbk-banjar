package tugasakhir.library.usecase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tugasakhir.library.model.entity.Author;
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
            if (authors.isEmpty()){
                responseInfo.setBussinessError("Authors is not found");
                log.info("[{}][SUCCESS GET ALL AUTHOR][DATA SIZE: {}]", getClass().getSimpleName(), authors.size());
            } else {
                responseInfo.setSuccess(authors);
                log.info("[{}][FAILED GET ALL AUTHOR][DATA SIZE: {}]", getClass().getSimpleName(), authors.size());
            }
        } catch (Exception ex) {
            log.info("[{}][FAILED GET ALL AUTHOR][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<List<Author>> getAllAuthorsByName(String authorName) {
        ResponseInfo<List<Author>> responseInfo = new ResponseInfo<>();

        try {
            List <Author> author;
            author = authorRepository.getAllAuthorsByName(authorName);
            if (author.isEmpty()){
                responseInfo.setBussinessError(authorName + " is not exist");
                log.info("[{}][SUCCESS GET AUTHORS BY NAME][{}]", getClass().getSimpleName(), authorName);
            } else {
                responseInfo.setSuccess(author);
                log.info("[{}][FAILED GET AUTHORS BY NAME][{}]", getClass().getSimpleName(), authorName);
            }
        } catch (Exception ex) {
            log.info("[{}][FAILED GET AUTHORS BY NAME][{}][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), authorName, ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Author> getAuthorById(String authorId) {
        ResponseInfo<Author> responseInfo = new ResponseInfo<>();

        try {
            Author author;
            author = authorRepository.getAuthorById(authorId);
            if (author == null){
                responseInfo.setBussinessError(authorId + " is not exist");
                log.info("[{}][SUCCESS GET AUTHOR][ID: {}]", getClass().getSimpleName(), authorId);
            } else {
                responseInfo.setSuccess(author);
                log.info("[{}][FAILED GET AUTHOR][ID: {}]", getClass().getSimpleName(), authorId);
            }
        } catch (Exception ex) {
            log.info("[{}][FAILED GET AUTHOR][ID: {}][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), authorId, ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Author> addNewAuthor(AuthorRq authorRq) {
        ResponseInfo<Author> responseInfo = new ResponseInfo<>();

        try {
            if (authorRepository.getAuthorByName(authorRq.getAuthorName()) == null){
                Author author;
                String authorId = authorRepository.generateAuthorId();
                author = AuthorsMapperImpl.toAuthor(authorRq, authorId);
                authorRepository.addAuthor(author);
                responseInfo.setSuccess(author);
                log.info("[{}][SUCCESS ADD NEW AUTHOR]", getClass().getSimpleName());
            } else {
                responseInfo.setBussinessError(authorRq.getAuthorName() + " is already exist");
                log.info("[{}][FAILED ADD NEW AUTHOR]", getClass().getSimpleName());
            }
        } catch (Exception ex) {
            log.info("[{}][FAILED ADD NEW AUTHOR][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.handleException(ex);
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
                log.info("[{}][SUCCESS UPDATE AUTHOR]", getClass().getSimpleName());
            } else {
                responseInfo.setBussinessError(updateAuthorRq.getAuthorName() + " is not exist");
                log.info("[{}][FAILED UPDATE AUTHOR]", getClass().getSimpleName());
            }
        } catch (Exception ex) {
            log.info("[{}][FAILED UPDATE AUTHOR][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.handleException(ex);
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
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }

}
