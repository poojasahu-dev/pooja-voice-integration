/**
 *
 */
package hm.voice.ecommerce.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.ResourceBreadcrumbBuilder;
import de.hybris.platform.acceleratorstorefrontcommons.constants.WebConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.ThirdPartyConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractSearchPageController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractSearchPageController.ShowMode;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.commercefacades.order.SaveCartFacade;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author Pooja
 *
 */
@Controller
@RequestMapping("/my-account-voice/saved-carts-voice")
public class AccountSavedCartsPageControllerVoice extends AbstractSearchPageController
{

	@Resource(name = "accountBreadcrumbBuilder")
	private ResourceBreadcrumbBuilder accountBreadcrumbBuilder;

	@Resource(name = "saveCartFacade")
	private SaveCartFacade saveCartFacade;

	private static final String REFRESH_UPLOADING_SAVED_CART = "refresh.uploading.saved.cart";
	private static final String REFRESH_UPLOADING_SAVED_CART_INTERVAL = "refresh.uploading.saved.cart.interval";
	private static final String SAVED_CARTS_CMS_PAGE = "saved-carts-voice";


	@RequestMapping(method = RequestMethod.GET)
	@RequireHardLogIn
	public String savedCarts(@RequestParam(value = "page", defaultValue = "0")
	final int page, @RequestParam(value = "show", defaultValue = "Page")
	final ShowMode showMode, @RequestParam(value = "sort", required = false)
	final String sortCode, @RequestParam(value = "isVoice", defaultValue = "0")
	final int isVoice, final Model model) throws CMSItemNotFoundException
	{
		// Handle paged search results
		final PageableData pageableData = createPageableData(page, 5, sortCode, showMode);
		final SearchPageData<CartData> searchPageData = saveCartFacade.getSavedCartsForCurrentUser(pageableData, null);
		populateModel(model, searchPageData, showMode);

		model.addAttribute("refreshSavedCart", getSiteConfigService().getBoolean(REFRESH_UPLOADING_SAVED_CART, false));
		model.addAttribute("refreshSavedCartInterval", getSiteConfigService().getLong(REFRESH_UPLOADING_SAVED_CART_INTERVAL, 0));
		model.addAttribute("isVoice", isVoice);

		final ContentPageModel savedCartsPage = getContentPageForLabelOrId(SAVED_CARTS_CMS_PAGE);
		storeCmsPageInModel(model, savedCartsPage);
		setUpMetaDataForContentPage(model, savedCartsPage);
		model.addAttribute(WebConstants.BREADCRUMBS_KEY, accountBreadcrumbBuilder.getBreadcrumbs("text.account.savedCarts"));
		model.addAttribute(ThirdPartyConstants.SeoRobots.META_ROBOTS, ThirdPartyConstants.SeoRobots.NOINDEX_NOFOLLOW);
		return getViewForPage(model);
	}

}
