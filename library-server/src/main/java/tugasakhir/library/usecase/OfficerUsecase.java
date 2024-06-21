package tugasakhir.library.usecase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tugasakhir.library.model.entity.Officer;
import tugasakhir.library.model.exception.NotFoundException;
import tugasakhir.library.model.request.officer.OfficerRq;
import tugasakhir.library.model.request.officer.UpdateOfficerRq;
import tugasakhir.library.model.response.ResponseInfo;
import tugasakhir.library.repository.OfficerRepository;
import tugasakhir.library.utils.officer.OfficerMapper;

import java.util.List;

@Component
@Slf4j
public class OfficerUsecase {
    @Autowired
    private OfficerRepository officerRepository;

    public ResponseInfo<List<Officer>> getAllOfficers() {
        ResponseInfo<List<Officer>> responseInfo = new ResponseInfo<>();

        try {
            List<Officer> officers;
            officers = officerRepository.getAllOfficers();
            officers.addAll(officerRepository.getAllOfficers());
            responseInfo.setSuccess(officers);
            log.info("[{}][SUCCESS GET ALL OFFICER][DATA SIZE: {}]", getClass().getSimpleName(), officers.size());
        } catch (Exception ex) {
            log.info("[{}][FAILED GET ALL OFFICER][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Officer> getOfficerById(String officerId) {
        ResponseInfo<Officer> responseInfo = new ResponseInfo<>();

        try {
            Officer officer;
            officer = officerRepository.getOfficerById(officerId);
            responseInfo.setSuccess(officer);
            log.info("[{}][SUCCESS GET OFFICER][ID: {}]", getClass().getSimpleName(), officerId);
        } catch (Exception ex) {
            log.info("[{}][FAILED GET OFFICER][ID: {}][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), officerId, ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Officer> addNewOfficer(OfficerRq officerRq) {
        ResponseInfo<Officer> responseInfo = new ResponseInfo<>();

        try {
            Officer officer;
            officerRq.setOfficerId(officerRepository.generateOfficerId());
            officer = OfficerMapper.INSTANCE.toOfficer(officerRq);
            officerRepository.addOfficer(officer);
            responseInfo.setSuccess(officer);
            log.info("[{}][SUCCESS ADD NEW OFFICER]", getClass().getSimpleName());
        } catch (Exception ex) {
            log.info("[{}][FAILED ADD NEW OFFICER][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Object> updateOfficer(UpdateOfficerRq updateOfficerRq) {
        ResponseInfo<Object> responseInfo = new ResponseInfo<>();

        try {
            Officer officer = officerRepository.getOfficerById(updateOfficerRq.getOfficerId());
            if (officer != null) {
                OfficerMapper.INSTANCE.updateOfficerFromUpdateOfficerRq(updateOfficerRq, officer);
                officerRepository.updateOfficer(officer);

                responseInfo.setSuccess();
            } else {
                throw new NotFoundException();
            }
            log.info("[{}][SUCCESS UPDATE OFFICER]", getClass().getSimpleName());
        } catch (Exception ex) {
            log.info("[{}][FAILED UPDATE OFFICER][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }


    public ResponseInfo<Object> deleteOfficer(String officerId) {
        ResponseInfo<Object> responseInfo = new ResponseInfo<>();

        try {
            officerRepository.deleteOfficer(officerId);
            responseInfo.setSuccess();
            log.info("[{}][SUCCESS DELETE OFFICER][{}]", getClass().getSimpleName(), officerId);
        } catch (Exception ex) {
            log.info("[{}][FAILED DELETE OFFICER][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

}
