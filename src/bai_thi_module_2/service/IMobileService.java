package bai_thi_module_2.service;

import bai_thi_module_2.model.Mobile;

import java.util.List;

public interface IMobileService {
    void addMobile(Mobile mobile);
    List<Mobile> getAllMobiles();
    Mobile getMobileById(String id);
    void updateMobile(String id, Mobile updatedMobile);
    void deleteMobile(String id);
}