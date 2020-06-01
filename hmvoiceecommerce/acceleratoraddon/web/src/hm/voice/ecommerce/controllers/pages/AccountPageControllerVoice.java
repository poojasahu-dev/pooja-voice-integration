/**
 *
 */
package hm.voice.ecommerce.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.forms.AddressForm;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.Breadcrumb;
import de.hybris.platform.acceleratorstorefrontcommons.forms.validation.AddressValidator;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.ResourceBreadcrumbBuilder;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.ThirdPartyConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractSearchPageController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.acceleratorstorefrontcommons.forms.verification.AddressVerificationResultHandler;
import de.hybris.platform.acceleratorstorefrontcommons.util.AddressDataUtil;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.commercefacades.address.AddressVerificationFacade;
import de.hybris.platform.commercefacades.address.data.AddressVerificationResult;
import de.hybris.platform.commercefacades.customer.CustomerFacade;
import de.hybris.platform.commercefacades.i18n.I18NFacade;
import de.hybris.platform.commercefacades.order.CheckoutFacade;
import de.hybris.platform.commercefacades.order.OrderFacade;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.commercefacades.order.data.OrderHistoryData;
import de.hybris.platform.commercefacades.user.UserFacade;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commercefacades.user.data.CountryData;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commercefacades.user.data.TitleData;
import de.hybris.platform.commerceservices.address.AddressVerificationDecision;
import de.hybris.platform.commerceservices.enums.CountryType;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;

import de.hybris.platform.util.Config;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * @author Pooja
 *
 */
@Controller
@RequestMapping(value = "/my-account-voice")
public class AccountPageControllerVoice extends AbstractSearchPageController
{
	@Resource(name = "orderFacade")
	private OrderFacade orderFacade;

	@Resource(name = "userFacade")
	private UserFacade userFacade;

	@Resource(name = "i18NFacade")
	private I18NFacade i18NFacade;

	@Resource(name = "customerFacade")
	private CustomerFacade customerFacade;

	@Resource(name = "acceleratorCheckoutFacade")
	private CheckoutFacade checkoutFacade;

	@Resource(name = "addressValidator")
	private AddressValidator addressValidator;

	@Resource(name = "addressVerificationFacade")
	private AddressVerificationFacade addressVerificationFacade;

	@Resource(name = "addressVerificationResultHandler")
	private AddressVerificationResultHandler addressVerificationResultHandler;

	@Resource(name = "addressDataUtil")
	private AddressDataUtil addressDataUtil;

	String ADD_ON_PREFIX = "addon:";
	String VIEW_PAGE_PREFIX = ADD_ON_PREFIX + "/" + "hmvoiceecommerce" + "/";
	String countryAddressForm = VIEW_PAGE_PREFIX + "fragments/countryAddressFormVoice";

	protected I18NFacade getI18NFacade()
	{
		return i18NFacade;
	}

	protected AddressValidator getAddressValidator()
	{
		return addressValidator;
	}

	protected AddressVerificationFacade getAddressVerificationFacade()
	{
		return addressVerificationFacade;
	}

	protected AddressVerificationResultHandler getAddressVerificationResultHandler()
	{
		return addressVerificationResultHandler;
	}

	@Resource(name = "accountBreadcrumbBuilder")
	private ResourceBreadcrumbBuilder accountBreadcrumbBuilder;


	@ModelAttribute("countries")
	public Collection<CountryData> getCountries()
	{
		return checkoutFacade.getCountries(CountryType.SHIPPING);
	}

	@ModelAttribute("titles")
	public Collection<TitleData> getTitles()
	{
		return userFacade.getTitles();
	}

	@ModelAttribute("countryDataMap")
	public Map<String, CountryData> getCountryDataMap()
	{
		final Map<String, CountryData> countryDataMap = new HashMap<>();
		for (final CountryData countryData : getCountries())
		{
			countryDataMap.put(countryData.getIsocode(), countryData);
		}
		return countryDataMap;
	}


	@RequestMapping(value = "/addressform", method = RequestMethod.GET)
	public String getCountryAddressForm(@RequestParam("addressCode")
	final String addressCode, @RequestParam("countryIsoCode")
	final String countryIsoCode, final Model model)
	{
		model.addAttribute("supportedCountries", getCountries());
		populateModelRegionAndCountry(model, countryIsoCode);

		final AddressForm addressForm = new AddressForm();
		model.addAttribute(ADDRESS_FORM_ATTR, addressForm);
		for (final AddressData addressData : userFacade.getAddressBook())
		{
			if (addressData.getId() != null && addressData.getId().equals(addressCode)
					&& countryIsoCode.equals(addressData.getCountry().getIsocode()))
			{
				model.addAttribute(ADDRESS_DATA_ATTR, addressData);
				addressDataUtil.convert(addressData, addressForm);
				break;
			}
		}
		return countryAddressForm;
	}



