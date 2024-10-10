package bai_thi_module_2.service;

import bai_thi_module_2.model.Mobile;
import bai_thi_module_2.repository.IMobileRepository;

import java.util.List;

public class MobileService implements IMobileService {
    private IMobileRepository mobileRepository;

    public MobileService(IMobileRepository mobileRepository) {
        this.mobileRepository = mobileRepository;
    }

    @Override
    public void addMobile(Mobile mobile) {
        if (isValidMobile(mobile)) {
            mobileRepository.addMobile(mobile);
        } else {
            throw new IllegalArgumentException("Thông tin điện thoại không hợp lệ.");
        }
    }

    private boolean isValidMobile(Mobile mobile) {
        return mobile != null && mobile.getId() != null && !mobile.getId().isEmpty()
                && mobile.getName() != null && !mobile.getName().isEmpty()
                && mobile.getPrice() > 0 && mobile.getStock() >= 0;
    }

    @Override
    public List<Mobile> getAllMobiles() {
        return mobileRepository.getAllMobiles();
    }

    @Override
    public Mobile getMobileById(String id) {
        return mobileRepository.getMobileById(id);
    }

    @Override
    public void updateMobile(String id, Mobile updatedMobile) {
        if (isValidMobile(updatedMobile)) {
            mobileRepository.updateMobile(id, updatedMobile);
        } else {
            throw new IllegalArgumentException("Thông tin điện thoại không hợp lệ.");
        }
    }

    @Override
    public void deleteMobile(String id) {
        mobileRepository.deleteMobile(id);
    }

    public List<Mobile> searchMobilesByName(String name) {
        List<Mobile> allMobiles = mobileRepository.getAllMobiles();
        return allMobiles.stream()
                .filter(mobile -> mobile.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }
}