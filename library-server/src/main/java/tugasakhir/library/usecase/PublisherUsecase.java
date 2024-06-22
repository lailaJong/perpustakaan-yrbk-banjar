package tugasakhir.library.usecase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tugasakhir.library.model.entity.Publisher;
import tugasakhir.library.model.exception.NotFoundException;
import tugasakhir.library.model.request.publisher.PublisherRq;
import tugasakhir.library.model.request.publisher.UpdatePublisherRq;
import tugasakhir.library.model.response.ResponseInfo;
import tugasakhir.library.repository.PublisherRepository;
import tugasakhir.library.utils.publisher.PublisherMapper;

import java.util.List;

@Component
@Slf4j
public class PublisherUsecase {
    @Autowired
    private PublisherRepository publisherRepository;

    public ResponseInfo<List<Publisher>> getAllPublishers() {
        ResponseInfo<List<Publisher>> responseInfo = new ResponseInfo<>();

        try {
            List<Publisher> publishers;
            publishers = publisherRepository.getAllPublishers();
            publishers.addAll(publisherRepository.getAllPublishers());
            responseInfo.setSuccess(publishers);
            log.info("[{}][SUCCESS GET ALL PUBLISHER][DATA SIZE: {}]", getClass().getSimpleName(), publishers.size());
        } catch (Exception ex) {
            log.info("[{}][FAILED GET ALL PUBLISHER][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Publisher> getPublisherById(String publisherId) {
        ResponseInfo<Publisher> responseInfo = new ResponseInfo<>();

        try {
            Publisher publisher;
            publisher = publisherRepository.getPublisherById(publisherId);
            responseInfo.setSuccess(publisher);
            log.info("[{}][SUCCESS GET PUBLISHER][ID: {}]", getClass().getSimpleName(), publisherId);
        } catch (Exception ex) {
            log.info("[{}][FAILED GET PUBLISHER][ID: {}][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), publisherId, ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Publisher> getPublisherByName(String publisherName) {
        ResponseInfo<Publisher> responseInfo = new ResponseInfo<>();

        try {
            Publisher publisher;
            publisher = publisherRepository.getPublisherByName(publisherName);
            responseInfo.setSuccess(publisher);
            log.info("[{}][SUCCESS GET PUBLISHER][NAME: {}]", getClass().getSimpleName(), publisherName);
        } catch (Exception ex) {
            log.info("[{}][FAILED GET PUBLISHER][NAME: {}][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), publisherName, ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Publisher> addNewPublisher(PublisherRq publisherRq) {
        ResponseInfo<Publisher> responseInfo = new ResponseInfo<>();

        try {
            Publisher publisher;
            publisherRq.setPublisherId(publisherRepository.generatePublisherId());
            publisher = PublisherMapper.INSTANCE.toPublisher(publisherRq);
            publisherRepository.addPublisher(publisher);
            responseInfo.setSuccess(publisher);
            log.info("[{}][SUCCESS ADD NEW PUBLISHER]", getClass().getSimpleName());
        } catch (Exception ex) {
            log.info("[{}][FAILED ADD NEW PUBLISHER][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Object> updatePublisher(UpdatePublisherRq updatePublisherRq) {
        ResponseInfo<Object> responseInfo = new ResponseInfo<>();

        try {
            Publisher publisher = publisherRepository.getPublisherById(updatePublisherRq.getPublisherId());
            if (publisher != null) {
                PublisherMapper.INSTANCE.updatePublisherFromUpdatePublisherRq(updatePublisherRq, publisher);
                publisherRepository.updatePublisher(publisher);

                responseInfo.setSuccess();
            } else {
                throw new NotFoundException();
            }
            log.info("[{}][SUCCESS UPDATE PUBLISHER]", getClass().getSimpleName());
        } catch (Exception ex) {
            log.info("[{}][FAILED UPDATE PUBLISHER][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }


    public ResponseInfo<Object> deletePublisher(String publisherId) {
        ResponseInfo<Object> responseInfo = new ResponseInfo<>();

        try {
            publisherRepository.deletePublisher(publisherId);
            responseInfo.setSuccess();
            log.info("[{}][SUCCESS DELETE PUBLISHER][{}]", getClass().getSimpleName(), publisherId);
        } catch (Exception ex) {
            log.info("[{}][FAILED DELETE PUBLISHER][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

}
