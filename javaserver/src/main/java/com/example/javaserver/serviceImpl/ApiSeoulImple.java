package com.example.javaserver.serviceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.example.javaserver.service.ApiSeoul;

@Service
public class ApiSeoulImple implements ApiSeoul {

    @Override
    public ArrayList seoulMarketVeg() {

        ArrayList returnVeg = new ArrayList<>();

        try {

            URL url = new URL(
                    "https://www.kamis.or.kr/service/price/xml.do?action=dailyPriceByCategoryList&p_product_cls_code=02&p_country_code=1101&p_regday=2022-06-02&p_convert_kg_yn=N&p_item_category_code=200&p_cert_key=f08f57bd-4699-4da7-a29f-1f6fa6c1692d&p_cert_id=2535&p_returntype=json");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                returnVeg.add(output);
            }
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.toString();
        } catch (IOException e) {
            e.toString();
        }
        System.out.println(returnVeg);

        return returnVeg;

    };

    @Override
    public ArrayList seoulMarketMeat() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList seoulMarketSea() {
        // TODO Auto-generated method stub
        return null;
    }

}
