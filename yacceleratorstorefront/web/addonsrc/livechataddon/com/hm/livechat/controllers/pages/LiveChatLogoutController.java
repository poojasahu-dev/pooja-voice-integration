/**
 *
 */
package com.hm.livechat.controllers.pages;

import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author Pooja
 *
 */
@Controller
@RequestMapping(value = "/resetFlag")
public class LiveChatLogoutController
{
	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "modelService")
	private ModelService modelService;

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public String isCurrentlyActiveFlagReset()
	{
		final UserModel userModel = userService.getCurrentUser();
		userModel.setIsCurrentlyActive(false);
		modelService.save(userModel);
		modelService.refresh(userModel);
		return "success";
	}

}
