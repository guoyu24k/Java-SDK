package cn.faceall.sdk;
import org.json.JSONObject;
import org.json.JSONArray;

public class FaMethod {
	//����
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
	
	
	//���췽��
    public  FaMethod(String apiKey,String apiSecret,String apiVersion)
    {
    	this.apiKey    =apiKey;
    	this.apiSecret =apiSecret;
    	this.apiVersion=apiVersion;
    	api            =new FaApi(apiKey,apiSecret,apiVersion);
    }
    //************************************
	// Method:      detection_detect
	// Description���Ը�����ͼƬ�����������
	// Returns:     JSONObject�������ݣ�����faceID��ImageID
	// Parameter:   String imageFile  ͼƬ�洢·������׼��ʽ����"C:\\Users\\Desktop\\1.jpg"
	//************************************
	public JSONObject detection_detect(String imageFile)
	{	   
		 FaFileData fileData=new FaFileData();
		 fileData.put("img_file", imageFile);
		 return api.execute(DETECTION_DETECT, fileData);
	}
	
	//************************************
	// Method:      detection_landmark
	// Description������������Ĺؼ���
	// Returns:     JSONObject�������ݣ�����ֵΪ�����Ľ��
	// Parameter:   String faceId   ��detection_detect�����з��ص�JSONObject������ȡ��faceId�ַ���
	//************************************
	public JSONObject detection_landmark(String faceId)
	{	   
		 FaFormData formData=new FaFormData();
		 formData.put("face_id", faceId);
		 return api.execute(DETECTION_LANDMARK, formData);
	}
	
	//************************************
	// Method:      detection_landmark68
	// Description�����������68���Ĺؼ���
	// Returns:     JSONObject�������ݣ�����ֵΪ�����Ľ��
	// Parameter:   String faceId   ��detection_detect�����з��ص�JSONObject������ȡ��faceId�ַ���
	//************************************	
	public JSONObject detection_landmark68(String faceId)
	{	   
		 FaFormData formData=new FaFormData();
		 formData.put("face_id", faceId);
		 return api.execute(DETECTION_LANDMARK68, formData);
	}
	
	//************************************
	// Method:      detection_feature
	// Description����������������������ʶ������Ҫʹ��������ȡ������������ᱣ���ڷ���������������ʹ��
	// Returns:     JSONObject�������ݣ��������������������������ʱ��
	// Parameter:   String faceId   ��detection_detect�����з��ص�JSONObject������ȡ��faceId�ַ���
	//************************************
	public JSONObject detection_feature(String faceId)
	{	   
		 FaFormData formData=new FaFormData();
		 formData.put("face_id", faceId);
		 return api.execute(DETECTION_FEATURE, formData);
	}
	
	//************************************
	// Method:      detection_attributes
	// Description��������������ԣ��������顢���䡢�Ա������
	// Returns:     JSONObject�������ݣ�����ֵΪ����Ľ��
	// Parameter:   String faceId   ��detection_detect�����з��ص�JSONObject������ȡ��faceId�ַ���
	//************************************	
	public JSONObject detection_attributes(String faceId)
	{	   
		 FaFormData formData=new FaFormData();
		 formData.put("face_id", faceId);
		 return api.execute(DETECTION_ATTRIBUTES, formData);
	}
	
	//************************************
	// Method:      faceset_create
	// Description: ����һ��faceset
	// Returns:     JSONObject�������ݣ�����ֵΪ�����ɹ����faceset��ID��ֵ��
	// Parameter:   String faceSetName  faceset������
	//************************************
	public JSONObject faceset_create(String faceSetName)
	{	   
		 FaFormData formData=new FaFormData();
		 formData.put("faceset_name", faceSetName);
		 return api.execute(FACESET_CREATE, formData);
	}
	
	//************************************
	// Method:      faceset_delete
	// Description: ɾ��һ��faceset
	// Returns:     JSONObject�������ݣ�ɾ���ɹ��򷵻�success��ֵ����1
	// Parameter:   String faceSetId   ��faceset_create�����з��ص�JSONObject������ȡ��faceSetId�ַ���
	//************************************
	public JSONObject faceset_delete(String faceSetId)
	{	   
		 FaFormData formData=new FaFormData();
		 formData.put("faceset_id", faceSetId);
		 return api.execute(FACESET_DELETE, formData);
	}
	
	//************************************
	// Method:      faceset_add_faces
	// Description: ��faceset���������
	// Returns:     JSONObject�������ݣ�������ӳɹ���������faceID
	// Parameter:   String faceSetId   ��faceset_create�����з��ص�JSONObject������ȡ��faceSetId�ַ���
	// Parameter:   String faceId   ��detection_detect�����з��ص�JSONObject������ȡ��faceId�ַ�����
	//				�ַ��������ɶ��faceId��ɣ�������ͬʱ��Ӷ���������ͬfaceId�á�,��������
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
	// Description: ��faceset��ɾ������
	// Returns:     JSONObject�������ݣ�����count ��ɾ������Ŀ
	// Parameter:   String faceSetId   ��faceset_create�����з��ص�JSONObject������ȡ��faceSetId�ַ���
	// Parameter:   String faceId   ��detection_detect�����з��ص�JSONObject������ȡ��faceId�ַ�����
	//				�ַ��������ɶ��faceId��ɣ�������ͬʱɾ������������ͬfaceId�á�,��������
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
	// Description: ��ȡfaceset����ϸ��Ϣ
	// Returns:     JSONObject�������ݣ����ظ���faceset����ϸ��Ϣ
	// Parameter:   String faceSetId   ��faceset_create�����з��ص�JSONObject������ȡ��faceSetId�ַ���
	//************************************
	public JSONObject faceset_get_info(String faceSetId)
	{	   
		 FaFormData formData=new FaFormData();
		 formData.put("faceset_id", faceSetId);
		 return api.execute(FACESET_GET_INFO, formData);
	}
	
	
	//************************************
	// Method:      faceset_set_info
	// Description: �޸�faceset������
	// Returns:     JSONObject�������ݣ��޸ĳɹ��򷵻�success��ֵ����1
	// Parameter:   String faceSetId   ��faceset_create�����з��ص�JSONObject������ȡ��faceSetId�ַ���
	// Parameter:   String faceSetName  faceset�޸ĺ������
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
	// Description: ѵ��������faceset
	// Returns:     JSONObject�������ݣ�ѵ���ɹ��򷵻�success��ֵ����1�������ط��������������������ʱ��
	// Parameter:   String faceSetId   ��faceset_create�����з��ص�JSONObject������ȡ��faceSetId�ַ���
	//************************************
	public JSONObject faceset_train(String faceSetId)
	{	   
		 FaFormData formData=new FaFormData();
		 formData.put("faceset_id", faceSetId);
		 return api.execute(FACESET_TRAIN, formData);
	}
	
	//************************************
	// Method:      FaceSet_GetList
	// Description: ��ȡfaceset���б�
	// FullName:    FaceAllSDK::FaceAll::FaceSet_GetList
	// Returns:     bool  ���ִ�гɹ���������1�����ɹ��򷵻�0��������Ϣ����ʹ��getErrorMsg()���
	
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