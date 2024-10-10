package bai_thi_module_2.repository;

import bai_thi_module_2.model.Mobile;
import bai_thi_module_2.utils.SaveAndLoad;

import java.util.ArrayList;
import java.util.List;

public class MobileRepository implements IMobileRepository {
    private List<Mobile> mobiles;
    private final String filePath = "src/bai_thi_module_2/data/Mobile.csv";

    public MobileRepository() {
        this.mobiles = loadMobiles();
    }

    private List<Mobile> loadMobiles() {
        List<Mobile> loadedMobiles = SaveAndLoad.loadMobiles(filePath);
        return loadedMobiles;
    }

    @Override
    public void addMobile(Mobile mobile) {
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
        }
    }
}