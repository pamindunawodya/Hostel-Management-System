package lk.ijse.bo;

import lk.ijse.bo.custom.impl.LoginDetailBOimpl;
import lk.ijse.bo.custom.impl.ReservationsBOimpl;
import lk.ijse.bo.custom.impl.RoomBOimpl;
import lk.ijse.bo.custom.impl.StudentBOimpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){
    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BOFactory() : boFactory;
    }

    public enum BOTypes{
        STUDENT,ROOM,RESERVATION,LoginDetail
    }

    //Object creation logic for BO objects
    public <T>T getBO(BOTypes types){
        switch (types){
            case STUDENT:
                return (T)new StudentBOimpl();
            case ROOM:
                return (T)new RoomBOimpl();
            case RESERVATION:
                return(T) new ReservationsBOimpl();
            case LoginDetail:
                return(T) new LoginDetailBOimpl();


            default:
                return null;
        }
    }
}