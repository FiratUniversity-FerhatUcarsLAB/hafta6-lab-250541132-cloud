/**
* Ad Soyad: [ESMANUR ULU]
* Numara: [250541132]
* Proje: [SinemaBiletFiyatlandirma]
* Tarih: [22.11.2025]
*/
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.InputMismatchException;

public class SinemaBiletFiyatlandirma {

    // Sabitler
    private static final int MESLEK_OGRENCI = 1;
    private static final int MESLEK_OGRETMEN = 2;
    private static final int FILM_TURU_2D = 1;
    private static final int FILM_TURU_3D = 2;
    private static final int FILM_TURU_IMAX = 3;
    private static final int FILM_TURU_4DX = 4;
    private static final int GUN_PAZARTESI = 1;
    private static final int GUN_CARSAMBA = 3;
    private static final int GUN_PERSEMBE = 4;
    private static final int GUN_CUMA = 5;
    private static final int GUN_PAZAR = 7;
    private static final int MATINE_BITIS_SAATI = 12;

    // 1. isWeekend(gun) - Hafta sonu mu kontrol et
    public boolean isWeekend(int gun) {
        switch (gun) {
            case GUN_CUMA:
            case 6:
            case GUN_PAZAR:
                return true;
            default:
                return false;
        }
    }

    // 2. isMatinee(saat) - Matine mi kontrol et
    public boolean isMatinee(int saat) {
        return saat < MATINE_BITIS_SAATI;
    }

    // 3. calculateBasePrice(gun, saat) - Temel fiyat
    public double calculateBasePrice(int gun, int saat) {
        boolean isWeekend = isWeekend(gun);
        boolean isMatinee = isMatinee(saat);
        double basePrice;

        if (isWeekend && isMatinee) {
            basePrice = 55.0;
        } else if (isWeekend && !isMatinee) {
            basePrice = 85.0;
        } else if (!isWeekend && isMatinee) {
            basePrice = 45.0;
        } else {
            basePrice = 65.0;
        }
        return basePrice;
    }

    // 4. calculateDiscount(yas, meslek, gun) - İndirim hesapla (En yüksek indirim oranı döner)
    public double calculateDiscount(int yas, int meslek, int gun) {
        double discountRate = 0.0;
        boolean isWeekend = isWeekend(gun);


        double yas65Indirimi = (yas >= 65) ? 0.30 : 0.0;
        double yas12Indirimi = (yas < 12) ? 0.25 : 0.0;

        discountRate = Math.max(yas65Indirimi, yas12Indirimi);

        switch (meslek) {
            case MESLEK_OGRENCI:

                if (isWeekend) {
                    discountRate = Math.max(discountRate, 0.15);
                } else {
                    discountRate = Math.max(discountRate, 0.20);
                }
                break;
            case MESLEK_OGRETMEN:
                if (gun == GUN_CARSAMBA) {
                    discountRate = Math.max(discountRate, 0.35);
                }
                break;
        }
        return discountRate;
    }

    // 5. getFormatExtra(filmTuru) - Format ekstra ücreti
    public double getFormatExtra(int filmTuru) {

        switch (filmTuru) {
            case FILM_TURU_3D:
                return 25.0;
            case FILM_TURU_IMAX:
                return 35.0;
            case FILM_TURU_4DX:
                return 50.0;
            case FILM_TURU_2D:
            default:
                return 0.0;
        }
    }

    // 6. calculateFinalPrice(...) - Toplam fiyat
    public double calculateFinalPrice(int gun, int saat, int yas, int meslek, int filmTuru) {
        double basePrice = calculateBasePrice(gun, saat);
        double discountRate = calculateDiscount(yas, meslek, gun);
        double formatExtra = getFormatExtra(filmTuru);

        // İndirimli fiyat
        double discountedPrice = basePrice * (1.0 - discountRate);

        // Toplam Fiyat
        double finalPrice = discountedPrice + formatExtra;

        return finalPrice;
    }


    public void generateTicketInfo(int gun, int saat, int yas, int meslek, int filmTuru) {
        double basePrice = calculateBasePrice(gun, saat);
        double discountRate = calculateDiscount(yas, meslek, gun);
        double discountAmount = basePrice * discountRate;
        double discountedPrice = basePrice - discountAmount;
        double formatExtra = getFormatExtra(filmTuru);
        double finalPrice = calculateFinalPrice(gun, saat, yas, meslek, filmTuru);


        System.out.println("\n---  SİNEMA BİLET BİLGİSİ ---");
        System.out.printf("Temel Fiyat: %.2f TL\n", basePrice);

        // İndirim Bilgisi
        if (discountRate > 0.0) {
            System.out.printf("Uygulanan İndirim Oranı: %d%%\n", (int)(discountRate * 100));
            System.out.printf("İndirim Miktarı: -%.2f TL\n", discountAmount);
            System.out.printf("İndirimli Temel Fiyat: %.2f TL\n", discountedPrice);
        } else {
            System.out.println("Uygulanan İndirim: Yok");
        }

        // Format Ekstra Bilgisi
        if (formatExtra > 0.0) {
            System.out.printf("Film Format Ekstra Ücreti: +%.2f TL\n", formatExtra);
        } else {
            System.out.println("Film Format Ekstra Ücreti: Yok");
        }

        System.out.println("---------------------------------");
        System.out.printf("**TOPLAM ÖDENECEK TUTAR: %.2f TL**\n", finalPrice);
        System.out.println("---------------------------------");
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        SinemaBiletFiyatlandirma sistem = new SinemaBiletFiyatlandirma();

        // Girdi Değişkenleri
        int gun = 0;
        int saat = 0;
        int yas = 0;
        int meslek = 0;
        int filmTuru = 0;


        try {
            System.out.println("---  BİLET BİLGİLERİNİ GİRİNİZ ---");

            System.out.print("Gün (1=Pzt, 2=Sal, ..., 7=Paz): ");
            gun = scanner.nextInt();

            System.out.print("Saat (8-23): ");
            saat = scanner.nextInt();

            System.out.print("Yaş: ");
            yas = scanner.nextInt();

            System.out.print("Meslek (1=Öğrenci, 2=Öğretmen, 3=Diğer): ");
            meslek = scanner.nextInt();

            System.out.print("Film Türü (1=2D, 2=3D, 3=IMAX, 4=4DX): ");
            filmTuru = scanner.nextInt();

            if (gun < 1 || gun > 7 || saat < 8 || saat > 23 || yas < 0 || meslek < 1 || meslek > 3 || filmTuru < 1 || filmTuru > 4) {
                System.out.println("\n*** HATA: Geçersiz girdi değeri. Lütfen aralıkları kontrol edin. ***");
            } else {
                sistem.generateTicketInfo(gun, saat, yas, meslek, filmTuru);
            }

        } catch (InputMismatchException e) {
            System.out.println("\n*** HATA: Lütfen sayısal bir değer girin. ***");
        } finally {
            scanner.close();
        }
    }
}
