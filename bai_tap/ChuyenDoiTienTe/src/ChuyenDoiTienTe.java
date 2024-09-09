import java.util.Scanner;

public class ChuyenDoiTienTe {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double VND = 23000;
        double USD;
        System.out.println("nhap so USD can doi: ");
        USD = sc.nextDouble();
        System.out.println("So tien VND la " + USD * VND);

    }
}
