package tugasakhir.library.usecase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tugasakhir.library.model.entity.Publisher;
import tugasakhir.library.model.request.publisher.PublisherRq;
import tugasakhir.library.model.request.publisher.UpdatePublisherRq;
import tugasakhir.library.model.response.ResponseInfo;
import tugasakhir.library.repository.PublisherRepository;
import tugasakhir.library.utils.publisher.PublisherMapperImpl;

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
            if (publishers.isEmpty()){
                responseInfo.setBussinessError("Publishers is not found");
                log.info("[{}][FAILED GET ALL PUBLISHERS][DATA SIZE: {}]", getClass().getSimpleName(), publishers.size());
            } else {
                responseInfo.setSuccess(publishers);
                log.info("[{}][SUCCESS GET ALL PUBLISHERS][DATA SIZE: {}]", getClass().getSimpleName(), publishers.size());
            }
        } catch (Exception ex) {
            log.info("[{}][FAILED GET ALL PUBLISHERS][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<List<Publisher>> getAllPublishersByName(String publisherName) {
        ResponseInfo<List<Publisher>> responseInfo = new ResponseInfo<>();

        try {
            List<Publisher> publishers;
            publishers = publisherRepository.getAllPublishersByName(publisherName);
            if (publishers.isEmpty()){
                responseInfo.setBussinessError(publisherName + " is not exist");
                log.info("[{}][FAILED GET ALL PUBLISHERS BY NAME][NAME: {}]", getClass().getSimpleName(), publisherName);
            } else {
                responseInfo.setSuccess(publishers);
                log.info("[{}][SUCCESS GET ALL PUBLISHERS BY NAME][NAME: {}]", getClass().getSimpleName(), publisherName);
            }
        } catch (Exception ex) {
            log.info("[{}][FAILED GET ALL PUBLISHERS BY NAME][NAME: {}][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), publisherName, ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Publisher> getPublisherById(String publisherId) {
        ResponseInfo<Publisher> responseInfo = new ResponseInfo<>();

        try {
            Publisher publisher;
            publisher = publisherRepository.getPublisherById(publisherId);
            if (publisher == null){
                responseInfo.setBussinessError(publisherId + " is not exist");
                log.info("[{}][FAILED GET PUBLISHER][ID: {}]", getClass().getSimpleName(), publisherId);
            } else {
                responseInfo.setSuccess(publisher);
                log.info("[{}][SUCCESS GET PUBLISHER][ID: {}]", getClass().getSimpleName(), publisherId);
            }
        } catch (Exception ex) {
            log.info("[{}][FAILED GET PUBLISHER][ID: {}][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), publisherId, ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Publisher> addNewPublisher(PublisherRq publisherRq) {
        ResponseInfo<Publisher> responseInfo = new ResponseInfo<>();

        try {
            if (publisherRepository.getPublisherByName(publisherRq.getPublisherName()) == null) {
                Publisher publisher;
                String id = publisherRepository.generatePublisherId();
                publisher = PublisherMapperImpl.toPublisher(publisherRq, id);
                publisherRepository.addPublisher(publisher);
                responseInfo.setSuccess(publisher);
                log.info("[{}][SUCCESS ADD NEW PUBLISHER]", getClass().getSimpleName());
            } else {
                responseInfo.setBussinessError(publisherRq.getPublisherName() + " is already exist");
                log.info("[{}][FAILED ADD NEW PUBLISHER]", getClass().getSimpleName());
            }
        } catch (Exception ex) {
            log.info("[{}][FAILED ADD NEW PUBLISHER][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Object> updatePublisher(UpdatePublisherRq updatePublisherRq) {
        ResponseInfo<Object> responseInfo = new ResponseInfo<>();

        try {
            Publisher publisher = publisherRepository.getPublisherById(updatePublisherRq.getPublisherId());
            if (publisher != null) {
                PublisherMapperImpl.updatePublisherFromUpdatePublisherRq(updatePublisherRq, publisher);
                publisherRepository.updatePublisher(publisher);

                responseInfo.setSuccess();
                log.info("[{}][SUCCESS UPDATE PUBLISHER]", getClass().getSimpleName());
            } else {
                responseInfo.setBussinessError(updatePublisherRq.getPublisherName() + " is not exist");
                log.info("[{}][FAILED UPDATE PUBLISHER]", getClass().getSimpleName());
            }
        } catch (Exception ex) {
            log.info("[{}][FAILED UPDATE PUBLISHER][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.handleException(ex);
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
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }

}
