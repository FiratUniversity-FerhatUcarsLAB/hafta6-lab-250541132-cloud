/**
 * Ad Soyad: [Esmanur Ulu]
 * Öğrenci No: [250541132]
 * Proje: [AkilliRestoranSiparisSistemi]
 * Tarih: [23.11.2025]
 */
import java .util.Scanner;

public class AkilliRestoranSiparisSistemi {
    public static double getMainDishPrice(String secim) {
        switch (secim.toLowerCase()) {
            case "izgara tavuk":
                return 85.0;
            case "adana kebap":
                return 120.0;
            case "levrek":
                return 110.0;
            case "mantı":
                return 65.0;
            default:
                return 0.0;

        }
    }

    public static double getAppetizerPrice(String secim) {
        switch (secim.toLowerCase()) {
            case "çorba":
                return 25.0;
            case "humus":
                return 45.0;
            case "sigara böreği":
                return 55.0;
            default:
                return 0.0;
        }
    }

    public static double getDrinkPrice(String secim) {
        switch (secim.toLowerCase()) {
            case "kola":
                return 15.0;
            case "ayran":
                return 12.0;
            case "meyve suyu":
                return 35.0;
            case "limonata":
                return 25.0;
            default:
                return 0.0;
        }
    }

    public static double getDessertPrice(String secim) {
        switch (secim.toLowerCase()) {
            case "künefe":
                return 65.0;
            case "baklava":
                return 55.0;
            case "sütlaç":
                return 35.0;
            default:
                return 0.0;
        }
    }
    public static boolean isComboOrder(boolean anaVar, boolean icecekVar,
        boolean tatliVar) {
        return anaVar && icecekVar && tatliVar;
    }

    public static boolean isHappyHour(int saat) {
        return saat >= 14 && saat < 17;
    }
    public static double calculateDiscount(double tutar, boolean combo,
        boolean ogrenci, int saat, boolean icecekAlindi) {
        double toplamIndirimOrani = 0.0;
        double indirimMiktari = 0.0;

        if (combo) {
            toplamIndirimOrani += 0.15;
            System.out.println("> Combo İndirimi (%15) uygulandı.");
        }

        if (tutar > 200.0) {
            toplamIndirimOrani += 0.10;
            System.out.println("> 200 TL Üzeri İndirimi (%10) uygulandı.");
        }

        if (isHappyHour(saat) && icecekAlindi) {
            System.out.println("> Happy Hour (14:00-17:00) İçeceklerde %20 indirim uygulanacaktır." +
            " (Toplam tutardan düşülecektir.)");
        }
        if (ogrenci) {
            toplamIndirimOrani += 0.10;
            System.out.println("> Öğrenci Ekstra İndirimi (%10) uygulandı.");
        }
        indirimMiktari = tutar * toplamIndirimOrani;
        return indirimMiktari;
    }

    public static double calculateServiceTip(double tutar) {
        return tutar * 0.10;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double anaYemekFiyat = 0.0;
        double icecekFiyat = 0.0;
        double tatliFiyat = 0.0;
        double baslangicFiyat = 0.0;

        String secim = "";

        System.out.println("--- Akıllı Restoran Sipariş Sistemi ---");
        System.out.println("\nMenü Kategorileri:");
        System.out.println("Ana Yemekler: Izgara Tavuk (85₺), Adana Kebap (120₺), Levrek (110₺), Mantı (65₺)");
        System.out.println("Başlangıçlar: Çorba (25₺), Humus (45₺), Sigara Böreği (55₺)");
        System.out.println("İçecekler: Kola (15₺), Ayran (12₺), Meyve Suyu (35₺), Limonata (25₺)");
        System.out.println("Tatlılar: Künefe (65₺), Baklava (55₺), Sütlaç (35₺)");
        System.out.println("------------------------------------");

        System.out.print("Ana Yemek seçiminizi girin (Yoksa 'Yok'): ");
        secim = scanner.nextLine();
        anaYemekFiyat = getMainDishPrice(secim);
        boolean anaVar = anaYemekFiyat > 0;


        System.out.print("İçecek seçiminizi girin (Yoksa 'Yok'): ");
        secim = scanner.nextLine();
        icecekFiyat = getDrinkPrice(secim);
        boolean icecekVar = icecekFiyat > 0;


        System.out.print("Tatlı seçiminizi girin (Yoksa 'Yok'): ");
        secim = scanner.nextLine();
        tatliFiyat = getDessertPrice(secim);
        boolean tatliVar = tatliFiyat > 0;

        System.out.print("Başlangıç seçiminizi girin (Yoksa 'Yok'): ");
        secim = scanner.nextLine();
        baslangicFiyat = getAppetizerPrice(secim);


        System.out.print("Şu anki saat kaç? (1-24 arası): ");
        int saat = scanner.nextInt();

        System.out.print("Öğrenci misiniz? (Evet/Hayır): ");
        String ogrenciCevap = scanner.next();
        boolean ogrenci = ogrenciCevap.equalsIgnoreCase("Evet");

        // --- Hesaplama Başlangıcı ---
        double brutTutar = anaYemekFiyat + icecekFiyat + tatliFiyat + baslangicFiyat;
        double toplamIndirim = 0.0;
        double icecekHappyHourIndirimi = 0.0;
        boolean combo = isComboOrder(anaVar, icecekVar, tatliVar);
        double happyHourBaslangicFiyati = icecekFiyat;
        if (isHappyHour(saat) && icecekVar) {
            icecekHappyHourIndirimi = icecekFiyat * 0.20;
            icecekFiyat -= icecekHappyHourIndirimi;
        }
        brutTutar = anaYemekFiyat + icecekFiyat + tatliFiyat + baslangicFiyat;

        System.out.println("\n--- İndirim Uygulamaları ---");
        toplamIndirim = calculateDiscount(brutTutar, combo, ogrenci, saat, icecekVar);

        toplamIndirim += icecekHappyHourIndirimi;
        double indirimliTutar = brutTutar - toplamIndirim;
        double bahsisOnerisi = calculateServiceTip(indirimliTutar);
        System.out.println("\n--- Sipariş Özeti ve Fatura ---");
        System.out.printf("Brüt Tutar (Happy Hour Öncesi): %.2f TL\n", (anaYemekFiyat + happyHourBaslangicFiyati + tatliFiyat + baslangicFiyat));
        System.out.printf("Happy Hour İndirimi: %.2f TL\n", icecekHappyHourIndirimi);


        System.out.printf("Toplam İndirim Miktarı: %.2f TL\n", toplamIndirim);
        System.out.printf("Ödenecek Tutar (KDV Dahil): **%.2f TL**\n", indirimliTutar);
        System.out.printf("Önerilen Bahşiş (%10): %.2f TL\n", bahsisOnerisi);
        System.out.printf("Bahşiş Dahil Toplam: %.2f TL\n", indirimliTutar + bahsisOnerisi);
        System.out.println("------------------------------------");

        scanner.close();
    }
}
