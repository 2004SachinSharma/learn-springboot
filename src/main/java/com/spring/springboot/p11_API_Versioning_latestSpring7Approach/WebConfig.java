package com.spring.springboot.p11_API_Versioning_latestSpring7Approach;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ApiVersionConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void configureApiVersioning(ApiVersionConfigurer configurer) {

//        for uri versioning

//        configurer.usePathSegment(2).addSupportedVersions("1", "2");

//        for requestParameter versioning

//        configurer.useQueryParam("versionvalue")
//                  .addSupportedVersions("1", "2")
//                  .setDefaultVersion("1"); //when no any QueryParameter be sent from the client in that condition, the default version to be chosen

//        for RequestHeader Versioning

//               configurer
//                .useRequestHeader("X-API-VERSION")
//                .addSupportedVersions("1", "2")
//                  .setDefaultVersion("1"); //when no any Header be sent from the client in that condition, the default version to be chosen

//        for MediatypeVersioning

        configurer.useMediaTypeParameter(
                MediaType.parseMediaType(
                        "application/vnd.myapp+json"),
                        "v")
                  .addSupportedVersions("1", "2");

//        Rest no need to change anything in the API, all same for all three except URI versioning where extra path variable needed to be put in the path for extracting the version


    }
}
