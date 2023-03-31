package kh.study.shop.config;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class UploadFileUtil {
	//파일이 첨부될 경로
	private static final String UPLOAD_PATH = "D:\\workspaceSTS\\Shop\\src\\main\\resources\\static\\images\\";
	
	//파일 첨부
	public static void uploadFile(MultipartFile mainImg) {
		//실제 첨부파일이 있을 떄만 첨부 기능 실행
		if(!mainImg.isEmpty()) {
			//첨부하려는 원본 파일명
			String originFileName = mainImg.getOriginalFilename();
			
			//파일명 중복을 막기 위해 랜덤한 파이명을 문자열로 생성
			String uuid = UUID.randomUUID().toString();
			//확장자 추출
			String  extension = originFileName.substring(originFileName.lastIndexOf("."));
			
			//첨부될 파일명 생성
			String fileName = uuid + extension; //"asxvge" + ".jpg"
			
			try {
				//파일 객체 생성
				File file = new File(UPLOAD_PATH + fileName);

				mainImg.transferTo(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	//다중 파일 첨부
	public static void multiUploadFile(List<MultipartFile> subImgs) {
		//첨두된 파일의 개수만큼 첨부를 시작
		for(MultipartFile subImg : subImgs) { 
			uploadFile(subImg);
		}
	}

}







