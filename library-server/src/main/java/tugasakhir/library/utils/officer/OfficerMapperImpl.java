package tugasakhir.library.utils.officer;

import tugasakhir.library.model.entity.Author;
import tugasakhir.library.model.entity.Officer;
import tugasakhir.library.model.request.author.AuthorRq;
import tugasakhir.library.model.request.author.UpdateAuthorRq;
import tugasakhir.library.model.request.officer.OfficerRq;
import tugasakhir.library.model.request.officer.UpdateOfficerRq;
import tugasakhir.library.repository.OfficerRepository;

/**
 * @author Putri Mele
 * on 18/06/2024
 */
public class OfficerMapperImpl implements OfficerMapper {

    private String officerId;
    private String userId;
    private String name;
    @Override
    public Officer toOfficer(OfficerRq officerRq) {
        if (officerRq == null) {
            return null;
        }
        Officer officer = new Officer();
        officer.setOfficerId(officerRq.getOfficerId());
        officer.setUserId(officerRq.getUserId());
        officer.setName(officerRq.getName());
        return officer;
    }

    @Override
    public void updateOfficerFromUpdateOfficerRq(UpdateOfficerRq updateOfficerRq, Officer officer) {
        if ( updateOfficerRq == null ) {
            return;
        }

        if ( updateOfficerRq.getUserId() != null ) {
            officer.setUserId( updateOfficerRq.getUserId() );
        }
        if ( updateOfficerRq.getName() != null ) {
            officer.setName( updateOfficerRq.getName() );
        }
    }
}