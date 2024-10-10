package bai_thi_module_2.controller;

import bai_thi_module_2.model.Mobile;
import bai_thi_module_2.service.IMobileService;

import java.util.List;

public class MobileController {
    private IMobileService mobileService;

    public MobileController(IMobileService mobileService) {
        this.mobileService = mobileService;
    }

    public void addMobile(Mobile mobile) {
        mobileService.addMobile(mobile);
    }

    public void updateMobile(String id, Mobile updatedMobile) {
        mobileService.updateMobile(id, updatedMobile);
    }

    public void deleteMobile(String id) {
        mobileService.deleteMobile(id);
    }

    public List<Mobile> getAllMobiles() {
        return mobileService.getAllMobiles();
    }

    public Mobile getMobileById(String id) {
        return mobileService.getMobileById(id);
    }
}