	private static final String TEXT_ACCOUNT_ADDRESS_BOOK = "text.account.addressBook";
	private static final String ORDER_CODE_PATH_VARIABLE_PATTERN = "{orderCode:.*}";
	private static final String REDIRECT_TO_ORDER_HISTORY_PAGE = REDIRECT_PREFIX + "/my-account/orders";
	private static final String ADDRESS_CODE_PATH_VARIABLE_PATTERN = "{addressCode:.*}";
	private static final String COUNTRY_DATA_ATTR = "countryData";
	private static final String ADDRESS_BOOK_EMPTY_ATTR = "addressBookEmpty";
	private static final String TITLE_DATA_ATTR = "titleData";
	private static final String ADDRESS_FORM_ATTR = "addressForm";
	private static final String COUNTRY_ATTR = "country";
	private static final String REGIONS_ATTR = "regions";
	private static final String IS_DEFAULT_ADDRESS_ATTR = "isDefaultAddress";
	private static final String MY_ACCOUNT_ADDRESS_BOOK_URL = "/my-account-voice/address-book-voice";
	private static final String REDIRECT_TO_ADDRESS_BOOK_PAGE = REDIRECT_PREFIX + MY_ACCOUNT_ADDRESS_BOOK_URL;
	private static final String FORM_GLOBAL_ERROR = "form.global.error";

	private static final String BREADCRUMBS_ATTR = "breadcrumbs";
	private static final String ADDRESS_DATA_ATTR = "addressData";
	//	private static final String ORDER_HISTORY_CMS_PAGE = "orders";
	private static final String ORDER_HISTORY_CMS_PAGE = "orders-voice";
	private static final String ADDRESS_BOOK_CMS_PAGE = "address-book-voice";
	private static final String ORDER_DETAIL_CMS_PAGE = "order-voice";
	private static final String ADD_EDIT_ADDRESS_CMS_PAGE = "add-edit-address-voice";
	private static final String REDIRECT_TO_EDIT_ADDRESS_PAGE = REDIRECT_PREFIX + "/my-account-voice/edit-address/";


	@RequestMapping(value = "/ordersVoice", method = RequestMethod.GET)
	@RequireHardLogIn
	public String ordersVoice(@RequestParam(value = "page", defaultValue = "0")
	final int page, @RequestParam(value = "show", defaultValue = "Page")
	final ShowMode showMode, @RequestParam(value = "sort", required = false)
	final String sortCode, @RequestParam(value = "dateNumber", defaultValue = "0")
	final int dateNumber, final Model model) throws CMSItemNotFoundException
	{
		// Handle paged search results
		final PageableData pageableData = createPageableData(page, 5, sortCode, showMode);
		final SearchPageData<OrderHistoryData> searchPageData = orderFacade.getPagedOrderHistoryForStatuses(pageableData);
		/************************************************************************************************/
		final int paginationVoice;
		int counter = 0;
		final List<OrderHistoryData> list = searchPageData.getResults();
		final Date date = new Date();
		final List<OrderHistoryData> listToAdd = new ArrayList<OrderHistoryData>();
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -dateNumber);
		final Date todate1 = cal.getTime();
		if (dateNumber > 0)
		{
			final PageableData pageableDataVoice = createPageableData(0, 5, sortCode, ShowMode.All);
			final SearchPageData<OrderHistoryData> searchPageDataVoice = orderFacade
					.getPagedOrderHistoryForStatuses(pageableDataVoice);
			for (final OrderHistoryData data : searchPageDataVoice.getResults())
			{
				final Date orderDate = data.getPlaced();
				if (orderDate.after(todate1))
				{
					counter++;
				}
			}
			if (counter % 5 == 0)
			{
				paginationVoice = counter / 5;
			}
			else
			{
				paginationVoice = (counter / 5) + 1;
			}
			for (final OrderHistoryData data : list)
			{
				final Date orderDate = data.getPlaced();
				if (orderDate.after(todate1))
				{
					listToAdd.add(data);
				}
			}
			searchPageData.setResults(listToAdd);
			searchPageData.getPagination().setTotalNumberOfResults(counter);
			searchPageData.getPagination().setNumberOfPages(paginationVoice);
		}

