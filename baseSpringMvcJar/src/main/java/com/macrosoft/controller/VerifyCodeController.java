package com.macrosoft.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.macrosoft.common.constant.CommonConst;
import com.macrosoft.common.verifyCode.VerifyCodeUtils;

@Controller
@RequestMapping(value = "/verfityCode")
public class VerifyCodeController {
	private static final HttpHeaders HTTP_HEADERS;

	static {
		HTTP_HEADERS = new HttpHeaders();
		HTTP_HEADERS.set("Pragma", "No-cache");
		HTTP_HEADERS.set("Cache-Control", "No-cache");
		HTTP_HEADERS.setDate("Expires", 0);
		HTTP_HEADERS.setContentType(MediaType.IMAGE_JPEG);
	}

	@RequestMapping(value = "/getCodeImage", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getCodeImage(HttpSession session,int width,int height) {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			String code = VerifyCodeUtils.outputVerifyImage(width, height, os, 4);
			session.setAttribute(CommonConst.VALID_CODE, code);
			return new ResponseEntity<byte[]>(os.toByteArray(), HTTP_HEADERS,
					HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(os);
		}
		return null;
	}
}
