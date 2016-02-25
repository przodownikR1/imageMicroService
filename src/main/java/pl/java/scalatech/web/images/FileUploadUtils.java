package pl.java.scalatech.web.images;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtils {
	public static final long DEFAULT_MAX_SIZE = 52428800;
	
	public static String defaultBaseDir = "upload";

	public static final String upload(HttpServletRequest request,
			MultipartFile file) throws Exception {
		String filename = extractFilename(file, defaultBaseDir);
		File desc = getAbsoluteFile(extractUploadDir(request), filename);
		file.transferTo(desc);
		return filename;
	}

	private static final File getAbsoluteFile(String uploadDir, String filename)
			throws IOException {
		if (uploadDir.endsWith("/")) {
			uploadDir = uploadDir.substring(0, uploadDir.length() - 1);
		}
		if (filename.startsWith("/")) {
			filename = filename.substring(0, uploadDir.length() - 1);
		}
		File desc = new File(uploadDir + "/" + filename);
		if (!desc.getParentFile().exists()) {
			desc.getParentFile().mkdirs();
		}
		if (!desc.exists()) {
			desc.createNewFile();
		}
		return desc;
	}

	public static final String extractFilename(MultipartFile file,
			String baseDir) throws UnsupportedEncodingException {
		String filename = file.getOriginalFilename();
		int slashIndex = filename.indexOf("/");
		if (slashIndex >= 0) {
			filename = filename.substring(slashIndex + 1);
		}
		filename = baseDir + "/" + filename;
		return filename;
	}

	public static final String extractUploadDir(HttpServletRequest request) {
		return request.getServletContext().getRealPath("/");
	}
}
 

