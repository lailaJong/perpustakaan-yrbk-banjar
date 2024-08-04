//package tugasakhir.library.usecase;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import tugasakhir.library.model.entity.ScoreDetail;
//import tugasakhir.library.model.exception.NotFoundException;
//import tugasakhir.library.model.request.scoredetail.ScoreDetailRq;
//import tugasakhir.library.model.request.scoredetail.UpdateScoreDetailRq;
//import tugasakhir.library.model.response.ResponseInfo;
//import tugasakhir.library.repository.ScoreDetailRepository;
//import tugasakhir.library.utils.scoredetail.ScoreDetailMapperImpl;
//
//import java.util.List;
//
//@Component
//@Slf4j
//public class ScoreDetailUsecase {
//    @Autowired
//    private ScoreDetailRepository scoreDetailRepository;
//
//    public ResponseInfo<List<ScoreDetail>> getAllScoreDetails() {
//        ResponseInfo<List<ScoreDetail>> responseInfo = new ResponseInfo<>();
//
//        try {
//            List<ScoreDetail> scoreDetails;
//            scoreDetails = scoreDetailRepository.getAllScoreDetails();
//            scoreDetails.addAll(scoreDetailRepository.getAllScoreDetails());
//            responseInfo.setSuccess(scoreDetails);
//            log.info("[{}][SUCCESS GET ALL SCORE DETAIL][DATA SIZE: {}]", getClass().getSimpleName(), scoreDetails.size());
//        } catch (Exception ex) {
//            log.info("[{}][FAILED GET ALL SCORE DETAIL][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
//            responseInfo.handleException(ex);
//        }
//        return responseInfo;
//    }
//
//    public ResponseInfo<ScoreDetail> getScoreDetailById(String scoreDetailId) {
//        ResponseInfo<ScoreDetail> responseInfo = new ResponseInfo<>();
//
//        try {
//            ScoreDetail scoreDetail;
//            scoreDetail = scoreDetailRepository.getScoreDetailById(scoreDetailId);
//            responseInfo.setSuccess(scoreDetail);
//            log.info("[{}][SUCCESS GET SCORE DETAIL][ID: {}]", getClass().getSimpleName(), scoreDetailId);
//        } catch (Exception ex) {
//            log.info("[{}][FAILED GET SCORE DETAIL][ID: {}][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), scoreDetailId, ex);
//            responseInfo.handleException(ex);
//        }
//        return responseInfo;
//    }
//
//    public ResponseInfo<ScoreDetail> addNewScoreDetail(ScoreDetailRq scoreDetailRq) {
//        ResponseInfo<ScoreDetail> responseInfo = new ResponseInfo<>();
//
//        try {
//            ScoreDetail scoreDetail;
//            scoreDetailRq.setScoreDetailId(scoreDetailRepository.generateScoreDetailId());
//            scoreDetail = ScoreDetailMapperImpl.toScoreDetail(scoreDetailRq);
//            scoreDetailRepository.addScoreDetail(scoreDetail);
//            responseInfo.setSuccess(scoreDetail);
//            log.info("[{}][SUCCESS ADD NEW SCORE DETAIL]", getClass().getSimpleName());
//        } catch (Exception ex) {
//            log.info("[{}][FAILED ADD NEW SCORE DETAIL][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
//            responseInfo.handleException(ex);
//        }
//        return responseInfo;
//    }
//
//    public ResponseInfo<Object> updateScoreDetail(UpdateScoreDetailRq updateScoreDetailRq) {
//        ResponseInfo<Object> responseInfo = new ResponseInfo<>();
//
//        try {
//            ScoreDetail scoreDetail = scoreDetailRepository.getScoreDetailById(updateScoreDetailRq.getScoreDetailId());
//            if (scoreDetail != null) {
//                ScoreDetailMapperImpl.updateScoreDetailFromUpdateScoreDetailRq(updateScoreDetailRq, scoreDetail);
//                scoreDetailRepository.updateScoreDetail(scoreDetail);
//
//                responseInfo.setSuccess();
//            } else {
//                throw new NotFoundException();
//            }
//            log.info("[{}][SUCCESS UPDATE SCORE DETAIL]", getClass().getSimpleName());
//        } catch (Exception ex) {
//            log.info("[{}][FAILED UPDATE SCORE DETAIL][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
//            responseInfo.handleException(ex);
//        }
//        return responseInfo;
//    }
//
//
//    public ResponseInfo<Object> deleteScoreDetail(String scoreDetailId) {
//        ResponseInfo<Object> responseInfo = new ResponseInfo<>();
//
//        try {
//            scoreDetailRepository.deleteScoreDetail(scoreDetailId);
//            responseInfo.setSuccess();
//            log.info("[{}][SUCCESS DELETE SCORE DETAIL][{}]", getClass().getSimpleName(), scoreDetailId);
//        } catch (Exception ex) {
//            log.info("[{}][FAILED DELETE SCORE DETAIL][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
//            responseInfo.handleException(ex);
//        }
//        return responseInfo;
//    }
//
//}
