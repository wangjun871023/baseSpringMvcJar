package com.macrosoft.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

	private int width;
	private int height;

	@RequestMapping(value = "/getCodeImage", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getCodeImage(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			String code = VerifyCodeUtils.outputVerifyImage(width, height, os, 4);
			session.setAttribute("verfityCode", code);
			return new ResponseEntity<byte[]>(os.toByteArray(), HTTP_HEADERS,
					HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(os);
		}
		return null;
	}

	@Value("100")
	public void setWidth(int width) {
		this.width = width;
	}

	@Value("18")
	public void setHeight(int height) {
		this.height = height;
	}
}
