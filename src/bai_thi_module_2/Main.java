package bai_thi_module_2;

import bai_thi_module_2.repository.IMobileRepository;

import bai_thi_module_2.repository.MobileRepository;
import bai_thi_module_2.service.IMobileService;
import bai_thi_module_2.service.MobileService;
import bai_thi_module_2.controller.MobileController;
import bai_thi_module_2.view.MobileView;

public class Main {
    public static void main(String[] args) {
        IMobileRepository mobileRepository = new MobileRepository();
        IMobileService mobileService = new MobileService(mobileRepository);
        MobileController mobileController = new MobileController(mobileService);
        MobileView mobileView = new MobileView(mobileController);


        mobileView.run();
    }
}