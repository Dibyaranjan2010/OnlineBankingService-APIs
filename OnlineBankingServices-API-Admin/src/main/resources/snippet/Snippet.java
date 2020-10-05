package snippet;

public class Snippet {
	## MULTIPART (MultipartProperties)
	# Enable multipart uploads
	spring.servlet.multipart.enabled=true
	# Threshold after which files are written to disk.
	spring.servlet.multipart.file-size-threshold=2KB
	# Max file size.
	spring.servlet.multipart.max-file-size=200MB
	# Max Request Size
	spring.servlet.multipart.max-request-size=215MB
	
	## File Storage Properties
	# All files uploaded through the REST API will be stored in this directory
	file.upload-dir=C:\Users\Dibya.Mishra\Documents\workspace-spring-tool-suite-4-4.7.1.RELEASE\OnlineBankingServices-API-Admin\src\main\resources
}
