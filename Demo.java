package cn.faceall.sdk;	
/**
 * 这是关于java sdk使用的案例，开发者可以自由复制和修改并发布
 *
 * sdk的使用，主要分为2种 
 * 1，调用带有文件上传的api (detection/detect)
 * 2，调用其他的api
 */

//import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

public class Demo extends Thread {

	public static void main(String[] args) {
		Demo demo=new Demo();
		demo.start();
	}
    /**
     * @param args
     */
    public void run()
    {
        /*
         * Step 1
         * 初始化api
         */
    	while(true)
        {
    		String apiKey = "e4T4dmBH0vQf7m05Rz1j9SVKiwHhSpMEmAW4pkow";
	        String apiSecret = "u3BvR6q8epZ7YLkfnFOq0UfcOgj5rHeVhanv0ZkX";
	        String apiVersion = "v2";
	        String file1 = "C:\\Users\\fguo\\Desktop\\faceset\\16.jpg";
	//        String file2 = "C:\\Users\\fguo\\Desktop\\faceset\\10.jpg";
	        String [] valueNeg1=new String[11];
	        for(int i=0;i<10;i++)
	        {
	        	valueNeg1[i]="E:\\实习\\测试faces\\neg\\"+(i+1)+"_1.jpg";
	//        	System.out.println(valueNeg1[i]);
	        }
	        
	        
	        String [] valueNeg2=new String[11];
	        for(int i=0;i<10;i++)
	        {
	        	valueNeg2[i]="E:\\实习\\测试faces\\neg\\"+(i+1)+"_2.jpg";
	//        	System.out.println(valueNeg2[i]);
	        }
	        
	        String [] valuePos1=new String[11];
	        for(int i=0;i<10;i++)
	        {
	        	valuePos1[i]="E:\\实习\\测试faces\\pos\\"+(i+1)+"_1.jpg";
	//        	System.out.println(valuePos1[i]);
	        }
	        
	        String [] valuePos2=new String[11];
	        for(int i=0;i<10;i++)
	        {
	        	valuePos2[i]="E:\\实习\\测试faces\\pos\\"+(i+1)+"_2.jpg";
	//        	System.out.println(valuePos2[i]);
	        }
	        FaMethod method = new FaMethod(apiKey, apiSecret, apiVersion);
	
	        /*
	         * Step 2
	         * 构造数据格式
	         */
	        
	        // 存储临时的数据结果
	        JSONObject result;
	        JSONArray resultArray;
	        JSONObject resultFaceSet;
	        String faceID = "";
	        String faceID1= "";
	        String faceSetId = "";
	        String imageID="";
	        String resultTmp_pos="";
	
	
	        /*
	         * Step 3
	         * 发送数据，并获取结果
	         */
	        // First Image
	        for(int i=0;i<2;i++)
	        {
	//	        System.out.println("detection_dection");
		        JSONObject detectResult=method.detection_detect(valueNeg1[i]);
		        if (detectResult== null) {
		            System.out.println("some thing wrong");
		            return;
		        }
		        System.out.println(detectResult);
		        try{       	
		        	faceID = detectResult.getJSONArray("faces").getJSONObject(0).getString("id");
		        	imageID = detectResult.getString("image_id");
		        } catch(JSONException e)
		        {
		        	e.printStackTrace();
		        }
		                
		        //Second Image
		
		        JSONObject detectResult1 = method.detection_detect(valueNeg2[i]);
		        if (detectResult1== null) {
		            System.out.println("some thing wrong");
		            return;
		        }
		        System.out.println(detectResult1);
		        try{
		        	faceID1 = detectResult1.getJSONArray("faces").getJSONObject(0).getString("id");
		   
		        }catch(JSONException e)
		        {
		        	e.printStackTrace();
		        }
		        
		        System.out.println("Detection_Landmark");
		        result = method.detection_landmark(faceID);
		        System.out.println(result);
		
		        System.out.println("Detection_Landmark68");
		        result = method.detection_landmark68(faceID);
		        System.out.println(result);
		        
		        System.out.println("Face_Get_Info");
		        resultArray = method.face_get_info(faceID);
		        System.out.println(resultArray);
		        
		        System.out.println("Image_Get_Info");
		        resultArray = method.image_get_info(imageID);
		        System.out.println(resultArray);
		        
		        System.out.println("Faceset_Create");
		        resultFaceSet = method.faceset_create("First FaceSet ID");
		        System.out.println(resultFaceSet);
		        
		        try{
		            faceSetId = resultFaceSet.getString("id");
		            
		            }catch(JSONException e)
		            {
		            	e.printStackTrace();
		            }
		       
		        
		        System.out.println("Faceset_Add_Faces");
		        result = method.faceset_add_faces(faceSetId,faceID);
		        System.out.println(result);
		        
		        System.out.println("Faceset_Get_Info");
		        result = method.faceset_get_info(faceSetId);
		        System.out.println(result);
		        
		        System.out.println("Faceset_Remove_faces");
		        result = method.faceset_remove_faces(faceSetId,faceID); 
		        System.out.println(result);		    
		        
		        System.out.println("Faceset_set_info");
		        result = method.faceset_set_info(faceSetId, "这是我的faceset");
		        System.out.println(result);
		        
		        System.out.println("Faceset_get_list");
		        resultArray = method.faceset_get_list();
		        System.out.println(resultArray);
		        
		        System.out.println("Faceset_Delete");
		        result = method.faceset_delete(faceSetId);
		        System.out.println(result);
		        
		        System.out.println("Object_Recognition");
		        result = method.object_recognize(file1);
		        System.out.println(result);
		        
	        }
	        
	        try {
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
//        System.out.println("Recognition_Celebrity");
//        result = method.recognition_celebrity(faceID);
//        System.out.println(result);
//        
//        System.out.println("Detection_Landmark");
//        result = method.detection_landmark(faceID);
//        System.out.println(result);
//
//        System.out.println("Detection_Landmark68");
//        result = method.detection_landmark68(faceID);
//        System.out.println(result);
//
//        System.out.println("Detection_Attributes");
//        result = method.detection_attributes(faceID);
//        System.out.println(result);
//        

        
        
        
        
        
        
//        
//        System.out.println("Detection_Feature");
//        result = api.execute(FaMethod.DETECTION_FEATURE,formData);
//        System.out.println(result);        
//        
//        System.out.println("Face_Get_Info");
//        result = api.execute(FaMethod.FACE_GET_INFO,formData);
//        System.out.println(result);
//        
//        System.out.println("Image_Get_Info");
//        result = api.execute(FaMethod.IMAGE_GET_INFO,imgData);
//        System.out.println(result);
//        
//        System.out.println("Image_Get_List");
//        FaFormData imgListData = new FaFormData();
//        imgListData.put("skip","3");
//        imgListData.put("limit","5");
//        result = api.execute(FaMethod.IMAGE_GET_LIST,imgListData);
//        System.out.println(result);
        
        /*FaFormData faceSetInitData = new FaFormData();
        faceSetInitData.put("faceset_name","First Faceset");
        
        System.out.println("Faceset_Create");
        String resultFaceset = api.execute(FaMethod.FACESET_CREATE,faceSetInitData);
        System.out.println(resultFaceset);
        
        FaFormData faceSetData = new FaFormData();
        int faceSetIndex = resultFaceset.indexOf("\"id\":\"")+"\"id\":\"".length();
        System.out.println("FacesetId:"+resultFaceset.substring(faceSetIndex,faceSetIndex+40));
        faceSetData.put("faceset_id",resultFaceset.substring(faceSetIndex,faceSetIndex+40));
        
        System.out.println("Faceset_Add_Faces");
        faceSetData.put("face_id",resultDetect.substring(faceIdIndex,faceIdIndex+40));       
        result = api.execute(FaMethod.FACESET_ADD_FACES,faceSetData);
        System.out.println(result);
        faceSetData.remove("face_id");
        
        System.out.println("Faceset_Get_Info");
        result = api.execute(FaMethod.FACESET_GET_INFO,faceSetData);
        System.out.println(result);
        
        System.out.println("Faceset_Remove_Faces");
        faceSetData.put("face_id",resultDetect.substring(faceIdIndex,faceIdIndex+40));
        result = api.execute(FaMethod.FACESET_REMOVE_FACES,faceSetData);
        System.out.println(result);
        faceSetData.remove("face_id");
        
        System.out.println("Faceset_Get_list");
        result = api.execute(FaMethod.FACESET_GET_LIST,null,null);
        System.out.println(result);
        
        System.out.println("Faceset_Delete");
        result = api.execute(FaMethod.FACESET_DELETE,faceSetData);
        System.out.println(result);*/
        
        
        
        
        
        
        
        
        
        
        /*
         * 发送文本数据
         */
//        String result = api.execute(FaMethod.DETECTION_LANDMARK, formData);
    }

}