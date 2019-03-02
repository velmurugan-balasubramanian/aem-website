package com.aem.website.core.models;

import javax.inject.Inject;

import java.util.List;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(
	    adaptables = {Resource.class},
	    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public interface NavigationModel {
	
	@Inject
	  List<MenuItem> getMenuItem(); 

	
	  @Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
	  interface MenuItem {
	    @Inject
	    String getMenuText();
	    
	    @Inject
	    String getMenuLink();
	    
	    // Inject Submenu to read nested Multifield values
	    @Inject
	    List<SubMenuItem> getSubmenuItem(); 
	  }

	  
	  @Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
	  interface SubMenuItem {
		    @Inject
		    String getSubmenuText();
		    
		    @Inject
		    String getSubmenuLink();
	  }
}
