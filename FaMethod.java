package cn.faceall.sdk;
import org.json.JSONObject;
import org.json.JSONArray;

public class FaMethod {
	//功能
	private static final String DETECTION_DETECT = "detection/detect";
    private static final String DETECTION_LANDMARK = "detection/landmark";
    private static final String DETECTION_LANDMARK68 = "detection/landmark68";
    private static final String DETECTION_FEATURE = "detection/feature";
    private static final String DETECTION_ATTRIBUTES = "detection/attributes";
    private static final String FACE_GET_INFO = "face/get_info";
    private static final String IMAGE_GET_INFO = "image/get_info";
    private static final String FACESET_CREATE = "faceset/create";
    private static final String FACESET_DELETE = "faceset/delete";
    private static final String FACESET_ADD_FACES = "faceset/add_faces";
    private static final String FACESET_REMOVE_FACES = "faceset/remove_faces";
    private static final String FACESET_GET_INFO = "faceset/get_info";
    private static final String FACESET_SET_INFO = "faceset/set_info";
    private static final String FACESET_TRAIN = "faceset/train";
    private static final String FACESET_GET_LIST = "faceset/get_list";
    private static final String RECOGNITION_CLUSTER = "recognition/cluster";
    private static final String RECOGNITION_COMPARE_FACE = "recognition/compare_face";
    private static final String RECOGNITION_COMPARE_FACE_FACESET = "recognition/compare_face_faceset";
    private static final String RECOGNITION_CELEBRITY = "recognition/celebrity";
    private static final String OBJECT_ROCOGNIZE = "object/recognize";
	
    public String apiKey    =null;
    public String apiSecret =null;
    public String apiVersion=null;

	private FaApi api=null;  
	
	
	//构造方法
    public  FaMethod(String apiKey,String apiSecret,String apiVersion)
    {
    	this.apiKey    =apiKey;
    	this.apiSecret =apiSecret;
    	this.apiVersion=apiVersion;
    	api            =new FaApi(apiKey,apiSecret,apiVersion);
    }
    //************************************
	// Method:      detection_detect
	// Description：对给定的图片进行人脸检测
	// Returns:     JSONObject类型数据，包括faceID和ImageID
	// Parameter:   String imageFile  图片存储路径，标准格式如下"C:\\Users\\Desktop\\1.jpg"
	//************************************
	public JSONObject detection_detect(String imageFile)
	{	   
		 FaFileData fileData=new FaFileData();
		 fileData.put("img_file", imageFile);
		 return api.execute(DETECTION_DETECT, fileData);
	}
	
	//************************************
	// Method:      detection_landmark
	// Description：计算给定脸的关键点
	// Returns:     JSONObject类型数据，返回值为计算后的结果
	// Parameter:   String faceId   在detection_detect方法中返回的JSONObject类中提取的faceId字符串
	//************************************
	public JSONObject detection_landmark(String faceId)
	{	   
		 FaFormData formData=new FaFormData();
		 formData.put("face_id", faceId);
		 return api.execute(DETECTION_LANDMARK, formData);
	}
	
	//************************************
	// Method:      detection_landmark68
	// Description：计算给定脸68个的关键点
	// Returns:     JSONObject类型数据，返回值为计算后的结果
	// Parameter:   String faceId   在detection_detect方法中返回的JSONObject类中提取的faceId字符串
	//************************************	
	public JSONObject detection_landmark68(String faceId)
	{	   
		 FaFormData formData=new FaFormData();
		 formData.put("face_id", faceId);
		 return api.execute(DETECTION_LANDMARK68, formData);
	}
	
	//************************************
	// Method:      detection_feature
	// Description：计算脸的特征，后续的识别功能需要使用这里提取的特征，结果会保存在服务器供后续功能使用
	// Returns:     JSONObject类型数据，包括服务器完整处理过程所用时间
	// Parameter:   String faceId   在detection_detect方法中返回的JSONObject类中提取的faceId字符串
	//************************************
	public JSONObject detection_feature(String faceId)
	{	   
		 FaFormData formData=new FaFormData();
		 formData.put("face_id", faceId);
		 return api.execute(DETECTION_FEATURE, formData);
	}
	
	//************************************
	// Method:      detection_attributes
	// Description：检测人脸的属性，包括表情、年龄、性别、种族等
	// Returns:     JSONObject类型数据，返回值为检测后的结果
	// Parameter:   String faceId   在detection_detect方法中返回的JSONObject类中提取的faceId字符串
	//************************************	
	public JSONObject detection_attributes(String faceId)
	{	   
		 FaFormData formData=new FaFormData();
		 formData.put("face_id", faceId);
		 return api.execute(DETECTION_ATTRIBUTES, formData);
	}
	
	//************************************
	// Method:      faceset_create
	// Description: 创建一个faceset
	// Returns:     JSONObject类型数据，返回值为创建成功后的faceset的ID键值对
	// Parameter:   String faceSetName  faceset的名字
	//************************************
	public JSONObject faceset_create(String faceSetName)
	{	   
		 FaFormData formData=new FaFormData();
		 formData.put("faceset_name", faceSetName);
		 return api.execute(FACESET_CREATE, formData);
	}
	
	//************************************
	// Method:      faceset_delete
	// Description: 删除一个faceset
	// Returns:     JSONObject类型数据，删除成功则返回success，值总是1
	// Parameter:   String faceSetId   在faceset_create方法中返回的JSONObject类中提取的faceSetId字符串
	//************************************
	public JSONObject faceset_delete(String faceSetId)
	{	   
		 FaFormData formData=new FaFormData();
		 formData.put("faceset_id", faceSetId);
		 return api.execute(FACESET_DELETE, formData);
	}
	
