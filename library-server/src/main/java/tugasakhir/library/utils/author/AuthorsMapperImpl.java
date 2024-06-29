package tugasakhir.library.utils.author;

import tugasakhir.library.model.entity.Author;
import tugasakhir.library.model.request.author.AuthorRq;
import tugasakhir.library.model.request.author.UpdateAuthorRq;

/**
 * @author Putri Mele
 * on 18/06/2024
 */
public class AuthorsMapperImpl{

    public static Author toAuthor(AuthorRq authorRq, String authorId) {
        if (authorRq == null) {
            return null;
        }
        Author author = new Author();
        author.setAuthorId(authorId);
        author.setAuthorName(authorRq.getAuthorName());
        return author;
    }

    public static void updateAuthorFromUpdateAuthorRq(UpdateAuthorRq updateAuthorRq, Author author) {
        if ( updateAuthorRq == null ) {
            return;
        }

        if ( updateAuthorRq.getAuthorName() != null ) {
            author.setAuthorName( updateAuthorRq.getAuthorName() );
        }
    }
}