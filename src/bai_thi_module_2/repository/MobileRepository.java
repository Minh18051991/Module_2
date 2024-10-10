package bai_thi_module_2.repository;

import bai_thi_module_2.model.AuthMobile;
import bai_thi_module_2.model.ImportedMobile;
import bai_thi_module_2.model.Mobile;
import bai_thi_module_2.utils.SaveAndLoad;

import java.util.ArrayList;
import java.util.List;

public class MobileRepository implements IMobileRepository {
    private List<Mobile> mobiles;
    private final String filePath = "src/bai_thi_module_2/data/Mobile.csv";
    private static int currentId = 1;

    public MobileRepository() {
        this.mobiles = loadMobiles();
        updateCurrentId();
    }

    private List<Mobile> loadMobiles() {
        return SaveAndLoad.loadMobiles(filePath);
    }

    private void updateCurrentId() {
        for (Mobile mobile : mobiles) {
            int id = Integer.parseInt(mobile.getId().substring(1)); // Lấy phần số trong ID
            if (id >= currentId) {
                currentId = id + 1; // Cập nhật ID cuối cùng
            }
        }
    }

    @Override
    public void addMobile(Mobile mobile) {
        String newId;

        if (mobile instanceof AuthMobile) {
            newId = "A" + String.format("%03d", currentId++);
        } else if (mobile instanceof ImportedMobile) {
            newId = "B" + String.format("%03d", currentId++);
        } else {
            throw new IllegalArgumentException("Loại di động không hợp lệ");
        }

        mobile.setId(newId); // Gán ID mới cho sản phẩm
        mobiles.add(mobile);
        SaveAndLoad.saveMobiles(mobiles, filePath);
    }

    @Override
    public List<Mobile> getAllMobiles() {
        return new ArrayList<>(mobiles);
    }

    @Override
    public Mobile getMobileById(String id) {
        return mobiles.stream()
                .filter(mobile -> mobile.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void updateMobile(String id, Mobile updatedMobile) {
        for (int i = 0; i < mobiles.size(); i++) {
            if (mobiles.get(i).getId().equals(id)) {
                mobiles.set(i, updatedMobile);
                SaveAndLoad.saveMobiles(mobiles, filePath);
                return;
            }
        }
    }

    @Override
    public void deleteMobile(String id) {
        if (mobiles.removeIf(mobile -> mobile.getId().equals(id))) {
            SaveAndLoad.saveMobiles(mobiles, filePath);
        } else {
            throw new IllegalArgumentException("Không tìm thấy điện thoại với ID: " + id);
        }
    }
}