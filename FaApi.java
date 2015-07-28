package cn.faceall.sdk;

import java.io.File;
import java.io.IOException;
//import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
//import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class FaApi {

    private final static String URL = "http://api.faceall.cn:80/";
    private String apiKey = null;
    private String apiSecret = null;
    private String version = null;

    public FaApi(String apiKey, String apiSecret, String version) {
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        this.version = version;
    }

    public JSONObject execute(String method, FaFormData formData) {
        return this.execute(method, formData, null);
    }

    public JSONObject execute(String method, FaFileData fileData) {
        return this.execute(method, null, fileData);
    }

    public JSONObject execute(String method, FaFormData formData, FaFileData fileData) {
        String url = URL + this.version + "/" + method;
        HttpPost httppost = new HttpPost(url);

        if (formData == null) {
            formData = new FaFormData();
        }

        formData.put("api_key", this.apiKey);
        formData.put("api_secret", this.apiSecret);

        // construct request entity
        MultipartEntityBuilder reqEntity = MultipartEntityBuilder.create();

        // parse form data
		Iterator<Map.Entry<String, String>> formIt = formData.entrySet().iterator();

        while (formIt.hasNext()) {

            Map.Entry<String, String> entity = formIt.next();
            reqEntity.addTextBody(entity.getKey(), entity.getValue(), ContentType.TEXT_PLAIN);

        }

        // parse file data
        if (fileData != null && !fileData.isEmpty()) {
            Iterator<Map.Entry<String, String>> fileIt = fileData.entrySet().iterator();

            while (fileIt.hasNext()) {

                Map.Entry<String, String> entity = fileIt.next();
                reqEntity.addPart(entity.getKey(), new FileBody(new File(entity.getValue())));

            }
        }

        httppost.setEntity(reqEntity.build());

        CloseableHttpClient httpClient = HttpClients.createDefault();

        StringBuilder sb = new StringBuilder();
        
        JSONObject jsonObj = new JSONObject();

        try {
            CloseableHttpResponse response = httpClient.execute(httppost);

            HttpEntity resEntity = response.getEntity();

            // get response
            if (resEntity != null) {

//            	InputStream in = resEntity.getContent();
//                byte[] buffer = new byte[1024];
//                int length = 0;
//
//                while ((length = in.read(buffer)) != -1) {
//                    sb.append(new String(buffer, 0, length, "utf-8"));
//                }
            	sb.append(EntityUtils.toString(resEntity, "UTF-8"));
            	try{
            		JSONObject objTmp = new JSONObject(sb.toString());
            		jsonObj = objTmp;
            	}catch(JSONException e){
            		e.printStackTrace();
            	}
          
            }
            response.close();

        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return jsonObj;
    }
    
    public JSONArray executeArray(String method, FaFormData formData) {
        return this.executeArray(method, formData, null);
    }

    public JSONArray executeArray(String method, FaFileData fileData) {
        return this.executeArray(method, null, fileData);
    }

    public JSONArray executeArray(String method, FaFormData formData, FaFileData fileData) {
        String url = URL + this.version + "/" + method;
        HttpPost httppost = new HttpPost(url);

        if (formData == null) {
            formData = new FaFormData();
        }

        formData.put("api_key", this.apiKey);
        formData.put("api_secret", this.apiSecret);

        // construct request entity
        MultipartEntityBuilder reqEntity = MultipartEntityBuilder.create();

        // parse form data
		Iterator<Map.Entry<String, String>> formIt = formData.entrySet().iterator();

        while (formIt.hasNext()) {

            Map.Entry<String, String> entity = formIt.next();
            reqEntity.addTextBody(entity.getKey(), entity.getValue(), ContentType.TEXT_PLAIN);

        }

        // parse file data
        if (fileData != null && !fileData.isEmpty()) {
            Iterator<Map.Entry<String, String>> fileIt = fileData.entrySet().iterator();

            while (fileIt.hasNext()) {

                Map.Entry<String, String> entity = fileIt.next();
                reqEntity.addPart(entity.getKey(), new FileBody(new File(entity.getValue())));

            }
        }

        httppost.setEntity(reqEntity.build());

        CloseableHttpClient httpClient = HttpClients.createDefault();

        StringBuilder sb = new StringBuilder();
        
        JSONArray jsonArr = new JSONArray();

        try {
            CloseableHttpResponse response = httpClient.execute(httppost);

            HttpEntity resEntity = response.getEntity();

            // get response
            if (resEntity != null) {

//            	InputStream in = resEntity.getContent();
//                byte[] buffer = new byte[1024];
//                int length = 0;
//
//                while ((length = in.read(buffer)) != -1) {
//                    sb.append(new String(buffer, 0, length, "utf-8"));
//                }
            	sb.append(EntityUtils.toString(resEntity, "UTF-8"));
            	try{
            		JSONArray arrTmp = new JSONArray(sb.toString());
            		jsonArr = arrTmp;
            	}catch(JSONException e){
            		e.printStackTrace();
            	}
          
            }
            response.close();

        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return jsonArr;
    }
}
