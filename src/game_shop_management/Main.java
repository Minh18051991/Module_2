package game_shop_management;

import game_shop_management.controller.GameShopController;
import game_shop_management.repository.GameShopRepository;
import game_shop_management.service.GameShopService;
import game_shop_management.view.GameShopView;

public class Main {
    public static void main(String[] args) {
        // Khởi tạo repository, service, controller và view
        GameShopRepository repository = new GameShopRepository();
        GameShopService service = new GameShopService(repository);
        GameShopController controller = new GameShopController(service);
        GameShopView view = new GameShopView(controller);

        // Hiển thị menu giao diện
        view.displayMenu();
    }
}