	//************************************
	// Method:      faceset_add_faces
	// Description: 向faceset中添加人脸
	// Returns:     JSONObject类型数据，返回添加成功的人脸的faceID
	// Parameter:   String faceSetId   在faceset_create方法中返回的JSONObject类中提取的faceSetId字符串
	// Parameter:   String faceId   在detection_detect方法中返回的JSONObject类中提取的faceId字符串。
	//				字符串可以由多个faceId组成，即可以同时添加多张脸，不同faceId用“,”隔开。
	//************************************
	public JSONObject faceset_add_faces(String faceSetId,String faceId)
	{	   
		 FaFormData formData=new FaFormData();
		 formData.put("faceset_id", faceSetId);
		 formData.put("face_id"   , faceId)   ;
		 return api.execute(FACESET_ADD_FACES, formData);
	}
	
	//************************************
	// Method:      faceset_add_faces
	// Description: 从faceset中删除人脸
	// Returns:     JSONObject类型数据，返回count ：删除的数目
	// Parameter:   String faceSetId   在faceset_create方法中返回的JSONObject类中提取的faceSetId字符串
	// Parameter:   String faceId   在detection_detect方法中返回的JSONObject类中提取的faceId字符串。
	//				字符串可以由多个faceId组成，即可以同时删除多张脸，不同faceId用“,”隔开。
	//************************************
	public JSONObject faceset_remove_faces(String faceSetId,String faceId)
	{	   
		 FaFormData formData=new FaFormData();
		 formData.put("faceset_id", faceSetId);
		 formData.put("face_id"   , faceId)   ;
		 return api.execute(FACESET_REMOVE_FACES, formData);
	}
	
	//************************************
	// Method:      faceset_get_info
	// Description: 获取faceset的详细信息
	// Returns:     JSONObject类型数据，返回给定faceset的详细信息
	// Parameter:   String faceSetId   在faceset_create方法中返回的JSONObject类中提取的faceSetId字符串
	//************************************
	public JSONObject faceset_get_info(String faceSetId)
	{	   
		 FaFormData formData=new FaFormData();
		 formData.put("faceset_id", faceSetId);
		 return api.execute(FACESET_GET_INFO, formData);
	}
	
	
	//************************************
	// Method:      faceset_set_info
	// Description: 修改faceset的名字
	// Returns:     JSONObject类型数据，修改成功则返回success，值总是1
	// Parameter:   String faceSetId   在faceset_create方法中返回的JSONObject类中提取的faceSetId字符串
	// Parameter:   String faceSetName  faceset修改后的名字
	//************************************
	public JSONObject faceset_set_info(String faceSetId,String faceSetName)
	{	   
		 FaFormData formData=new FaFormData();
		 formData.put("faceset_id", faceSetId);
		 formData.put("faceset_name",faceSetName);
		 return api.execute(FACESET_SET_INFO, formData);
	}
	
	//************************************
	// Method:      faceset_train
	// Description: 训练给定的faceset
	// Returns:     JSONObject类型数据，训练成功则返回success，值总是1，并返回服务器完整处理过程所用时间
	// Parameter:   String faceSetId   在faceset_create方法中返回的JSONObject类中提取的faceSetId字符串
	//************************************
	public JSONObject faceset_train(String faceSetId)
	{	   
		 FaFormData formData=new FaFormData();
		 formData.put("faceset_id", faceSetId);
		 return api.execute(FACESET_TRAIN, formData);
	}
	
	//************************************
	// Method:      FaceSet_GetList
	// Description: 获取faceset的列表
	// FullName:    FaceAllSDK::FaceAll::FaceSet_GetList
	// Returns:     bool  如果执行成功，将返回1，不成功则返回0，错误信息可以使用getErrorMsg()获得
	
	//************************************
	public JSONArray faceset_get_list()
	{	   
		 return api.executeArray(FACESET_GET_LIST,null,null);
	}
	
	public JSONObject recognition_cluster(String faceSetId)
	{	   
		 FaFormData formData=new FaFormData();
		 formData.put("faceset_id", faceSetId);
		 return api.execute(RECOGNITION_CLUSTER, formData);
	}
	
	public JSONObject recognition_compare_face(String faceId1,String faceId2)
	{	   
		 FaFormData formData=new FaFormData();
		 formData.put("face_id1", faceId1);
		 formData.put("face_id2", faceId2);
		 return api.execute(RECOGNITION_COMPARE_FACE, formData);
	}
	
	public JSONObject recognition_compare_face_faceset(String faceSetId,String faceId)
	{	   
		 FaFormData formData=new FaFormData();
		 formData.put("faceset_id", faceSetId);
		 formData.put("face_id"   , faceId);
		 return api.execute(RECOGNITION_COMPARE_FACE_FACESET, formData);
	}
	
	public JSONObject recognition_celebrity(String faceId)
	{	   
		 FaFormData formData=new FaFormData();
		 formData.put("face_id"   , faceId);
		 return api.execute(RECOGNITION_CELEBRITY, formData);
	}
	
	public JSONArray face_get_info(String faceId)
	{	   
		 FaFormData formData=new FaFormData();
		 formData.put("face_id"   , faceId);
		 return api.executeArray(FACE_GET_INFO, formData);
	}
	
	public JSONArray image_get_info(String imageId)
	{	   
		 FaFormData formData=new FaFormData();
		 formData.put("image_id"   , imageId);
		 return api.executeArray(IMAGE_GET_INFO, formData);
	}
	
	public JSONObject object_recognize(String imageFile)
	{	   
		 FaFileData fileData=new FaFileData();
		 fileData.put("img_file", imageFile);
		 return api.execute(OBJECT_ROCOGNIZE, fileData);
	}
	
}