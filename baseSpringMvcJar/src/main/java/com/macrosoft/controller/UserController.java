package com.macrosoft.controller;  
  
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.macrosoft.model.User;
import com.macrosoft.service.IUserService;
  
@Controller  
@RequestMapping(value="/user")  
public class UserController {  
	protected Log logger=LogFactory.getLog(getClass()); 
    /** 
     * 注入userService。 
     * 如果userService继承了某个接口， 
     * 这里类型必须是接口IUserService， 
     * 不能是类UserService，不知道为什么。 
     */  
    @Autowired  
    private IUserService userService;  
      
    @RequestMapping(value="/registe", method=RequestMethod.GET)  
    public String registe() {  
        User user = new User(0, "小马云3", "888");  
        userService.saveUser(user);  
        return "index";  
    }  
    
    
 

	

	
	
	
//	@RequestMapping(value = "/beforeList",method = RequestMethod.GET)
//	public String beforeList(Model model) { 
//		return "example/example_list";
//	}
//	/**
//	 * 查询数据
//	 * @param entity
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(value = "/list")
//	public @ResponseBody Map<String, Object> list(Example entity, ServletRequest request) {   
//		Page page=null; 
//    try{
//    	entity.initPageInfo();
//    	page =exampleService.listPage(entity, entity.getPageNoInt(), entity.getPageSizeInt()); 
//    }
//    catch(Exception ex){
//    	page=new Page();
//    	return ResponseUtils.sendPage(page,false);
//    }  
//		return ResponseUtils.sendPage(page);
//	}
//	 
//	@RequestMapping(value = "/beforeCreate")
//	public String beforeCreate(Model model) {
//		model.addAttribute("task", new Example());
//		model.addAttribute("action", "create");
//		return "example/example_add";
//	}
//  /**
//   * 新增数据
//   * @param newExample
//   * @param redirectAttributes
//   * @return
//   */
//	@RequestMapping(value = "/create", method = RequestMethod.POST)
//	public @ResponseBody Map<String, Object> create( Example entity, RedirectAttributes redirectAttributes) {
//		BaseForm temp=null;
//		try{  
//			temp=exampleService.create(entity); 
//			if(temp!=null){
//				temp.setSuccess(temp.getSuccess()); 
//				temp.setInfo(temp.getInfo()); 
//			}
//		}
//		catch(Exception ex){
//			temp=new BaseForm(); 
//			temp.setSuccess(false);
//			logger.error(ex,ex);
//		}
//		return ResponseUtils.sendBaseForm(temp); 
//	}
//  /**
//   * 修改数据之前的操作
//   * @param id
//   * @param model
//   * @return
//   */
//	@RequestMapping(value = "/beforeUpdate", method = RequestMethod.GET)
//	public String beforeUpdate(@PathVariable("id") Long id, Model model) {
//		model.addAttribute("task", exampleService.getById(id));
//		model.addAttribute("action", "update");
//		return "example/example_update";
//	}
//  /**
//   * 修改数据
//   * @param task
//   * @param redirectAttributes
//   * @return
//   */
//	@RequestMapping(value = "/update", method = RequestMethod.POST)
//	public @ResponseBody Map<String, Object> update(  Example entity, RedirectAttributes redirectAttributes) {
//		BaseForm temp=null;
//		try{  
//			temp=exampleService.update(entity); 
//			if(temp!=null){
//				temp.setSuccess(temp.getSuccess()); 
//				temp.setInfo(temp.getInfo()); 
//			}
//		}
//		catch(Exception ex){ 
//			temp=new BaseForm(); 
//			temp.setSuccess(false);
//			logger.error(ex,ex);
//		}
//		return ResponseUtils.sendBaseForm(temp); 
//	}
//  /**
//   * 删除数据
//   * @param entity
//   * @param redirectAttributes
//   * @return
//   */
//	@RequestMapping(value = "/delete")
//	public @ResponseBody Map<String, Object> delete(Example entity, RedirectAttributes redirectAttributes) {
//		BaseForm temp=null;
//		try{  
//			temp=exampleService.delete(entity.getIds(),entity); 
//			if(temp!=null){
//				temp.setSuccess(temp.getSuccess()); 
//				temp.setInfo(temp.getInfo()); 
//			}
//		}
//		catch(Exception ex){
//			temp.setSuccess(false);
//			logger.error(ex,ex);
//		}
//		return ResponseUtils.sendBaseForm(temp); 
//	}
//  /**
//   * 新增数据
//   * @param newExample
//   * @param redirectAttributes
//   * @return
//   */
//	@RequestMapping(value = "/listAge")
//	public @ResponseBody Map<String, Object> listAge(@Valid Example entity, RedirectAttributes redirectAttributes) {
//		List temp=null;
//		try{  
//			temp=exampleService.listAge(entity);  
//		}
//		catch(Exception ex){ 
//			logger.error(ex,ex); 
//		}
//		return ResponseUtils.sendList(temp,true); 
//	}
//	/**
//	 * 使用@ModelAttribute, 实现Struts2 Preparable二次部分绑定的效果,先根据form的id从数据库查出Example对象,
//	 * 再把Form提交的内容绑定到该对象上。
//	 * 因为仅update()方法的form中有id属性，因此本方法在该方法中执行.
//	 */
//	@ModelAttribute("preloadExample")
//	public Example getExample(@RequestParam(value = "id", required = false) Long id) {
//		if (id != null) {
//			return exampleService.getById(id);
//		}
//		return null;
//	}
//
//	/**
//	 * 取出Shiro中的当前用户Id.
//	 */
//	private Long getCurrentUserId() {
//		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
//		return user.id;
//	}
	
	
}  
