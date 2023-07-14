import jdk.internal.icu.util.VersionInfo;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
public class laptop {
    public static void main(String[] args) throws IOException {
        ConnectURI koneksiSaya = new ConnectURI();
        URL myAddress = koneksiSaya.buildURL("https://dummyjson.com/products/search?q=Laptop");
        String response = koneksiSaya.getResponseFromHttpUrl(myAddress);
        System.out.println(response);
        assert response != null;
        JSONArray responsedJSON = new JSONArray(response);
        JSONObject json = new JSONObject(responsedJSON);

        JSONArray productsArray = json.getJSONArray("products");
        ArrayList<laptop> modellaptop = new ArrayList<>();
        for (int i = 0; i < productsArray.length(); i++) {
            JSONObject productObject = productsArray.getJSONObject(i);
            laptop laptop = new laptop();
            laptop.setId(productObject.getString("id"));
            laptop.setTitle(productObject.getString("title"));
            laptop.setDescription(productObject.getString("description"));
            laptop.setPrice(productObject.getString("price"));
            laptop.setRating(productObject.getString("rating"));
            laptop.setBrand(productObject.getString("brand"));
            modellaptop.add(laptop);
        }
        laptop[] productArray = modellaptop.toArray(new laptop[0]);

        for (int i = 0; i < productArray.length - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < productArray.length; j++) {
                if (productArray[j].getRating().compareTo(productArray[maxIndex].getRating()) > 0) {
                    maxIndex = j;
                }
            }
            if (maxIndex != i) {
                laptop temp = productArray[i];
                productArray[i] = productArray[maxIndex];
                productArray[maxIndex] = temp;
            }
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the minimum rating threshold: ");
        String ratingThreshold = scanner.nextLine();


        System.out.println("Response are : ");
        for (int index = 0; index < modellaptop.size(); index++) {
            if (modellaptop.get(index).getRating().compareTo(ratingThreshold) > 0) {
                System.out.println("ID  : " + modellaptop.get(index).getId());
                System.out.println("Nama Laptop : " + modellaptop.get(index).getTitle());
                System.out.println("Deskripsi : " + modellaptop.get(index).getDescription());
                System.out.println("Harga : " + modellaptop.get(index).getPrice());
                System.out.println("Penilaian : " + modellaptop.get(index).getRating());
                System.out.println("Brand : " + modellaptop.get(index).getBrand());

            }
        }
    }
}