		/************************************************************************************************/

		populateModel(model, searchPageData, showMode);
		final ContentPageModel orderHistoryPage = getContentPageForLabelOrId(ORDER_HISTORY_CMS_PAGE);
		storeCmsPageInModel(model, orderHistoryPage);
		setUpMetaDataForContentPage(model, orderHistoryPage);
		model.addAttribute("dateNumber", dateNumber);
		model.addAttribute("pageNumber", page);
		model.addAttribute(BREADCRUMBS_ATTR, accountBreadcrumbBuilder.getBreadcrumbs("text.account.orderHistory"));
		model.addAttribute(ThirdPartyConstants.SeoRobots.META_ROBOTS, ThirdPartyConstants.SeoRobots.NOINDEX_NOFOLLOW);
		return getViewForPage(model);
	}

	@RequestMapping(value = "/address-book-voice", method = RequestMethod.GET)
	@RequireHardLogIn
	public String getAddressBook(final Model model) throws CMSItemNotFoundException
	{
		model.addAttribute(ADDRESS_DATA_ATTR, userFacade.getAddressBook());
		final ContentPageModel addressBookPage = getContentPageForLabelOrId(ADDRESS_BOOK_CMS_PAGE);
		storeCmsPageInModel(model, addressBookPage);
		setUpMetaDataForContentPage(model, addressBookPage);
		model.addAttribute(BREADCRUMBS_ATTR, accountBreadcrumbBuilder.getBreadcrumbs(TEXT_ACCOUNT_ADDRESS_BOOK));
		model.addAttribute(ThirdPartyConstants.SeoRobots.META_ROBOTS, ThirdPartyConstants.SeoRobots.NOINDEX_NOFOLLOW);
		return getViewForPage(model);
	}


	@RequestMapping(value = "/order/" + ORDER_CODE_PATH_VARIABLE_PATTERN, method = RequestMethod.GET)
	@RequireHardLogIn
	public String order(@PathVariable("orderCode")
	final String orderCode, final Model model, final RedirectAttributes redirectModel) throws CMSItemNotFoundException
	{
		try
		{
			final OrderData orderDetails = orderFacade.getOrderDetailsForCode(orderCode);
			model.addAttribute("orderData", orderDetails);

			final List<Breadcrumb> breadcrumbs = accountBreadcrumbBuilder.getBreadcrumbs(null);
			breadcrumbs.add(new Breadcrumb("/my-account/orders",
					getMessageSource().getMessage("text.account.orderHistory", null, getI18nService().getCurrentLocale()), null));
			breadcrumbs.add(new Breadcrumb("#", getMessageSource().getMessage("text.account.order.orderBreadcrumb", new Object[]
			{ orderDetails.getCode() }, "Order {0}", getI18nService().getCurrentLocale()), null));
			model.addAttribute(BREADCRUMBS_ATTR, breadcrumbs);

		}
		catch (final UnknownIdentifierException e)
		{
			/*
			 * LOG.warn("Attempted to load a order that does not exist or is not visible", e);
			 * GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.ERROR_MESSAGES_HOLDER,
			 * "system.error.page.not.found", null); return REDIRECT_TO_ORDER_HISTORY_PAGE;
			 */
			e.printStackTrace();
		}
		final ContentPageModel orderDetailPage = getContentPageForLabelOrId(ORDER_DETAIL_CMS_PAGE);
		storeCmsPageInModel(model, orderDetailPage);
		model.addAttribute(ThirdPartyConstants.SeoRobots.META_ROBOTS, ThirdPartyConstants.SeoRobots.NOINDEX_NOFOLLOW);
		setUpMetaDataForContentPage(model, orderDetailPage);
		return getViewForPage(model);
	}



	@RequestMapping(value = "/edit-address/" + ADDRESS_CODE_PATH_VARIABLE_PATTERN, method = RequestMethod.GET)
	@RequireHardLogIn
	public String editAddress(@PathVariable("addressCode")
	final String addressCode, final Model model) throws CMSItemNotFoundException
	{
		final AddressForm addressForm = new AddressForm();
		model.addAttribute(COUNTRY_DATA_ATTR, checkoutFacade.getCountries(CountryType.SHIPPING));
		model.addAttribute(TITLE_DATA_ATTR, userFacade.getTitles());
		model.addAttribute(ADDRESS_FORM_ATTR, addressForm);
		final List<AddressData> addressBook = userFacade.getAddressBook();
		model.addAttribute(ADDRESS_BOOK_EMPTY_ATTR, Boolean.valueOf(CollectionUtils.isEmpty(addressBook)));


		for (final AddressData addressData : addressBook)
		{
			if (addressData.getId() != null && addressData.getId().equals(addressCode))
			{
				model.addAttribute(REGIONS_ATTR, getI18NFacade().getRegionsForCountryIso(addressData.getCountry().getIsocode()));
				model.addAttribute(COUNTRY_ATTR, addressData.getCountry().getIsocode());
				model.addAttribute(ADDRESS_DATA_ATTR, addressData);
				addressDataUtil.convert(addressData, addressForm);

				if (userFacade.isDefaultAddress(addressData.getId()))
				{
					addressForm.setDefaultAddress(Boolean.TRUE);
					model.addAttribute(IS_DEFAULT_ADDRESS_ATTR, Boolean.TRUE);
				}
				else
				{
					addressForm.setDefaultAddress(Boolean.FALSE);
					model.addAttribute(IS_DEFAULT_ADDRESS_ATTR, Boolean.FALSE);
				}
				break;
			}
		}
		final ContentPageModel addEditAddressPage = getContentPageForLabelOrId(ADD_EDIT_ADDRESS_CMS_PAGE);
		storeCmsPageInModel(model, addEditAddressPage);
		setUpMetaDataForContentPage(model, addEditAddressPage);

		final List<Breadcrumb> breadcrumbs = accountBreadcrumbBuilder.getBreadcrumbs(null);
		breadcrumbs.add(new Breadcrumb(MY_ACCOUNT_ADDRESS_BOOK_URL,
				getMessageSource().getMessage(TEXT_ACCOUNT_ADDRESS_BOOK, null, getI18nService().getCurrentLocale()), null));
		breadcrumbs.add(new Breadcrumb("#",
				getMessageSource().getMessage("text.account.addressBook.addEditAddress", null, getI18nService().getCurrentLocale()),
				null));
		model.addAttribute(BREADCRUMBS_ATTR, breadcrumbs);
		model.addAttribute(ThirdPartyConstants.SeoRobots.META_ROBOTS, ThirdPartyConstants.SeoRobots.NOINDEX_NOFOLLOW);
		model.addAttribute("edit", Boolean.TRUE);
		return getViewForPage(model);
	}

	protected void setUpAddressFormAfterError(final AddressForm addressForm, final Model model)
	{
		model.addAttribute(COUNTRY_DATA_ATTR, checkoutFacade.getCountries(CountryType.SHIPPING));
		model.addAttribute(TITLE_DATA_ATTR, userFacade.getTitles());
		model.addAttribute(ADDRESS_BOOK_EMPTY_ATTR, Boolean.valueOf(CollectionUtils.isEmpty(userFacade.getAddressBook())));
		model.addAttribute(IS_DEFAULT_ADDRESS_ATTR, Boolean.valueOf(userFacade.isDefaultAddress(addressForm.getAddressId())));
		if (addressForm.getCountryIso() != null)
		{
			populateModelRegionAndCountry(model, addressForm.getCountryIso());
		}
	}

	protected void populateModelRegionAndCountry(final Model model, final String countryIsoCode)
	{
		model.addAttribute(REGIONS_ATTR, getI18NFacade().getRegionsForCountryIso(countryIsoCode));
		model.addAttribute(COUNTRY_ATTR, countryIsoCode);
	}

	@RequestMapping(value = "/edit-address/" + ADDRESS_CODE_PATH_VARIABLE_PATTERN, method = RequestMethod.POST)
	@RequireHardLogIn
	public String editAddress(final AddressForm addressForm, final BindingResult bindingResult, final Model model,
			final RedirectAttributes redirectModel) throws CMSItemNotFoundException
	{
		getAddressValidator().validate(addressForm, bindingResult);
		final ContentPageModel addEditAddressPage = getContentPageForLabelOrId(ADD_EDIT_ADDRESS_CMS_PAGE);
		if (bindingResult.hasErrors())
		{
			GlobalMessages.addErrorMessage(model, FORM_GLOBAL_ERROR);
			storeCmsPageInModel(model, addEditAddressPage);
			setUpMetaDataForContentPage(model, addEditAddressPage);
			setUpAddressFormAfterError(addressForm, model);
			return getViewForPage(model);
		}

		model.addAttribute(ThirdPartyConstants.SeoRobots.META_ROBOTS, ThirdPartyConstants.SeoRobots.NOINDEX_NOFOLLOW);

		final AddressData newAddress = addressDataUtil.convertToVisibleAddressData(addressForm);

		if (Boolean.TRUE.equals(addressForm.getDefaultAddress()) || userFacade.getAddressBook().size() <= 1)
		{
			newAddress.setDefaultAddress(true);
		}

		final AddressVerificationResult<AddressVerificationDecision> verificationResult = getAddressVerificationFacade()
				.verifyAddressData(newAddress);
		final boolean addressRequiresReview = getAddressVerificationResultHandler().handleResult(verificationResult, newAddress,
				model, redirectModel, bindingResult, getAddressVerificationFacade().isCustomerAllowedToIgnoreAddressSuggestions(),
				"checkout.multi.address.updated");

		model.addAttribute(REGIONS_ATTR, getI18NFacade().getRegionsForCountryIso(addressForm.getCountryIso()));
		model.addAttribute(COUNTRY_ATTR, addressForm.getCountryIso());
		model.addAttribute("edit", Boolean.TRUE);
		model.addAttribute(IS_DEFAULT_ADDRESS_ATTR, Boolean.valueOf(userFacade.isDefaultAddress(addressForm.getAddressId())));

		if (addressRequiresReview)
		{
			storeCmsPageInModel(model, addEditAddressPage);
			setUpMetaDataForContentPage(model, addEditAddressPage);
			return getViewForPage(model);
		}

		userFacade.editAddress(newAddress);

		GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.CONF_MESSAGES_HOLDER, "account.confirmation.address.updated",
				null);
		return REDIRECT_TO_EDIT_ADDRESS_PAGE + newAddress.getId();
	}

	@RequestMapping(value = "/add-address", method = RequestMethod.GET)
	@RequireHardLogIn
	public String addAddress(final Model model) throws CMSItemNotFoundException
	{
		model.addAttribute(COUNTRY_DATA_ATTR, checkoutFacade.getCountries(CountryType.SHIPPING));
		model.addAttribute(TITLE_DATA_ATTR, userFacade.getTitles());
		final AddressForm addressForm = getPreparedAddressForm();
		model.addAttribute(ADDRESS_FORM_ATTR, addressForm);
		model.addAttribute(ADDRESS_BOOK_EMPTY_ATTR, Boolean.valueOf(CollectionUtils.isEmpty(userFacade.getAddressBook())));
		model.addAttribute(IS_DEFAULT_ADDRESS_ATTR, Boolean.FALSE);
		final ContentPageModel addEditAddressPage = getContentPageForLabelOrId(ADD_EDIT_ADDRESS_CMS_PAGE);
		storeCmsPageInModel(model, addEditAddressPage);
		setUpMetaDataForContentPage(model, addEditAddressPage);

		final List<Breadcrumb> breadcrumbs = accountBreadcrumbBuilder.getBreadcrumbs(null);
		breadcrumbs.add(new Breadcrumb(MY_ACCOUNT_ADDRESS_BOOK_URL,
				getMessageSource().getMessage(TEXT_ACCOUNT_ADDRESS_BOOK, null, getI18nService().getCurrentLocale()), null));
		breadcrumbs.add(new Breadcrumb("#",
				getMessageSource().getMessage("text.account.addressBook.addEditAddress", null, getI18nService().getCurrentLocale()),
				null));
		model.addAttribute(BREADCRUMBS_ATTR, breadcrumbs);
		model.addAttribute(ThirdPartyConstants.SeoRobots.META_ROBOTS, ThirdPartyConstants.SeoRobots.NOINDEX_NOFOLLOW);
		return getViewForPage(model);
	}

	@RequestMapping(value = "/add-address", method = RequestMethod.POST)
	@RequireHardLogIn
	public String addAddress(final AddressForm addressForm, final BindingResult bindingResult, final Model model,
			final RedirectAttributes redirectModel) throws CMSItemNotFoundException
	{
		getAddressValidator().validate(addressForm, bindingResult);
		if (bindingResult.hasErrors())
		{
			GlobalMessages.addErrorMessage(model, FORM_GLOBAL_ERROR);
			final ContentPageModel addEditAddressPage = getContentPageForLabelOrId(ADD_EDIT_ADDRESS_CMS_PAGE);
			storeCmsPageInModel(model, addEditAddressPage);
			setUpMetaDataForContentPage(model, addEditAddressPage);
			setUpAddressFormAfterError(addressForm, model);
			return getViewForPage(model);
		}

		final AddressData newAddress = addressDataUtil.convertToVisibleAddressData(addressForm);

		if (CollectionUtils.isEmpty(userFacade.getAddressBook()))
		{
			newAddress.setDefaultAddress(true);
		}
		else
		{
			newAddress.setDefaultAddress(addressForm.getDefaultAddress() != null && addressForm.getDefaultAddress().booleanValue());
		}

		final AddressVerificationResult<AddressVerificationDecision> verificationResult = getAddressVerificationFacade()
				.verifyAddressData(newAddress);
		final boolean addressRequiresReview = getAddressVerificationResultHandler().handleResult(verificationResult, newAddress,
				model, redirectModel, bindingResult, getAddressVerificationFacade().isCustomerAllowedToIgnoreAddressSuggestions(),
				"checkout.multi.address.added");

		populateModelRegionAndCountry(model, addressForm.getCountryIso());
		model.addAttribute("edit", Boolean.FALSE);
		model.addAttribute(IS_DEFAULT_ADDRESS_ATTR, Boolean.valueOf(userFacade.isDefaultAddress(addressForm.getAddressId())));

		if (addressRequiresReview)
		{
			storeCmsPageInModel(model, getContentPageForLabelOrId(ADD_EDIT_ADDRESS_CMS_PAGE));
			setUpMetaDataForContentPage(model, getContentPageForLabelOrId(ADD_EDIT_ADDRESS_CMS_PAGE));
			return getViewForPage(model);
		}

		userFacade.addAddress(newAddress);


		GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.CONF_MESSAGES_HOLDER, "account.confirmation.address.added",
				null);

		return REDIRECT_TO_EDIT_ADDRESS_PAGE + newAddress.getId();
	}


	@RequestMapping(value = "/select-suggested-address", method = RequestMethod.POST)
	public String doSelectSuggestedAddress(final AddressForm addressForm, final RedirectAttributes redirectModel)
	{
		final Set<String> resolveCountryRegions = org.springframework.util.StringUtils
				.commaDelimitedListToSet(Config.getParameter("resolve.country.regions"));

		final AddressData selectedAddress = addressDataUtil.convertToVisibleAddressData(addressForm);

		final CountryData countryData = selectedAddress.getCountry();

		if (!resolveCountryRegions.contains(countryData.getIsocode()))
		{
			selectedAddress.setRegion(null);
		}

		if (Boolean.TRUE.equals(addressForm.getEditAddress()))
		{
			userFacade.editAddress(selectedAddress);
		}
		else
		{
			userFacade.addAddress(selectedAddress);
		}

		GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.CONF_MESSAGES_HOLDER, "account.confirmation.address.added");

		return REDIRECT_TO_ADDRESS_BOOK_PAGE;
	}

	@RequestMapping(value = "/remove-address/" + ADDRESS_CODE_PATH_VARIABLE_PATTERN, method =
	{ RequestMethod.GET, RequestMethod.POST })
	@RequireHardLogIn
	public String removeAddress(@PathVariable("addressCode")
	final String addressCode, final RedirectAttributes redirectModel)
	{
		final AddressData addressData = new AddressData();
		addressData.setId(addressCode);
		userFacade.removeAddress(addressData);

		GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.CONF_MESSAGES_HOLDER, "account.confirmation.address.removed");
		return REDIRECT_TO_ADDRESS_BOOK_PAGE;
	}

	@RequestMapping(value = "/set-default-address/" + ADDRESS_CODE_PATH_VARIABLE_PATTERN, method = RequestMethod.GET)
	@RequireHardLogIn
	public String setDefaultAddress(@PathVariable("addressCode")
	final String addressCode, final RedirectAttributes redirectModel)
	{
		final AddressData addressData = new AddressData();
		addressData.setDefaultAddress(true);
		addressData.setVisibleInAddressBook(true);
		addressData.setId(addressCode);
		userFacade.setDefaultAddress(addressData);
		GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.CONF_MESSAGES_HOLDER,
				"account.confirmation.default.address.changed");
		return REDIRECT_TO_ADDRESS_BOOK_PAGE;
	}

	protected AddressForm getPreparedAddressForm()
	{
		final CustomerData currentCustomerData = customerFacade.getCurrentCustomer();
		final AddressForm addressForm = new AddressForm();
		addressForm.setFirstName(currentCustomerData.getFirstName());
		addressForm.setLastName(currentCustomerData.getLastName());
		addressForm.setTitleCode(currentCustomerData.getTitleCode());
		return addressForm;
	}




}
