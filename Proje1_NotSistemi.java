/**
 * Ad Soyad: [Esmanur Ulu]
 * Numara: [250541132]
 * Proje: [OgrenciNotSistemi]
 * Tarih: [22.11.2025]
 */


package v2;
import java .util.Scanner;

public class OgrenciNotSistemi {
    public static double calculateAverage(int vize_notu, int final_notu, int odev_notu){
        double ortalama = vize_notu *0.3
                + final_notu * 0.4
                + odev_notu * 0.3;

        return ortalama;
    }
    public static boolean isPassingGrade(double ortalama ){
        if (ortalama >= 50){
            return true;
        }else{
            return false;
        }
    }
    public static String getLetterGrade(double ortalama){
        String harf_notu= "GEÇERSİZ";
        if(ortalama >= 90 && ortalama <= 100) {
            harf_notu = "A";
        }else if(ortalama >= 80 && ortalama <= 89){
            harf_notu = "B";
        }else if(ortalama >= 70 && ortalama <= 79){
            harf_notu = "C";
        }else if(ortalama >=60 && ortalama <= 69){
            harf_notu = "D";
        }else{
            harf_notu = "F";
        }
        return harf_notu;
}

    public static boolean isHonorList(double ortalama,
    int vize_notu, int final_notu, int odev_notu){
      if(ortalama >= 85 && vize_notu >= 70 && final_notu >= 70 && odev_notu >= 70) {
          return true;
      }else{
          return false ;
      }
    }
    public static boolean hasRetakeRight(double ortalama){
        if(ortalama >= 40 && ortalama <50){
            return true;
        }else{
            return false ;
        }
    }
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int vize_notu,final_notu,odev_notu;
        System.out.print("Vize Notunu Girin:");
        vize_notu = scanner.nextInt();
        System.out.print("Final Notu Girin:");
        final_notu = scanner.nextInt();
        System.out.print("Ödev Notunu Girin:");
        odev_notu = scanner.nextInt();


        System.out.println("=== OGRENCİ NOT RAPORU ===");
        System.out.println("Vize Notu:" + vize_notu);
        System.out.println("Final Notu:" + final_notu);
        System.out.println("Ödev Notu:" + odev_notu);
        System.out.println("---------------");
        double ortalama =  calculateAverage(vize_notu, final_notu,  odev_notu);

        System.out.println("Ortalama:" + ortalama);
        System.out.println("Harf Notu:" + getLetterGrade(ortalama));
        if(isPassingGrade(ortalama)){
            System.out.println("Durum: GEÇTİ");
        }else{
            System.out.println("Durum: KALDI");
        }

        System.out.println("Onur Listesi:" + (isHonorList(ortalama,vize_notu,final_notu
            ,odev_notu) ==true ? "EVET" : "HAYIR"));

        System.out.println("Bütünleme:" + (hasRetakeRight (ortalama)== true ? "VAR" : "YOK"));
    }
}
