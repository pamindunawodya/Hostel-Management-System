package lk.ijse.bo.custom;

import lk.ijse.dto.LoginDetailDTO;

public interface LoginDetailBO {
    LoginDetailDTO findByPk(String pk);

    boolean update(LoginDetailDTO loginDetailDTO);
}
