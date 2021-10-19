package lk.itsei.fx.sms.business;

import lk.itsei.fx.sms.dao.DAOFactory;
import lk.itsei.fx.sms.dao.custom.RegistrationDetailsDAO;
import lk.itsei.fx.sms.dao.custom.StudentDAO;
import lk.itsei.fx.sms.dto.RegistrationDetailsDTO;
import lk.itsei.fx.sms.entity.RegistrationDetails;

import java.util.ArrayList;
import java.util.List;

public class RegistrationDetailsBusiness {

    private static RegistrationDetailsDAO registrationDetailsDAO = (RegistrationDetailsDAO) DAOFactory.getInstance ().getDAO (DAOFactory.DAOTypes.REGISTRATION_DETAILS);


    public static List<RegistrationDetailsDTO> getRegistrationDetails() throws Exception {
        List<RegistrationDetails> detailsList = registrationDetailsDAO.findAll ();
        List<RegistrationDetailsDTO> detailsDTOList = new ArrayList <> ();
        for (RegistrationDetails registrationDetails : detailsList) {
            detailsDTOList.add (new RegistrationDetailsDTO (
                    registrationDetails.getRid (),
                    registrationDetails.getSid (),
                    registrationDetails.getStudentName (),
                    registrationDetails.getCid (),
                    registrationDetails.getName (),
                    registrationDetails.getStatus (),
                    registrationDetails.getPrice (),
                    registrationDetails.getCourseDuration ()
            ));
        }
        return detailsDTOList;
    }

    public static boolean createRegistration(RegistrationDetailsDTO registrationDetailsDTO) throws Exception {
        System.out.println (registrationDetailsDTO.toString ());
         return registrationDetailsDAO.add (
                new RegistrationDetails (
                        registrationDetailsDTO.getRid (),
                        registrationDetailsDTO.getSid (),
                        registrationDetailsDTO.getStudentName (),
                        registrationDetailsDTO.getCid (),
                        registrationDetailsDTO.getName (),
                        registrationDetailsDTO.getStatus (),
                        registrationDetailsDTO.getPrice (),
                        registrationDetailsDTO.getCourseDuration ()
                )

        );
    }

    public static boolean deleteRegistration(String id) throws Exception {
        return registrationDetailsDAO.delete (id);
    }

    public static RegistrationDetailsDTO findRegistration(String id) throws Exception {
        RegistrationDetails registrationDetails = registrationDetailsDAO.find (id);
        return new RegistrationDetailsDTO (
                registrationDetails.getRid (),
                registrationDetails.getSid (),
                registrationDetails.getStudentName (),
                registrationDetails.getCid (),
                registrationDetails.getName (),
                registrationDetails.getStatus (),
                registrationDetails.getPrice (),
                registrationDetails.getCourseDuration ()
        );
    }
}
