package com.aem.website.core.models;

import javax.inject.Inject;
import java.util.List;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(
	    adaptables = {Resource.class},
	    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public interface FooterModel {
	
	@Inject
	  List<FooterColumn> getFooterColumn();

	  @Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
	  interface FooterColumn {
	    @Inject
	    String getHeading();
	    
	    
	    // inject footer item to read nested multifield vales
	    @Inject
	    List<FooterItem> getFooterItem(); 
	  }
	
	  @Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
	  interface FooterItem {
		    @Inject
		    String getFooterText();
		    
		    @Inject
		    String getFooterLink();
	  }
}
