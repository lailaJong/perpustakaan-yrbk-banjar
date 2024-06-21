package tugasakhir.library.utils.scoredetail;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import tugasakhir.library.model.entity.Author;
import tugasakhir.library.model.entity.ScoreDetail;
import tugasakhir.library.model.request.author.AuthorRq;
import tugasakhir.library.model.request.author.UpdateAuthorRq;
import tugasakhir.library.model.request.scoredetail.ScoreDetailRq;
import tugasakhir.library.model.request.scoredetail.UpdateScoreDetailRq;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ScoreDetailMapper {
    ScoreDetailMapper INSTANCE = Mappers.getMapper(ScoreDetailMapper.class);
    ScoreDetail toScoreDetail(ScoreDetailRq scoreDetailRq);

    String getScoreDetailId(int point);
    @Mapping(target = "scoreDetailId", ignore = true)
    void updateScoreDetailFromUpdateScoreDetailRq(UpdateScoreDetailRq updateScoreDetailRq, @MappingTarget ScoreDetail scoreDetail);
}