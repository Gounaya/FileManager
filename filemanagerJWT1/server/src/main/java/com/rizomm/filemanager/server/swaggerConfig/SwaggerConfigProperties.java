package com.rizomm.filemanager.server.swaggerConfig;

import lombok.Data;
import lombok.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration("swaggerConfigProperties")
public class SwaggerConfigProperties {
    private String apiVersion = "1.0";
    private String enabled = "true";
    private String title= "File Manager 3.0";
    private String description="Sample Swagger implementation for the `File Manager 3.0` service, leveraging annotations at the controller-method level.";
    private String useDefaultResponseMessages="false";
    private String enableUrlTemplating="false";
    private String deepLinking="true";
    private String defaultModelsExpandDepth="1";
    private String defaultModelExpandDepth="1";
    private String displayOperationId="false";
    private String displayRequestDuration="false";
    private String filter="false";
    private String maxDisplayedTags="0";
    private String showExtensions="false";
}

