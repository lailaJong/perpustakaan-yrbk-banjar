package tugasakhir.library.utils.publisher;

import tugasakhir.library.model.entity.Author;
import tugasakhir.library.model.entity.Publisher;
import tugasakhir.library.model.request.author.AuthorRq;
import tugasakhir.library.model.request.author.UpdateAuthorRq;
import tugasakhir.library.model.request.publisher.PublisherRq;
import tugasakhir.library.model.request.publisher.UpdatePublisherRq;

/**
 * @author Putri Mele
 * on 18/06/2024
 */
public class PublisherMapperImpl implements PublisherMapper {

    @Override
    public Publisher toPublisher(PublisherRq publisherRq) {
        if (publisherRq == null) {
            return null;
        }
        Publisher publisher = new Publisher();
        publisher.setPublisherId(publisherRq.getPublisherId());
        publisher.setPublisherName(publisherRq.getPublisherName());
        return publisher;
    }

    @Override
    public void updatePublisherFromUpdatePublisherRq(UpdatePublisherRq updatePublisherRq, Publisher publisher) {
        if ( updatePublisherRq == null ) {
            return;
        }

        if ( updatePublisherRq.getPublisherName() != null ) {
            publisher.setPublisherName( updatePublisherRq.getPublisherName() );
        }
    }
}