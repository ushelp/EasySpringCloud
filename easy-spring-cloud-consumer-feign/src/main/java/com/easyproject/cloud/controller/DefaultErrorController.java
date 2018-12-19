package com.easyproject.cloud.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("${server.error.path:${error.path:/error}}")
public class DefaultErrorController extends AbstractErrorController {
	
	Logger logger = LoggerFactory.getLogger(DefaultErrorController.class);
    public DefaultErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @Value("${server.error.path:${error.path:/error}}")
    public final String ERROR_PATH = "/error";

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
    /**
     * HTML error response
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(produces = "text/html")
    public ModelAndView errorHtml(HttpServletRequest request,
                                  HttpServletResponse response) {

        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        statusCode = statusCode == null ? HttpStatus.INTERNAL_SERVER_ERROR.value() : statusCode;
        response.setStatus(getStatus(request).value());
        Map<String, Object> model = new HashMap<String, Object>();
        switch (statusCode){
            case 404:
                break;
            case 500:
                break;
            default:
                statusCode = 500;
                break;
        }
        return new ModelAndView("error/"+statusCode+".html", model);
    }

    /**
     * JSON error response
     * @param request
     * @param exception
     * @return
     * @throws Exception
     */
    @RequestMapping
    public Map<String, Object> error(HttpServletRequest request,Exception exception) throws Exception {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        statusCode = statusCode == null ? HttpStatus.INTERNAL_SERVER_ERROR.value() : statusCode;
        Map<String, Object> body = new HashMap<String, Object>();
        HttpStatus status = getStatus(request);
        
        body.put("code",statusCode);
        body.put("message",HttpStatus.valueOf(statusCode));
        body.put("data",null);
        body.put("exception",exception.getMessage());
        return body;
    }

}