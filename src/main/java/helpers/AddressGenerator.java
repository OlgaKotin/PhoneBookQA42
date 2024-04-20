package helpers;

import java.util.Random;

public class AddressGenerator {
    public static void main(String[] args) {
        System.out.println(generateAddress());
    }

    private static final String[] city = {"Jerusalem", "Tel Aviv", "Haifa", "Beersheba", "Nazareth", "Eilat", "Ashdod", "Netanya", "Petah Tikva", "Rishon LeZion", "Herzliya", "Holon", "Tiberias", "Ramat Gan", "Modi'in", "Ashkelon", "Bat Yam", "Rehovot", "Jaffa", "Acre", "Kiryat Gat", "Ra'anana", "Herzliya Pituah", "Safed", "Hod HaSharon", "Kfar Saba", "Ramat HaSharon", "Modi'in Illit", "Bnei Brak", "Ramla", "Lod", "Ness Ziona", "Qiryat Shemona", "Dimona", "Migdal HaEmek", "Ma'alot-Tarshiha", "Kiryat Ata", "Yavne", "Giv'atayim", "Nahariya", "Arad", "Nesher", "Sderot", "Tirat Carmel", "Or Yehuda", "Ramat Ef'al", "Kiryat Malakhi", "Tamra", "Daliyat al-Karmel", "Yehud", "Ofaqim", "Kiryat Motzkin", "Rosh HaAyin", "Maghar", "Kafr Kanna", "Netivot", "Tirah", "Abu Sinan", "Yokneam Illit", "Binyamina-Giv'at Ada", "Sakhnin", "Nes Ziyyona", "Karmiel", "Qalansawe", "Giv'at Shmuel", "Zikhron Ya'akov", "Migdal", "Kafr Qasim", "Kefar Yona", "Modi'in-Maccabim-Re'ut", "Pardes Hanna-Karkur", "Kfar Yehoshua", "Ma'ale Efraim", "Kiryat Tiv'on", "Tira", "Tirat Karmel", "Hod HaSharon", "Yehud-Monosson", "Ein Mahel", "Umm al-Fahm", "Ganei Tikva", "Hadera", "Giv'at Ze'ev", "Zikhron Ya'akov", "Yavne'el", "Kfar Yona", "Kiryat Ono", "Pardesiyya", "Ramat Yishai", "Harish", "Ma'ale Adumim", "Zefat", "Bet Shemesh", "Tirat Karmel", "Ariel", "Nof HaGalil", "Beit She'an", "Kiryat Bialik", "Migdal HaEmek"};
    private static final String[] street = { "Rothschild Boulevard", "Dizengoff Street", "Ben Yehuda Street", "Allenby Street", "Herzl Street", "HaHashmonaim Street", "Yefet Street", "HaCarmel Street", "Balfour Street", "King George Street", "Ben Gurion Boulevard", "HaYarkon Street", "Rambam Street", "HaAtsma'ut Street", "Jaffa Street", "HaMeginim Street", "HaRav Kook Street", "Rehovot Street", "HaHistadrut Street", "Shenkin Street", "Frishman Street", "Emek Refaim Street", "Yehuda HaLevi Street", "HaShoftim Street", "HaCarmelit Street", "Herzl Rosenblum Street", "Habima Square", "HaNamal Street", "HaMasger Street", "Habonim Street", "HaArba'a Street", "HaMigdalim Street", "HaGolan Street", "HaTzoran Street", "HaTayasim Street", "Ha'Avoda Street", "HaEshkol Street", "HaZorfim Street", "HaKlil Street", "HaShachar Street", "HaSolelim Street", "HaAlon Street", "HaBait Street", "HaYasmin Street", "HaTavor Street", "HaSadeh Street", "HaGiborim Street", "HaNadiv Street", "HaMitzpe Street", "HaDagan Street", "HaAlonim Street", "HaTamar Street", "HaTikva Street", "HaOren Street", "HaArgaman Street", "HaShahar Street", "HaEmek Street", "HaAmir Street", "HaErez Street", "HaNasi Street", "HaRakevet Street", "HaYarkonim Street", "HaDegel Street", "HaTayas Street", "HaRishonim Street", "HaGefen Street", "HaLevanon Street", "HaYarden Street", "HaTzvi Street",};


    private static String generateZipCode() {
        Random random = new Random();
        StringBuilder zipCode = new StringBuilder();
        for(int i=0; i<6; i++){
            if(i==0){
                zipCode.append(random.nextInt(9)+1);
            }
            zipCode.append(random.nextInt(10));
        }
        return zipCode.toString();
    }
    private static String generateCity() {
        Random random = new Random();
        String randomCity = city[random.nextInt(city.length)];
        return randomCity;
    }
    private static String generateStreet() {
        Random random = new Random();
        String randomStreet = street[random.nextInt(street.length)];
        return randomStreet;
    }

    private static String generateHouseNumber() {
        Random random = new Random();
        StringBuilder houseNumber = new StringBuilder();
        return houseNumber.append(random.nextInt(100)+1).toString();
    }

    public static String generateAddress() {

        StringBuilder address = new StringBuilder();
        address.append(generateZipCode() + ", " + generateCity() + ", " + generateStreet() + ", " + generateHouseNumber());
        return address.toString();
    }

}
