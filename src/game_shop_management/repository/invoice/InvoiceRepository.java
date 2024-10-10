package game_shop_management.repository.invoice;

import game_shop_management.model.Customers;
import game_shop_management.model.Invoice;
import game_shop_management.model.Products;
import game_shop_management.repository.products.IProductsRepository;
import game_shop_management.repository.products.ProductsRepository;
import game_shop_management.util.FileHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InvoiceRepository implements IInvoiceRepository {
    private static final String INVOICE_FILE = "src/game_shop_management/data/Invoices.csv";
    private List<Invoice> invoices;
    private IProductsRepository productsRepository;

    public InvoiceRepository() {
        this.productsRepository = new ProductsRepository(); // Khởi tạo repository sản phẩm
        this.invoices = loadInvoices(); // Đảm bảo loadInvoices được gọi sau khi khởi tạo productsRepository
    }

    @Override
    public void saveInvoice(Invoice invoice) {
        invoices.add(invoice);
        FileHandler.saveToFile(invoices, INVOICE_FILE, "invoiceId,customerId,invoiceDate,totalAmount,products");
    }

    @Override
    public void deleteInvoice(String invoiceId) {
        invoices.removeIf(invoice -> invoice.getInvoiceId().equals(invoiceId));
        FileHandler.saveToFile(invoices, INVOICE_FILE, "invoiceId,customerId,invoiceDate,totalAmount,products");
    }

    @Override
    public List<Invoice> findInvoicesByCustomerId(String customerId) {
        return invoices.stream()
                .filter(invoice -> invoice.getCustomer().getId().equals(customerId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Invoice> getAllInvoices() {
        return new ArrayList<>(invoices);
    }

    @Override
    public List<Invoice> loadInvoices() {
        List<Invoice> allInvoices = FileHandler.loadFromFile(INVOICE_FILE, line -> {
            String[] values = line.split(",");
            // Kiểm tra số lượng trường
            if (values.length < 5) {
                System.out.println("Dữ liệu không hợp lệ: " + line);
                return null; // Hoặc xử lý khác
            }

            String invoiceId = values[0];
            String customerId = values[1]; // Lấy ID khách hàng
            String invoiceDate = values[2];
            double totalPrice = Double.parseDouble(values[3]);
            List<Products> products = new ArrayList<>();

            // Tách danh sách sản phẩm
            String productData = values[4];
            String[] productEntries = productData.split(",");

            for (String entry : productEntries) {
                String[] parts = entry.split(":");
                String productId = parts[0]; // ID sản phẩm
                int quantity = Integer.parseInt(parts[1]); // Số lượng

                // Tìm sản phẩm theo ID và thêm vào danh sách
                Products product = findProductById(productId); // Phương thức để tìm sản phẩm
                if (product != null) {
                    products.add(product); // Thêm sản phẩm vào danh sách
                }
            }

            // Tạo đối tượng Invoice
            return new Invoice(invoiceId, new Customers(customerId, "", "", ""), invoiceDate, products);
        });

        return allInvoices.isEmpty() ? new ArrayList<>() : allInvoices;
    }

    private Products findProductById(String productId) {
        List<Products> allProducts = productsRepository.getProducts();
        for (Products product : allProducts) {
            if (product.getId().equals(productId)) {
                return product;
            }
        }
        return null; // Trả về null nếu không tìm thấy
    }
}