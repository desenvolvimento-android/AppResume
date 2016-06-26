package resume.rarg.com.appresume;

import java.util.ArrayList;

public class DataProvider {

    public static String[] placeNameArray = {"Carrer summary", "Personal information", "Professional experience", "Android dev skills", "Software dev skills", "Google Analytics", "University Education", "Canadian Visa status", "Find my location now"};

    public static ArrayList<DataItem> dataList() {
        ArrayList<DataItem> list = new ArrayList<>();
        for (int i = 0; i < placeNameArray.length; i++) {
            DataItem dataItem = new DataItem();
            dataItem.name = placeNameArray[i];
            dataItem.imageName = placeNameArray[i].replaceAll("\\s+", "_").toLowerCase();
            if (i == 2 || i == 5) {
                dataItem.isFav = true;
            }
            list.add(dataItem);
        }
        return (list);
    }
}
