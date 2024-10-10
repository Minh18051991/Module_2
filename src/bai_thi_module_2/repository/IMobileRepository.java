package bai_thi_module_2.repository;

import bai_thi_module_2.model.Mobile;

import java.util.List;

public interface IMobileRepository{
    void addMobile(Mobile mobile);
    List<Mobile> getAllMobiles();
    Mobile getMobileById(String id);
    void updateMobile(String id, Mobile updatedMobile);
    void deleteMobile(String id);
}