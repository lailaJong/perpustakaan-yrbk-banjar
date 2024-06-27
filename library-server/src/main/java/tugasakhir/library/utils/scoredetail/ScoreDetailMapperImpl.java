package tugasakhir.library.utils.scoredetail;

import tugasakhir.library.model.entity.Author;
import tugasakhir.library.model.entity.ScoreDetail;
import tugasakhir.library.model.request.author.AuthorRq;
import tugasakhir.library.model.request.author.UpdateAuthorRq;
import tugasakhir.library.model.request.scoredetail.ScoreDetailRq;
import tugasakhir.library.model.request.scoredetail.UpdateScoreDetailRq;

/**
 * @author Putri Mele
 * on 18/06/2024
 */
public class ScoreDetailMapperImpl{
    public static ScoreDetail toScoreDetail(ScoreDetailRq scoreDetailRq) {
        if (scoreDetailRq == null) {
            return null;
        }
        ScoreDetail scoreDetail = new ScoreDetail();
        scoreDetail.setScoreDetailId(scoreDetailRq.getScoreDetailId());
        scoreDetail.setExtraBorrowTime(scoreDetailRq.getExtraBorrowTime());
        scoreDetail.setExtraBooksQuota(scoreDetailRq.getExtraBooksQuota());
        return scoreDetail;
    }

    public static void updateScoreDetailFromUpdateScoreDetailRq(UpdateScoreDetailRq updateScoreDetailRq, ScoreDetail scoreDetail) {
        if ( updateScoreDetailRq == null ) {
            return;
        }

        if ( updateScoreDetailRq.getExtraBorrowTime() != -1 ) {
            scoreDetail.setExtraBorrowTime( updateScoreDetailRq.getExtraBorrowTime() );
        }
        if ( updateScoreDetailRq.getExtraBooksQuota() != -1 ) {
            scoreDetail.setExtraBooksQuota( updateScoreDetailRq.getExtraBooksQuota() );
        }
    }

    public static String getScoreDetailId(int point){
        String scoreDetailId;
        if (point < 0){
            scoreDetailId = "SCR00";
        }
        else if (point > 1 && point <= 10){
            scoreDetailId = "SCR01";
        }
        else if (point > 10 && point <= 30){
            scoreDetailId = "SCR02";
        } else if (point <= 50) {
            scoreDetailId = "SCR03";
        } else if (point <= 70) {
            scoreDetailId = "SCR04";
        } else if (point <= 100) {
            scoreDetailId = "SCR05";
        } else if (point <= 120) {
            scoreDetailId = "SCR06";
        } else if (point <= 150) {
            scoreDetailId = "SCR07";
        } else if (point <= 170) {
            scoreDetailId = "SCR08";
        } else if (point <= 200) {
            scoreDetailId = "SCR09";
        } else {
            scoreDetailId = "SCR10";
        }
        return scoreDetailId;
    }
}