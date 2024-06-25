package tugasakhir.library.utils.validation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import tugasakhir.library.config.properties.ApplicationProperties;
import tugasakhir.library.model.entity.ScoreDetail;
import tugasakhir.library.repository.MemberRepository;
import tugasakhir.library.repository.ScoreDetailRepository;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Putri Mele
 * on 25/06/2024
 */

@Slf4j
public class BenefitValidation {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ScoreDetailRepository scoreDetailRepository;
    @Autowired
    private ApplicationProperties applicationProperties;

    public int getTotalTime(String userId){
        int totalTime = 0;
        String scoreId = memberRepository.getScoreId(userId);
        ScoreDetail scoreDetail = scoreDetailRepository.getScoreDetailById(scoreId);
        int benefitTime = scoreDetail.getExtraBorrowTime();
        totalTime = applicationProperties.getRegulerTime() + benefitTime;
        log.info(String.valueOf(totalTime));
        return totalTime;
    }

    public int getTotalQuota(String userId){
        int totalQuota = 0;
        String scoreId = memberRepository.getScoreId(userId);
        ScoreDetail scoreDetail = scoreDetailRepository.getScoreDetailById(scoreId);
        int benefitQuota = scoreDetail.getExtraBooksQuota();
        totalQuota = applicationProperties.getRegulerTime() + benefitQuota;
        log.info(String.valueOf(totalQuota));
        return totalQuota;
    }

    public Date setReturnDates(String userId) {
        Date returnDate;
        int totalTime = getTotalTime(userId);
        Calendar calendar = Calendar.getInstance(); // Gets a calendar using the default time zone and locale.
        calendar.add(Calendar.DAY_OF_MONTH, totalTime); // Adds one day to the calendar
        returnDate = calendar.getTime(); // Sets taking date to the next day
        return returnDate;
    }
}
