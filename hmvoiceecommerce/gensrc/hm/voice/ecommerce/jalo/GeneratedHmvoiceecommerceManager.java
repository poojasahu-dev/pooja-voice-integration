/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Jun 1, 2020, 11:09:08 AM                    ---
 * ----------------------------------------------------------------
 */
package hm.voice.ecommerce.jalo;

import de.hybris.platform.acceleratorcms.jalo.actions.ListAddToCartActionVoice;
import de.hybris.platform.acceleratorcms.jalo.components.SearchResultsListComponentVoice;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.JaloSystemException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.extension.Extension;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.jalo.type.JaloGenericCreationException;
import hm.voice.ecommerce.constants.HmvoiceecommerceConstants;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type <code>HmvoiceecommerceManager</code>.
 */
@SuppressWarnings({"deprecation","unused","cast"})
public abstract class GeneratedHmvoiceecommerceManager extends Extension
{
	protected static final Map<String, Map<String, AttributeMode>> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, Map<String, AttributeMode>> ttmp = new HashMap();
		DEFAULT_INITIAL_ATTRIBUTES = ttmp;
	}
	@Override
	public Map<String, AttributeMode> getDefaultAttributeModes(final Class<? extends Item> itemClass)
	{
		Map<String, AttributeMode> ret = new HashMap<>();
		final Map<String, AttributeMode> attr = DEFAULT_INITIAL_ATTRIBUTES.get(itemClass.getName());
		if (attr != null)
		{
			ret.putAll(attr);
		}
		return ret;
	}
	
	public ListAddToCartActionVoice createListAddToCartActionVoice(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( HmvoiceecommerceConstants.TC.LISTADDTOCARTACTIONVOICE );
			return (ListAddToCartActionVoice)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating ListAddToCartActionVoice : "+e.getMessage(), 0 );
		}
	}
	
	public ListAddToCartActionVoice createListAddToCartActionVoice(final Map attributeValues)
	{
		return createListAddToCartActionVoice( getSession().getSessionContext(), attributeValues );
	}
	
	public SearchResultsListComponentVoice createSearchResultsListComponentVoice(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( HmvoiceecommerceConstants.TC.SEARCHRESULTSLISTCOMPONENTVOICE );
			return (SearchResultsListComponentVoice)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating SearchResultsListComponentVoice : "+e.getMessage(), 0 );
		}
	}
	
	public SearchResultsListComponentVoice createSearchResultsListComponentVoice(final Map attributeValues)
	{
		return createSearchResultsListComponentVoice( getSession().getSessionContext(), attributeValues );
	}
	
	@Override
	public String getName()
	{
		return HmvoiceecommerceConstants.EXTENSIONNAME;
	}
	
}
