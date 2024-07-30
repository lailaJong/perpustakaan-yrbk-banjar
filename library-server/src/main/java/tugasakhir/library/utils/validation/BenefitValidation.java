package tugasakhir.library.utils.validation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tugasakhir.library.config.properties.ApplicationProperties;
import tugasakhir.library.model.entity.ScoreDetail;
import tugasakhir.library.repository.BorrowingDetailRepository;
import tugasakhir.library.repository.MemberRepository;
import tugasakhir.library.repository.OrderDetailRepository;
import tugasakhir.library.repository.ScoreDetailRepository;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Putri Mele
 * on 25/06/2024
 */

@Slf4j
@Component
public class BenefitValidation {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ScoreDetailRepository scoreDetailRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private BorrowingDetailRepository borrowingDetailRepository;
    @Autowired
    private ApplicationProperties applicationProperties;

    public int getTotalTime(String userId){
        String scoreId = memberRepository.getScoreId(userId);
        ScoreDetail scoreDetail = scoreDetailRepository.getScoreDetailById(scoreId);
        int benefitTime = scoreDetail.getExtraBorrowTime();
        int totalTime = applicationProperties.getRegularTime() + benefitTime;
        log.info(String.valueOf(totalTime));
        return totalTime;
    }

    public int getTotalQuota(String userId){
        String scoreId = memberRepository.getScoreId(userId);
        ScoreDetail scoreDetail = scoreDetailRepository.getScoreDetailById(scoreId);
        int benefitQuota = scoreDetail.getExtraBooksQuota();
        int totalQuota = applicationProperties.getRegularQuota() + benefitQuota;
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

    public Boolean isQuotasAvailable (String userId){
        int totalOrdering = orderDetailRepository.getCountOrderDetailByUserId(userId);
        int totalBorrowing = borrowingDetailRepository.getCountBorrowingStatusByUserId(userId);
        int totalQuotas = getTotalQuota(userId);
        int remainingQuota = totalQuotas - (totalOrdering + totalBorrowing);
        return remainingQuota > 0;
    }
}
