package tugasakhir.library.utils.author;

import tugasakhir.library.model.entity.Author;
import tugasakhir.library.model.request.author.AuthorRq;
import tugasakhir.library.model.request.author.UpdateAuthorRq;

/**
 * @author Putri Mele
 * on 18/06/2024
 */
public class AuthorsMapperImpl implements AuthorsMapper {

    @Override
    public Author toAuthor(AuthorRq authorRq) {
        if (authorRq == null) {
            return null;
        }
        Author author = new Author();
        author.setAuthorId(authorRq.getAuthorId());
        author.setAuthorName(authorRq.getAuthorName());
        return author;
    }

    @Override
    public void updateAuthorFromUpdateAuthorRq(UpdateAuthorRq updateAuthorRq, Author author) {
        if ( updateAuthorRq == null ) {
            return;
        }

        if ( updateAuthorRq.getAuthorName() != null ) {
            author.setAuthorName( updateAuthorRq.getAuthorName() );
        }
    }
}